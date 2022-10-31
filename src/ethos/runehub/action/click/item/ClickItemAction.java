package ethos.runehub.action.click.item;

import com.google.common.base.Preconditions;
import ethos.event.Event;
import ethos.model.items.ItemAssistant;
import ethos.model.players.Player;

import java.util.logging.Logger;

public abstract class ClickItemAction extends Event<Player> {

    protected abstract void onActionStart();

    protected abstract void onActionStop();

    protected abstract void onTick();

    protected abstract void onUpdate();

    protected void playerHasItemPrerequisite() {

        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(itemId), "You have run out of $" + ItemAssistant.getItemName(itemId));

    }

    protected boolean checkPrerequisites() {
        try {
            this.playerHasItemPrerequisite();
            return true;
        } catch (Exception e) {
            this.getActor().sendMessage(e.getMessage());
            this.stop();
        }
        return false;
    }

    @Override
    public void execute() {
        Logger.getGlobal().fine("Executing Event Tick");
        if (this.checkPrerequisites())
            this.onTick();
    }

    @Override
    public void stop() {
        Logger.getGlobal().fine("Stopping Event");
        super.stop();
        this.onActionStop();
        if (attachment != null) {
            this.getActor().stopAnimation();
        }
    }

    @Override
    public void initialize() {
        Logger.getGlobal().fine("Starting Event");
        super.initialize();
        this.onActionStart();
    }

    protected Player getActor() {
        return this.getAttachment();
    }

    protected int getItemId() {
        return itemId;
    }

    protected int getItemAmount() {
        return itemAmount;
    }

    protected int getItemSlot() {
        return itemSlot;
    }

    public ClickItemAction(Player attachment, int ticks, int itemId, int itemAmount, int itemSlot) {
        super(attachment, ticks);
        this.itemId = itemId;
        this.itemAmount = itemAmount;
        this.itemSlot = itemSlot;
    }

    private final int itemId, itemAmount, itemSlot;
}
