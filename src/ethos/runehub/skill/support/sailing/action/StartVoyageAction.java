package ethos.runehub.skill.support.sailing.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.skill.support.sailing.Sailing;
import org.runehub.api.util.PreconditionUtils;

public class StartVoyageAction extends VoyageMenuAction{

    @Override
    protected void validate() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getSailing().getShipSlot(shipSlot).hasShip()),"You have no $ship in this $slot.");
        Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().getSkillController().getSailing().getShipSlot(shipSlot).onVoyage()),"This ship is currently on a voyage.");
        Preconditions.checkArgument(PreconditionUtils.isTrue(!this.getActor().getSkillController().getSailing().getShipSlot(shipSlot).isVoyageComplete()),"This ship is currently on a voyage.");
        Preconditions.checkArgument(PreconditionUtils.isTrue(voyageAvailable(voyageSlot)),"One of your ships is already on this voyage.");
        Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().getSkillController().getSailing().getShipSlot(shipSlot).isVoyageComplete()),"You must claim your previous voyage first.");
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getContext().getPlayerSaveData().getAvailableVoyages().get(voyageSlot).getContext().playerHasAllLevels(this.getActor())),
                "You need the following levels; " + this.getActor().getContext().getPlayerSaveData().getAvailableVoyages().get(voyageSlot).getContext().getMissingLevelString(this.getActor()));
    }

    @Override
    protected void onAction() {
        this.getActor().getSkillController().getSailing().getShipSlot(shipSlot).assignVoyageToShipSlot(this.getActor().getContext().getPlayerSaveData().getAvailableVoyages().get(voyageSlot));
        if (this.getActor().getSkillController().getSailing().getShipSlot(shipSlot).getVoyageDuration(voyageSlot) <= Sailing.CANCEL_TIME) {
            this.getActor().sendMessage("Your ship will set sail immediately.");
        } else {
            this.getActor().sendMessage("You have " + TimeUtils.getDurationString(Sailing.CANCEL_TIME) + " before your ship sets sail.");
        }
        this.getActor().save();
    }

    private boolean voyageAvailable(int voyage) {
        for (int slot = 0; slot < this.getActor().getContext().getPlayerSaveData().getShipSlot().length; slot++) {
            if ((this.getActor().getContext().getPlayerSaveData().getAvailableVoyages().get(voyage).getId() == this.getActor().getContext().getPlayerSaveData().getShipSlot()[slot][Sailing.VOYAGE_ID]))
                return false;
        }
        return true;
    }

    public StartVoyageAction(Player actor, int shipSlot, int voyageSlot) {
        super(actor,shipSlot,voyageSlot);
    }

    @Override
    protected void onEvent() {

    }
}
