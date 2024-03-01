package ethos.runehub.skill.gathering.woodcutting.action;

import ethos.model.players.Player;
import ethos.runehub.skill.gathering.GatheringSkillAction;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.node.context.impl.WoodcuttingNodeContext;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.Loot;

import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Cinnamon tree is the source of the commodity cinnamon it provides the cinnamon logs
 * there must be a 3x3 area clear around the tree for it to respawn
 * Cinnamon trees will respawn in a random location within a 3x3 area of their original spawn
 * this is to prevent autoclicking and camping
 */
public class ChopCinnamonTreeSkillAction extends GatheringSkillAction {

    @Override
    public void onTick() {
        Logger.getGlobal().fine("Starting Harvest Sequence");
        this.updateAnimation();
        if (this.isSuccessful(
                (int) (targetedNodeContext.getNode().getMinRoll() * this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getPowerBonus())
                ,(int) (targetedNodeContext.getNode().getMaxRoll() * this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getPowerBonus()))) {
            this.onGather();
        } else if(this.isSuccessful(32,32)) {
            this.onRespawn();
            this.getActor().sendMessage("Your prolonged chopping has destroyed the bark within!");
        }
    }

    @Override
    protected GatheringTool getGetBestAvailableTool() throws NullPointerException {
        return this.getActor().getSkillController().getWoodcutting().getBestAvailableTool();
    }

    @Override
    protected void onEvent() {
        this.getActor().sendMessage("You receive an extra log!");
        final Loot loot = LootTableLoader.getInstance().read(6223316775799507538L).roll(1).stream().collect(Collectors.toList()).get(0);
        final int itemId = Math.toIntExact(loot.getId());
        final int amount = Math.toIntExact(loot.getId());
        this.getActor().getItems().createGroundItem(itemId, this.getTargetedNodeContext().getX(), this.getTargetedNodeContext().getY(), amount);
    }

    @Override
    protected void updateAnimation() {
        if (super.getElapsedTicks() == 4 || super.getElapsedTicks() % 4 == 0) {
            this.getActor().startAnimation(this.getActor().getContext().getPlayerSaveData().getSkillAnimationOverrideMap().containsKey(this.getSkillId()) ?
                    this.getActor().getContext().getPlayerSaveData().getSkillAnimationOverrideMap().get(this.getSkillId()) :
                    this.getActor().getSkillController().getWoodcutting().getBestAvailableTool().getAnimationId());
        }
    }

    public ChopCinnamonTreeSkillAction(Player player, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(player, 8, new WoodcuttingNodeContext(nodeId, nodeX, nodeY, nodeZ), 4);
    }
}
