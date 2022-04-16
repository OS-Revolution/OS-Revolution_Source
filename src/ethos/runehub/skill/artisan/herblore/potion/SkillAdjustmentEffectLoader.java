package ethos.runehub.skill.artisan.herblore.potion;

import ethos.runehub.skill.artisan.herblore.potion.effect.SkillAdjustmentEffect;
import org.runehub.api.io.load.LazyLoader;

public class SkillAdjustmentEffectLoader extends LazyLoader<Integer, SkillAdjustmentEffect> {

    private static SkillAdjustmentEffectLoader instance = null;

    public static SkillAdjustmentEffectLoader getInstance() {
        if (instance == null)
            instance = new SkillAdjustmentEffectLoader();
        return instance;
    }

    @Override
    protected SkillAdjustmentEffect load(Integer key) {
        switch (key) {

            default: return super.load(key);
        }
    }

    private SkillAdjustmentEffectLoader() {
        super(SkillAdjustmentEffectDAO.getInstance());
    }
}
