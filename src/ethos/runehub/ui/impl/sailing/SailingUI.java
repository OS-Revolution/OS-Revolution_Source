package ethos.runehub.ui.impl.sailing;

import ethos.model.players.Player;
import ethos.runehub.LootTableContainerUtils;
import ethos.runehub.TimeUtils;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.support.sailing.Sailing;
import ethos.runehub.skill.support.sailing.Ship;
import ethos.runehub.skill.support.sailing.action.RerollVoyageAction;
import ethos.runehub.skill.support.sailing.action.StartVoyageAction;
import ethos.runehub.skill.support.sailing.ship.ShipLoader;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.ui.GameUI;
import ethos.runehub.ui.UIUtils;
import ethos.runehub.ui.component.button.Button;
import ethos.runehub.ui.component.impl.TextComponent;
import ethos.runehub.ui.event.action.ActionListener;
import ethos.runehub.world.wushanko.region.IslandRegionLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.ContainerType;
import org.runehub.api.model.math.impl.AdjustableInteger;

import java.text.NumberFormat;
import java.util.*;

public class SailingUI extends GameUI {

    private void drawShipName() {
        if (this.getPlayer().getContext().getPlayerSaveData().getShipSlot()[selectedShipSlot][Sailing.SHIP_ID] > 0) {
            final String shipName = ShipLoader.getInstance().read(this.getPlayer().getContext().getPlayerSaveData().getShipSlot()[selectedShipSlot][Sailing.SHIP_ID]).getName();
            shipNameLabel.setText(shipName);
        } else {
            shipNameLabel.setText("Empty");
        }
        this.writeLine(shipNameLabel);
    }

    @Override
    protected void onOpen() {
        this.registerButton(new UICloseActionListener(), 148114);
        this.registerButton(actionEvent -> updateShipSlot(0, shipSlotOneLabel), 187134);
        this.registerButton(actionEvent -> updateShipSlot(1, shipSlotTwoLabel), 187135);
        this.registerButton(actionEvent -> updateShipSlot(2, shipSlotThreeLabel), 187136);
        this.registerButton(actionEvent -> {
            this.getPlayer().getSkillController().getSailing().train(new StartVoyageAction(this.getPlayer(), selectedShipSlot, selectedVoyageSlot));
        }, 150111);
        this.registerButton(actionEvent -> {
            this.getPlayer().getSkillController().getSailing().train(new RerollVoyageAction(this.getPlayer(), selectedShipSlot, selectedVoyageSlot));
        }, 150112);
        this.registerButton(actionEvent -> {
            this.getPlayer().getSkillController().getSailing().cancelVoyage(this.getVoyage(), selectedShipSlot);
        }, 150113);
        this.registerButton(actionEvent -> {
            this.getPlayer().getSkillController().getSailing().claimVoyage(this.getVoyage(), selectedShipSlot);
        }, 150114);
        this.registerVoyageListButtonComponents();
        this.clearVoyageList();
        this.clearRewards();
        this.clearShipSlotText();
        this.updateShipSlot(0,shipSlotOneLabel);
        this.refresh();
    }

    private void refresh() {
        this.writeVoyageRewards();
        this.drawOptionTextComponents();
        this.drawVoyageList();
        this.updateVoyageTextComponents();
    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onAction(int buttonId) {
        super.onAction(buttonId);
        this.refresh();
    }

    @Override
    protected void onEvent() {

    }

    private void updateShipSlot(int slot, TextComponent component) {
        selectedShipSlot = slot;
        clearShipSlotText();
        updateShipSlotTextComponent(component);
        drawShipName();
        writeLine(shipSlotOneLabel);
        writeLine(shipSlotTwoLabel);
        writeLine(shipSlotThreeLabel);
    }

    private void clearShipSlotText() {
        UIUtils.removeHighlight(shipSlotOneLabel);
        UIUtils.removeHighlight(shipSlotTwoLabel);
        UIUtils.removeHighlight(shipSlotThreeLabel);
    }

    private void updateShipSlotTextComponent(TextComponent component) {
        UIUtils.highlightText(MarkupUtils.GREEN,component);
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
        final AdjustableInteger slot = new AdjustableInteger(0);
        Arrays.stream(this.getVoyage().getContext().getContainerIds()).forEach(id -> {
            LootTableContainerUtils.getLootTableContainer(id, ContainerType.ITEM).ifPresent(lootTableContainer -> {
                lootTableContainer.getLootTables().forEach(lootTableContainerEntry -> {
                    LootTableLoader.getInstance().read(lootTableContainerEntry.getId()).getLootTableEntries().forEach(lootTableEntry -> {
                        this.getPlayer().getPA().itemOnInterface(lootTableEntry.getId(), lootTableEntry.getAmount().getMax(), 38521, slot.value());
                        slot.increment();
                    });
                });
            });
        });
    }

    private String getVoyageDurationString() {
        final long voyageDurationMS = this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getVoyageDuration(selectedVoyageSlot);
        return this.distanceColor() + TimeUtils.getDurationString(voyageDurationMS);
    }

    private String distanceColor() {
        final int totalDistance = 1228;
        final int voyageDistance = this.getVoyage().getDistance();
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

    private String getSeafaringStatString() {
        return this.getStatString(
                this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).calculatePlayerSeafaringTotal(),
                this.getVoyage().getSeafaring(),
                this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getSeafaringSuccessChance(this.getVoyage().getSeafaring())
        );
    }

    private String getCombatStatString() {
        return this.getStatString(
                this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).calculatePlayerCombatTotal(),
                this.getVoyage().getCombat(),
                this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getCombatSuccessChance(this.getVoyage().getCombat())
        );
    }

