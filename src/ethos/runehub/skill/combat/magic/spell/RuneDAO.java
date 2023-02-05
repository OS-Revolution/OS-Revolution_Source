package ethos.runehub.skill.combat.magic.spell;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.combat.magic.MagicTablet;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class RuneDAO extends BetaAbstractDataAcessObject<Rune> {

    private static RuneDAO instance = null;

    public static RuneDAO getInstance() {
        if (instance == null)
            instance = new RuneDAO();
        return instance;
    }

    private RuneDAO() {
        super(RunehubConstants.SKILL_DB, Rune.class);
    }
}
