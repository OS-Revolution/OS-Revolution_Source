package ethos.runehub.action.item;

import ethos.model.players.Player;

import java.util.List;

public abstract class DrinkStatAdjustingPotion extends ClickPotionConsumableAction {

    protected void adjustSkills() {
        skillAdjustments.stream()
                .filter(skillAdjustment -> this.getActor().playerLevel[skillAdjustment.getSkillId()] > 1)
                .forEach(skillAdjustment -> {
                    int skillId = skillAdjustment.getSkillId();
                    int adjustedSkillLevel = (int) ((this.getActor().playerLevel[skillId] + skillAdjustment.getBaseChange()) * skillAdjustment.getModifier());
                    System.out.println("Adjusted Skill Level: " + adjustedSkillLevel);
                    System.out.println("Base: " + skillAdjustment.getBaseChange());
                    System.out.println("Modifier: " + skillAdjustment.getModifier());
                    if(adjustedSkillLevel < 1) {
                        this.getActor().playerLevel[skillId] = 1;
                    }

                    this.getActor().getPA().refreshSkill(skillId);
                    this.getActor().getPA().setSkillLevel(skillId, this.getActor().playerLevel[skillId], this.getActor().playerXP[skillId]);
                });
    }

    public DrinkStatAdjustingPotion(Player player, int ticks, int itemId, int itemAmount, int itemSlot, int fourDoseId, int threeDoseId, int twoDoseId, int oneDoseId,
                                    List<SkillAdjustment> skillAdjustments) {
        super(player, ticks, itemId, itemAmount, itemSlot, fourDoseId, threeDoseId, twoDoseId, oneDoseId);
        this.skillAdjustments = skillAdjustments;
    }

    public DrinkStatAdjustingPotion(Player player, int itemId, int itemAmount, int itemSlot, int fourDoseId, int threeDoseId, int twoDoseId, int oneDoseId,
                                    List<SkillAdjustment> skillAdjustments) {
        this(player,2,itemId,itemAmount,itemSlot,fourDoseId,threeDoseId,twoDoseId,oneDoseId,skillAdjustments);
    }

    private final List<SkillAdjustment> skillAdjustments;
}
