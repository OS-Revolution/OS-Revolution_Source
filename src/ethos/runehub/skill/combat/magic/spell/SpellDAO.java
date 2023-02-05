package ethos.runehub.skill.combat.magic.spell;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class SpellDAO extends BetaAbstractDataAcessObject<Spell> {

    private static SpellDAO instance = null;

    public static SpellDAO getInstance() {
        if (instance == null)
            instance = new SpellDAO();
        return instance;
    }

    private SpellDAO() {
        super(RunehubConstants.SKILL_DB, Spell.class);
    }
}
