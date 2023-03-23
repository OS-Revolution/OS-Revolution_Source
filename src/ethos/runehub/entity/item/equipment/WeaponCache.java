package ethos.runehub.entity.item.equipment;

import org.runehub.api.io.load.LazyLoader;

public class WeaponCache extends LazyLoader<Integer, Weapon> {

    private static WeaponCache instance = null;

    public static WeaponCache getInstance() {
        if (instance == null)
            instance = new WeaponCache();
        return instance;
    }

    private WeaponCache() {
        super(WeaponDAO.getInstance());
    }
}
