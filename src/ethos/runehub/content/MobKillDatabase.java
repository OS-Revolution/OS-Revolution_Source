package ethos.runehub.content;

import ethos.runehub.RunehubConstants;
import ethos.util.Misc;

import javax.swing.text.Utilities;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MobKillDatabase {

    private static MobKillDatabase instance = null;

    public static MobKillDatabase getInstance() {
        if (instance == null)
            instance = new MobKillDatabase();
        return instance;
    }

    public void storeKill(MobKill mobKill) {
        boolean indexFound = false;
        for (int i = 0; i < memCache.length ; i++) {
            if (memCache[i] == null) {
                memCache[i] = mobKill;
                indexFound = true;
                break;
            }
        }

        if (!indexFound) {
            pushCacheToDisk();
            storeKill(mobKill);
        }

    }

    public void pushCacheToDisk() {
        Logger.getGlobal().severe("Pushing MobKill Cache to Disk");
        for (int i = 0; i < memCache.length ; i++) {
            if (memCache[i] != null) {
                create(memCache[i]);
                memCache[i] = null;
            }
        }
    }

    public MobKill create(MobKill mobKill) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseLocation)) {
            // Create the mob_kills table if it doesn't exist
            createMobKillsTable(connection);

            // Insert the MobKill object into the database
            insertMobKill(connection, mobKill);

            return mobKill;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<MobKill> read(long playerId, int mobId) {
        List<MobKill> mobKills = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseLocation)) {
            // Build the query based on the provided parameters
            String query = "SELECT killer_id, amount_killed, mob_id FROM mob_kills";
            if (playerId != -1 && mobId != -1) {
                query += " WHERE killer_id = ? AND mob_id = ?";
            } else if (playerId != -1) {
                query += " WHERE killer_id = ?";
            } else if (mobId != -1) {
                query += " WHERE mob_id = ?";
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                if (playerId != -1 && mobId != -1) {
                    preparedStatement.setLong(1, playerId);
                    preparedStatement.setInt(2, mobId);
                } else if (playerId != -1) {
                    preparedStatement.setLong(1, playerId);
                } else if (mobId != -1) {
                    preparedStatement.setInt(1, mobId);
                }

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        long killerId = resultSet.getLong("killer_id");
                        int amountKilled = resultSet.getInt("amount_killed");
                        int retrievedMobId = resultSet.getInt("mob_id");

                        MobKill mobKill = new MobKill(killerId, amountKilled, retrievedMobId);
                        mobKills.add(mobKill);
                    }
                }
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("No data present");
        }

        return mobKills;
    }


    private void createMobKillsTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS mob_kills (id INTEGER PRIMARY KEY AUTOINCREMENT, killer_id INTEGER, amount_killed INTEGER, mob_id INTEGER)");
        }
    }

    private void insertMobKill(Connection connection, MobKill mobKill) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mob_kills (killer_id, amount_killed, mob_id) VALUES (?, ?, ?)")) {
            preparedStatement.setLong(1, mobKill.getKillerId());
            preparedStatement.setInt(2, mobKill.getAmountKilled());
            preparedStatement.setInt(3, mobKill.getMobId());

            preparedStatement.executeUpdate();
        }
    }


    private MobKillDatabase() {
        this.databaseLocation = RunehubConstants.PLAYER_DB;
        this.memCache = new MobKill[200];
    }


    private final String databaseLocation;
    private final MobKill[] memCache;
}
