package ethos.runehub.entity.item.equipment;

import org.runehub.api.io.load.LazyLoader;

public class RangedWeaponCache extends LazyLoader<Integer, RangedWeapon> {

    private static RangedWeaponCache instance = null;

    public static RangedWeaponCache getInstance() {
        if (instance == null)
            instance = new RangedWeaponCache();
        return instance;
    }

    private RangedWeaponCache() {
        super(RangedWeaponDAO.getInstance());
    }
}