    private String getMoraleStatString() {
        return this.getStatString(
                this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).calculatePlayerMoraleTotal(),
                this.getVoyage().getMorale(),
                this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getMoraleSuccessChance(this.getVoyage().getMorale())
        );
    }

    private String getSuccessRateString() {
        final float percentOfTotalDistance = this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getVoyageSuccessRate(selectedVoyageSlot);
        if (percentOfTotalDistance >= 0.8) {
            return "@gre@" + this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getSuccessRatePrint(percentOfTotalDistance);
        } else if (percentOfTotalDistance < 0.8 && percentOfTotalDistance >= 0.5) {
            return "@yel@" + this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getSuccessRatePrint(percentOfTotalDistance);
        }
        return "@red@" + this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).getSuccessRatePrint(percentOfTotalDistance);
    }

    private void clearVoyageList() {
        for (int i = 38037; i < 38037 + 99; i++) {
            this.getPlayer().getPA().sendFrame126("", i);
        }
    }

    private void drawVoyageList() {
        for (int i = 0; i < voyages.size(); i++) {
//            int voyageIndex = i == 0 ? i : i - 1;
            final Voyage voyage = voyages.get(i);
            final TextComponent label = voyageListLabels.get(i);
            voyageListLabels.get(i).setText(voyage.getName());
            if (this.getPlayer().getSkillController().getSailing().voyageAvailable(voyage)
                    || this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).onVoyage(voyage)) {
                if (this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).onVoyage(voyage)
                        && !this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).isVoyageComplete()) {
                    UIUtils.highlightText(MarkupUtils.YELLOW, label);
                } else if (this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).isVoyageComplete()
                        && this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).onVoyage(voyage)) {
                    UIUtils.highlightText(MarkupUtils.GREEN, label);
                } else {
                    UIUtils.highlightText(MarkupUtils.RED, label);
                }
            } else {
                UIUtils.highlightText("@bla@", label);
            }
            this.writeLine(label);
        }
    }

    private void updateVoyageTextComponents() {
        if (this.getPlayer().getContext().getPlayerSaveData().getShipSlot()[selectedShipSlot][Sailing.SHIP_ID] > 0) {
            if (this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).onVoyage(selectedVoyageSlot)) {
                if (this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).isVoyageComplete()) {
                    voyageDurationLabel.setText("Voyage Completed");
                } else {
                    voyageDurationLabel.setText(this.distanceColor() + TimeUtils.getDurationString((this.getPlayer().getSkillController().getSailing().getVoyageReturnTime(selectedShipSlot) - System.currentTimeMillis())));
                }
            } else {
                voyageDurationLabel.setText(this.getVoyageDurationString());
            }
            seafaringLabel.setText("Voyage Seafaring: " + this.getSeafaringStatString());
            combatLabel.setText("Voyage Combat: " + this.getCombatStatString());
            moraleLabel.setText("Voyage Morale: " + this.getMoraleStatString());
            successLabel.setText("Success Rate: " + this.getSuccessRateString());
        } else {
            voyageDurationLabel.setText("Duration: N/A");
            seafaringLabel.setText("Voyage Seafaring: N/A");
            combatLabel.setText("Voyage Combat: N/A");
            moraleLabel.setText("Voyage Morale: N/A");
            successLabel.setText("Success Rate: N/A");
        }
        voyageNameLabel.setText(this.getVoyage().getName());
        regionLabel.setText("Region: " + IslandRegionLoader.getInstance().read(this.getVoyage().getRegion()).getName());
        distanceLabel.setText("Distance: " + NumberFormat.getInstance().format(this.getVoyage().getDistance()) + " leagues.");

        this.writeLine(voyageNameLabel);
        this.writeLine(voyageDurationLabel);
        this.writeLine(seafaringLabel);
        this.writeLine(combatLabel);
        this.writeLine(moraleLabel);
        this.writeLine(successLabel);
        this.writeLine(regionLabel);
        this.writeLine(distanceLabel);
    }

    private Voyage getVoyage() {
        return this.getPlayer().getContext().getPlayerSaveData().getAvailableVoyages().get(selectedVoyageSlot);
    }


    private void drawOptionTextComponents() {
        if (!this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).onVoyage(selectedVoyageSlot)) {
            UIUtils.highlightText(MarkupUtils.GREEN, optionOneLabel);
            UIUtils.highlightText(MarkupUtils.GREEN, optionTwoLabel);
            UIUtils.removeHighlight(optionThreeLabel);
            UIUtils.removeHighlight(optionFourLabel);
            UIUtils.removeHighlight(optionFiveLabel);
        } else if (this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).onVoyage(selectedVoyageSlot)
                && !this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).isVoyageComplete()) {
            UIUtils.highlightText(MarkupUtils.GREEN, optionThreeLabel);
            UIUtils.removeHighlight(optionOneLabel);
            UIUtils.removeHighlight(optionTwoLabel);
            UIUtils.removeHighlight(optionFourLabel);
            UIUtils.removeHighlight(optionFiveLabel);
        } else if (this.getPlayer().getSkillController().getSailing().getShipSlot(selectedShipSlot).isVoyageComplete()) {
            UIUtils.highlightText(MarkupUtils.GREEN, optionFourLabel);
            UIUtils.removeHighlight(optionOneLabel);
            UIUtils.removeHighlight(optionTwoLabel);
            UIUtils.removeHighlight(optionThreeLabel);
            UIUtils.removeHighlight(optionFiveLabel);
        } else {
            UIUtils.removeHighlight(optionOneLabel);
            UIUtils.removeHighlight(optionTwoLabel);
            UIUtils.removeHighlight(optionThreeLabel);
            UIUtils.removeHighlight(optionFourLabel);
            UIUtils.removeHighlight(optionFiveLabel);
        }

        writeLine(optionOneLabel);
        writeLine(optionTwoLabel);
        writeLine(optionThreeLabel);
        writeLine(optionFourLabel);
        writeLine(optionFiveLabel);
    }

    private void registerVoyageListButtonComponents() {
        final int startingLine = 148149;
        for (int i = (startingLine - 1); i < startingLine + voyages.size(); i++) {
            int voyageIndex = i - startingLine;
            this.registerButton(actionEvent -> {
                selectedVoyageSlot = voyageIndex;
                this.updateVoyageTextComponents();
                this.writeVoyageRewards();
                this.drawOptionTextComponents();
            }, i);
        }
    }

    private List<TextComponent> registerVoyageListTextComponents() {
        final int startingLine = 38037;
        final List<TextComponent> components = new LinkedList<>();
        for (int i = startingLine; i < startingLine + voyages.size(); i++) {
            int voyageIndex = i - startingLine;
            components.add(new TextComponent(i,voyages.get(voyageIndex).getName()));
        }
        return components;
    }



    public SailingUI(Player player, List<Voyage> voyages) {
        super(38000, player);
        this.voyages = voyages;
        this.shipSlotOneLabel = new TextComponent(48018,"Ship Slot 1");
        this.shipSlotTwoLabel = new TextComponent(48019, "Ship Slot 2");
        this.shipSlotThreeLabel = new TextComponent(48020,"Ship Slot 3");
        this.shipNameLabel = new TextComponent(38026,"Empty");
        this.optionOneLabel = new TextComponent(38511,"Start Voyage");
        this.optionTwoLabel = new TextComponent(38512,"Re-roll Voyage");
        this.optionThreeLabel = new TextComponent(38513,"Cancel Voyage");
        this.optionFourLabel = new TextComponent(38514,"Complete Voyage");
        this.optionFiveLabel = new TextComponent(38515,"");
        this.voyageListLabels = new LinkedList<>(registerVoyageListTextComponents());
        this.voyageNameLabel = new TextComponent(38501,"Voyage Name: ");
        this.voyageDurationLabel = new TextComponent(38502,"Duration: ");
        this.seafaringLabel = new TextComponent(38503,"Voyage Seafaring: ");
        this.combatLabel = new TextComponent(38504,"Voyage Combat: ");
        this.moraleLabel = new TextComponent(38505,"Voyage Morale: ");
        this.successLabel = new TextComponent(38506,"Success Rate: ");
        this.regionLabel = new TextComponent(38507,"Region: ");
        this.distanceLabel = new TextComponent(38508,"Distance: ");
        this.buttonMap = new HashMap<>();
    }

    private final List<Voyage> voyages;
    private int selectedShipSlot, selectedVoyageSlot;
    private final TextComponent shipSlotOneLabel, shipSlotTwoLabel, shipSlotThreeLabel;
    private final TextComponent shipNameLabel;
    private final TextComponent optionOneLabel,optionTwoLabel,optionThreeLabel,optionFourLabel,optionFiveLabel;
    private final List<TextComponent> voyageListLabels;
    private final TextComponent voyageNameLabel,voyageDurationLabel,seafaringLabel,combatLabel,moraleLabel,successLabel,regionLabel,distanceLabel;
    private final Map<Integer,Button> buttonMap;

}
