package ethos.runehub.entity.item.impl;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.action.click.item.ClickItemAction;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.entity.item.Item;
import org.runehub.api.util.PreconditionUtils;

public class PortableBankChest extends Item {

    public PortableBankChest() {
        super(ItemIdContextLoader.getInstance().read(8152));
    }

    public static class OnFirstClick extends ClickItemAction {

        @Override
        protected void playerHasItemPrerequisite() {
            super.playerHasItemPrerequisite();
            Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getContext().getPlayerSaveData().getPortableBankUsesAvailable() > 0), "Your @8152 is out of charges.");
            Preconditions.checkArgument(this.getActor().getAttributes().isMember(),"You must be a member to use this.");
        }

        @Override
        protected void onActionStart() {

        }

        @Override
        protected void onActionStop() {

        }

        @Override
        protected void onTick() {
            this.getActor().getPA().openUpBank();
            this.getActor().getContext().getPlayerSaveData().setPortableBankUsesAvailable(this.getActor().getContext().getPlayerSaveData().getPortableBankUsesAvailable() - 1);
            this.getActor().getContext().getPlayerSaveData().setPortableBankUses(this.getActor().getContext().getPlayerSaveData().getPortableBankUses() + 1);
            this.getActor().sendMessage("You have #" + this.getActor().getContext().getPlayerSaveData().getPortableBankUsesAvailable() + " charges remaining.");
            this.stop();
        }

        @Override
        protected void onUpdate() {

        }

        public OnFirstClick(Player attachment, int itemSlot) {
            super(attachment, 1, 8152, 1, itemSlot);
        }
    }

    public static class OnSecondClick extends ClickItemAction {

        @Override
        protected void playerHasItemPrerequisite() {
            super.playerHasItemPrerequisite();
            Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getContext().getPlayerSaveData().getPortableBankUsesAvailable() > 0), "Your @8152 is out of charges.");
        }

        @Override
        protected void onActionStart() {

        }

        @Override
        protected void onActionStop() {

        }

        @Override
        protected void onTick() {
            this.getActor().sendMessage("You have #" + this.getActor().getContext().getPlayerSaveData().getPortableBankUsesAvailable() + " charges remaining.");
            this.stop();
        }

        @Override
        protected void onUpdate() {

        }

        public OnSecondClick(Player attachment, int itemSlot) {
            super(attachment, 1, 8152, 1, itemSlot);
        }
    }
}
