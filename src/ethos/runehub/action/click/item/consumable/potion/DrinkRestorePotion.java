package ethos.runehub.action.click.item.consumable.potion;

import ethos.model.players.Player;
import ethos.runehub.skill.artisan.herblore.potion.PotionEffectFactory;
import ethos.runehub.skill.artisan.herblore.potion.effect.SkillAdjustmentEffect;

import java.util.Arrays;

public class DrinkRestorePotion extends ClickPotionConsumableAction {

    @Override
    protected void applyEffect() {
        this.adjustSkills();
    }

    protected void adjustSkills() {
        Arrays.stream(this.getPotion().getEffectId())
                .filter(value -> PotionEffectFactory.getInstance().read(value) instanceof SkillAdjustmentEffect)
                .forEach(value -> {
                    final SkillAdjustmentEffect skillAdjustment = (SkillAdjustmentEffect) PotionEffectFactory.getInstance().read(value);
                    int skillId = skillAdjustment.getSkillId();
                    int adjustedSkillLevel = (int) ((this.getActor().getLevelForXP(this.getActor().playerXP[skillId]) + skillAdjustment.getBaseChange()) * skillAdjustment.getModifier());
                    if(adjustedSkillLevel <= this.getActor().getLevelForXP(this.getActor().playerXP[skillId])) {
                        this.getActor().playerLevel[skillId] = adjustedSkillLevel;
                    } else {
                        this.getActor().playerLevel[skillId] = this.getActor().getLevelForXP(this.getActor().playerXP[skillId]);
                    }
                    this.getActor().getPA().refreshSkill(skillId);
                });
    }

    public DrinkRestorePotion(Player player, int ticks, int itemId, int itemAmount, int itemSlot) {
        super(player, ticks, itemId, itemAmount, itemSlot);
    }

    public DrinkRestorePotion(Player player, int itemId, int itemAmount, int itemSlot) {
        this(player,3,itemId,itemAmount,itemSlot);
    }
}
