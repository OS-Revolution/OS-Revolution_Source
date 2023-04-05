package ethos.runehub.action.click.item.consumable.star;

import ethos.model.players.Player;
import ethos.runehub.action.click.item.ClickConsumableItemAction;
import ethos.runehub.content.journey.JourneyStepType;
import ethos.runehub.ui.GameUI;
import ethos.util.Misc;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class ClickSkillStarAction extends ClickConsumableItemAction {

    @Override
    protected void onActionStart() {
        if (this.getActor().getContext().getPlayerSaveData().getBonusXp() == null) {
            this.getActor().getContext().getPlayerSaveData().setBonusXp(new HashMap<>());
            Arrays.stream(SkillDictionary.Skill.values()).forEach(skill -> this.getActor().getContext().getPlayerSaveData().getBonusXp().put(skill.getId(), new AdjustableInteger(0)));
        }
        if (this.getSkill() != null) {
            if (this.getActor().getContext().getPlayerSaveData().getBonusXp().get(this.getSkill().getId()).value() == Integer.MAX_VALUE) {
                this.getActor().sendMessage("You can not gain anymore bonus XP for this skill until you've used what you've got.");
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
        this.getActor().sendMessage("The star shines and then fades away granting you #" + getBonusXp() + " $" + Misc.capitalize(getSkill().name().toLowerCase()) + " bonus XP .");
        this.getActor().getContext().getPlayerSaveData().getBonusXp().computeIfPresent(this.getSkill().getId(), (key, val) -> {
            int bonusXp = getBonusXp();
            long totalBonusXp = (long)val.value() + bonusXp;
            if (totalBonusXp >>> 32 != 0) {
                totalBonusXp = Integer.MAX_VALUE;
            }
            return new AdjustableInteger((int) totalBonusXp);
        });
        this.getActor().getAttributes().getJourneyController().checkJourney(this.getItemId(),this.getItemAmount(), JourneyStepType.CONSUME_ITEM);
//        this.getActor().getAttributes().getJourneyController().checkJourney(2222024974161497586L,1);
        this.getActor().getPA().sendBonusXp(this.getSkill().getId(), this.getActor().getContext().getPlayerSaveData().getBonusXp().get(this.getSkill().getId()).value());
    }

    private SkillDictionary.Skill getSkill() {
        String itemName = ItemIdContextLoader.getInstance().read(this.getItemId()).getName();
        String skillName = null;
        String[] nameSplit = itemName.split(" ");
        if (nameSplit.length == 3) {
            skillName = nameSplit[1];
        } else if (nameSplit.length == 2) {
            skillName = nameSplit[0];
        }

        return SkillDictionary.Skill.values()[SkillDictionary.getSkillIdFromName(Objects.requireNonNull(skillName))];
    }

    private int getBonusXp() {
        String itemName = ItemIdContextLoader.getInstance().read(this.getItemId()).getName();
        int skillId = this.getSkill().getId();
        if (itemName.contains("Dull")) {
            return this.getActor().getSkillController().getLevel(skillId) * 105;
        } else if (itemName.contains("Shining")) {
            return this.getActor().getSkillController().getLevel(skillId) * 305;
        } else if (itemName.contains("Glorious")) {
            return this.getActor().getSkillController().getLevel(skillId) * 405;
        } else {
            return this.getActor().getSkillController().getLevel(skillId) * 205;
        }
    }

    public ClickSkillStarAction(Player attachment, int itemId, int itemSlot) {
        super(attachment, 1, itemId, 1, itemSlot);
    }
}
