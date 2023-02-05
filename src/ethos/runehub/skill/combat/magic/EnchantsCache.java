package ethos.runehub.skill.combat.magic;

import org.runehub.api.io.load.LazyLoader;

public class EnchantsCache extends LazyLoader<Integer, Enchantable> {

    private static EnchantsCache instance = null;

    public static EnchantsCache getInstance() {
        if (instance == null)
            instance = new EnchantsCache();
        return instance;
    }

    private EnchantsCache() {
        super(EnchantsDAO.getInstance());
    }
}
