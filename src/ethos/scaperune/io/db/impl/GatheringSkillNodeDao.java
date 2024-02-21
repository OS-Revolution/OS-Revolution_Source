package ethos.scaperune.io.db.impl;

import ethos.scaperune.skill.gathering.GatheringNode;
import org.scaperune.api.entity.item.ItemContext;
import org.scaperune.api.io.db.impl.DataAccessObjectImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GatheringSkillNodeDao extends DataAccessObjectImpl<GatheringNode, Integer> {

    @Override
    protected void createTableIfNotExists() {
        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS gathering_node ("
                        + "id INTEGER,"
                        + "depletedId INTEGER,"
                        + "respawnTime INTEGER,"
                        + "depletionTime INTEGER,"
                        + "skillId INTEGER,"
                        + "interactionXP INTEGER,"
                        + "interactionLevelRequirement INTEGER,"
                        + "PRIMARY KEY (id))"
        )) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(GatheringNode item) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO gathering_node (id, depletedId, respawnTime, depletionTime, skillId, interactionXP, interactionLevelRequirement) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)"
        )) {
            statement.setInt(1, item.getId());
            statement.setInt(2, item.getDepletedId());
            statement.setInt(3, item.getRespawnTime());
            statement.setInt(4, item.getDepletionTime());
            statement.setInt(5, item.getSkillId());
            statement.setInt(6, item.getInteractionXP());
            statement.setInt(7, item.getInteractionLevelRequirement());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected GatheringNode onRead(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM gathering_node WHERE id = ?"
        )) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int depletedId = resultSet.getInt("depletedId");
                int respawnTime = resultSet.getInt("respawnTime");
                int depletionTime = resultSet.getInt("depletionTime");
                int skillId = resultSet.getInt("skillId");
                int interactionXP = resultSet.getInt("interactionXP");
                int interactionLevelRequirement = resultSet.getInt("interactionLevelRequirement");
                int low = resultSet.getInt("low");
                int high = resultSet.getInt("high");
                long tableId = resultSet.getLong("tableId");

                GatheringNode gatheringNode = new GatheringNode(id, depletedId, respawnTime, depletionTime,
                        skillId, interactionXP, interactionLevelRequirement, low, high, tableId);

                return gatheringNode;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    @Override
    protected void onUpdate(GatheringNode item) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE gathering_node SET depletedId = ?, respawnTime = ?, depletionTime = ?, skillId = ?, interactionXP = ?, interactionLevelRequirement = ? "
                        + "WHERE id = ?"
        )) {
            statement.setInt(1, item.getDepletedId());
            statement.setInt(2, item.getRespawnTime());
            statement.setInt(3, item.getDepletionTime());
            statement.setInt(4, item.getSkillId());
            statement.setInt(5, item.getInteractionXP());
            statement.setInt(6, item.getInteractionLevelRequirement());
            statement.setInt(7, item.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDelete(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM gathering_node WHERE id = ?"
        )) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Collection<GatheringNode> onReadAll() {
        List<GatheringNode> gatheringNodes = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM gathering_node")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int depletedId = resultSet.getInt("depletedId");
                int respawnTime = resultSet.getInt("respawnTime");
                int depletionTime = resultSet.getInt("depletionTime");
                int skillId = resultSet.getInt("skillId");
                int interactionXP = resultSet.getInt("interactionXP");
                int interactionLevelRequirement = resultSet.getInt("interactionLevelRequirement");
                int low = resultSet.getInt("low"); // Adjust the column name to match your table
                int high = resultSet.getInt("high"); // Adjust the column name to match your table
                long tableId = resultSet.getLong("tableId"); // Adjust the column name to match your table

                GatheringNode gatheringNode = new GatheringNode(id, depletedId, respawnTime, depletionTime,
                        skillId, interactionXP, interactionLevelRequirement, low, high, tableId);

                gatheringNodes.add(gatheringNode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gatheringNodes;
    }



    @Override
    protected Integer getKey(GatheringNode gatheringNode) {
        return gatheringNode.getId();
    }

    public GatheringSkillNodeDao(Connection connection) {
        super(connection);
    }
}
