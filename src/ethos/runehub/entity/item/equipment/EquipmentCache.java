package ethos.runehub.entity.item.equipment;

import org.runehub.api.io.load.LazyLoader;

public class EquipmentCache extends LazyLoader<Integer, Equipment> {

    private static EquipmentCache instance = null;

    public static EquipmentCache getInstance() {
        if (instance == null)
            instance = new EquipmentCache();
        return instance;
    }

    private EquipmentCache() {
        super(EquipmentDAO.getInstance());
    }
}
