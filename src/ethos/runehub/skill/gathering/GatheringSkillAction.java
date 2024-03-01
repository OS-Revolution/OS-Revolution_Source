package ethos.runehub.skill.gathering;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.clip.Region;
import ethos.clip.WorldObject;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.node.context.impl.GatheringNodeContext;
import ethos.runehub.skill.node.impl.RenewableNode;
import ethos.runehub.skill.node.io.RenewableNodeLoader;
import ethos.util.PreconditionUtils;
import ethos.world.objects.GlobalObject;
import org.runehub.api.io.load.impl.LootTableLoader;

import java.util.Optional;
import java.util.logging.Logger;

public abstract class GatheringSkillAction extends SkillAction {

    protected abstract void onEvent();

    protected boolean isException() {
        return false;
    }

    protected void onException() {

    }

    protected void onDeplete() {
        this.onRespawn();
    }

    @Override
    public void onTick() {
        Logger.getGlobal().fine("Starting Harvest Sequence");
        this.updateAnimation();
        if (this.isSuccessful(
                (int) (targetedNodeContext.getNode().getMinRoll() * this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getPowerBonus())
                ,(int) (targetedNodeContext.getNode().getMaxRoll() * this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getPowerBonus()))) {
            this.onGather();
        }
    }

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(this.getActor().getContext().getPlayerSaveData().getSkillAnimationOverrideMap().containsKey(this.getSkillId()) ?
                this.getActor().getContext().getPlayerSaveData().getSkillAnimationOverrideMap().get(this.getSkillId()) : tool.getAnimationId());
        this.getActor().turnPlayerTo(this.getTargetedNodeContext().getX(),this.getTargetedNodeContext().getY());
        this.getActor().getSkillController().getFishing().setPower(this.getGetBestAvailableTool().getBasePower());
        this.getActor().getSkillController().getFishing().setEfficiency(this.getGetBestAvailableTool().getBaseEfficiency());
        this.getActor().getSkillController().getFishing().setGains(this.getGetBestAvailableTool().getXpGainMultiplier());
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onUpdate() {
        this.updateAnimation();
    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getBestAvailableTool() != null, "You do not have a valid tool.");
    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getLevel(this.getSkillId()) >= this.getTargetedNodeContext().getNode().getLevelRequirement()),
                "You need a $"
                        + RunehubUtils.getSkillName(this.getSkillId())
                        + " level of at least $"
                        + this.getTargetedNodeContext().getNode().getLevelRequirement()
                        + " to do this.");

    }

    @Override
    protected void validateWorldRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isFalse(Server.getGlobalObjects().exists(this.getRenewableNode().getDepletedNodeId(), targetedNodeContext.getX(), targetedNodeContext.getY())),
                "This node is depleted.");
    }

    @Override
    protected void validateInventory() {
        Preconditions.checkArgument(this.getActor().getItems().freeSlots() >= 1, "You must have at least $" + 1 + " free inventory slot to do this.");
    }

    @Override
    protected void addItems(int id, int amount) {
        this.getActor().getItems().addItem(id,amount);
    }

    protected void addItems() {
        Logger.getGlobal().fine("Adding Items");
        LootTableLoader.getInstance().read(this.getTargetedNodeContext().getNode().getGatherableItemTableId()).roll(this.getActor().getAttributes().getMagicFind()).forEach(loot -> {
            this.addItems(Math.toIntExact(loot.getId()), Math.toIntExact(loot.getAmount()));
        });
    }

    protected boolean depleteNode() {
        Logger.getGlobal().fine("depleting node roll");
        final RenewableNode node = RenewableNodeLoader.getInstance().read(this.getTargetedNodeContext().getNode().getId());
        final int baseMinRoll = node.getDepletionMinRoll();
        final int playerBaseRoll = Skill.SKILL_RANDOM.nextInt(GatheringSkill.DEPLETION_ODDS);
        final double minRollModifier = this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getEfficiencyBonus();
        final double minRoll = baseMinRoll + minRollModifier;

        return baseMinRoll <= 0 || playerBaseRoll >= minRoll;
    }

    protected boolean isEventTick() {
        final int ROLL = this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getEventOdds();
        return (Skill.SKILL_RANDOM.nextInt(ROLL) + 1) == ROLL;
    }

    protected void onGather() {
        if (this.isEventTick()) {
            this.onEvent();
        }
        if(this.isException()) {
            this.onException();
        } else {
            this.addItems();
        }
        this.addXp(this.getTargetedNodeContext().getNode().getInteractionExperience());
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

    public GatheringNodeContext<?> getTargetedNodeContext() {
        return targetedNodeContext;
    }

    public void setTool(GatheringTool tool) {
        this.tool = tool;
    }

    protected abstract GatheringTool getGetBestAvailableTool() throws NullPointerException;

    public GatheringSkillAction(Player player, int skillId, GatheringNodeContext<?> targetedNodeContext, int ticks) {
        super(player, skillId, ticks);
        this.targetedNodeContext = targetedNodeContext;
        this.tool = this.getGetBestAvailableTool();
    }

    private GatheringTool tool;
    protected final GatheringNodeContext<?> targetedNodeContext;
}
