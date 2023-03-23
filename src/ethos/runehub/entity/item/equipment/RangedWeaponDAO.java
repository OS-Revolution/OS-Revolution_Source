package ethos.runehub.entity.item.equipment;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class RangedWeaponDAO extends BetaAbstractDataAcessObject<RangedWeapon> {

    private static RangedWeaponDAO instance = null;

    public static RangedWeaponDAO getInstance() {
        if (instance == null)
            instance = new RangedWeaponDAO();
        return instance;
    }

    private RangedWeaponDAO() {
        super(RunehubConstants.EQUIPMENT, RangedWeapon.class);
    }
}
