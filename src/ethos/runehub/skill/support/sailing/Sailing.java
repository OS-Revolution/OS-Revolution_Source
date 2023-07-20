package ethos.runehub.skill.support.sailing;

import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.LootTableContainerUtils;
import ethos.runehub.RunehubUtils;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.support.SupportSkill;
import ethos.runehub.skill.support.sailing.event.VoyageEvent;
import ethos.runehub.skill.support.sailing.io.SailingMetric;
import ethos.runehub.skill.support.sailing.io.SailingMetricDAO;
import ethos.runehub.skill.support.sailing.ship.DefaultShipwrightImpl;
import ethos.runehub.skill.support.sailing.ship.Ship;
import ethos.runehub.skill.support.sailing.ship.impl.SeafaringShipwright;
import ethos.runehub.skill.support.sailing.ui.ShipwrightUI;
import ethos.runehub.skill.support.sailing.ui.VoyageUI;
import ethos.runehub.skill.support.sailing.voyage.TradeGood;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.world.wushanko.island.IslandLoader;
import ethos.runehub.world.wushanko.region.IslandRegionLoader;
import ethos.util.Misc;
import org.apache.poi.hssf.record.formula.functions.T;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.ItemContext;
import org.runehub.api.model.entity.item.loot.ContainerType;
import org.runehub.api.model.entity.item.loot.Loot;
import org.runehub.api.model.entity.item.loot.LootTable;
import org.runehub.api.model.entity.item.loot.LootTableContainer;
import org.runehub.api.model.math.impl.IntegerRange;
import org.runehub.api.util.IDManager;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.*;

public class Sailing extends SupportSkill {

    public static final double BASE_KNOTS_PER_HOUR = 7.3D;
    public static final int BASE_HOURS_SAILED_PER_DAY = 12;
    public static final double BASE_KNOTS_PER_MS = BASE_KNOTS_PER_HOUR / 3600000;

    public static void sendTestUI(Player player) {
//        player.getSkillController().getSailing().generateDailyVoyages();
//        DefaultShipwrightImpl defaultSeafaringShipwright = new SeafaringShipwright(player);
//
//        System.out.println("Old Seafaring Tier: " + defaultSeafaringShipwright.getSeafaringTier(player.getSkillController().getSailing().getShip(0)));
//        defaultSeafaringShipwright.upgrade(0);
//        System.out.println("New Seafaring Tier: " + defaultSeafaringShipwright.getSeafaringTier(player.getSkillController().getSailing().getShip(0)));
    }

    public double getOutgoingCargoWeight() {
        double weight = Arrays.stream(this.getPlayer().getAttributes().getSelectedSellOffers())
                .filter(Objects::nonNull)
                .mapToDouble(item -> ItemIdContextLoader.getInstance().read(TradeGood.fromLong(item).getItemId()).getWeight() * TradeGood.fromLong(item).getStock())
                .sum();
        return weight;
    }

    public double getIncomingCargoWeight() {
        double weight = Arrays.stream(this.getPlayer().getAttributes().getSelectedBuyOffers())
                .filter(Objects::nonNull)
                .mapToDouble(item -> ItemIdContextLoader.getInstance().read(TradeGood.fromLong(item).getItemId()).getWeight() * TradeGood.fromLong(item).getStock())
                .sum();
        return weight;
    }

