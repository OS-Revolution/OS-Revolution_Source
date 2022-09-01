package ethos.runehub.skill.support.sailing.voyage;

import ethos.model.players.Player;
import ethos.runehub.LootTableContainerUtils;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.support.sailing.Sailing;
import ethos.runehub.skill.support.sailing.SailingUtils;
import ethos.runehub.skill.support.sailing.ship.Ship;
import ethos.runehub.skill.support.sailing.ship.ShipLoader;
import ethos.runehub.world.wushanko.island.IslandLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.ContainerType;
import org.runehub.api.model.entity.item.loot.Loot;
import org.runehub.api.model.entity.item.loot.LootTableContainer;
import org.runehub.api.model.math.impl.IntegerRange;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class VoyageController {

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

    public float getVoyageSuccessRate(Voyage voyage) {
        return (
                this.getSeafaringSuccessChance(voyage.getSeafaring())
                        + this.getMoraleSuccessChance(voyage.getMorale())
                        + this.getCombatSuccessChance(voyage.getCombat())
        ) / 3;
    }

    public void startVoyage() {
        this.assignVoyageToShipSlot();
    }

    public boolean completeVoyage() {
        float successRoll = Skill.SKILL_RANDOM.nextFloat();
        return isSelectedShipOnSelectedVoyage() && isShipOnVoyage() && (successRoll <= this.getVoyageSuccessRate(voyage));
    }

    public boolean isSelectedShipOnSelectedVoyage() {
        return player.getContext().getPlayerSaveData().getShipSlot()[shipSlot][Sailing.VOYAGE_ID] == voyage.getId()
                && player.getContext().getPlayerSaveData().getShipSlot()[shipSlot][Sailing.REGION_ID] == voyage.getRegion()
                && player.getContext().getPlayerSaveData().getShipSlot()[shipSlot][Sailing.ISLAND_ID] == voyage.getIsland();
    }

    public void cancelVoyage() {
        this.resetShip();
        player.save();
    }

    public Voyage generateVoyage() {
        final int id = new IntegerRange(1, 10000).getRandomValue();
        final int region = this.getScaledVoyageRegion();
        final int island = SailingUtils.getIslandFromRegion(region);

        if (player.getContext().getPlayerSaveData().getAvailableVoyages().stream().noneMatch(voyage -> voyage.getIsland() == island))
            return new Voyage(
                    id,
                    IslandLoader.getInstance().read(island).getName(),
                    SailingUtils.getStatRangeBasedOnRegion(region),
                    SailingUtils.getStatRangeBasedOnRegion(region),
                    SailingUtils.getStatRangeBasedOnRegion(region),
                    SailingUtils.getDistanceFromRegion(region),
                    region,
                    island
            );
        return generateVoyage();
    }

    public float getSeafaringSuccessChance(int seafaring) {
        float odds = (float) this.calculatePlayerSeafaringTotal() / seafaring;
        Logger.getGlobal().fine("Player Seafaring: " + this.calculatePlayerSeafaringTotal());
        Logger.getGlobal().fine("Odds: " + odds);
        return Math.min(odds, 1.0f);
    }

    public float getCombatSuccessChance(int combat) {
        float odds = (float) this.calculatePlayerCombatTotal() / combat;
        Logger.getGlobal().fine("Player Combat: " + this.calculatePlayerCombatTotal());
        Logger.getGlobal().fine("Odds: " + odds);
        return Math.min(odds, 1.0f);
    }

    public float getMoraleSuccessChance(int morale) {
        float odds = (float) this.calculatePlayerMoraleTotal() / morale;
        Logger.getGlobal().fine("Player Morale: " + this.calculatePlayerMoraleTotal());
        Logger.getGlobal().fine("Odds: " + odds);
        return Math.min(odds, 1.0f);
    }


    public int calculatePlayerSeafaringTotal() {
        return this.getShip().getSeafaring() + this.getCrewLevelSeafaringBonus() + this.getSailingLevelSeafaringBonus();
    }

    public int calculatePlayerCombatTotal() {
        return this.getShip().getCombat() + this.getCrewLevelCombatBonus() + this.getSailingLevelCombatBonus();
    }

    public int calculatePlayerMoraleTotal() {
        return this.getShip().getMorale() + this.getCrewLevelMoraleBonus() + this.getSailingLevelMoraleBonus();
    }
    
    public List<Loot> getLootFromVoyage() {
        final Optional<LootTableContainer> lootTableContainer = LootTableContainerUtils.getLootTableContainer(voyage.getIsland(), ContainerType.ITEM);
        final List<Loot> lootList = new ArrayList<>();
        if (lootTableContainer.isPresent()) {
            lootTableContainer.get().roll(player.getAttributes().getMagicFind()).forEach(table -> {
                lootList.addAll(LootTableLoader.getInstance().read(table.getId()).roll(player.getAttributes().getMagicFind()));
            });
        } else if (LootTableContainerUtils.getLootTableContainer(voyage.getRegion() + 50000, ContainerType.ITEM).isPresent()) {
            LootTableContainerUtils.getLootTableContainer(voyage.getRegion() + 50000, ContainerType.ITEM).get().roll(player.getAttributes().getMagicFind()).forEach(table -> {
                lootList.addAll(LootTableLoader.getInstance().read(table.getId()).roll(player.getAttributes().getMagicFind()));
            });
        }
        return lootList;
    }

    public void removeVoyage() {
        player.getContext().getPlayerSaveData().getAvailableVoyages().remove(voyage);
    }

    public void updatePlayerVoyageStats() {
        player.getContext().getPlayerSaveData().setLeaguesTravelled(player.getContext().getPlayerSaveData().getLeaguesTravelled() + voyage.getDistance());
        player.getContext().getPlayerSaveData().getIslandsVisited().add(voyage.getIsland());
        if (player.getContext().getPlayerSaveData().getIslandsVisited() == null) {
            player.getContext().getPlayerSaveData().setIslandsVisited(new ArrayList<>());
            player.getContext().getPlayerSaveData().getIslandsVisited().add(voyage.getIsland());
        } else if (!player.getContext().getPlayerSaveData().getIslandsVisited().contains(voyage.getIsland())) {
            player.getContext().getPlayerSaveData().getIslandsVisited().add(voyage.getIsland());
        }
        if (player.getContext().getPlayerSaveData().getRegionsVisited() == null) {
            player.getContext().getPlayerSaveData().setRegionsVisited(new ArrayList<>());
            player.getContext().getPlayerSaveData().getRegionsVisited().add(voyage.getRegion());
        } else if (!player.getContext().getPlayerSaveData().getRegionsVisited().contains(voyage.getRegion())) {
            player.getContext().getPlayerSaveData().getRegionsVisited().add(voyage.getRegion());
        }
    }

    public boolean isShipOnVoyage() {
        return player.getContext().getPlayerSaveData().getShipSlot()[shipSlot][Sailing.VOYAGE_ID] > 0;
    }

    private void assignVoyageToShipSlot() {
        player.getContext().getPlayerSaveData().getShipSlot()[shipSlot][Sailing.VOYAGE_ID] = voyage.getId();
        player.getContext().getPlayerSaveData().getShipSlot()[shipSlot][Sailing.REGION_ID] = voyage.getRegion();
        player.getContext().getPlayerSaveData().getShipSlot()[shipSlot][Sailing.ISLAND_ID] = voyage.getIsland();
//                player.getContext().getPlayerSaveData().setShipSlotOneEndTimestamp(System.currentTimeMillis() + this.getVoyageDuration(voyage));
    }

    private void resetShip() {
        player.getContext().getPlayerSaveData().getShipSlot()[shipSlot][Sailing.VOYAGE_ID] = 0;
        player.getContext().getPlayerSaveData().getShipSlot()[shipSlot][Sailing.REGION_ID] = 0;
        player.getContext().getPlayerSaveData().getShipSlot()[shipSlot][Sailing.ISLAND_ID] = 0;
    }

    private int getScaledVoyageRegion() {
        final int statTotals = this.calculatePlayerCombatTotal() + this.calculatePlayerMoraleTotal() + this.calculatePlayerSeafaringTotal();
        if (statTotals <= 12 && statTotals > 0) {
            return 0;
        } else if (statTotals <= 25 && statTotals > 12) {
            return new IntegerRange(0, 2).getRandomValue();
        } else if (statTotals <= 50 && statTotals > 25) {
            return new IntegerRange(3, 5).getRandomValue();
        }
        return new IntegerRange(5, 7).getRandomValue();
    }

    private double calculatePlayerSpeedBonus() {
        return (this.getPlayerSpeedBonus())
                + (this.getSailingLevelSpeedBonus())
                + (this.getCrewLevelSpeedBonus())
                + this.getShip().getSpeed();
    }

    private int getSailingLevelMoraleBonus() {
        return player.getSkillController().getLevel(player.getSkillController().getSailing().getId()) * 3;
    }

    private int getCrewLevelMoraleBonus() {
        return player.getContext().getPlayerSaveData().getCrewLevel() * 5;
    }

    private int getSailingLevelCombatBonus() {
        return player.getSkillController().getLevel(player.getSkillController().getSailing().getId()) * 3;
    }

    private int getCrewLevelCombatBonus() {
        return player.getContext().getPlayerSaveData().getCrewLevel() * 5;
    }

    private int getSailingLevelSeafaringBonus() {
        return player.getSkillController().getLevel(player.getSkillController().getSailing().getId()) * 3;
    }

    private int getCrewLevelSeafaringBonus() {
        return player.getContext().getPlayerSaveData().getCrewLevel() * 5;
    }

    private double getSailingLevelSpeedBonus() {
        return player.getSkillController().getLevel(player.getSkillController().getSailing().getId()) * 0.02D;
    }

    private double getCrewLevelSpeedBonus() {
        return player.getContext().getPlayerSaveData().getCrewLevel() * 0.05D;
    }

    private double getPlayerSpeedBonus() {
        return player.getContext().getPlayerSaveData().getVoyageSpeedLevel() * 0.03D;
    }
    
    private Ship getShip() {
        return ShipLoader.getInstance().read(player.getContext().getPlayerSaveData().getShipSlot()[shipSlot][Sailing.SHIP_ID]);
    }

    public void setShipSlot(int shipSlot) {
        this.shipSlot = shipSlot;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public VoyageController(Player player) {
        this.player = player;
    }
    
    private final Player player;
    private Voyage voyage;
    private int shipSlot;
}
