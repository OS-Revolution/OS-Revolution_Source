package ethos.runehub.skill.gathering.woodcutting.action;

import ethos.model.players.Player;
import ethos.runehub.skill.gathering.GatheringSkillAction;
import ethos.runehub.skill.node.context.impl.WoodcuttingNodeContext;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.Loot;

import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ActiveWoodcuttingSkillAction extends GatheringSkillAction {

    @Override
    protected void onDeplete() {
        this.onRespawn();
    }

    @Override
    protected void onEvent() {
        Logger.getGlobal().fine("Dropping Birds Nest");
        final Loot loot = LootTableLoader.getInstance().read(4937229515252058548L).roll(1).stream().collect(Collectors.toList()).get(0);
        final int itemId = Math.toIntExact(loot.getId());
        final int amount = Math.toIntExact(loot.getId());
        this.getActor().getItems().createGroundItem(itemId, this.getTargetedNodeContext().getX(), this.getTargetedNodeContext().getY(), amount);
    }

    @Override
    protected void updateAnimation() {
        if (super.getElapsedTicks() == 4 || super.getElapsedTicks() % 4 == 0) {
            this.getActor().startAnimation(this.getActor().getSkillController().getWoodcutting().getGetBestAvailableTool().getAnimationId());
        }
    }

    public ActiveWoodcuttingSkillAction(Player player, int skillId, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(player, skillId, new WoodcuttingNodeContext(nodeId,nodeX,nodeY,nodeZ), 4,player.getSkillController().getWoodcutting().getGetBestAvailableTool());
    }
}
