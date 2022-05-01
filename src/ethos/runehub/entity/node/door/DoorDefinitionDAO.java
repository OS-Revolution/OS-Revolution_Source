package ethos.runehub.entity.node.door;

import org.runehub.api.io.data.impl.AbstractDataAcessObject;

public class DoorDefinitionDAO extends AbstractDataAcessObject<DoorDefinition> {

    private static DoorDefinitionDAO instance = null;

    public static DoorDefinitionDAO getInstance() {
        if (instance == null)
            instance = new DoorDefinitionDAO();
        return instance;
    }

    private DoorDefinitionDAO() {
        super("./Data/runehub/db/nodes.db", DoorDefinition.class);
    }
}
