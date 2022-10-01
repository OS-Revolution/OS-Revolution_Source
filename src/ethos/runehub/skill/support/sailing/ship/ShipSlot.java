package ethos.runehub.skill.support.sailing.ship;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.entity.player.PlayerSaveData;
import ethos.runehub.skill.SkillController;
import ethos.runehub.skill.support.sailing.Sailing;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.ui.impl.sailing.SailingUI;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.logging.Logger;

public class ShipSlot {

    public boolean onVoyage() {
        return saveData.getShipSlot()[slot][Sailing.VOYAGE_ID] > 1;
    }

    public boolean onVoyage(Voyage voyage) {
        return saveData.getShipSlot()[slot][Sailing.VOYAGE_ID] == voyage.getId();
    }

    public boolean onVoyage(int voyage) {
        return saveData.getShipSlot()[slot][Sailing.VOYAGE_ID] == saveData.getAvailableVoyages().get(voyage).getId();
    }

    public boolean hasShip() {
        return saveData.getShipSlot()[slot][Sailing.SHIP_ID] > 0;
    }

    public boolean isVoyageComplete() {
        return saveData.getVoyageTimestamp()[slot][Sailing.END_TIMESTAMP] != 0 && saveData.getVoyageTimestamp()[slot][Sailing.END_TIMESTAMP] <= System.currentTimeMillis();
    }

    public float getSeafaringSuccessChance(int seafaring) {
        final int total = this.calculatePlayerSeafaringTotal();
        float odds = (float) total / seafaring;
        Logger.getGlobal().fine("Player Seafaring: " + total);
        Logger.getGlobal().fine("Odds: " + odds);
        return Math.min(odds, 1.0f);
    }

    public float getCombatSuccessChance(int combat) {
        final int total = this.calculatePlayerCombatTotal();
        float odds = (float) total / combat;
        Logger.getGlobal().fine("Player Combat: " + total);
        Logger.getGlobal().fine("Odds: " + odds);
        return Math.min(odds, 1.0f);
    }

    public float getMoraleSuccessChance(int morale) {
        final int total = this.calculatePlayerMoraleTotal();
        float odds = (float) total / morale;
        Logger.getGlobal().fine("Player Morale: " + total);
        Logger.getGlobal().fine("Odds: " + odds);
        return Math.min(odds, 1.0f);
    }


    public int calculatePlayerSeafaringTotal() {
        return this.getShip().getSeafaring() + this.getCrewLevelSeafaringBonus() + this.getSailingLevelSeafaringBonus() + this.getSailingScoreSeafaringBonus();
    }

    public int calculatePlayerCombatTotal() {
        return this.getShip().getCombat() + this.getCrewLevelCombatBonus() + this.getSailingLevelCombatBonus() + this.getSailingScoreCombatBonus();
    }

    public int calculatePlayerMoraleTotal() {
        return this.getShip().getMorale() + this.getCrewLevelMoraleBonus() + this.getSailingLevelMoraleBonus() + this.getSailingScoreMoraleBonus();
    }

    public long getVoyageDuration(Voyage voyage) {
        final double NAUTICAL_MILES_TRAVELLED_PER_DAY = (Sailing.BASE_KNOTS_PER_HOUR + this.calculatePlayerSpeedBonus()) * Sailing.BASE_HOURS_SAILED_PER_DAY;
        final double DAYS_TO_COMPLETE_VOYAGE = voyage.getDistance() / NAUTICAL_MILES_TRAVELLED_PER_DAY;
        final double VOYAGE_DURATION_MS = DAYS_TO_COMPLETE_VOYAGE * Duration.ofDays(1).toMillis();
        Logger.getGlobal().fine("Nautical Leagues Travelled Per Day: " + NAUTICAL_MILES_TRAVELLED_PER_DAY);
        Logger.getGlobal().fine("Days to Complete Voyage: " + DAYS_TO_COMPLETE_VOYAGE);
        Logger.getGlobal().fine("Voyage Duration MS: " + VOYAGE_DURATION_MS);
        Logger.getGlobal().fine("Voyage Duration Hours: " + Duration.ofMillis(Math.round(VOYAGE_DURATION_MS)).toHours());
        Logger.getGlobal().fine("Voyage Duration Minutes: " + Duration.ofMillis(Math.round(VOYAGE_DURATION_MS)).toMinutes());
        Logger.getGlobal().fine("Player Speed Boost: " + this.getPlayerSpeedBonus());
        return Math.round(VOYAGE_DURATION_MS);
    }

