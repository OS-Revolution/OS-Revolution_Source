package ethos.runehub.ui.impl.sailing;

import ethos.model.players.Player;
import ethos.runehub.LootTableContainerUtils;
import ethos.runehub.RunehubUtils;
import ethos.runehub.TimeUtils;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryCache;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryDAO;
import ethos.runehub.skill.support.sailing.Sailing;
import ethos.runehub.skill.support.sailing.ship.ShipLoader;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.ui.impl.SelectionParentUI;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.ContainerType;
import org.runehub.api.model.math.impl.AdjustableInteger;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VoyageUI extends SelectionParentUI {

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Select a Voyage");
        rewardLabel.setText("Successful Voyage Rewards");
        listTitleLabel.setText("Voyages");
        this.registerButton(actionEvent -> selectShipSlot(0), 187134);
        this.registerButton(actionEvent -> selectShipSlot(1), 187135);
        this.registerButton(actionEvent -> selectShipSlot(2), 187136);
        this.drawListItems();
        this.drawTabs();
        this.selectShipSlot(0);
        this.updateUI();
    }

    private void drawListItems() {
        final List<Voyage> items = this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages();
        int index = 0;
        for (int i = 0; i < items.size(); i++) {
            Voyage item = items.get(i);
            if (this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).onVoyage(item)
                    && !this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).isVoyageComplete()) {
                listItemLabels[index].setText(MarkupUtils.highlightText(MarkupUtils.YELLOW, item.getName()));
                index++;
            } else if (this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).onVoyage(item)
                    && this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).isVoyageComplete()) {
                listItemLabels[index].setText(MarkupUtils.highlightText(MarkupUtils.GREEN, item.getName()));
                index++;
            } else {
                boolean unavailable = false;
                for (int j = 0; j < this.getPlayer().getContext().getPlayerSaveData().getShipSlot().length; j++) {
                    unavailable = this.getPlayer().getSkillController().getSailing().getShipSlot(j).onVoyage(item);
                }
                if (!unavailable) {
                    listItemLabels[index].setText(item.getName());
                    listItemButtons[index].addActionListener(actionEvent -> drawSelectedItem(item));
                    index++;
                }
            }

        }
    }

    private void writeVoyageRewards(Voyage voyage) {
        this.clearItems();
        final AdjustableInteger slot = new AdjustableInteger(0);
        Arrays.stream(voyage.getContext().getContainerIds()).forEach(id -> {
            LootTableContainerUtils.getLootTableContainer(id, ContainerType.ITEM).ifPresent(lootTableContainer -> {
                lootTableContainer.getLootTables().forEach(lootTableContainerEntry -> {
                    LootTableLoader.getInstance().read(lootTableContainerEntry.getId()).getLootTableEntries().forEach(lootTableEntry -> {
                        items[slot.value()] = new GameItem(lootTableEntry.getId(), lootTableEntry.getAmount().getMax());
                        slot.increment();
                    });
                });
            });
        });
    }

    private void drawSelectedItem(Voyage voyage) {
        infoH1Label.setText(voyage.getName());
        infoH2Label.setText(this.getVoyageDurationString(voyage));
        infoLine1Label.setText("Seafaring: " + this.getSeafaringStatString(voyage));
        infoLine2Label.setText("Combat: " + this.getSeafaringStatString(voyage));
        infoLine3Label.setText("Morale: " + this.getSeafaringStatString(voyage));
        infoLine4Label.setText("Success Rate: " + this.getSuccessRateString(voyage));
//        this.drawItems(item);
//        this.drawOption(item);
        this.drawOptions(voyage);
        this.writeVoyageRewards(voyage);
        this.updateUI();
    }

    private void selectShipSlot(int slot) {
        this.drawTabs();
        if (slot == 0) {
            tabOneLabel.setText(MarkupUtils.highlightText("@whi@", this.getShipName(slot)));
        } else if (slot == 1) {
            tabTwoLabel.setText(MarkupUtils.highlightText("@whi@", this.getShipName(slot)));
        } else if (slot == 2) {
            tabThreeLabel.setText(MarkupUtils.highlightText("@whi@", this.getShipName(slot)));
        }
        selectedShipSlot = slot;
        this.clearInfo();
        this.drawListItems();
        this.updateUI();
    }

    private void drawTabs() {
        tabOneLabel.setText(this.getShipName(0));
        tabTwoLabel.setText(this.getShipName(1));
        tabThreeLabel.setText(this.getShipName(2));
    }

    private void drawOptions(Voyage voyage) {
        optionTwoLabel.setText("");
        if (this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).onVoyage()) {
            if (this.getPlayer().getSkillController().getSailing().canCancelVoyage(voyage, selectedShipSlot)
                    && !this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).isVoyageComplete()) {
                optionOneLabel.setText("Cancel Voyage");
                this.registerButton(actionEvent -> this.getPlayer().getSkillController().getSailing().cancelVoyage(voyage, selectedShipSlot), 150111);
            } else if (this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).isVoyageComplete()) {
                optionOneLabel.setText("Claim Rewards");
                this.registerButton(actionEvent -> this.getPlayer().getSkillController().getSailing().claimVoyage(voyage, selectedShipSlot), 150111);
            } else {
                optionOneLabel.setText("");
            }
        } else {
            optionOneLabel.setText("Start Voyage");
            optionTwoLabel.setText("Re-Roll Voyage");
            this.registerButton(actionEvent -> this.getPlayer().getSkillController().getSailing().startVoyage(voyage, selectedShipSlot), 150111);
            this.registerButton(actionEvent -> {
                this.getPlayer().getSkillController().getSailing().rerollVoyage(voyage, selectedShipSlot);
                this.clearInfo();
                this.drawListItems();
                this.updateUI();
            }, 150112);
        }
    }

    private String getShipName(int slot) {
        if (this.getPlayer().getContext().getPlayerSaveData().getShipSlot()[slot][Sailing.SHIP_ID] > 0) {
            final String shipName = ShipLoader.getInstance().read(this.getPlayer().getContext().getPlayerSaveData().getShipSlot()[slot][Sailing.SHIP_ID]).getName();
            if (this.getPlayer().getSkillController().getSailing().onVoyage(slot) && !this.getPlayer().getSkillController().getSailing().getShipSlot(slot).isVoyageComplete())
                return MarkupUtils.highlightText(MarkupUtils.YELLOW, shipName);
            else if (this.getPlayer().getSkillController().getSailing().getShipSlot(slot).isVoyageComplete())
                return MarkupUtils.highlightText(MarkupUtils.GREEN, shipName);
            return shipName;
        }
        return "Empty";
    }

    private String getVoyageDurationString(Voyage voyage) {
        final long voyageDurationMS = this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getVoyageDuration(voyage);
        return this.distanceColor(voyage) + TimeUtils.getDurationString(voyageDurationMS);
    }

    private String distanceColor(Voyage voyage) {
        final int totalDistance = 1228;
        final int voyageDistance = voyage.getDistance();
        final float percentOfTotalDistance = (float) voyageDistance / totalDistance;
        if (percentOfTotalDistance >= 0.8) {
            return "@red@";
        } else if (percentOfTotalDistance < 0.8 && percentOfTotalDistance >= 0.5) {
            return "@yel@";
        }
        return "@gre@";
    }

    private String getStatString(int a, int b, float percent) {
        if (percent >= 0.8) {
            return "@gre@" + a + "/" + b;
        } else if (percent < 0.8 && percent >= 0.5) {
            return "@yel@" + a + "/" + b;
        }
        return "@red@" + a + "/" + b;
    }

    private String getSeafaringStatString(Voyage voyage) {
        return this.getStatString(
                this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).calculatePlayerSeafaringTotal(),
                voyage.getSeafaring(),
                this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getSeafaringSuccessChance(voyage.getSeafaring())
        );
    }

    private String getCombatStatString(Voyage voyage) {
        return this.getStatString(
                this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).calculatePlayerCombatTotal(),
                voyage.getCombat(),
                this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getCombatSuccessChance(voyage.getCombat())
        );
    }

    private String getMoraleStatString(Voyage voyage) {
        return this.getStatString(
                this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).calculatePlayerMoraleTotal(),
                voyage.getMorale(),
                this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getMoraleSuccessChance(voyage.getMorale())
        );
    }

    private String getSuccessRateString(Voyage voyage) {
        final float percentOfTotalDistance = this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getVoyageSuccessRate(voyage);
        if (percentOfTotalDistance >= 0.8) {
            return "@gre@" + this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getSuccessRatePrint(percentOfTotalDistance);
        } else if (percentOfTotalDistance < 0.8 && percentOfTotalDistance >= 0.5) {
            return "@yel@" + this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getSuccessRatePrint(percentOfTotalDistance);
        }
        return "@red@" + this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getSuccessRatePrint(percentOfTotalDistance);
    }

    public VoyageUI(Player player) {
        super(player);
    }

    private int selectedShipSlot;
}
