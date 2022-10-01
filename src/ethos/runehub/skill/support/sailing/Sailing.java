package ethos.runehub.skill.support.sailing;

import ethos.model.players.Player;
import ethos.runehub.LootTableContainerUtils;
import ethos.runehub.TimeUtils;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.support.SupportSkill;
import ethos.runehub.skill.support.sailing.ship.ShipSlot;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.skill.support.sailing.voyage.VoyageContext;
import ethos.runehub.skill.support.sailing.voyage.VoyageDAO;
import ethos.runehub.ui.impl.sailing.SailingUI;
import ethos.runehub.world.wushanko.island.IslandLoader;
import org.runehub.api.io.load.impl.LootTableContainerLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.ContainerType;
import org.runehub.api.model.entity.item.loot.Loot;
import org.runehub.api.model.entity.item.loot.LootTableContainer;
import org.runehub.api.model.math.impl.IntegerRange;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class Sailing extends SupportSkill {

    public static final int BASE_MAX_DAILY_VOYAGES = 5;
    public static final int BASE_DAILY_REROLLS = 3;
    public static final int SHIP_ID = 0;
    public static final int VOYAGE_ID = 1;
    public static final int REGION_ID = 2;
    public static final int ISLAND_ID = 3;
    public static final int START_TIMESTAMP = 0;
    public static final int END_TIMESTAMP = 1;
    public static final double BASE_KNOTS_PER_HOUR = 7.3D;
    public static final int BASE_HOURS_SAILED_PER_DAY = 12;
    public static final double BASE_KNOTS_PER_MS = BASE_KNOTS_PER_HOUR / 3600000;
    public static final long CANCEL_TIME = Duration.ofMinutes(10).toMillis();

    public void sendVoyageSelectionUI() {
        this.getPlayer().sendUI(new SailingUI(this.getPlayer(), this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages()));
    }

    public boolean voyageAvailable(Voyage voyage) {
        for (int slot = 0; slot < this.getPlayer().getContext().getPlayerSaveData().getShipSlot().length; slot++) {
            if ((voyage.getId() == this.getPlayer().getContext().getPlayerSaveData().getShipSlot()[slot][Sailing.VOYAGE_ID]))
                return false;
        }
        return true;
    }

    public void startVoyage(Voyage voyage, int slot) {
        if (!this.getShipSlot(slot).hasShip()) { // no ship
            this.getPlayer().sendMessage("You have no $ship in this $slot.");
        } else if (this.getShipSlot(slot).onVoyage() && !this.getShipSlot(slot).isVoyageComplete()) { //this ship is on a voyage
            this.getPlayer().sendMessage("This ship is currently on a voyage.");
        } else if (!voyageAvailable(voyage)) { //a ship is on this voyage
            this.getPlayer().sendMessage("One of your ships is already on this voyage.");
        } else if (this.getShipSlot(slot).isVoyageComplete()) {
            this.getPlayer().sendMessage("You must claim your previous voyage first.");
        } else if (!voyage.getContext().playerHasAllLevels(this.getPlayer())) {
            this.getPlayer().sendMessage("You need the following levels; " + voyage.getContext().getMissingLevelString(this.getPlayer()));
        } else {
            this.getShipSlot(slot).assignVoyageToShipSlot(voyage);
            if (this.getShipSlot(slot).getVoyageDuration(voyage) <= CANCEL_TIME) {
                this.getPlayer().sendMessage("Your ship will set sail immediately.");
            } else {
                this.getPlayer().sendMessage("You have " + TimeUtils.getDurationString(CANCEL_TIME) + " before your ship sets sail.");
            }
            this.getPlayer().save();
        }
    }

    public void cancelVoyage(Voyage voyage, int slot) {
        if (!this.getShipSlot(slot).hasShip()) { // no ship
            this.getPlayer().sendMessage("You have no $ship in this $slot.");
        } else if (!this.getShipSlot(slot).onVoyage()) { //this ship is on a voyage
            this.getPlayer().sendMessage("This ship is currently at port.");
        } else if (!this.getShipSlot(slot).onVoyage(voyage)) { //a ship is on this voyage
            this.getPlayer().sendMessage("This ship is not on this voyage.");
        } else if ((System.currentTimeMillis() - this.getVoyageStartTime(slot)) > CANCEL_TIME
                || this.getShipSlot(slot).getVoyageDuration(voyage) <= CANCEL_TIME) {
            this.getPlayer().sendMessage("It's too late to cancel this voyage.");
        } else {
            this.getShipSlot(slot).resetShip();
            this.getPlayer().sendMessage("You cancel the voyage.");
            this.getPlayer().save();
        }
    }

    public void claimVoyage(Voyage voyage, int slot) {
        if (!this.getShipSlot(slot).hasShip()) { // no ship
            this.getPlayer().sendMessage("You have no $ship in this $slot.");
        } else if (!this.getShipSlot(slot).onVoyage()) { //this ship is on a voyage
            this.getPlayer().sendMessage("This ship is currently at port.");
        } else if (!this.getShipSlot(slot).onVoyage(voyage)) { //a ship is on this voyage
            this.getPlayer().sendMessage("This ship is not on this voyage.");
        } else if (!this.getShipSlot(slot).isVoyageComplete()) {
            this.getPlayer().sendMessage("Your ship has not completed this voyage yet.");
        } else {
            this.getShipSlot(slot).resetShip();
            if (SKILL_RANDOM.nextFloat() <= this.getShipSlot(slot).getVoyageSuccessRate(voyage)) {
                this.getPlayer().sendMessage("Your ship successfully completed it's voyage.");
                this.removeVoyage(voyage);
                this.updatePlayerVoyageStats(voyage);
                this.getShipSlot(slot).resetShip();
                this.getLootFromVoyage(voyage).forEach(loot -> this.getPlayer().getItems().addItemUnderAnyCircumstance((int) loot.getId(), (int) loot.getAmount()));
                this.getPlayer().getContext().getPlayerSaveData().setVoyagesCompleted(this.getPlayer().getContext().getPlayerSaveData().getVoyagesCompleted() + 1);
                this.getPlayer().getPA().addSkillXP((voyage.getRegion() == 0 ? 1 : voyage.getRegion()) * voyage.getDistance(), this.getId(), false);
                if (VoyageDAO.getInstance().getAllEntries().stream().anyMatch(voyage1 -> voyage1.getId() == voyage.getId())) {
                    this.getPlayer().getContext().getPlayerSaveData().getStoryVoyagesCompleted().add(voyage.getId());
                }
            } else {
                this.getPlayer().sendMessage("Your ship failed to complete it's voyage.");
                this.removeVoyage(voyage);
                this.getShipSlot(slot).resetShip();
                this.getPlayer().getContext().getPlayerSaveData().setVoyagesFailed(this.getPlayer().getContext().getPlayerSaveData().getVoyagesFailed() + 1);
            }
            this.getPlayer().save();
        }
    }

    public void rerollVoyage(Voyage voyage, int slot) {
        if (this.getPlayer().getContext().getPlayerSaveData().getVoyageRerolls() == 0) {
            this.getPlayer().sendMessage("You do not have any voyage re-rolls available.");
        } else if (this.getShipSlot(slot).onVoyage(voyage)
                || !this.voyageAvailable(voyage)) {
            this.getPlayer().sendMessage("You can't re-roll a voyage that you are on.");
        } else {
            int index = this.getVoyageIndex(voyage);
            this.getPlayer().getContext().getPlayerSaveData().setVoyageRerolls(this.getPlayer().getContext().getPlayerSaveData().getVoyageRerolls() - 1);
            this.getPlayer().sendMessage("You have #" + this.getPlayer().getContext().getPlayerSaveData().getVoyageRerolls() + " re-rolls available.");
            this.removeVoyage(voyage);
            System.out.println("index: " + index);
//            this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().remove(index);
            this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().add(index, generateVoyage());
            this.getPlayer().getContext().getPlayerSaveData().setVoyagesRerolled(this.getPlayer().getContext().getPlayerSaveData().getVoyagesRerolled() + 1);
            this.getPlayer().save();

        }
    }

    private int getVoyageIndex(Voyage voyage) {
        int index = 0;
        for (int i = 0; i < this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().size(); i++) {
            if (voyage.getId() == this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().get(i).getId())
                return i;
        }
        return index;
    }

    public List<Loot> getLootFromVoyage(Voyage voyage) {
        final List<Loot> lootList = new ArrayList<>();
        Arrays.stream(voyage.getContext().getContainerIds()).forEach(value -> {
            LootTableContainerUtils.getLootTableContainer(value, ContainerType.ITEM).ifPresent(lootTableContainer ->
                    lootTableContainer.roll(this.getPlayer().getAttributes().getMagicFind()).forEach(loot -> {
                        lootList.addAll(LootTableLoader.getInstance().read(loot.getId()).roll(this.getPlayer().getAttributes().getMagicFind()));
                    }));
        });
        return lootList;
    }

    private int getSpecialVoyageChance() {
        return Math.round(this.getScore() * 0.03f);
    }

    public int getScore() {
        return
                (int) ((this.getPlayer().getContext().getPlayerSaveData().getLeaguesTravelled() / 500)
                        + (this.getPlayer().getContext().getPlayerSaveData().getStoryVoyagesCompleted().size() * 5));
    }

    public Voyage generateVoyage() {
        if (Skill.SKILL_RANDOM.nextInt(500 - this.getSpecialVoyageChance()) == 0) {
            final Voyage voyage = VoyageDAO.getInstance().getAllEntries().get(Skill.SKILL_RANDOM.nextInt(VoyageDAO.getInstance().getAllEntries().size() - 1));
            if (!this.getPlayer().getContext().getPlayerSaveData().getStoryVoyagesCompleted().contains(voyage.getId())
                    && this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().stream().noneMatch(v -> v.getId() == voyage.getId())) {
                return voyage;
            }
        } else {
            final int id = new IntegerRange(1, 500000).getRandomValue();
            final int region = this.getScaledVoyageRegion();
            final int island = SailingUtils.getIslandFromRegion(region);
            if (this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().stream().noneMatch(voyage -> voyage.getIsland() == island))
                return new Voyage(
                        id,
                        IslandLoader.getInstance().read(island).getName(),
                        SailingUtils.getStatRangeBasedOnRegion(region),
                        SailingUtils.getStatRangeBasedOnRegion(region),
                        SailingUtils.getStatRangeBasedOnRegion(region),
                        SailingUtils.getDistanceFromRegion(region),
                        region,
                        island,
                        false,
                        false,
                        new VoyageContext(
                                new int[]{
                                        0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0,
                                        0, 0, 0, 0
                                },
                                new int[]{SailingUtils.getLootTableContainerIdForRegion(region), SailingUtils.getLootTableContainerIdForIsland(island)}
                        )
                );
        }
        return generateVoyage();
    }

    public int getBestAvailableRegion() {
        final long statTotals = this.getPlayer().getContext().getPlayerSaveData().getLeaguesTravelled();
        if (statTotals <= 500 && statTotals > 0) {
            return 0;
        } else if (statTotals <= 3500 && statTotals > 500) {
            return 1;
        } else if (statTotals <= 10000 && statTotals > 3500) {
            return 2;
        } else if (statTotals <= 31000 && statTotals > 10000) {
            return 3;
        } else if (statTotals <= 75000 && statTotals > 31000) {
            return 4;
        } else if (statTotals <= 110000 && statTotals > 75000) {
            return 5;
        } else if (statTotals <= 180000 && statTotals > 110000) {
            return 6;
        } else {
            return 8;
        }
    }

    private int getScaledVoyageRegion() {
//        final int statTotals = this.calculatePlayerCombatTotal() + this.calculatePlayerMoraleTotal() + this.calculatePlayerSeafaringTotal();
        final long statTotals = this.getPlayer().getContext().getPlayerSaveData().getLeaguesTravelled();
        if (this.getPlayer().getContext().getPlayerSaveData().getPreferredRegion() == -1) {
            if (statTotals <= 500 && statTotals > 0) {
                return 0;
            } else if (statTotals <= 3500 && statTotals > 500) {
                return new IntegerRange(0, 1).getRandomValue();
            } else if (statTotals <= 10000 && statTotals > 3500) {
                return new IntegerRange(0, 2).getRandomValue();
            } else if (statTotals <= 31000 && statTotals > 10000) {
                return new IntegerRange(1, 3).getRandomValue();
            } else if (statTotals <= 75000 && statTotals > 31000) {
                return new IntegerRange(2, 4).getRandomValue();
            } else if (statTotals <= 110000 && statTotals > 75000) {
                return new IntegerRange(3, 5).getRandomValue();
            } else if (statTotals <= 180000 && statTotals > 110000) {
                return new IntegerRange(4, 6).getRandomValue();
            } else {
                return new IntegerRange(5, 8).getRandomValue();
            }
        }
        return this.getPlayer().getContext().getPlayerSaveData().getPreferredRegion();
    }

    private void removeVoyage(Voyage voyage) {
        this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().remove(voyage);
    }

    private void updatePlayerVoyageStats(Voyage voyage) {
        this.getPlayer().getContext().getPlayerSaveData().setLeaguesTravelled(this.getPlayer().getContext().getPlayerSaveData().getLeaguesTravelled() + voyage.getDistance());
        if (this.getPlayer().getContext().getPlayerSaveData().getIslandsVisited() == null) {
            this.getPlayer().getContext().getPlayerSaveData().setIslandsVisited(new ArrayList<>());
            this.getPlayer().getContext().getPlayerSaveData().getIslandsVisited().add(voyage.getIsland());
        } else if (!this.getPlayer().getContext().getPlayerSaveData().getIslandsVisited().contains(voyage.getIsland())) {
            this.getPlayer().getContext().getPlayerSaveData().getIslandsVisited().add(voyage.getIsland());
        }
        if (this.getPlayer().getContext().getPlayerSaveData().getRegionsVisited() == null) {
            this.getPlayer().getContext().getPlayerSaveData().setRegionsVisited(new ArrayList<>());
            this.getPlayer().getContext().getPlayerSaveData().getRegionsVisited().add(voyage.getRegion());
        } else if (!this.getPlayer().getContext().getPlayerSaveData().getRegionsVisited().contains(voyage.getRegion())) {
            this.getPlayer().getContext().getPlayerSaveData().getRegionsVisited().add(voyage.getRegion());
        }
    }

    public void generateDailyVoyages() {
        final List<Voyage> save = new ArrayList<>();
        for (int i = 0; i < this.getPlayer().getContext().getPlayerSaveData().getShipSlot().length; i++) {
            for (Voyage voyage : this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages()) {
                if (this.getPlayer().getContext().getPlayerSaveData().getShipSlot()[i][VOYAGE_ID] == voyage.getId()) {
                    save.add(voyage);
                }
            }
        }
        this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().clear();
        this.getPlayer().getContext().getPlayerSaveData().setAvailableVoyages(save);
        if (this.getPlayer().getContext().getPlayerSaveData().isDailyAvailable()) {
            int newVoyages = (5 - this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().size());
            for (int i = 0; i < newVoyages; i++) {
                this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().add(generateVoyage());
            }
            this.getPlayer().save();
        }
    }

    public long getVoyageReturnTime(int slot) {
        return this.getPlayer().getContext().getPlayerSaveData().getVoyageTimestamp()[slot][END_TIMESTAMP];
    }

    public long getVoyageStartTime(int slot) {
        return this.getPlayer().getContext().getPlayerSaveData().getVoyageTimestamp()[slot][START_TIMESTAMP];
    }

    public ShipSlot getShipSlot(int slot) {
        if (shipSlots[slot] == null)
            shipSlots[slot] = new ShipSlot(slot, this.getPlayer().getContext().getPlayerSaveData(), this.getPlayer().getSkillController());
        return shipSlots[slot];
    }

    @Override
    public int getId() {
        return 23;
    }

    public Sailing(Player player) {
        super(player);
        this.shipSlots = new ShipSlot[3];
    }

    private final ShipSlot[] shipSlots;
}
