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
                                .withJob(new Job.JobBuilder()
                                        .forSkill(rs.getInt("jobSkillId"))
                                        .withDifficulty(Job.Difficulty.values()[rs.getInt("jobDifficultyId")])
                                        .withQuota(rs.getInt("jobQuota"))
                                        .withTargetId(rs.getInt("jobTargetId"))
                                        .build())
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
                .addQuery("(id,jobScore,jobSkillId,jobQuota,jobDifficultyId,jobTargetId)")
                .addQuery("VALUES(?,?,?,?,?,?)")
                .build();
        try (Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl());
             PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
            pstmt.setLong(1, context.getId());
            pstmt.setInt(2, context.getJobScore().value());
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
        PlayerCharacterContext context = this.getCachedEntries().get(l);
        if (context == null) {
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
                }
                context = builder.build();
                this.getCachedEntries().putIfAbsent(l, context);
                return context;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {

            }
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
                new QueryParameter("jobDifficultyId", SqlDataType.INTEGER)
        );

        final Query query = new Query.QueryBuilder()
                .addQuery("ALTER TABLE")
                .addQuery(TABLE_NAME)
                .addQuery("ADD")
                .addQuery(" jobQuota INTEGER")
                .addQuery(" jobSkillId INTEGER")
                .addQuery(" jobTargetId INTEGER")
                .addQuery(" jobDifficultyId INTEGER")
                .addQuery(" jobScore INTEGER")
                .build();
        try (Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl());
             PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
            System.out.println(query.getSql());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
