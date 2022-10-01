package ethos.runehub.action.click.item.consumable;

import ethos.model.players.Player;
import ethos.runehub.action.click.item.ClickConsumableItemAction;

public class PortableBankChargeScroll extends ClickConsumableItemAction {

    @Override
    protected void removeItem() {
        this.getActor().getItems().deleteItem2(this.getItemId(),1);
    }

    @Override
    protected void consume() {
        this.getActor().getContext().getPlayerSaveData().setPortableBankUsesAvailable(this.getActor().getContext().getPlayerSaveData().getPortableBankUsesAvailable() + 1);
        this.getActor().sendMessage("Portable Bank Charges: #" + this.getActor().getContext().getPlayerSaveData().getPortableBankUsesAvailable());
    }

    public PortableBankChargeScroll(Player attachment, int itemAmount, int itemSlot) {
        super(attachment, 1, 3495, itemAmount, itemSlot);
    }
}
