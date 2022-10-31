package ethos.runehub.action.click.item;

import ethos.model.players.Player;

public abstract class ClickConsumableItemAction extends ClickItemAction {

    protected abstract void consume();

    @Override
    protected void onActionStart() {
//        this.removeItem();
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.removeItem();
        this.consume();
        this.stop();
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
