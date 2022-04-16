package ethos.runehub.db;

import ethos.phantasye.job.Job;
import ethos.runehub.ServerFileSystem;
import ethos.runehub.entity.player.PlayerCharacterContext;
import org.rhd.api.io.db.DatabaseAcessManager;
import org.rhd.api.io.db.Query;
import org.rhd.api.io.db.QueryParameter;
import org.rhd.api.io.db.SqlDataType;
import org.rhd.api.io.db.impl.AbstractDataAcessObject;
import org.rhd.api.io.fs.ApplicationFileSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerCharacterContextDataAccessObject extends AbstractDataAcessObject<PlayerCharacterContext> {

    private static final String TABLE_NAME = "player_context";

    private static PlayerCharacterContextDataAccessObject instance = null;

    public static PlayerCharacterContextDataAccessObject getInstance() {
        if (instance == null)
            instance = new PlayerCharacterContextDataAccessObject();
        return instance;
    }

    @Override
    public List<PlayerCharacterContext> getAllEntries() {
        final List<PlayerCharacterContext> items = new ArrayList<>();
        final Query query = new Query.QueryBuilder()
                .addQuery("SELECT * FROM")
                .addQuery(TABLE_NAME)
                .build();
        try (Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl());
             PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                items.add(
                        new PlayerCharacterContext.PlayerCharacterContextBuilder(rs.getLong("id"))
                                .withJobScore(rs.getInt("jobScore"))
                                .withHotspots(rs.getString("hotspots"))
                                .withSpawnPoints(rs.getLong("spawnPoints"))
                                .withJob(new Job.JobBuilder()
                                        .forSkill(rs.getInt("jobSkillId"))
                                        .withDifficulty(Job.Difficulty.values()[rs.getInt("jobDifficultyId")])
                                        .withQuota(rs.getInt("jobQuota"))
                                        .withTargetId(rs.getInt("jobTargetId"))
                                        .build())
                                .setMagicFind(rs.getDouble("magicFind"))
                                .withLastHomeTeleportTimestamp(rs.getLong("lastHomeTeleportTimestamp"))
                                .withInstantTeleportCharges(rs.getInt("instantTeleportCharges"))
                                .withSkillAnimationOverrides(rs.getString("skillAnimationOverrides"))
                                .build()
                );
            }
            return items;
        } catch (SQLException e) {
            System.out.println("CUSTOM: " + e.getMessage());
        }
        return items;
    }

    @Override
    public void update(PlayerCharacterContext context) {
        try {
            final Query query = new Query.QueryBuilder().addQuery("UPDATE")
                    .addQuery(TABLE_NAME)
                    .addQuery("SET "
                            + "jobScore='" + context.getJobScore().value() + "'"
                            + ",jobSkillId='" + context.getActiveJob().getSkillId() + "'"
                            + ",jobQuota='" + context.getActiveJob().getQuota().value() + "'"
                            + ",jobDifficultyId='" + context.getActiveJob().getDifficulty().ordinal() + "'"
                            + ",jobTargetId='" + context.getActiveJob().getTargetId() + "'"
                            + ",magicFind='" + context.getMagicFind().value() + "'"
                            + ",hotspots='" + context.getHotspotsAsJson() + "'"
                            + ",spawnPoints='" + context.getSpawnPoints().value() + "'"
                            + ",lastHomeTeleportTimestamp='" + context.getLastHomeTeleportTimestamp() + "'"
                            + ",instantTeleportCharges='" + context.getInstantTeleportCharges().value() + "'"
                            + ",skillAnimationOverrides='" + context.getSkillAnimationOverridesAsJson() + "'"
                    )

                    .addQuery("WHERE id='" + context.getId() + "'")
                    .build();
            try (Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl());
                 PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("PROBLEM");
                if (this.getCachedEntries().containsKey(context.getId())) {
                    this.delete(context);
                }
                this.create(context);
            }
            if (!this.getCachedEntries().containsKey(context.getId())) {
                this.getCachedEntries().putIfAbsent(context.getId(), context);
            } else {
                this.getCachedEntries().replace(context.getId(), context);
            }
        } catch (NullPointerException e) {

        }
    }

    @Override
    public void create(PlayerCharacterContext context) {
        final Query query = new Query.QueryBuilder().addQuery("INSERT INTO")
                .addQuery(TABLE_NAME)
                .addQuery("(id,jobScore,jobSkillId,jobQuota,jobDifficultyId,jobTargetId,magicFind,hotspots,spawnPoints,lastHomeTeleportTimestamp,instantTeleportCharges,skillAnimationOverrides)")
                .addQuery("VALUES(?,?,?,?,?,?,?,?,?,?,?,?)")
                .build();
        try (Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl());
             PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
            pstmt.setLong(1, context.getId());
            pstmt.setInt(2, context.getJobScore().value());
            pstmt.setString(8, context.getHotspotsAsJson());
            pstmt.setLong(9,context.getSpawnPoints().value());
            pstmt.setLong(10,context.getLastHomeTeleportTimestamp());
            pstmt.setInt(11,context.getInstantTeleportCharges().value());
            pstmt.setString(12,context.getSkillAnimationOverridesAsJson());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (!this.getCachedEntries().containsKey(context.getId())) {
            this.getCachedEntries().putIfAbsent(context.getId(), context);
        } else {
            this.getCachedEntries().replace(context.getId(), context);
        }
    }

    @Override
    public void delete(PlayerCharacterContext context) {
        final Query query = new Query.QueryBuilder()
                .addQuery("DELETE FROM")
                .addQuery(TABLE_NAME)
                .addQuery("WHERE id='" + context.getId() + "'")
                .build();
        try (Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl());
             PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.getCachedEntries().remove(context.getId());
    }

    @Override
    public PlayerCharacterContext read(PlayerCharacterContext context) {
        return this.read(context.getId());
    }

    @Override
    public PlayerCharacterContext read(long l) {
        System.out.println("READING: " + l);
            final Query query = new Query.QueryBuilder()
                    .addQuery("SELECT * FROM")
                    .addQuery(TABLE_NAME)
                    .addQuery("WHERE id=" + l)
                    .build();
            try (Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl());
                 PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
                ResultSet rs = pstmt.executeQuery();
                PlayerCharacterContext.PlayerCharacterContextBuilder builder = new PlayerCharacterContext.PlayerCharacterContextBuilder(rs.getLong("id"));
                while (rs.next()) {
                    builder.withJobScore(rs.getInt("jobScore"));
                    builder.withJob(new Job.JobBuilder()
                            .forSkill(rs.getInt("jobSkillId"))
                            .withDifficulty(Job.Difficulty.values()[rs.getInt("jobDifficultyId")])
                            .withQuota(rs.getInt("jobQuota"))
                            .withTargetId(rs.getInt("jobTargetId"))
                            .build());
                    builder.setMagicFind(rs.getDouble("magicFind"));
                    builder.withHotspots(rs.getString("hotspots"));
                    builder.withSpawnPoints(rs.getLong("spawnPoints"));
                    builder.withLastHomeTeleportTimestamp(rs.getLong("lastHomeTeleportTimestamp"));
                    builder.withInstantTeleportCharges(rs.getInt("instantTeleportCharges"));
                    builder.withSkillAnimationOverrides(rs.getString("skillAnimationOverrides"));
                }
                return builder.build();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {

            }
        final PlayerCharacterContext ctx = new PlayerCharacterContext.PlayerCharacterContextBuilder(l)
                .build();
        this.create(ctx);
        return ctx;
    }

    private PlayerCharacterContextDataAccessObject() {
        super(ServerFileSystem.getInstance().buildFileRequest()
                .inDirectory(ApplicationFileSystem.APP_DIRECTORY)
                .inDirectory(ServerFileSystem.APP_HOME)
                .inDirectory(ServerFileSystem.RESOURCE_HOME)
                .inDirectory(ServerFileSystem.DATABASE_HOME)
                .withFileName("player-context")
                .withExtension(".db")
                .build()
                .getAbsolutePath());
        this.getDatabaseServiceProvider().createTable(TABLE_NAME,
                new QueryParameter("id", SqlDataType.BIGINT, QueryParameter.PRIMARY_KEY),
                new QueryParameter("jobScore", SqlDataType.INTEGER),
                new QueryParameter("jobQuota", SqlDataType.INTEGER),
                new QueryParameter("jobSkillId", SqlDataType.INTEGER),
                new QueryParameter("jobTargetId", SqlDataType.INTEGER),
                new QueryParameter("jobDifficultyId", SqlDataType.INTEGER),
                new QueryParameter("magicFind", SqlDataType.DOUBLE),
                new QueryParameter("hotspots", SqlDataType.TEXT),
                new QueryParameter("spawnPoints", SqlDataType.BIGINT),
                new QueryParameter("lastHomeTeleportTimestamp", SqlDataType.BIGINT),
                new QueryParameter("instantTeleportCharges", SqlDataType.INTEGER),
                new QueryParameter("skillAnimationOverrides", SqlDataType.INTEGER)
        );

        final Query query = new Query.QueryBuilder()
                .addQuery("ALTER TABLE")
                .addQuery(TABLE_NAME)
                .addQuery("ADD")
//                .addQuery(" jobQuota INTEGER")
//                .addQuery(" jobSkillId INTEGER")
//                .addQuery(" jobTargetId INTEGER")
//                .addQuery(" jobDifficultyId INTEGER")
//                .addQuery(" jobScore INTEGER")
//                .addQuery(" magicFind DOUBLE")
                .addQuery(" skillAnimationOverrides INTEGER")
                .build();
        try (Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl());
             PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
//            System.out.println(query.getSql());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
