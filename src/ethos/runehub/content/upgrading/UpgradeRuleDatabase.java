package ethos.runehub.content.upgrading;

import ethos.runehub.RunehubConstants;
import ethos.runehub.content.MobKill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UpgradeRuleDatabase {

    private static final String TABLE_NAME = "item_upgrade_rules";

    private static UpgradeRuleDatabase instance = null;

    public static UpgradeRuleDatabase getInstance() {
        if (instance == null)
            instance = new UpgradeRuleDatabase();
        return instance;
    }

    private void initializeDatabase() {
        create(new UpgradeRule(1277,1,1279,1,50,false,0.3f,0.05f,0.15f,0.1f));
    }

    public UpgradeRule create(UpgradeRule entry) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseLocation)) {
            // Create the mob_kills table if it doesn't exist
            createTable(connection);

            // Insert the MobKill object into the database
            insertUpgradeRule(connection, entry);

            return entry;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<UpgradeRule> read(int sourceId, int upgradeId) {
        List<UpgradeRule> upgradeRules = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseLocation)) {
            // Build the query based on the provided parameters
            String query = "SELECT * FROM " + TABLE_NAME;
            if (sourceId != -1 && upgradeId != -1) {
                query += " WHERE source_item_id = ? AND upgraded_item_id = ?";
            } else if (sourceId != -1) {
                query += " WHERE source_item_id = ?";
            } else if (upgradeId != -1) {
                query += " WHERE upgraded_item_id = ?";
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                if (sourceId != -1 && upgradeId != -1) {
                    preparedStatement.setInt(1, sourceId);
                    preparedStatement.setInt(2, upgradeId);
                } else if (sourceId != -1) {
                    preparedStatement.setInt(1, sourceId);
                } else if (upgradeId != -1) {
                    preparedStatement.setInt(1, upgradeId);
                }

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int retrievedSourceId = resultSet.getInt("source_item_id");
                        int retrievedUpgradeId = resultSet.getInt("upgraded_item_id");
                        int sourceItemAmount = resultSet.getInt("source_item_amount");
                        int upgradedItemAmount = resultSet.getInt("upgraded_item_amount");
                        int upgradePointCost = resultSet.getInt("upgrade_point_cost");
                        boolean members = resultSet.getBoolean("members");
                        float baseSuccessChance = resultSet.getFloat("base_success_chance");
                        float baseConsumeChance = resultSet.getFloat("base_consume_chance");
                        float baseUpgradeChanceIncrease = resultSet.getFloat("base_upgrade_chance_increase");
                        float baseUpgradeCostIncrease = resultSet.getFloat("base_upgrade_cost_increase");

                        UpgradeRule upgradeRule = new UpgradeRule(
                                retrievedSourceId,
                                sourceItemAmount,
                                retrievedUpgradeId,
                                upgradedItemAmount,
                                upgradePointCost,
                                members,
                                baseSuccessChance,
                                baseConsumeChance,
                                baseUpgradeChanceIncrease,
                                baseUpgradeCostIncrease
                        );
                        upgradeRules.add(upgradeRule);
                    }
                }
            }
        } catch (SQLException e) {
            // Handle any exceptions appropriately
            e.printStackTrace();
        }

        return upgradeRules;
    }



    private void createTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, source_item_id INTEGER, source_item_amount INTEGER, upgraded_item_id INTEGER, upgraded_item_amount INTEGER, upgrade_point_cost INTEGER, members BOOLEAN, base_success_chance REAL, base_consume_chance REAL, base_upgrade_chance_increase REAL, base_upgrade_cost_increase REAL)");
        }
    }


    private void insertUpgradeRule(Connection connection, UpgradeRule upgradeRule) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + TABLE_NAME + " (source_item_id, source_item_amount, upgraded_item_id, upgraded_item_amount, upgrade_point_cost, members, base_success_chance, base_consume_chance, base_upgrade_chance_increase, base_upgrade_cost_increase) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, upgradeRule.getSourceItemId());
            preparedStatement.setInt(2, upgradeRule.getSourceItemAmount());
            preparedStatement.setInt(3, upgradeRule.getUpgradedItemId());
            preparedStatement.setInt(4, upgradeRule.getUpgradedItemAmount());
            preparedStatement.setInt(5, upgradeRule.getUpgradePointCost());
            preparedStatement.setBoolean(6, upgradeRule.isMembers());
            preparedStatement.setFloat(7, upgradeRule.getBaseSuccessChance());
            preparedStatement.setFloat(8, upgradeRule.getBaseConsumeChance());
            preparedStatement.setFloat(9, upgradeRule.getBaseUpgradeChanceIncrease());
            preparedStatement.setFloat(10, upgradeRule.getBaseUpgradeCostIncrease());

            preparedStatement.executeUpdate();
        }
    }



    private UpgradeRuleDatabase() {
        this.databaseLocation = RunehubConstants.UPGRADE_DB;
        this.memCache = new MobKill[200];
    }


    private final String databaseLocation;
    private final MobKill[] memCache;
}
