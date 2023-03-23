package ethos.runehub.entity.item.equipment;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class WeaponDAO extends BetaAbstractDataAcessObject<Weapon> {

    private static WeaponDAO instance = null;

    public static WeaponDAO getInstance() {
        if (instance == null)
            instance = new WeaponDAO();
        return instance;
    }

    private WeaponDAO() {
        super(RunehubConstants.EQUIPMENT, Weapon.class);
    }
}
