package ethos.runehub.action.click.item.consumable;

import ethos.model.players.Player;
import ethos.runehub.action.click.item.ClickConsumableItemAction;

public class TeleportChargeScroll extends ClickConsumableItemAction {

    @Override
    protected void removeItem() {
        this.getActor().getItems().deleteItem2(this.getItemId(),1);
    }

    @Override
    protected void consume() {
        this.getActor().getContext().getPlayerSaveData().getInstantTeleportCharges().add(this.getItemAmount());
        this.getActor().sendMessage("You read the @" + this.getItemId() + " and gain #" + this.getItemAmount() + " Home Teleport Charges.");
    }

    public TeleportChargeScroll(Player attachment, int itemAmount, int itemSlot) {
        super(attachment, 1, 2396, itemAmount, itemSlot);
    }
}
