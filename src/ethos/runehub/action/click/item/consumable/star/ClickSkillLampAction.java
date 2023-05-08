package ethos.runehub.action.click.item.consumable.star;

import ethos.model.players.Player;
import ethos.runehub.action.click.item.ClickConsumableItemAction;
import ethos.runehub.content.journey.JourneyStepType;
import ethos.util.Misc;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class ClickSkillLampAction extends ClickConsumableItemAction {

    @Override
    protected void onActionStart() {
        if (this.getSkill() != null) {
            if (this.getActor().playerXP[this.getSkill().getId()] == Integer.MAX_VALUE) {
                this.getActor().sendMessage("You can not gain anymore XP for this skill.");
                this.stop();
            }
        } else {
            this.stop();
        }
    }

    @Override
    protected void onTick() {
        this.consume();
        this.stop();
    }

    @Override
    protected void consume() {
        this.removeItem();
        this.getActor().sendMessage("You rub the lamp and receive #" + getBonusXp() + " $" + Misc.capitalize(getSkill().name().toLowerCase()) + " XP .");
        this.getActor().getSkillController().addImmutableXP(this.getSkill().getId(),this.getBonusXp());
    }

    private SkillDictionary.Skill getSkill() {
        String itemName = ItemIdContextLoader.getInstance().read(this.getItemId()).getName();
        String skillName = null;
        String[] nameSplit = itemName.split(" ");
        if (nameSplit.length == 4) {
            skillName = nameSplit[1];
        } else if (nameSplit.length == 3) {
            skillName = nameSplit[0];

        }

        return SkillDictionary.Skill.values()[SkillDictionary.getSkillIdFromName(Objects.requireNonNull(skillName))];
    }

    private int calculateXP(int level, int size) {
        if (level < 1 || level > 99) {
            throw new IllegalArgumentException("Level must be between 1 and 99");
        }
        double scalingFactor = (level - 1) / 98.0;
        int minValue = 66 + (67 * size);
        int maxValue = 8872 + (8872 * size);
        return (int) Math.round(minValue + scalingFactor * (maxValue - minValue));
    }

    private int getBonusXp() {
        String itemName = ItemIdContextLoader.getInstance().read(this.getItemId()).getName();
        int skillId = this.getSkill().getId();
        if (itemName.contains("Small")) {
            return this.calculateXP(this.getActor().getSkillController().getLevel(skillId),0);
        } else if (itemName.contains("Large")) {
            return this.calculateXP(this.getActor().getSkillController().getLevel(skillId),3);
        } else if (itemName.contains("Huge")) {
            return this.calculateXP(this.getActor().getSkillController().getLevel(skillId),7);
        } else {
            return this.calculateXP(this.getActor().getSkillController().getLevel(skillId),1);
        }
    }

    public ClickSkillLampAction(Player attachment, int itemId, int itemSlot) {
        super(attachment, 1, itemId, 1, itemSlot);
    }
}
