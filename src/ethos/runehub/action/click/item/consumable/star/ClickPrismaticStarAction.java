package ethos.runehub.action.click.item.consumable.star;

import ethos.model.players.Player;
import ethos.runehub.action.click.item.ClickConsumableItemAction;
import ethos.runehub.content.journey.JourneyStepType;
import ethos.runehub.ui.GameUI;
import ethos.runehub.ui.impl.SelectASkillUI;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;
import java.util.HashMap;

public class ClickPrismaticStarAction extends ClickConsumableItemAction {

    @Override
    protected void onActionStart() {
        this.getActor().sendUI(new SelectASkillUI(this.getActor()));
        this.getActor().getAttributes().setUsingStar(true);
    }

    @Override
    protected void onTick() {
        if (this.getActor().getAttributes().getActiveUI() != null) {
            if (this.getActor().getAttributes().getActiveUI().getState() == GameUI.State.COMPLETED) {
                this.consume();
                this.stop();
            }
        } else {
            this.stop();
        }
    }

    @Override
    protected void consume() {
        int skillId = this.getActor().getAttributes().getSkillSelected();
        if (this.getActor().getContext().getPlayerSaveData().getBonusXp() == null) {
            this.getActor().getContext().getPlayerSaveData().setBonusXp(new HashMap<>());
            Arrays.stream(SkillDictionary.Skill.values()).forEach(skill -> this.getActor().getContext().getPlayerSaveData().getBonusXp().put(skill.getId(), new AdjustableInteger(0)));
        }
        this.removeItem();
        this.getActor().sendMessage("The star shines and then fades away granting you #" + getBonusXp() + " bonus XP.");
        this.getActor().getContext().getPlayerSaveData().getBonusXp().computeIfPresent(skillId, (key, val) -> new AdjustableInteger(val.add(getBonusXp())));
        this.getActor().getPA().sendBonusXp(skillId, this.getActor().getContext().getPlayerSaveData().getBonusXp().get(skillId).value());
        this.getActor().getAttributes().getActiveUI().close();
        this.getActor().getAttributes().getJourneyController().checkJourney(this.getItemId(),this.getItemAmount(), JourneyStepType.CONSUME_ITEM);
//        this.getActor().getAttributes().getJourneyController().checkJourney(2222024974161497586L,1);
    }

    private int getBonusXp() {
        switch (this.getItemId()) {
            case 6822:
                return this.getActor().getSkillController().getLevel(this.getActor().getAttributes().getSkillSelected()) * 115;
            case 6823:
                return this.getActor().getSkillController().getLevel(this.getActor().getAttributes().getSkillSelected()) * 215;
            case 6824:
                return this.getActor().getSkillController().getLevel(this.getActor().getAttributes().getSkillSelected()) * 315;
            case 6825:
                return this.getActor().getSkillController().getLevel(this.getActor().getAttributes().getSkillSelected()) * 350;
        }
        return 0;
    }

    public ClickPrismaticStarAction(Player attachment, int itemId, int itemSlot) {
        super(attachment, 1, itemId, 1, itemSlot);
    }
}
