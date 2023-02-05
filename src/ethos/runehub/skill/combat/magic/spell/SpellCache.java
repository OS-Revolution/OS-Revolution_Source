package ethos.runehub.skill.combat.magic.spell;

import org.runehub.api.io.load.LazyLoader;

public class SpellCache extends LazyLoader<Integer, Spell> {

    private static SpellCache instance = null;

    public static SpellCache getInstance() {
        if (instance == null)
            instance = new SpellCache();
        return instance;
    }

    private SpellCache() {
        super(SpellDAO.getInstance());
    }
}
