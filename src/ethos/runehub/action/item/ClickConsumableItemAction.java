package ethos.runehub.action.item;

import ethos.model.players.Player;

public abstract class ClickConsumableItemAction extends ClickItemAction {

    protected abstract void consume();

    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.consume();
    }

    @Override
    protected void onUpdate() {

    }

    protected void removeItem() {
        this.getActor().getItems().deleteItem(this.getItemId(),1);
    }

    public ClickConsumableItemAction(Player attachment, int ticks, int itemId, int itemAmount, int itemSlot) {
        super(attachment, ticks, itemId, itemAmount, itemSlot);
    }
}
