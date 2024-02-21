package ethos.runehub.action.click.item;

import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.LootTableContainerUtils;
import ethos.runehub.RunehubConstants;
import ethos.runehub.action.click.item.consumable.MembershipBond;
import ethos.runehub.action.click.item.consumable.PortableBankChargeScroll;
import ethos.runehub.action.click.item.consumable.booster.EfficiencyBooster;
import ethos.runehub.action.click.item.consumable.star.ClickPrismaticStarAction;
import ethos.runehub.action.click.item.consumable.TeleportChargeScroll;
import ethos.runehub.action.click.item.consumable.potion.ClickPotionConsumableAction;
import ethos.runehub.action.click.item.consumable.star.ClickSkillLampAction;
import ethos.runehub.action.click.item.consumable.star.ClickSkillStarAction;
import ethos.runehub.action.click.item.consumable.tablet.BreakTeleportTabletAction;
import ethos.runehub.entity.item.impl.PortableBankChest;
import ethos.runehub.loot.LootboxController;
import org.runehub.api.io.data.impl.LootTableContainerDAO;
import org.runehub.api.io.load.impl.LootTableContainerLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.ContainerType;
import org.runehub.api.model.entity.item.loot.Loot;

import java.util.ArrayList;

public class ClickItemActionFactory {


    public static ClickItemAction onClick(Player player, int itemId, int slot) {
        ClickItemAction action = null;
        if (RunehubConstants.STAR_IDS.contains(itemId)) {
            action = new ClickSkillStarAction(player,itemId,slot);
        }

        if (RunehubConstants.LAMP_IDS.contains(itemId)) {
            action = new ClickSkillLampAction(player,itemId,slot);
        }
        switch (itemId) {
            case 2428:
            case 121:
            case 123:
            case 125:
            case 113:
            case 115:
            case 117:
            case 119:
            case 2432:
            case 133:
            case 135:
            case 137:
            case 9739:
            case 9741:
            case 9743:
            case 9745:
            case 2430:
            case 127:
            case 129:
            case 131:
            case 3016:
            case 3018:
            case 3020:
            case 3022:
            case 3008:
            case 3010:
            case 3012:
            case 3014:
            case 6685:
            case 6687:
            case 6689:
            case 6691:
            case 2434:
            case 139:
            case 141:
            case 143:
            case 3024:
            case 3026:
            case 3028:
            case 3030:
            case 2446:
            case 175:
            case 177:
            case 179:
            case 2448:
            case 181:
            case 183:
            case 185:
            case 3040:
            case 3042:
            case 3044:
            case 3046:
            case 2444:
            case 169:
            case 171:
            case 173:
            case 2452:
            case 2454:
            case 2456:
            case 2458:
            case 739:
                action = new ClickPotionConsumableAction(
                        player,
                        itemId,
                        1,
                        slot
                );
                break;
//            case 6198:
//                player.getItems().deleteItem(itemId, slot, 1);
////                double playerMF = player.getContext().getPlayerSaveData().getMagicFind().value();
////                float mf = (float) playerMF;
//                LootboxController.getInstance().openLootboxWithoutUI(player,LootTableContainerUtils.getLootTableContainer(itemId, ContainerType.ITEM).orElse(null),1);
////                final Loot lootTable = new ArrayList<>(LootTableContainerLoader.getInstance().readAll().stream().filter(lootTableContainer -> lootTableContainer.getContainerId() == itemId).findAny().orElseThrow().roll(mf)).get(0);
////
////                LootTableLoader.getInstance().read(lootTable.getId()).roll(mf).forEach(loot -> {
////                    player.getItems().addItem((int) loot.getId(), (int) loot.getAmount());
////                });
//                break;
            case 2396:
                action = new TeleportChargeScroll(player, 1, slot);
                break;
            case 6822:
            case 6823:
            case 6824:
            case 6825:
                action = new ClickPrismaticStarAction(player,itemId,slot);
                break;
            case 8152:
                action = new PortableBankChest.OnFirstClick(player,slot);
                break;
            case 3495:
                action = new PortableBankChargeScroll(player,1,slot);
                break;
            case 13190:
            case 13192:
                action = new MembershipBond(player,itemId,1,slot);
                break;
            case 8023:
                action = new EfficiencyBooster(player,itemId,1,slot,1,8);
                break;
            case 8024:
                action = new EfficiencyBooster(player,itemId,1,slot,6,8);
                break;
            case 8025:
                action = new EfficiencyBooster(player,itemId,1,slot,12,8);
                break;
            case 8026:
                action = new EfficiencyBooster(player,itemId,1,slot,24,8);
                break;
            case 8007:
            case 8008:
            case 8009:
            case 8010:
            case 8011:
            case 8115:
            case 8116:
            case 8117:
            case 8118:
            case 8119:
            case 8120:
            case 8121:
            case 8246:
            case 8247:
            case 8248:
            case 8380:
            case 8381:
                action = new BreakTeleportTabletAction(player,itemId,1,slot);
                break;
        }
//        if (action != null)
//            Server.getEventHandler().submit(action);
        return action;
    }
}
