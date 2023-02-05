package ethos.runehub.skill.combat.magic.spell;

import org.runehub.api.io.load.LazyLoader;

public class RuneCache extends LazyLoader<Integer, Rune> {

    private static RuneCache instance = null;

    public static RuneCache getInstance() {
        if (instance == null)
            instance = new RuneCache();
        return instance;
    }

    private RuneCache() {
        super(RuneDAO.getInstance());
    }
}
