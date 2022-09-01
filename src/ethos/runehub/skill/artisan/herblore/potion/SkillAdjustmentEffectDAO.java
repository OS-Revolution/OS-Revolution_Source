package ethos.runehub.skill.artisan.herblore.potion;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.artisan.herblore.potion.effect.SkillAdjustmentEffect;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class SkillAdjustmentEffectDAO extends BetaAbstractDataAcessObject<SkillAdjustmentEffect> {

    private static SkillAdjustmentEffectDAO instance = null;

    public static SkillAdjustmentEffectDAO getInstance() {
        if (instance == null)
            instance = new SkillAdjustmentEffectDAO();
        return instance;
    }

    private SkillAdjustmentEffectDAO() {
        super(RunehubConstants.EFFECT_DB, SkillAdjustmentEffect.class);
    }
}