    private String getItemString(long[] tradeGoods) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < tradeGoods.length; i++) {
            long value = tradeGoods[i];
            if (value != 0) {
                ItemContext context = ItemIdContextLoader.getInstance().read(TradeGood.fromLong(value).getItemId());
                if (i == 0) {
                    stringBuilder.append("[" + context.getName() + ", ");
                } else if (i == tradeGoods.length - 1) {
                    stringBuilder.append(context.getName() + "]");
                } else {
                    stringBuilder.append(context.getName() + ", ");
                }
            }
        }
        return stringBuilder.toString();
    }

    private int getVoyageIndex(Voyage voyage) {
        int voyageIndex = 0;
        for (int i = 0; i < this.getPlayer().getSailingSaveData().getAvailabeVoyages().length; i++) {
            if (voyage.toLong() == this.getPlayer().getSailingSaveData().getAvailabeVoyages()[i]) {
                voyageIndex = i;
                break;
            }
        }
        return voyageIndex;
    }

    public void addCoinsToCoffer(String value) {
        try {
            int amount = Integer.parseInt(value);
            if (this.getPlayer().getItems().playerHasItem(995, amount)) {
                this.getPlayer().getItems().deleteItem2(995, amount);
                this.getPlayer().getSailingSaveData().setCoffer(this.getPlayer().getSailingSaveData().getCoffer() + amount);
                this.getPlayer().sendMessage("You add #" + amount + " @bla@coins to your coffer. You now have #" +
                        this.getPlayer().getSailingSaveData().getCoffer());
            } else {
                this.getPlayer().sendMessage("You do not have that many coins.");
            }
        } catch (Exception e) {
            this.getPlayer().sendMessage("Please enter a valid number.");
        }
    }

    public void collectVoyageTradeGoods(int slot) {
        Ship ship = this.getShip(slot);
        Voyage voyage = Voyage.fromLong(this.getPlayer().getSailingSaveData().getActiveVoyages()[slot]);
        int voyageIndex = this.getVoyageIndex(voyage);

        if (ship.getStatus() == Ship.Status.RETURN_SUCCESS.ordinal()) {
            ship.setStatus(Ship.Status.AVAILABLE.ordinal());

            this.getPlayer().getSailingSaveData().setAvailableVoyage(voyageIndex,0);
            this.getPlayer().getSailingSaveData().setActiveVoyage(slot, 0);
            this.getPlayer().getSailingSaveData().setShipSlot(slot, ship.toLong());
            this.transferBuyingCargoToStockpile(slot);
            this.transferSellingCargoToStockpile(slot, voyageIndex);
            this.getPlayer().save();
            this.getPlayer().sendMessage("You unload your ship's goods.");
        } else if (ship.getStatus() == Ship.Status.AVAILABLE.ordinal()) {
            this.getPlayer().sendMessage("This ship is not on a voyage.");
        } else if (ship.getStatus() == Ship.Status.RETURN_FAILED.ordinal()) {
            ship.setStatus(Ship.Status.AVAILABLE.ordinal());

            this.getPlayer().getSailingSaveData().setAvailableVoyage(voyageIndex,0);
            this.getPlayer().getSailingSaveData().setActiveVoyage(slot, 0);
            this.getPlayer().getSailingSaveData().setShipSlot(slot, ship.toLong());
            this.clearCargo(slot);
            this.getPlayer().save();
            this.getPlayer().sendMessage("This ship failed it's voyage and the cargo was lost at sea.");
        } else {
            this.getPlayer().sendMessage("This ship is currently sailing.");
        }
    }

    public void completeVoyage(int slot) {
        Ship ship = this.getShip(slot);
        Voyage voyage = Voyage.fromLong(this.getPlayer().getSailingSaveData().getActiveVoyages()[slot]);

        if (SKILL_RANDOM.nextFloat() <= ship.getVoyageSuccessRate(voyage)) {
            ship.setStatus(Ship.Status.RETURN_SUCCESS.ordinal());
        } else {
            ship.setStatus(Ship.Status.RETURN_FAILED.ordinal());
        }
        this.getPlayer().getSkillController().addXP(this.getId(),getVoyageXP(voyage,slot));
        this.getPlayer().getSailingSaveData().setLeaguesTravelled(
                this.getPlayer().getSailingSaveData().getLeaguesTravelled() + voyage.getDistance()
        );
        this.getPlayer().getSailingSaveData().setShipSlot(slot, ship.toLong());
        this.getPlayer().save();
    }

    public int getVoyageXP(Voyage voyage, int slot) {
        Ship ship = this.getShip(slot);
        int baseXP = (voyage.getRegion() == 0 ? 1 : voyage.getRegion()) * voyage.getDistance();
        double outgoingWeightBonus = (int) (baseXP * (this.getOutgoingCargoWeight() / (double) ship.getWeightCapacity()) * 0.5);
        double incomingWeightBonus = (int) (baseXP * (this.getIncomingCargoWeight() / (double) ship.getWeightCapacity()) * 0.5);
        double underdogBonus =Math.ceil(1.0f - ship.getVoyageSuccessRate(voyage));
        baseXP = (int) Math.ceil(baseXP + (baseXP * outgoingWeightBonus) + (baseXP * incomingWeightBonus) + (baseXP * underdogBonus));
        return baseXP;
    }

    private void setStartVariables(int slot, long vId) {
        Ship ship = this.getShip(slot);
        Voyage voyage = Voyage.fromLong(vId);

        ship.setStatus(Ship.Status.ON_VOYAGE.ordinal());

        this.getPlayer().getSailingSaveData().setActiveVoyage(slot, vId);
        this.getPlayer().getSailingSaveData().setShipSlotTimestamp(slot, Instant.now().toEpochMilli() + ship.getVoyageDuration(voyage));
        this.getPlayer().getSailingSaveData().setShipSlot(slot, ship.toLong());
        this.saveBuyingCargo(slot);
        this.saveSellingCargo(slot);
        this.getPlayer().save();
        SailingMetricDAO.getInstance().create(
                new SailingMetric(
                        IDManager.getUUID(),
                        vId,
                        ship.toLong(),
                        this.getPlayer().getContext().getId(),
                        this.getPlayer().getSailingSaveData().getBuyingCargo()[slot],
                        this.getPlayer().getSailingSaveData().getSellingCargo()[slot]
                )
        );
        Server.getEventHandler().submit(new VoyageEvent(this.getPlayer(), ship.getVoyageDuration(voyage), slot));
    }

    public void startVoyage(int slot, long vId) {
        Ship ship = this.getShip(slot);
        if (ship.getStatus() == Ship.Status.AVAILABLE.ordinal()) {
            this.setStartVariables(slot, vId);
            this.getPlayer().sendMessage("Your ship sets sail.");
        } else if (ship.getStatus() == Ship.Status.RETURN_SUCCESS.ordinal()) {
            this.getPlayer().sendMessage("This ship has trade goods that need to be unloaded.");
        } else if (ship.getStatus() == Ship.Status.RETURN_FAILED.ordinal()) {
            this.getPlayer().sendMessage("This ship failed it's voyage and needs to be repaired.");
        } else {
            this.getPlayer().sendMessage("This ship is currently sailing.");
        }
    }

    private void clearCargo(int slot) {
        long[] items = this.getPlayer().getSailingSaveData().getBuyingCargo()[slot];
        for (int i = 0; i < items.length; i++) {
            GameItem gameItem = GameItem.decodeGameItem(items[i]);
            if (gameItem.getId() != 0) {
                this.getPlayer().getSailingSaveData().setBuyCargo(i, 0, slot);
            }
        }

        long[] sellingCargo = this.getPlayer().getSailingSaveData().getSellingCargo()[slot];
        for (int i = 0; i < sellingCargo.length; i++) {
            GameItem gameItem = GameItem.decodeGameItem(sellingCargo[i]);
            if (gameItem.getId() != 0) {
                this.getPlayer().getSailingSaveData().setSellCargo(i, 0, slot);
            }
        }
    }

    private void transferBuyingCargoToStockpile(int slot) {
        long[] items = this.getPlayer().getSailingSaveData().getBuyingCargo()[slot];
        for (int i = 0; i < items.length; i++) {
            GameItem gameItem = GameItem.decodeGameItem(items[i]);
            if (gameItem.getId() != 0) {
                this.getPlayer().getSailingSaveData().setBuyCargo(i, 0, slot);
                this.getPlayer().getSailingSaveData().setOrAddCargo(gameItem.encodeGameItem());
            }
        }
    }

    private void transferSellingCargoToStockpile(int slot, int vIndex) {
        long[] items = this.getPlayer().getSailingSaveData().getSellingCargo()[slot];
        Map<Integer, TradeGood> tradeGoodMap = this.getPlayer().getSailingSaveData().getSoldTradeGoods(vIndex);
        for (int i = 0; i < items.length; i++) {
            GameItem gameItem = GameItem.decodeGameItem(items[i]);
            if (gameItem.getId() != 0) {
                TradeGood tradeGood = tradeGoodMap.get(gameItem.getId());
                GameItem coins = new GameItem(995, tradeGood.getBasePrice() * gameItem.getAmount());
                this.getPlayer().getSailingSaveData().setOrAddCargo(coins.encodeGameItem());
                this.getPlayer().getSailingSaveData().setSellCargo(i, 0, slot);
            }
        }
    }

    private void saveBuyingCargo(int slot) {
        long[] items = this.getPlayer().getAttributes().getSelectedBuyOffers();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != 0) {
                TradeGood tradeGood = TradeGood.fromLong(items[i]);
                GameItem gameItem = new GameItem(tradeGood.getItemId(), tradeGood.getStock());
                int fees = tradeGood.getStock() * tradeGood.getBasePrice();

                System.out.println("Removing from Coffer: " + fees);
                System.out.println("Purchasing: " + tradeGood);

                this.getPlayer().getSailingSaveData().setCoffer(this.getPlayer().getSailingSaveData().getCoffer() - fees);
                this.getPlayer().getSailingSaveData().setBuyCargo(i, gameItem.encodeGameItem(), slot);
                this.getPlayer().getAttributes().setBuyOffer(i, 0);
            }
        }
    }

    private void saveSellingCargo(int slot) {
        long[] items = this.getPlayer().getAttributes().getSelectedSellOffers();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != 0) {
                TradeGood tradeGood = TradeGood.fromLong(items[i]);
                GameItem gameItem = new GameItem(tradeGood.getItemId(), tradeGood.getStock());

                System.out.println("Selling: " + tradeGood);

                this.getPlayer().getSailingSaveData().setSellCargo(i, gameItem.encodeGameItem(), slot);
                this.getPlayer().getAttributes().setSellOffer(i, 0);
                this.getPlayer().getSailingSaveData().setOrRemoveCargo(gameItem.encodeGameItem());
            }
        }
    }

    public void repairShip(int slot) {
        Ship ship = this.getShip(slot);
        if (ship.getStatus() == Ship.Status.RETURN_FAILED.ordinal()) {
            System.out.println("Do some repairing");
        } else if (ship.getStatus() == Ship.Status.RETURN_SUCCESS.ordinal()) {
            this.getPlayer().sendMessage("This ship has trade good that need to be unloaded.");
        } else if (ship.getStatus() == Ship.Status.AVAILABLE.ordinal()) {
            this.getPlayer().sendMessage("This ship is sailing condition and ready to sail.");
        } else {
            this.getPlayer().sendMessage("This ship is currently sailing.");
        }
    }

    public void generateDailyVoyages() {
        final long[] remainingVoyages = this.getPlayer().getSailingSaveData().getAvailabeVoyages();
        final long[] activeVoyages = this.getPlayer().getSailingSaveData().getActiveVoyages();
        final List<Integer> indiciesToReplace = SailingUtils.findInactiveVoyages(activeVoyages, remainingVoyages);

        for (int i = 0; i < indiciesToReplace.size(); i++) {
            int index = indiciesToReplace.get(i);
            Voyage voyage = this.generateVoyage();
            long vId = voyage.toLong();
            this.getPlayer().getSailingSaveData().setAvailableVoyage(index, vId);
            this.generateVoyageBoughtTradeGoods(index);
            this.generateVoyageSoldTradeGoods(index, voyage.getIsland(), voyage.getRegion());
        }
    }

    public Voyage generateVoyage() {
        final int region = this.getScaledVoyageRegion();
        final int island = SailingUtils.getIslandFromRegion(region);
        final Voyage voyage = new Voyage(
                SailingUtils.getStatRangeBasedOnRegion(region),
                SailingUtils.getStatRangeBasedOnRegion(region),
                SailingUtils.getStatRangeBasedOnRegion(region),
                SailingUtils.getDistanceFromRegion(region),
                region,
                island
        );
        return voyage;
    }

    public Ship getShip(int slot) {
        return Ship.fromLong(this.getPlayer().getSailingSaveData().getShipSlot()[slot]);
    }

    public String getFormattedWeight(double value) {
        final DecimalFormat decimalFormat = new DecimalFormat("####.##");
        return decimalFormat.format(value);
    }

    private void generateVoyageBoughtTradeGoods(int voyageIndex) {
        int targetRegion = this.getScaledVoyageRegion();
        int targetIsland = SailingUtils.getIslandFromRegion(targetRegion);
        LootTable islandLootTable = LootTableLoader.getInstance().read(IslandLoader.getInstance().read(targetIsland).getTableId());
        LootTable regionLootTable = LootTableLoader.getInstance().read(IslandRegionLoader.getInstance().read(targetRegion).getTableId());
        for (int i = 0; i < 10; i++) {
            Collection<Loot> islandLoot = LootTableContainerUtils.open(islandLootTable, this.getPlayer().getAttributes().getMagicFind());
            Collection<Loot> regionLoot = LootTableContainerUtils.open(regionLootTable, this.getPlayer().getAttributes().getMagicFind());
            Loot loot = null;
            if (i > 5) {
                loot = regionLoot.stream().findFirst().orElse(null);
            } else {
                loot = islandLoot.stream().findFirst().orElse(null);
            }

            if (loot != null) {
                TradeGood tradeGood = new TradeGood(
                        (int) loot.getId(),
                        (int) loot.getAmount(),
                        (int) RunehubUtils.applyPercentageRange(ItemIdContextLoader.getInstance().read((int) loot.getId()).getValue(), 0.15)
                );
                this.getPlayer().getSailingSaveData().setVoyageBoughtGoods(voyageIndex, i, tradeGood.toLong());
            }
        }
    }

    private void generateVoyageSoldTradeGoods(int voyageIndex, int islandId, int regionId) {
        LootTable islandLootTable = LootTableLoader.getInstance().read(IslandLoader.getInstance().read(islandId).getTableId());
        LootTable regionLootTable = LootTableLoader.getInstance().read(IslandRegionLoader.getInstance().read(regionId).getTableId());
        for (int i = 0; i < 10; i++) {
            Collection<Loot> islandLoot = LootTableContainerUtils.open(islandLootTable, this.getPlayer().getAttributes().getMagicFind());
            Collection<Loot> regionLoot = LootTableContainerUtils.open(regionLootTable, this.getPlayer().getAttributes().getMagicFind());
            Loot loot = null;
            if (i > 5) {
                loot = regionLoot.stream().findFirst().orElse(null);
            } else {
                loot = islandLoot.stream().findFirst().orElse(null);
            }

            if (loot != null) {
                TradeGood tradeGood = new TradeGood(
                        (int) loot.getId(),
                        (int) loot.getAmount(),
                        (int) RunehubUtils.applyPercentageRange(ItemIdContextLoader.getInstance().read((int) loot.getId()).getValue(), 0.15)
                );
                this.getPlayer().getSailingSaveData().setVoyageSoldGoods(voyageIndex, i, tradeGood.toLong());
            }
        }
    }

    private int getScaledVoyageRegion() {
//        final int statTotals = this.calculatePlayerCombatTotal() + this.calculatePlayerMoraleTotal() + this.calculatePlayerSeafaringTotal();
        final long statTotals = this.getPlayer().getSailingSaveData().getLeaguesTravelled();
        if (this.getPlayer().getSailingSaveData().getPreferredRegionId() == -1) {
            if (statTotals <= 500 && statTotals >= 0) {
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
        return this.getPlayer().getSailingSaveData().getPreferredRegionId();
    }


    @Override
    public int getId() {
        return 23;
    }

    public Sailing(Player player) {
        super(player);
    }
}
