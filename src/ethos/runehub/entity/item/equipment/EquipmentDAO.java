package ethos.runehub.entity.item.equipment;

import ethos.runehub.RunehubConstants;
import ethos.runehub.entity.item.ItemInteraction;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class EquipmentDAO extends BetaAbstractDataAcessObject<Equipment> {

    private static EquipmentDAO instance = null;

    public static EquipmentDAO getInstance() {
        if (instance == null)
            instance = new EquipmentDAO();
        return instance;
    }

    private EquipmentDAO() {
        super(RunehubConstants.EQUIPMENT, Equipment.class);
    }
}
