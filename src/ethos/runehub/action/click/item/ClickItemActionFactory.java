package ethos.runehub.action.click.item;

import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.action.click.item.consumable.potion.ClickPotionConsumableAction;

public class ClickItemActionFactory {


    public static void onClick(Player player, int itemId, int slot) {
        ClickItemAction action = null;
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
            case 140:
            case 142:
            case 144:
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
                action = new ClickPotionConsumableAction(
                        player,
                        itemId,
                        1,
                        slot
                );
                break;
        }
        if (action != null)
            Server.getEventHandler().submit(action);
    }
}
