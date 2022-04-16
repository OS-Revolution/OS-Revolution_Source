package ethos.runehub.action.click.item.consumable.potion;

import ethos.model.players.Player;
import ethos.runehub.skill.artisan.herblore.potion.PotionEffectFactory;
import ethos.runehub.skill.artisan.herblore.potion.effect.SkillAdjustmentEffect;

import java.util.Arrays;

public class DrinkStatAdjustingPotion extends ClickPotionConsumableAction {

    @Override
    protected void applyEffect() {
        this.adjustSkills();
    }

    protected void adjustSkills() {
        Arrays.stream(this.getPotion().getEffectId())
                .filter(value -> PotionEffectFactory.getInstance().read(value) instanceof SkillAdjustmentEffect)
//                .filter(skillAdjustment -> this.getActor().playerLevel[skillAdjustment.getSkillId()] >= 1)
                .forEach(value -> {
                    final SkillAdjustmentEffect skillAdjustment = (SkillAdjustmentEffect) PotionEffectFactory.getInstance().read(value);
                    int skillId = skillAdjustment.getSkillId();
                    int adjustedSkillLevel = (int) ((this.getActor().getLevelForXP(this.getActor().playerXP[skillId]) + skillAdjustment.getBaseChange()) * skillAdjustment.getModifier());
                    int boost = this.getActor().playerLevel[skillId] - adjustedSkillLevel;
                    System.out.println("Adjusted Skill Level: " + adjustedSkillLevel);
                    System.out.println("Base: " + skillAdjustment.getBaseChange());
                    System.out.println("Modifier: " + skillAdjustment.getModifier());
                    if(adjustedSkillLevel < 1) {
                        this.getActor().playerLevel[skillId] = 1;
                    }

                    this.getActor().playerLevel[skillId] = adjustedSkillLevel;
//                    this.getActor().getPA().setSkillLevel(skillId, adjustedSkillLevel, this.getActor().playerXP[skillId]);
                    this.getActor().getPA().refreshSkill(skillId);
                });
    }

    public DrinkStatAdjustingPotion(Player player, int ticks, int itemId, int itemAmount, int itemSlot) {
        super(player, ticks, itemId, itemAmount, itemSlot);
    }

    public DrinkStatAdjustingPotion(Player player, int itemId, int itemAmount, int itemSlot) {
        this(player,3,itemId,itemAmount,itemSlot);
    }
}
