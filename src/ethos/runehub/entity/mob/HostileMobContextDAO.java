package ethos.runehub.entity.mob;


import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.DatabaseAcessManager;
import org.runehub.api.io.data.Query;
import org.runehub.api.io.data.impl.AbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;
import org.runehub.api.model.entity.item.Drop;
import org.runehub.api.util.IOUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class HostileMobContextDAO extends AbstractDataAcessObject<HostileMobContext> {

    private static HostileMobContextDAO instance = null;

    public static HostileMobContextDAO getInstance() {
        if (instance == null)
            instance = new HostileMobContextDAO();
        return instance;
    }

    private HostileMobContextDAO() {
        super(RunehubConstants.OS_DEFINTIONS_DB, HostileMobContext.class);
        this.getDatabaseServiceProvider().createTable();
    }

    @Override
    public List<HostileMobContext> getAllEntries() {
        final List<HostileMobContext> contexts = new ArrayList<>();
        final Query query = new Query.QueryBuilder()
                .addQuery("SELECT * FROM")
                .addQuery(this.getDatabaseServiceProvider().getStoredObject().tableName())
                .build();
        try (Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl());
             PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                final HostileMobContext.NpcContextBuilder bldr = new HostileMobContext.NpcContextBuilder(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                bldr.setDrops((List<Drop>) IOUtils.getObjectFromBytes(rs.getBytes("drops")));
                bldr.setAttackTypes((List<String>) IOUtils.getObjectFromBytes(rs.getBytes("attackType")));
                bldr.setAttributes((List<String>) IOUtils.getObjectFromBytes(rs.getBytes("attributes")));
                bldr.setCategories((List<String>) IOUtils.getObjectFromBytes(rs.getBytes("category")));
                bldr.setAggressive(rs.getBoolean("aggressive"));
                bldr.setCombatLevel(rs.getInt("combatLevel"));
                bldr.setDuplicate(rs.getBoolean("duplicate"));
                bldr.setExamine(rs.getString("examine"));
                bldr.setHitpoints(rs.getInt("hitpoints"));
                bldr.setMaxHit(rs.getInt("maxHit"));
                bldr.setMembers(rs.getBoolean("members"));
                bldr.setPoisonImmune(rs.getBoolean("poisonImmune"));
                bldr.setPoisonous(rs.getBoolean("poisonous"));
                bldr.setReleaseDate(rs.getString("release"));
                bldr.setSize(rs.getInt("size"));
                bldr.setSlayerLevel(rs.getInt("slayerLevel"));
                bldr.setSlayerMonster(rs.getBoolean("slayerMonster"));
                bldr.setVenomImmune(rs.getBoolean("venomImmune"));
                bldr.setVenomous(rs.getBoolean("venomous"));
                bldr.setWiki(rs.getString("wiki"));
                bldr.setDefenceLevel(rs.getInt("defenceLevel"));
                bldr.setDefenceStab(rs.getInt("defenceStab"));
                bldr.setDefenceSlash(rs.getInt("defenceSlash"));
                bldr.setDefenceCrush(rs.getInt("defenceCrush"));
                bldr.setDefenceRanged(rs.getInt("defenceRanged"));
                bldr.setDefenceMage(rs.getInt("defenceMage"));
                HostileMobContext context = bldr.build();
                contexts.add(context);
                this.getCache().create((long) context.getId(), context);
            }
            return contexts;
        } catch (SQLException | IOException | ClassNotFoundException e) {
            Logger.getLogger("Hostile-Mob-Context DAO").warning(e.getMessage());
        }
        return contexts;
    }
//
    @Override
    public HostileMobContext read(long l) {
        final Query query = new Query.QueryBuilder()
                .addQuery("SELECT * FROM")
                .addQuery(this.getDatabaseServiceProvider().getStoredObject().tableName())
                .addQuery("WHERE id=" + l)
                .build();
        try (Connection conn = DatabaseAcessManager.getInstance().connect(this.getDatabaseServiceProvider().getUrl());
             PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(query.getSql())) {
            ResultSet rs = pstmt.executeQuery();
            final HostileMobContext.NpcContextBuilder bldr = new HostileMobContext.NpcContextBuilder(
                    rs.getInt("id"),
                    rs.getString("name")
            );
            while (rs.next()) {
                bldr.setDrops((List<Drop>) IOUtils.getObjectFromBytes(rs.getBytes("drops")));
                bldr.setAttackTypes((List<String>) IOUtils.getObjectFromBytes(rs.getBytes("attackType")));
                bldr.setAttributes((List<String>) IOUtils.getObjectFromBytes(rs.getBytes("attributes")));
                bldr.setCategories((List<String>) IOUtils.getObjectFromBytes(rs.getBytes("category")));
                bldr.setAggressive(rs.getBoolean("aggressive"));
                bldr.setCombatLevel(rs.getInt("combatLevel"));
                bldr.setDuplicate(rs.getBoolean("duplicate"));
                bldr.setExamine(rs.getString("examine"));
                bldr.setHitpoints(rs.getInt("hitpoints"));
                bldr.setMaxHit(rs.getInt("maxHit"));
                bldr.setMembers(rs.getBoolean("members"));
                bldr.setPoisonImmune(rs.getBoolean("poisonImmune"));
                bldr.setPoisonous(rs.getBoolean("poisonous"));
                bldr.setReleaseDate(rs.getString("release"));
                bldr.setSize(rs.getInt("size"));
                bldr.setSlayerLevel(rs.getInt("slayerLevel"));
                bldr.setSlayerMonster(rs.getBoolean("slayerMonster"));
                bldr.setVenomImmune(rs.getBoolean("venomImmune"));
                bldr.setVenomous(rs.getBoolean("venomous"));
                bldr.setWiki(rs.getString("wiki"));
                bldr.setDefenceLevel(rs.getInt("defenceLevel"));
                bldr.setDefenceStab(rs.getInt("defenceStab"));
                bldr.setDefenceSlash(rs.getInt("defenceSlash"));
                bldr.setDefenceCrush(rs.getInt("defenceCrush"));
                bldr.setDefenceRanged(rs.getInt("defenceRanged"));
                bldr.setDefenceMage(rs.getInt("defenceMage"));
            }
            return bldr.build();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            Logger.getLogger("Hostile-Mob-Context DAO").warning(e.getMessage());
        }
        return null;
    }

}