    public long getVoyageDuration(int voyage) {
        final double NAUTICAL_MILES_TRAVELLED_PER_DAY = (Sailing.BASE_KNOTS_PER_HOUR + this.calculatePlayerSpeedBonus()) * Sailing.BASE_HOURS_SAILED_PER_DAY;
        final double DAYS_TO_COMPLETE_VOYAGE = saveData.getAvailableVoyages().get(voyage).getDistance() / NAUTICAL_MILES_TRAVELLED_PER_DAY;
        final double VOYAGE_DURATION_MS = DAYS_TO_COMPLETE_VOYAGE * Duration.ofDays(1).toMillis();
        Logger.getGlobal().fine("Nautical Leagues Travelled Per Day: " + NAUTICAL_MILES_TRAVELLED_PER_DAY);
        Logger.getGlobal().fine("Days to Complete Voyage: " + DAYS_TO_COMPLETE_VOYAGE);
        Logger.getGlobal().fine("Voyage Duration MS: " + VOYAGE_DURATION_MS);
        Logger.getGlobal().fine("Voyage Duration Hours: " + Duration.ofMillis(Math.round(VOYAGE_DURATION_MS)).toHours());
        Logger.getGlobal().fine("Voyage Duration Minutes: " + Duration.ofMillis(Math.round(VOYAGE_DURATION_MS)).toMinutes());
        Logger.getGlobal().fine("Player Speed Boost: " + this.getPlayerSpeedBonus());
        return Math.round(VOYAGE_DURATION_MS);
    }

    public float getVoyageSuccessRate(Voyage voyage) {
        return (
                this.getSeafaringSuccessChance(voyage.getSeafaring())
                        + this.getMoraleSuccessChance(voyage.getMorale())
                        + this.getCombatSuccessChance(voyage.getCombat())
        ) / 3;
    }

    public float getVoyageSuccessRate(int voyage) {
        return (
                this.getSeafaringSuccessChance(saveData.getAvailableVoyages().get(voyage).getSeafaring())
                        + this.getMoraleSuccessChance(saveData.getAvailableVoyages().get(voyage).getMorale())
                        + this.getCombatSuccessChance(saveData.getAvailableVoyages().get(voyage).getCombat())
        ) / 3;
    }

    public void assignVoyageToShipSlot(Voyage voyage) {
        saveData.updateShipSlot(slot, Sailing.VOYAGE_ID, voyage.getId());
        saveData.updateShipSlot(slot, Sailing.REGION_ID, voyage.getRegion());
        saveData.updateShipSlot(slot, Sailing.ISLAND_ID, voyage.getIsland());

        saveData.getVoyageTimestamp()[slot][Sailing.START_TIMESTAMP] = (System.currentTimeMillis());
        saveData.getVoyageTimestamp()[slot][Sailing.END_TIMESTAMP] = (System.currentTimeMillis() + this.getVoyageDuration(voyage));
    }

    public void resetShip() {
        saveData.updateShipSlot(slot, Sailing.VOYAGE_ID, 0);
        saveData.updateShipSlot(slot, Sailing.REGION_ID, 0);
        saveData.updateShipSlot(slot, Sailing.ISLAND_ID, 0);

        saveData.getVoyageTimestamp()[slot][Sailing.START_TIMESTAMP] = (0L);
        saveData.getVoyageTimestamp()[slot][Sailing.END_TIMESTAMP] = (0L);
    }

    public String getSuccessRatePrint(float value) {
        final DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return decimalFormat.format(value * 100) + "%";
    }


    private double calculatePlayerSpeedBonus() {
        return (this.getPlayerSpeedBonus())
                + (this.getSailingLevelSpeedBonus())
                + (this.getCrewLevelSpeedBonus())
                + this.getShip().getSpeed()
                + this.getSailingScoreSpeedBonus();
    }

    private int getSailingLevelMoraleBonus() {
        return skillController.getLevel(skillController.getSailing().getId()) * 3;
    }

    private int getCrewLevelMoraleBonus() {
        return saveData.getCrewLevel() * 5;
    }

    private int getSailingLevelCombatBonus() {
        return skillController.getLevel(skillController.getSailing().getId()) * 3;
    }

    private int getCrewLevelCombatBonus() {
        return saveData.getCrewLevel() * 5;
    }

    private int getSailingLevelSeafaringBonus() {
        return skillController.getLevel(skillController.getSailing().getId()) * 3;
    }

    private int getCrewLevelSeafaringBonus() {
        return saveData.getCrewLevel() * 5;
    }

    private int getSailingScoreSeafaringBonus() {
        return Math.round(skillController.getSailing().getScore() * 0.03f);
    }

    private int getSailingScoreCombatBonus() {
        return Math.round(skillController.getSailing().getScore() * 0.03f);
    }

    private int getSailingScoreMoraleBonus() {
        return Math.round(skillController.getSailing().getScore() * 0.03f);
    }

    private double getSailingLevelSpeedBonus() {
        return skillController.getLevel(skillController.getSailing().getId()) * 0.02D;
    }

    private double getCrewLevelSpeedBonus() {
        return saveData.getCrewLevel() * 0.05D;
    }

    private double getSailingScoreSpeedBonus() {
        return skillController.getSailing().getScore() * 0.05D;
    }

    private double getPlayerSpeedBonus() {
        return saveData.getVoyageSpeedLevel() * 0.03D;
    }

    private Ship getShip() {
        return ShipLoader.getInstance().read(saveData.getShipSlot()[slot][Sailing.SHIP_ID]);
    }

    public int getSlot() {
        return slot;
    }

    public ShipSlot(int slot, PlayerSaveData saveData, SkillController skillController) {
        this.slot = slot;
        this.saveData = saveData;
        this.skillController = skillController;
    }

    private final SkillController skillController;
    private final int slot;
    private final PlayerSaveData saveData;
}
