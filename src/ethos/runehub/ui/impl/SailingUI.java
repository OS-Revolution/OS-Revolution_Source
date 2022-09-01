package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.LootTableContainerUtils;
import ethos.runehub.TimeUtils;
import ethos.runehub.skill.support.sailing.Sailing;
import ethos.runehub.skill.support.sailing.ship.ShipLoader;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.ui.GameUI;
import ethos.runehub.world.wushanko.island.IslandLoader;
import ethos.runehub.world.wushanko.region.IslandRegionLoader;
import org.runehub.api.io.load.impl.LootTableContainerLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.ContainerType;
import org.runehub.api.model.entity.item.loot.LootTableContainer;
import org.runehub.api.model.math.impl.AdjustableInteger;

import java.text.NumberFormat;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class SailingUI extends GameUI {

    @Override
    protected void onOpen() {
        this.writeShipTabs();
        this.clearVoyageList();
        this.writeVoyageList();
        this.clearVoyageInfo();
    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onAction(int buttonId) {
        switch (buttonId) {
            case 187134: //tab 1
                this.selectedShipId = this.getPlayer().getContext().getPlayerSaveData().getShipSlot()[0][Sailing.SHIP_ID];
                break;
            case 187135: //tab 2
                this.selectedShipId = this.getPlayer().getContext().getPlayerSaveData().getShipSlot()[1][Sailing.SHIP_ID];
                break;
            case 187136: //tab 3
                this.selectedShipId = this.getPlayer().getContext().getPlayerSaveData().getShipSlot()[2][Sailing.SHIP_ID];
                break;
            case 148114:
                this.close();
                break;
            case 150111:
                this.startVoyage();
                break;
            case 150112:
                this.rerollVoyage(selectedVoyage.getId());
                break;
            case 150113://cancel
                this.cancelVoyage();
                break;
            case 150114:
                this.completeVoyage();
                break;
            default:
                //clicking voyage
                int voyageIndex = buttonId - 148149;
                selectedVoyage = voyages.get(voyageIndex);
//                this.resetVoyageListColors();
//                this.getPlayer().getPA().sendFrame126("@gre@" + selectedVoyage.getName(),buttonId);
//                this.writeVoyageInfo();
                this.writeVoyageRewards();
                break;
        }
    }

    @Override
    protected void onEvent() {

    }


    private void completeVoyage() {
        this.getPlayer().getSkillController().getSailing().finishVoyage();
//        if (this.getPlayer().getContext().getPlayerSaveData().getShipSlotOneVoyage() > 0) {
//            if (this.getPlayer().getContext().getPlayerSaveData().getShipSlotOneEndTimestamp() <= System.currentTimeMillis()) {
//                if (selectedVoyage.getId() == this.getPlayer().getContext().getPlayerSaveData().getShipSlotOneVoyage()) {
//                    this.getPlayer().getSkillController().getSailing().completeVoyage(selectedVoyage);
//                    this.refreshUI();
//                    this.getPlayer().sendMessage("You cancel the voyage.");
//                } else {
//                    this.getPlayer().sendMessage("You can't complete a voyage you're not on. Your ship is heading for " + IslandLoader.getInstance().read(this.getPlayer().getContext()
//                            .getPlayerSaveData().getShipSlotOneIsland()).getName());
//                }
//            } else {
//                this.getPlayer().sendMessage("Your ship has not returned from it's voyage yet.");
//            }
//        } else {
//            this.getPlayer().sendMessage("This ship is not on a voyage.");
//
//        }
    }


    private void cancelVoyage() {
//        if (this.getPlayer().getContext().getPlayerSaveData().getShipSlotOneVoyage() > 0) {
//            if (selectedVoyage.getId() == this.getPlayer().getContext().getPlayerSaveData().getShipSlotOneVoyage()) {
//                this.getPlayer().getSkillController().getSailing().cancelVoyage();
//                this.refreshUI();
//                this.getPlayer().sendMessage("You cancel the voyage.");
//            } else {
//                this.getPlayer().sendMessage("You can't cancel a voyage you're not on. Your ship is heading for " + IslandLoader.getInstance().read(this.getPlayer().getContext()
//                        .getPlayerSaveData().getShipSlotOneIsland()).getName());
//            }
//        } else {
//            this.getPlayer().sendMessage("This ship is not on a voyage.");
//        }
    }

    public void startVoyage() {
//        if (this.getPlayer().getContext().getPlayerSaveData().getShipSlotOneVoyage() == 0) {
//            this.getPlayer().getSkillController().getSailing().start(selectedVoyage);
//            this.refreshUI();
//        } else {
//            this.getPlayer().sendMessage("This ship is already on a voyage.");
//        }
    }

    public void rerollVoyage(int voyageId) {
//        final Optional<Voyage> voyageOptional = this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().stream().filter(voyage -> voyage.getId() == voyageId).findAny();
//        if (this.getPlayer().getContext().getPlayerSaveData().getVoyageRerolls() > 0) {
//            if (voyageOptional.isPresent()) {
//                if (voyageOptional.get().getId() != this.getPlayer().getContext().getPlayerSaveData().getShipSlotOneVoyage()) {
//                    final Voyage newVoyage = this.getPlayer().getSkillController().getSailing().generateVoyage();
//                    this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().remove(voyageOptional.get());
//                    this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().add(newVoyage);
//                    this.getPlayer().save();
//                    selectedVoyage = newVoyage;
//                    this.writeVoyageRewards();
//                    this.writeVoyageInfo();
//                    this.writeVoyageList();
//                    this.writeVoyageOptions();
//                } else {
//                    this.getPlayer().sendMessage("You may not re-roll an active voyage.");
//                }
//            } else {
//                this.getPlayer().sendMessage("Something went wrong.");
//            }
//        } else {
//            this.getPlayer().sendMessage("You do not have any $Voyage $Re-rolls available.");
//        }
    }

    private void refreshUI() {
        this.clearRewards();
        this.writeShipTabs();
        this.writeVoyageList();
        this.writeVoyageOptions();
//        this.writeVoyageInfo();
        this.writeVoyageRewards();
    }

    private void clearRewards() {
        int slot = 0;
        for (int i = 0; i < 12; i++) {
            this.getPlayer().getPA().itemOnInterface(-1, 0, 38521, slot);
            this.getPlayer().getPA().sendFrame126("", 36511 + i);
            slot++;
        }
    }

    private void writeVoyageRewards() {
        this.clearRewards();
        final Optional<LootTableContainer> lootTableContainer = LootTableContainerUtils.getLootTableContainer(selectedVoyage.getIsland(), ContainerType.ITEM);
        if (lootTableContainer.isPresent()) {
            lootTableContainer.get().getLootTables().forEach(lootTableContainerEntry -> {
                final AdjustableInteger slot = new AdjustableInteger(0);
                LootTableLoader.getInstance().read(lootTableContainerEntry.getId()).getLootTableEntries().forEach(lootTableEntry -> {

                    this.getPlayer().getPA().itemOnInterface(lootTableEntry.getId(), lootTableEntry.getAmount().getMax(), 38521, slot.value());
                    slot.increment();
                });
            });
        } else if (LootTableContainerUtils.getLootTableContainer(selectedVoyage.getRegion() + 50000, ContainerType.ITEM).isPresent()) {
            LootTableContainerUtils.getLootTableContainer(selectedVoyage.getRegion() + 50000, ContainerType.ITEM).get().getLootTables().forEach(lootTableContainerEntry -> {
                final AdjustableInteger slot = new AdjustableInteger(0);
                LootTableLoader.getInstance().read(lootTableContainerEntry.getId()).getLootTableEntries().forEach(lootTableEntry -> {
                    this.getPlayer().getPA().itemOnInterface(lootTableEntry.getId(), lootTableEntry.getAmount().getMax(), 38521, slot.value());
                    slot.increment();
                });
            });
        }

    }

    private void resetVoyageListColors() {
        for (int i = 38037; i < 38037 + voyages.size(); i++) {
            int voyageIndex = i - 38037;
            this.getPlayer().getPA().sendFrame126(voyages.get(voyageIndex).getName(), i);
        }
    }

    private void clearVoyageInfo() {
        this.getPlayer().getPA().sendFrame126("", 38501);
        this.getPlayer().getPA().sendFrame126("", 38502);
        this.getPlayer().getPA().sendFrame126("", 38503);
        this.getPlayer().getPA().sendFrame126("", 38504);
        this.getPlayer().getPA().sendFrame126("", 38505);
        this.getPlayer().getPA().sendFrame126("", 38506);
        this.getPlayer().getPA().sendFrame126("", 38507);
        this.getPlayer().getPA().sendFrame126("", 38508);
        this.getPlayer().getPA().sendFrame126("", 38511);
        this.getPlayer().getPA().sendFrame126("", 38512);
        this.getPlayer().getPA().sendFrame126("", 38513);
        this.getPlayer().getPA().sendFrame126("", 38514);
        this.getPlayer().getPA().sendFrame126("", 38515);
    }

    private void clearVoyageList() {
        for (int i = 38037; i < 38137; i++) {
            this.getPlayer().getPA().sendFrame126("", i);
        }
    }

    private void writeVoyageList() {
//        for (int i = 38037; i < 38037 + voyages.size(); i++) {
//            int voyageIndex = i - 38037;
//            if (voyages.get(voyageIndex).getId() == this.getPlayer().getContext().getPlayerSaveData().getShipSlotOneVoyage()) {
//                this.getPlayer().getPA().sendFrame126("@yel@" + voyages.get(voyageIndex).getName(), i);
//            } else {
//                this.getPlayer().getPA().sendFrame126(voyages.get(voyageIndex).getName(), i);
//            }
//        }
    }

//    private void writeVoyageInfo() {
////        if (selectedVoyage.getId() != this.getPlayer().getContext().getPlayerSaveData().getShipSlotOneVoyage()) {
////            this.getPlayer().getPA().sendFrame126(this.getVoyageDurationString(), 38502);
////        } else {
////            final long duration = this.getPlayer().getContext().getPlayerSaveData().getShipSlotOneEndTimestamp() - System.currentTimeMillis();
////            this.getPlayer().getPA().sendFrame126(duration > 0 ? (this.distanceColor() + TimeUtils.getDurationString(duration)) : TimeUtils.getDurationString(0), 38502);
////
////        }
//        this.getPlayer().getPA().sendFrame126(selectedVoyage.getName(), 38501);
//        this.getPlayer().getPA().sendFrame126("Voyage Seafaring: " + this.getSeafaringStatString(), 38503);
//        this.getPlayer().getPA().sendFrame126("Voyage Combat: " + this.getCombatStatString(), 38504);
//        this.getPlayer().getPA().sendFrame126("Voyage Morale: " + this.getMoraleStatString(), 38505);
//        this.getPlayer().getPA().sendFrame126("Success Rate: " + this.getSuccessRateString(), 38506);
//        this.getPlayer().getPA().sendFrame126("Region: " + IslandRegionLoader.getInstance().read(selectedVoyage.getRegion()).getName(), 38507);
//        this.getPlayer().getPA().sendFrame126("Distance: " + NumberFormat.getInstance().format(selectedVoyage.getDistance()) + " leagues.", 38508);
//        this.writeVoyageOptions();
//    }

    private void writeVoyageOptions() {
        this.getPlayer().getPA().sendFrame126("Start Voyage", 38511);
        this.getPlayer().getPA().sendFrame126("Re-roll Voyage", 38512);
        this.getPlayer().getPA().sendFrame126("Cancel Voyage", 38513);
        this.getPlayer().getPA().sendFrame126("Complete Voyage", 38514);
        this.getPlayer().getPA().sendFrame126("", 38515);
    }

    private void writeShipTabs() {
//        if (this.getPlayer().getContext().getPlayerSaveData().getShipSlotOne() >= 0) {
//            final String shipName = ShipLoader.getInstance().read(this.getPlayer().getContext().getPlayerSaveData().getShipSlotOne()).getName();
//            this.getPlayer().getPA().sendFrame126(shipName, 38026);
//            this.getPlayer().getPA().sendFrame126("Ship Slot 1", 48018);
//            this.getPlayer().getPA().sendFrame126("Ship Slot 2", 48019);
//            this.getPlayer().getPA().sendFrame126("Ship Slot 3", 48020);
//        } else {
//            this.getPlayer().getPA().sendFrame126("N/A", 38026);
//            this.getPlayer().getPA().sendFrame126("Ship Slot 1", 48018);
//            this.getPlayer().getPA().sendFrame126("Ship Slot 2", 48019);
//            this.getPlayer().getPA().sendFrame126("Ship Slot 3", 48020);
//        }
    }

//    private String getVoyageDurationString() {
//        final long voyageDurationMS = this.getPlayer().getSkillController().getSailing().getVoyageDuration(selectedVoyage);
//        return this.distanceColor() + TimeUtils.getDurationString(voyageDurationMS);
//    }

    private String distanceColor() {
        final int totalDistance = 1228;
        final int voyageDistance = selectedVoyage.getDistance();
        final float percentOfTotalDistance = (float) voyageDistance / totalDistance;
        if (percentOfTotalDistance >= 0.8) {
            return "@red@";
        } else if (percentOfTotalDistance < 0.8 && percentOfTotalDistance >= 0.5) {
            return "@yel@";
        }
        return "@gre@";
    }

//    private String getSeafaringStatString() {
//        final float percentOfTotalDistance = this.getPlayer().getSkillController().getSailing().getSeafaringSuccessChance(selectedVoyage.getSeafaring());
//        if (percentOfTotalDistance >= 0.8) {
//            return "@gre@" + this.getPlayer().getSkillController().getSailing().calculatePlayerSeafaringTotal() + "/" + selectedVoyage.getSeafaring();
//        } else if (percentOfTotalDistance < 0.8 && percentOfTotalDistance >= 0.5) {
//            return "@yel@" + this.getPlayer().getSkillController().getSailing().calculatePlayerSeafaringTotal() + "/" + selectedVoyage.getSeafaring();
//        }
//        return "@red@" + this.getPlayer().getSkillController().getSailing().calculatePlayerSeafaringTotal() + "/" + selectedVoyage.getSeafaring();
//    }

//    private String getCombatStatString() {
//        final float percentOfTotalDistance = this.getPlayer().getSkillController().getSailing().getCombatSuccessChance(selectedVoyage.getCombat());
//        if (percentOfTotalDistance >= 0.8) {
//            return "@gre@" + this.getPlayer().getSkillController().getSailing().calculatePlayerCombatTotal() + "/" + selectedVoyage.getCombat();
//        } else if (percentOfTotalDistance < 0.8 && percentOfTotalDistance >= 0.5) {
//            return "@yel@" + this.getPlayer().getSkillController().getSailing().calculatePlayerCombatTotal() + "/" + selectedVoyage.getCombat();
//        }
//        return "@red@" + this.getPlayer().getSkillController().getSailing().calculatePlayerCombatTotal() + "/" + selectedVoyage.getCombat();
//    }
//
//    private String getMoraleStatString() {
//        final float percentOfTotalDistance = this.getPlayer().getSkillController().getSailing().getMoraleSuccessChance(selectedVoyage.getMorale());
//        if (percentOfTotalDistance >= 0.8) {
//            return "@gre@" + this.getPlayer().getSkillController().getSailing().calculatePlayerMoraleTotal() + "/" + selectedVoyage.getMorale();
//        } else if (percentOfTotalDistance < 0.8 && percentOfTotalDistance >= 0.5) {
//            return "@yel@" + this.getPlayer().getSkillController().getSailing().calculatePlayerMoraleTotal() + "/" + selectedVoyage.getMorale();
//        }
//        return "@red@" + this.getPlayer().getSkillController().getSailing().calculatePlayerMoraleTotal() + "/" + selectedVoyage.getMorale();
//    }
//
//    private String getSuccessRateString() {
//        final float percentOfTotalDistance = this.getPlayer().getSkillController().getSailing().getVoyageSuccessRate(selectedVoyage);
//        if (percentOfTotalDistance >= 0.8) {
//            return "@gre@" + this.getPlayer().getSkillController().getSailing().getVoyageSuccessRatePrint(percentOfTotalDistance);
//        } else if (percentOfTotalDistance < 0.8 && percentOfTotalDistance >= 0.5) {
//            return "@yel@" + this.getPlayer().getSkillController().getSailing().getVoyageSuccessRatePrint(percentOfTotalDistance);
//        }
//        return "@red@" + this.getPlayer().getSkillController().getSailing().getVoyageSuccessRatePrint(percentOfTotalDistance);
//    }

    public SailingUI(Player player, List<Voyage> voyages) {
        super(38000, player);
        this.voyages = voyages;
    }

    private final List<Voyage> voyages;
    private Voyage selectedVoyage;
    private int selectedShipId;
}
