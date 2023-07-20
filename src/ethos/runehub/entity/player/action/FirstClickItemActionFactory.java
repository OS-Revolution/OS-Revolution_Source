package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.LootTableContainerUtils;
import ethos.runehub.content.PointController;
import ethos.runehub.entity.player.action.impl.ClickItemAction;
import ethos.runehub.entity.player.action.impl.item.FirstClickEnchantedGemAction;
import ethos.runehub.entity.player.action.impl.item.FirstClickGoldAccumulatorAction;
import ethos.runehub.entity.player.action.impl.node.FirstClickPortableSkillingStation;
import ethos.runehub.loot.LootboxController;
import ethos.runehub.skill.combat.prayer.action.BuryRemainsAction;
import org.runehub.api.model.entity.item.loot.ContainerType;

import java.util.logging.Logger;

public class FirstClickItemActionFactory {

    public static ClickItemAction getAction(Player player, int nodeX, int nodeY, int nodeId) {
        Logger.getGlobal().fine("First Click Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 7478:
                return new ClickItemAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.getItems().delete(nodeId,1);
                        player.getAttributes().getPointController().addPoints(PointController.PointType.JOURNEY,1);
                        player.sendMessage("You received #" + 1 + " $" + PointController.PointType.JOURNEY + " $point. You now have #"+ player.getAttributes().getPointController().getPoints(PointController.PointType.JOURNEY) + " $" + PointController.PointType.JOURNEY + " $points.");
                    }
                };
            case 19887:
                return new ClickItemAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        LootboxController.getInstance().openLootboxWithoutUI(player, LootTableContainerUtils.getLootTableContainer(nodeId, ContainerType.ITEM).orElse(null),4);
                    }
                };

            case 8554:
            case 8528:
            case 8530:
            case 8532:
            case 8534:
                return new FirstClickPortableSkillingStation(player, nodeX, nodeY, nodeId);
            case 4155:
                return new FirstClickEnchantedGemAction(player,nodeX,nodeY,nodeId);
            case 8411:
            case 8412:
            case 8413:
                return new FirstClickGoldAccumulatorAction(player,nodeX,nodeY,nodeId);
            case 526:
            case 528:
            case 530:
            case 532:
            case 534:
            case 536:
            case 2859:
            case 3123:
            case 3125:
            case 3179:
            case 3180:
            case 3183:
            case 4812:
            case 4830:
            case 4832:
            case 4834:
            case 6729:
            case 6812:
            case 10891:
            case 11943:
                return new ClickItemAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.getSkillController().getPrayer().train(new BuryRemainsAction(player,nodeId));
                    }
                };
        }
        return null;
    }


}
