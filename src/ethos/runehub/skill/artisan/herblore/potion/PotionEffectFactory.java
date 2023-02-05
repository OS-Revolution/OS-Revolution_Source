package ethos.runehub.skill.artisan.herblore.potion;

import ethos.runehub.skill.artisan.herblore.potion.effect.*;
import org.runehub.api.io.load.LazyLoader;

public class PotionEffectFactory extends LazyLoader<Integer, Effect> {

    private static PotionEffectFactory instance = null;

    public static PotionEffectFactory getInstance() {
        if (instance == null)
            instance = new PotionEffectFactory();
        return instance;
    }

    @Override
    protected Effect load(Integer key) {
        switch (key) {
            case 0: // attack potion
                return new SkillAdjustmentEffect(key,0,3,1.1f);
            case 1: //defence potion
                return new SkillAdjustmentEffect(key,1,3,1.1f);
            case 2:// strength potion
                return new SkillAdjustmentEffect(key,2,3,1.1f);
            case 3://super attack potion
                return new SkillAdjustmentEffect(key,0,5,1.15f);
            case 4://super defence
                return new SkillAdjustmentEffect(key,1,5,1.15f);
            case 5://super strength
                return new SkillAdjustmentEffect(key,2,5,1.15f);
            case 6://restore
                return new RestoreEffect(key,0,10,1.3f);
            case 7://restore
                return new RestoreEffect(key,1,10,1.3f);
            case 8://restore
                return new RestoreEffect(key,2,10,1.3f);
            case 9://restore
                return new RestoreEffect(key,4,10,1.3f);
            case 10://restore
                return new RestoreEffect(key,6,10,1.3f);
            case 11://energy
                return new EnergyRestoreEffect(key,1000);
            case 12://super energy
                return new EnergyRestoreEffect(key,2000);
            case 13: //super restore start
                return new RestoreEffect(key,0,8,1.25f);
            case 14:
                return new RestoreEffect(key,1,8,1.25f);
            case 15:
                return new RestoreEffect(key,2,8,1.25f);
            case 16:
                return new RestoreEffect(key,4,8,1.25f);
            case 17:
                return new RestoreEffect(key,5,8,1.25f);
            case 18:
                return new RestoreEffect(key,6,8,1.25f);
            case 19:
                return new RestoreEffect(key,7,8,1.25f);
            case 20:
                return new RestoreEffect(key,8,8,1.25f);
            case 21:
                return new RestoreEffect(key,9,8,1.25f);
            case 22:
                return new RestoreEffect(key,10,8,1.25f);
            case 23:
                return new RestoreEffect(key,11,8,1.25f);
            case 24:
                return new RestoreEffect(key,12,8,1.25f);
            case 25:
                return new RestoreEffect(key,13,8,1.25f);
            case 26:
                return new RestoreEffect(key,14,8,1.25f);
            case 27:
                return new RestoreEffect(key,15,8,1.25f);
            case 28:
                return new RestoreEffect(key,16,8,1.25f);
            case 29:
                return new RestoreEffect(key,17,8,1.25f);
            case 30:
                return new RestoreEffect(key,18,8,1.25f);
            case 31:
                return new RestoreEffect(key,19,8,1.25f);
            case 32:
                return new RestoreEffect(key,20,8,1.25f);
            case 33:
                return new RestoreEffect(key,21,8,1.25f);
            case 34: //end super restore
                return new RestoreEffect(key,22,8,1.25f);
            case 35: //prayer potion
                return new RestoreEffect(key,5,7,1.25f);
            case 36: //saradomin brew
                return new SkillAdjustmentEffect(key,3,2,1.15f);
            case 37: //saradomin brew
                return new SkillAdjustmentEffect(key,1,2,1.2f);
            case 38: //saradomin brew
                return new SkillAdjustmentEffect(key,0,-2,0.9f,false);
            case 39: //saradomin brew
                return new SkillAdjustmentEffect(key,2,-2,0.9f,false);
            case 40: //saradomin brew
                return new SkillAdjustmentEffect(key,4,-2,0.9f,false);
            case 41: //saradomin brew
                return new SkillAdjustmentEffect(key,6,-2,0.9f,false);
            case 42: //antipoison
                return new AntiPoisonEffect(key,90);
            case 43: //superantipoison
                return new AntiPoisonEffect(key,360);
            case 44: //ranging potion
                return new SkillAdjustmentEffect(key,4,4,1.1f,false);
            case 45: //magic potion
                return new SkillAdjustmentEffect(key,6,4,1.0f,false);
            case 46: //antifire
                return new AntiFireEffect(key,360);
            case 47:
                return new XPBoostingEffect(key,10000,8);

            default: throw new NullPointerException("No Effect with ID: " + key);
        }
    }

    private PotionEffectFactory() {
        super(null);
    };
}
