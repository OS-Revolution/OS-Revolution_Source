package ethos.runehub.skill.support.sailing.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.skill.support.SupportSkillAction;
import ethos.runehub.skill.support.sailing.ship.Ship;
import ethos.runehub.skill.support.sailing.ship.ShipLoader;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import org.runehub.api.util.PreconditionUtils;

public class StartVoyage extends SupportSkillAction {

    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
//        this.getActor().getContext().getPlayerSaveData().setShipSlotOneVoyage(voyage.getId());
//        this.getActor().getContext().getPlayerSaveData().s(System.currentTimeMillis() + voyage.getDistance());
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void validateInventory() {
//        Preconditions.checkArgument(this.getActor().getContext().getPlayerSaveData().getActiveVoyage() > 0, "Your ship is already out on a voyage.");
    }

    @Override
    protected void validateLevelRequirements() {
//        Preconditions.checkArgument(this.getActor().getContext().getPlayerSaveData().getShip() > 0, "You do not own a ship.");
//        Preconditions.checkArgument(this.getActor().getContext().getPlayerSaveData().isCrewAvailable(), "You need a crew.");
    }

    @Override
    protected void validateItemRequirements() {
//        Ship ship = ShipLoader.getInstance().read(this.getActor().getContext().getPlayerSaveData().getShip());
//        Preconditions.checkArgument(ship.getSeafaring() >= voyage.getSeafaring(), "Your ship is not seafaring enough to take this voyage.");
//        Preconditions.checkArgument(ship.getCombat() >= voyage.getCombat(), "Your ship is not equipped well enough for this voyage.");
//        Preconditions.checkArgument(ship.getMorale() >= voyage.getMorale(), "Your crew lacks the morale to take this voyage.");
    }

    @Override
    protected void validateWorldRequirements() {

    }

    @Override
    protected void updateAnimation() {

    }

//    private int calculatePlayerSeafaringTotal() {
//        return this.getPlayerShip().getSeafaring() + this.getCrewLevelSeafaringBonus() + this.getSailingLevelSeafaringBonus();
//    }
//
//    private int calculatePlayerCombatTotal() {
//        return this.getPlayerShip().getCombat() + this.getCrewLevelCombatBonus() + this.getSailingLevelCombatBonus();
//    }
//
//    private int calculatePlayerMoraleTotal() {
//        return this.getPlayerShip().getMorale() + this.getCrewLevelMoraleBonus() + this.getSailingLevelMoraleBonus();
//    }
//
//    private Ship getPlayerShip(int slot) {
//        return ShipLoader.getInstance().read(this.getPlayer().getContext().getPlayerSaveData().getShipSlot()[slot][0]);
//    }
//
//    private double calculatePlayerSpeedBonus() {
//        return (this.getPlayerSpeedBonus())
//                + (this.getSailingLevelSpeedBonus())
//                + (this.getCrewLevelSpeedBonus())
//                + this.getPlayerShip().getSpeed();
//    }

    public StartVoyage(Player actor, Voyage voyage) {
        super(actor, actor.getSkillController().getSailing().getId(), 1);
        this.voyage = voyage;
    }

    private final Voyage voyage;
}
