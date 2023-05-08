package ethos.runehub.skill.support.sailing;

import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.event.skill.sailing.VoyageEvent;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.skill.support.sailing.voyage.VoyageDAO;

import java.time.Duration;
import java.util.logging.Logger;

import static ethos.runehub.skill.Skill.SKILL_RANDOM;

public class ShipController {

//    private static ShipController instance = null;
//
//    public static ShipController getInstance(Ship ship) {
//        if (instance == null)
//            instance = new ShipController();
//        instance.setShip(ship);
//        return instance;
//    }


    public void completeVoyage() {
        if (SKILL_RANDOM.nextFloat() <= this.getActiveVoyageSuccessRate()) {
            this.onSuccessfulVoyage();
        } else {
            this.onFailedVoyage();
        }
        this.updateShip();
    }

    private void onSuccessfulVoyage() {
        ship.setStatus(Ship.Status.RETURNED_SUCCESS.ordinal());
        ship.setVoyage(0);
    }

    private void onFailedVoyage() {
        ship.setStatus(Ship.Status.RETURN_FAILED.ordinal());
        ship.setVoyage(0);
    }

    public boolean isAvailable() {
        return ship.getStatus() == Ship.Status.AVAILABLE.ordinal();
    }

    private float getSeafaringSuccessChance(int seafaring) {
        final int total = this.calculatePlayerSeafaringTotal();
        float odds = (float) total / seafaring;
        Logger.getGlobal().fine("Player Seafaring: " + total);
        Logger.getGlobal().fine("Odds: " + odds);
        return Math.min(odds, 1.0f);
    }

    private float getCombatSuccessChance(int combat) {
        final int total = this.calculatePlayerCombatTotal();
        float odds = (float) total / combat;
        Logger.getGlobal().fine("Player Combat: " + total);
        Logger.getGlobal().fine("Odds: " + odds);
        return Math.min(odds, 1.0f);
    }

    private float getMoraleSuccessChance(int morale) {
        final int total = this.calculatePlayerMoraleTotal();
        float odds = (float) total / morale;
        Logger.getGlobal().fine("Player Morale: " + total);
        Logger.getGlobal().fine("Odds: " + odds);
        return Math.min(odds, 1.0f);
    }

    private int calculatePlayerSeafaringTotal() {
        return ship.getSeafaring();
    }

    private int calculatePlayerCombatTotal() {
        return ship.getCombat();
    }

    private int calculatePlayerMoraleTotal() {
        return ship.getMorale();
    }

    public float getVoyageSuccessRate(Voyage voyage) {
        return (
                this.getSeafaringSuccessChance(voyage.getSeafaring())
                        + this.getMoraleSuccessChance(voyage.getMorale())
                        + this.getCombatSuccessChance(voyage.getCombat())
        ) / 3;
    }

    public float getActiveVoyageSuccessRate() {
        Voyage voyage = VoyageDAO.getInstance().read(ship.getVoyage());
        return (
                this.getSeafaringSuccessChance(voyage.getSeafaring())
                        + this.getMoraleSuccessChance(voyage.getMorale())
                        + this.getCombatSuccessChance(voyage.getCombat())
        ) / 3;
    }

    public void assignVoyage(int voyageId) {
        ship.setVoyage(voyageId);
        ship.setStatus(Ship.Status.ON_VOYAGE.ordinal());
        saveData.getShipSlotTimestamp()[slot] = System.currentTimeMillis() + this.getVoyageDuration(voyageId);
        this.updateShip();
    }

        public int getStatus() {
        return ship.getStatus();
    }

    public long getVoyageDuration(int voyage) {
        final double NAUTICAL_MILES_TRAVELLED_PER_DAY = (Sailing.BASE_KNOTS_PER_HOUR + ship.getSpeed()) * Sailing.BASE_HOURS_SAILED_PER_DAY;
        final double DAYS_TO_COMPLETE_VOYAGE = VoyageDAO.getInstance().read(voyage).getDistance() / NAUTICAL_MILES_TRAVELLED_PER_DAY;
        final double VOYAGE_DURATION_MS = DAYS_TO_COMPLETE_VOYAGE * Duration.ofDays(1).toMillis();
        Logger.getGlobal().fine("Nautical Leagues Travelled Per Day: " + NAUTICAL_MILES_TRAVELLED_PER_DAY);
        Logger.getGlobal().fine("Days to Complete Voyage: " + DAYS_TO_COMPLETE_VOYAGE);
        Logger.getGlobal().fine("Voyage Duration MS: " + VOYAGE_DURATION_MS);
        Logger.getGlobal().fine("Voyage Duration Hours: " + Duration.ofMillis(Math.round(VOYAGE_DURATION_MS)).toHours());
        Logger.getGlobal().fine("Voyage Duration Minutes: " + Duration.ofMillis(Math.round(VOYAGE_DURATION_MS)).toMinutes());
        Logger.getGlobal().fine("Player Speed Boost: " + ship.getSpeed());
        return Math.round(VOYAGE_DURATION_MS);
    }

    private void updateShip() {
        saveData.getShipSlot()[slot] = ship.toBitArray();
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public ShipController(SailingSaveData saveData,int slot) {
        this.ship = Ship.fromBitArray( saveData.getShipSlot()[slot]);
        this.saveData = saveData;
    }

    private int slot;
    private Ship ship;
    private SailingSaveData saveData;

}
