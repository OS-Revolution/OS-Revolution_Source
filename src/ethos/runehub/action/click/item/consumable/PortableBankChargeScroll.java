package ethos.runehub.action.click.item.consumable;

import com.google.common.base.Preconditions;
import ethos.model.items.ItemAssistant;
import ethos.model.players.Player;
import ethos.runehub.action.click.item.ClickConsumableItemAction;

public class PortableBankChargeScroll extends ClickConsumableItemAction {

    @Override
    protected void playerHasItemPrerequisite() {
        super.playerHasItemPrerequisite();
        Preconditions.checkArgument(this.getActor().getAttributes().isMember(),"You must be a member to use this.");
    }

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
