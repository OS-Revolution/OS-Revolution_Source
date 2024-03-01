package ethos.runehub.skill.gathering.fishing.action;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.gathering.GatheringSkill;
import ethos.runehub.skill.node.context.impl.FishingNodeContext;
import ethos.util.PreconditionUtils;

public class FishingPlatformFishingAction extends NetFishingAction {


    @Override
    protected void onEvent() {
        this.getActor().sendMessage("As you lean over minnows pour out of your... $pockets?");
        int amount = (int) (this.getActor().getItems().getItemAmount(21356) * 0.10);
        this.getActor().getItems().deleteItem2(21356, amount);
        if (super.isEventTick()) {
            super.onEvent();
        }
    }

    @Override
    protected boolean isEventTick() {
        final int ROLL = this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getEventOdds();
        int baseChance = 20;
        if (this.getActor().getAttributes().isMember())
            baseChance -= 10;
        return (Skill.SKILL_RANDOM.nextInt(ROLL)) <= baseChance;
    }

    @Override
    protected boolean depleteNode() {
        return false;
    }

    @Override
    protected void addItems() {
        this.getActor().getItems().addItem(21356, this.getBonusFish());
    }

    @Override
    protected void validateWorldRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(Server.getGlobalObjects().exists(20930, targetedNodeContext.getX(), targetedNodeContext.getY())),
                "The fishing spot depleted.");
    }

    private int getBonusFish() {
        int base = 1;
        if (this.getActor().getAttributes().isMember())
            base += base * 1.5;
        if (this.getActor().playerEquipment[this.getActor().playerRing] == 3742)
            base += 10;
        return base;
    }

    public FishingPlatformFishingAction(Player player, int nodeX, int nodeY) {
        super(player, new FishingNodeContext(9, nodeX, nodeY, player.heightLevel));
    }
}
