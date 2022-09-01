package ethos.runehub.ui.impl;

import ethos.model.content.LootingBag.LootingBagItem;
import ethos.model.players.Player;
import ethos.runehub.ui.GameUI;

public class MoaiRechargeUI extends GameUI {

    @Override
    protected void onOpen() {
        this.sendItems();
        this.sendInventoryItems();
    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onAction(int buttonId) {
        switch (buttonId) {

            default:
                this.close();
                break;
        }
    }

    @Override
    protected void onEvent() {

    }

    private void sendItems() {

        StringBuilder sendSpells =new StringBuilder("#");

        for (int i = 0; i < 3; i++) {
            int id = 0;
            int amt = 0;

//            if (i < items.size()) {
//                LootingBagItem item = items.get(i);
//                if (item != null) {
//                    id = item.getId();
//                    amt = item.getAmount();
//                }
//            }

            if (id <= 0) {
                id = -1;
            }
            this.getPlayer().getPA().sendFrame34a(30033 + i, id, 0 , amt);
            if (id == -1)
                id = 0;
            if (i == 2) {
                sendSpells.append(id).append(":").append(amt);
            } else {
                sendSpells.append(id).append(":").append(amt).append("-");
            }
        }
        sendSpells.append("$");
        this.getPlayer().getPA().sendFrame126(sendSpells.toString(), 49999);
    }

    private void sendInventoryItems() {
        for (int i = 0; i < 28; i++) {
            int id = 0;
            int amt = 0;

            if (i < this.getPlayer().playerItems.length) {
                id = this.getPlayer().playerItems[i];
                amt = this.getPlayer().playerItemsN[i];
            }
            int START_INVENTORY_INTERFACE=30005;
            this.getPlayer().getPA().sendFrame34a(START_INVENTORY_INTERFACE+ i, id - 1, 0 , amt);
        }
    }

    private void resetItems() {
        this.getPlayer().getItems().resetItems(3214);
        this.getPlayer().getPA().requestUpdates();
    }

    public MoaiRechargeUI(Player player) {
        super(30000, player);
    }
}
