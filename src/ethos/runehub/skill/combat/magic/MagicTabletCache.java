package ethos.runehub.skill.combat.magic;

import org.runehub.api.io.load.LazyLoader;

public class MagicTabletCache extends LazyLoader<Integer, MagicTablet> {

    private static MagicTabletCache instance = null;

    public static MagicTabletCache getInstance() {
        if (instance == null)
            instance = new MagicTabletCache();
        return instance;
    }

    private MagicTabletCache() {
        super(MagicTabletDAO.getInstance());
    }
}
