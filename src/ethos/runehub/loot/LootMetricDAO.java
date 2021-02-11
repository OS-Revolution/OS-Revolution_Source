package ethos.runehub.loot;

import org.rhd.api.io.db.DatabaseAcessManager;
import org.rhd.api.io.db.Query;
import org.rhd.api.io.db.QueryParameter;
import org.rhd.api.io.db.SqlDataType;
import org.rhd.api.io.db.impl.AbstractDataAcessObject;
import org.rhd.api.io.fs.ApplicationFileSystem;
import org.runehub.app.editor.l.io.fs.LocalFileSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class LootMetricDAO extends AbstractDataAcessObject<LootMetric> {

    private static final String METRIC_ID_COL = "metricId";
    private static final String PLAYER_ID_COL = "playerId";
    private static final String CONTAINER_ID_COL = "containerId";
    private static final String TABLE_ID_COL = "tableId";
    private static final String ITEM_ID_COL = "itemId";
    private static final String TIMESTAMP_COL = "timestamp";
    private static final String AMOUNT_COL = "amount";
    private static final String ROLLS_COL = "rolls";
    private static final String TIER_COL = "tier";
    private static final String TYPE_COL = "type";
    private static final String MAGIC_FIND_COL = "magicFind";


    private static LootMetricDAO instance = null;

    public static LootMetricDAO getInstance() {
        if (instance == null)
            instance = new LootMetricDAO();
        return instance;
    }

    private LootMetricDAO() {
        super(LocalFileSystem.getInstance().buildFileRequest()
                .inDirectory(ApplicationFileSystem.APP_DIRECTORY)
                .inDirectory(LocalFileSystem.APP_HOME)
                .inDirectory(LocalFileSystem.RESOURCE_HOME)
                .inDirectory(LocalFileSystem.DATABASE_HOME)
                .withFileName("metrics")
                .withExtension(".db")
                .build()
                .getAbsolutePath());
    }

    private List<LootMetric> getTableItems(String tableName) {
        final List<LootMetric> items = new ArrayList<>();
        final Query query = new Query.QueryBuilder()
                .addQuery("SELECT * FROM")
                .addQuery("'" + tableName + "'")
                .build();
        try (Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl());
             PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                items.add(new LootMetric(
                        Long.parseLong(tableName),
                        rs.getLong(TIMESTAMP_COL),
                        rs.getString(PLAYER_ID_COL),
                        rs.getInt(CONTAINER_ID_COL),
                        rs.getInt(TABLE_ID_COL),
                        rs.getInt(ITEM_ID_COL),
                        rs.getInt(AMOUNT_COL),
                        rs.getDouble(TIER_COL),
                        rs.getLong(ROLLS_COL),
                        rs.getInt(TYPE_COL),
                        rs.getDouble(MAGIC_FIND_COL)
                ));
            }
            return items;
        } catch (SQLException e) {
            System.out.println("CUSTOM: " + e.getMessage());
        }
        return items;
    }

    @Override
    public List<LootMetric> getAllEntries() {
        final List<LootMetric> entries = new ArrayList<>();
        final Query query = new Query.QueryBuilder()
                .addQuery("SELECT * FROM")
                .addQuery("sqlite_master")
                .addQuery("WHERE type ='table'")
                .build();
        try (Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl());
             PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
            ResultSet rs = conn.getMetaData().getTables(null, null, null, null);
            while (rs.next()) {
                try {
                    String tableName = rs.getString("TABLE_NAME");
                    List<LootMetric> lootMetrics = this.getTableItems(tableName);
                    entries.addAll(lootMetrics);
                } catch (NumberFormatException e) {

                }
            }
            return entries;
        } catch (SQLException e) {
            System.out.println("Update Error: " + e.getMessage());
        }
        return entries;
    }

    @Override
    public void update(LootMetric lootMetric) {
        try(Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl())) {
                final Query query = new Query.QueryBuilder()
                        .addQuery("UPDATE")
                        .addQuery("'" + lootMetric.getMetricId() + "'")
                        .addQuery("SET "
                                + CONTAINER_ID_COL + "='" + lootMetric.getContainerId() +"'"
                                + "," + TABLE_ID_COL + "='" + lootMetric.getTableId()+"'"
                                + "," + PLAYER_ID_COL + "='" + lootMetric.getUserId()+"'"
                                + "," + ITEM_ID_COL + "='" + lootMetric.getItemId()+"'"
                                + "," + AMOUNT_COL + "='" + lootMetric.getAmount()+"'"
                                + "," + TIER_COL + "='" + lootMetric.getTier()+"'"
                                + "," + TIMESTAMP_COL + "='" + lootMetric.getTimestamp()+"'"
                                + "," + ROLLS_COL + "='" + lootMetric.getRolls().value()+"'"
                                + "," + TYPE_COL + "='" + lootMetric.getType()+"'"
                                + "," + MAGIC_FIND_COL + "='" + lootMetric.getMagicFind()+"'")
                        .build();
                try (PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                this.getCachedEntries().putIfAbsent((long) lootMetric.getMetricId(), lootMetric);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(LootMetric lootMetric) {
        this.getDatabaseServiceProvider().createTable("'" + lootMetric.getMetricId() + "'",
                new QueryParameter(CONTAINER_ID_COL, SqlDataType.INTEGER),
                new QueryParameter(TABLE_ID_COL, SqlDataType.INTEGER),
                new QueryParameter(PLAYER_ID_COL, SqlDataType.TEXT),
                new QueryParameter(ITEM_ID_COL, SqlDataType.INTEGER),
                new QueryParameter(AMOUNT_COL, SqlDataType.INTEGER),
                new QueryParameter(TIER_COL, SqlDataType.DOUBLE),
                new QueryParameter(TIMESTAMP_COL, SqlDataType.BIGINT),
                new QueryParameter(ROLLS_COL, SqlDataType.BIGINT),
                new QueryParameter(TYPE_COL, SqlDataType.INTEGER),
                new QueryParameter(MAGIC_FIND_COL, SqlDataType.DOUBLE)
        );
        try(Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl())) {
                final Query query = new Query.QueryBuilder().addQuery("INSERT INTO")
                        .addQuery("'" + lootMetric.getMetricId() + "'")
                        .addQuery("(" +
                                CONTAINER_ID_COL +
                                "," +
                                TABLE_ID_COL +
                                "," +
                                PLAYER_ID_COL +
                                "," +
                                ITEM_ID_COL +
                                "," +
                                AMOUNT_COL +
                                "," +
                                TIER_COL +
                                "," +
                                TIMESTAMP_COL +
                                "," +
                                ROLLS_COL +
                                "," +
                                TYPE_COL +
                                "," +
                                MAGIC_FIND_COL +
                                ")"
                        )
                        .addQuery("VALUES(?,?,?,?,?,?,?,?,?,?)")
                        .build();
                try (PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
                    pstmt.setInt(1, lootMetric.getContainerId());
                    pstmt.setInt(2, lootMetric.getTableId());
                    pstmt.setString(3, lootMetric.getUserId());
                    pstmt.setInt(4, lootMetric.getItemId());
                    pstmt.setInt(5, lootMetric.getAmount());
                    pstmt.setDouble(6, lootMetric.getTier());
                    pstmt.setLong(7, lootMetric.getTimestamp());
                    pstmt.setLong(8, lootMetric.getRolls().value());
                    pstmt.setInt(9, lootMetric.getType());
                    pstmt.setDouble(10, lootMetric.getMagicFind());

                    pstmt.execute();
                } catch (SQLException e) {
                    Logger.getLogger(LootMetricDAO.class.getName()).finest(e.getMessage());
                }
            this.getCachedEntries().putIfAbsent((long) lootMetric.getTableId(), lootMetric);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(LootMetric lootMetric) {
        final Query query = new Query.QueryBuilder()
                .addQuery("DROP TABLE")
                .addQuery("'" + lootMetric.getMetricId() + "'")
                .build();
        this.getDatabaseServiceProvider().executeStatement(query.getSql());
    }

    @Override
    public LootMetric read(LootMetric lootMetric) {
        return this.read(lootMetric.getMetricId());
    }

    @Override
    public LootMetric read(long l) {
        LootMetric value = this.getCachedEntries().get(l);
        if (value == null) {
            final Query query = new Query.QueryBuilder()
                    .addQuery("SELECT * FROM")
                    .addQuery("'" + l + "'")
                    .build();
            try (Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl());
                 PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    value = new LootMetric(
                            l,
                            rs.getLong(TIMESTAMP_COL),
                            rs.getString(PLAYER_ID_COL),
                            rs.getInt(CONTAINER_ID_COL),
                            rs.getInt(TABLE_ID_COL),
                            rs.getInt(ITEM_ID_COL),
                            rs.getInt(AMOUNT_COL),
                            rs.getDouble(TIER_COL),
                            rs.getLong(ROLLS_COL),
                            rs.getInt(TYPE_COL),
                            rs.getDouble(MAGIC_FIND_COL)
                    );
                }
                this.getCachedEntries().putIfAbsent(l,value);
                return value;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {

            }
        }
        return this.getCachedEntries().get(l);
    }

}
