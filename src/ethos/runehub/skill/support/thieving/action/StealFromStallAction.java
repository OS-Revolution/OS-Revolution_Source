package ethos.runehub.skill.support.thieving.action;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.clip.Region;
import ethos.clip.WorldObject;
import ethos.model.content.achievement.AchievementType;
import ethos.model.content.achievement.Achievements;
import ethos.model.players.Player;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.node.context.impl.GatheringNodeContext;
import ethos.runehub.skill.node.context.impl.ThievingStallNodeContext;
import ethos.runehub.skill.node.impl.RenewableNode;

import ethos.runehub.skill.node.io.RenewableNodeLoader;
import ethos.runehub.skill.support.SupportSkillAction;
import ethos.util.PreconditionUtils;
import ethos.world.objects.GlobalObject;
import org.runehub.api.util.SkillDictionary;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.Loot;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class StealFromStallAction extends SupportSkillAction {


    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(881);
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.updateAnimation();
        if (this.isSuccessful(
                (int) (targetedNodeContext.getNode().getMinRoll() * this.getActor().getSkillController().getSupportSkill(this.getSkillId()).getEfficiencyBonus())
                , (int) (targetedNodeContext.getNode().getMaxRoll() * this.getActor().getSkillController().getSupportSkill(this.getSkillId()).getEfficiencyBonus()))) {
            this.onSuccess();
        } else {
            this.onFailure();
        }
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void validateInventory() {
        Preconditions.checkArgument(this.getActor().getItems().freeSlots() >= 1, "You must have at least #" + 1 + " free inventory slot to do this.");
    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getLevel(this.getSkillId()) >= targetedNodeContext.getNode().getLevelRequirement()),
                "You need a ?"
                        + SkillDictionary.getSkillNameFromId(this.getSkillId())
                        + " level of at least #"
                        + targetedNodeContext.getNode().getLevelRequirement()
                        + " to do this.");
    }

    @Override
    protected void validateItemRequirements() {

    }

    @Override
    protected void validateWorldRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isFalse(Server.getGlobalObjects().exists(this.getRenewableNode().getDepletedNodeId(), targetedNodeContext.getX(), targetedNodeContext.getY())),
                "This node is depleted.");
    }

    @Override
    protected void updateAnimation() {

    }

    protected void onEvent() {
    }

    protected void onDeplete() {
        this.onRespawn();
    }

    protected boolean depleteNode() {
        Logger.getGlobal().fine("depleting node roll");
        final RenewableNode node = RenewableNodeLoader.getInstance().read(targetedNodeContext.getNodeId());
        final int baseMinRoll = node.getDepletionMinRoll();
        final int playerBaseRoll = Skill.SKILL_RANDOM.nextInt(this.getActor().getSkillController().getSupportSkill(this.getSkillId()).getDepletionOdds());
        final double minRollModifier = this.getActor().getSkillController().getSupportSkill(this.getSkillId()).getEfficiencyBonus();
        final double minRoll = baseMinRoll + minRollModifier;

        return baseMinRoll <= 0 || playerBaseRoll >= minRoll;
    }

    protected boolean isEventTick() {
        final int ROLL = this.getActor().getSkillController().getSupportSkill(this.getSkillId()).getEventOdds();
        return (Skill.SKILL_RANDOM.nextInt(ROLL) + 1) == ROLL;
    }

    protected void onFailure() {
        this.getActor().sendMessage("You failed!");
        this.getActor().getAttributes().setCaughtThievingTimestamp(System.currentTimeMillis());
        this.onSuccess();
    }

    protected void onSuccess() {
        if (this.isEventTick()) {
            this.onEvent();
        }
        Collection<Loot> loot = LootTableLoader.getInstance().read(targetedNodeContext.getNode().getGatherableItemTableId()).roll(0);

        loot.forEach(loot1 -> {this.getActor().getItems().addItem((int) loot1.getId(), (int) loot1.getAmount());});
        this.addXp(targetedNodeContext.getNode().getInteractionExperience());
		Achievements.increase(this.getActor(), AchievementType.THIEV, 1);
        if (this.depleteNode()) {
            this.onDeplete();
        }
    }

    protected void onRespawn() {
        int face = 0;
        Optional<WorldObject> worldObject = Region.getWorldObject(
                targetedNodeContext.getNodeId(),
                targetedNodeContext.getX(),
                targetedNodeContext.getY(),
                targetedNodeContext.getZ()
        );
        if (worldObject.isPresent()) {
            face = worldObject.get().getFace();
        }
        Server.getGlobalObjects().add(
                new GlobalObject(
                        this.getRenewableNode().getDepletedNodeId(),
                        targetedNodeContext.getX(),
                        targetedNodeContext.getY(),
                        targetedNodeContext.getZ(),
                        face,
                        10,
                        this.getRenewableNode().getRespawnTime(),
                        targetedNodeContext.getNodeId())
        );
    }

    protected RenewableNode getRenewableNode() {
        return this.getActor().getSkillController().getRenewableNode(targetedNodeContext.getNodeId());
    }

    public StealFromStallAction(Player actor, int stallId, int stallX, int stallY, int stallZ) {
        super(actor, SkillDictionary.Skill.THIEVING.getId(), 4,null);
        this.targetedNodeContext = new ThievingStallNodeContext(stallId, stallX, stallY, stallZ);
    }

    private final GatheringNodeContext<?> targetedNodeContext;
}
