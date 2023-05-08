package ethos.runehub.ui.impl.sailing;

import ethos.model.players.Player;
import ethos.runehub.LootTableContainerUtils;
import ethos.runehub.TimeUtils;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.support.sailing.ship.Ship;
import ethos.runehub.skill.support.sailing.ship.ShipLoader;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.skill.support.sailing.voyage.VoyageDAO;
import ethos.runehub.ui.impl.SelectionParentUI;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.ContainerType;
import org.runehub.api.model.entity.item.loot.Loot;

import java.util.ArrayList;
import java.util.List;

public class PortUI extends SelectionParentUI {

    @Override
    protected void onOpen() {
        super.onOpen();
        this.fillListItems();
        titleLabel.setText("Sailing");
        tabOneLabel.setText(this.getShipNameForSlot(0));
        tabTwoLabel.setText(this.getShipNameForSlot(1));
        tabThreeLabel.setText(this.getShipNameForSlot(2));
        listTitleLabel.setText("Voyages");
        rewardLabel.setText("Possible Rewards");
        optionTwoLabel.setText("");
        optionThreeLabel.setText("");
        optionFourLabel.setText("");
        optionFiveLabel.setText("");
        highlightSelectedSlot();
        this.updateUI();
    }

    @Override
    protected void updateUI() {
        highlightSelectedSlot();
        writeOptionText();
        super.updateUI();
    }

    private void writeOptionText() {
        int shipStatus = getPlayer().getSkillController().getSailing2().getShipController(selectedSlot).getStatus();
        switch (shipStatus) {
            case 0:
                optionOneLabel.setText("Start Voyage");
                this.registerButton(actionEvent -> {
                    this.getPlayer().getSkillController().getSailing2().assignShipSlotToVoyage(selectedSlot,selectedVoyage.getId());
                    this.writeOptionText();
                },150111);
                break;
            case 2:
                optionOneLabel.setText("Claim Rewards");
                break;
            case 3:
                optionOneLabel.setText("Repair Ship");
                break;
            default:
                optionOneLabel.setText("");
                break;
        }
    }

    private void onListItemAction(Voyage voyage) {
        selectedVoyage = voyage;
        infoH1Label.setText(voyage.getName());
        infoH2Label.setText(TimeUtils.getDurationString(this.getPlayer().getSkillController().getSailing2().getShipController(selectedSlot).getVoyageDuration(voyage.getId())));
        infoLine1Label.setText("Seafaring: " + voyage.getSeafaring());
        infoLine2Label.setText("Morale: " + voyage.getMorale());
        infoLine3Label.setText("Combat: " + voyage.getCombat());
        infoLine4Label.setText("Distance: " + voyage.getDistance() + " leagues");
        infoLine5Label.setText("Success Rate: " + this.getPlayer().getSkillController().getSailing2().getSuccessRatePrint(
                selectedSlot,voyage
        ));
        this.sendRewardItems(voyage);
        this.highlistSelectedListItem(voyage.getId());
        this.writeOptionText();
        this.updateUI();
    }

    private void sendRewardItems(Voyage voyage) {
        this.clearItems();
        final List<Loot> lootList = this.getPossibleLoot(voyage);
        for (int i = 0; i < items.length; i++) {
            if (lootList.size() > i) {
                items[i] = new GameItem((int) lootList.get(i).getId(), (int) lootList.get(i).getAmount());
            }
        }
    }

    private List<Loot> getPossibleLoot(Voyage voyage) {
        final List<Loot> lootList = new ArrayList<>();
        final int[] containers = voyage.getContext().getContainerIds();
        for (int i = 0; i < containers.length; i++) {
            LootTableContainerUtils.getLootTableContainer(containers[i], ContainerType.ITEM).ifPresent(container -> {
                container.getLootTables().forEach(table -> {
                    LootTableLoader.getInstance().read(table.getId()).getLootTableEntries().forEach(lootTableEntry -> {
                        lootList.add(new Loot(lootTableEntry.getId(), lootTableEntry.getAmount().getMax(), lootTableEntry.getChance(), table.getId()));
                    });
                });
            });
        }
        return lootList;
    }

    private String getShipNameForSlot(int slot) {
        final int shipId = ethos.runehub.skill.support.sailing.Ship.fromBitArray(this.getPlayer().getSailingSaveData().getShipSlot()[slot]).getId();
        if (shipId > 0) {
            return ShipLoader.getInstance().read(shipId).getName();
        }
        return "Empty";
    }

    private void fillListItems() {
        for (int i = 0; i < this.getPlayer().getSailingSaveData().getAvailabeVoyages().length; i++) {
            if (this.getPlayer().getSailingSaveData().getAvailabeVoyages()[i] != 0) {
                final int index = i;
                listItemLabels[i].setText(VoyageDAO.getInstance().read(this.getPlayer().getSailingSaveData().getAvailabeVoyages()[i]).getName());
                listItemButtons[i].addActionListener(actionEvent -> {
                    onListItemAction(VoyageDAO.getInstance().read(this.getPlayer().getSailingSaveData().getAvailabeVoyages()[index]));
                });
            }
        }
    }

    private void highlistSelectedListItem(int index) {
        for (int i = 0; i < this.getPlayer().getSailingSaveData().getAvailabeVoyages().length; i++) {
            if (this.getPlayer().getSailingSaveData().getAvailabeVoyages()[i] != 0 && this.getPlayer().getSailingSaveData().getAvailabeVoyages()[i] != index) {
                listItemLabels[i].setText(VoyageDAO.getInstance().read(this.getPlayer().getSailingSaveData().getAvailabeVoyages()[i]).getName());
            } else if (this.getPlayer().getSailingSaveData().getAvailabeVoyages()[i] == index) {
                listItemLabels[i].setText(MarkupUtils.highlightText("@whi@", VoyageDAO.getInstance().read(this.getPlayer().getSailingSaveData().getAvailabeVoyages()[i]).getName()));
            }
        }
    }

    private void highlightSelectedSlot() {
        tabOneLabel.setText(this.getShipNameForSlot(0));
        tabTwoLabel.setText(this.getShipNameForSlot(1));
        tabThreeLabel.setText(this.getShipNameForSlot(2));

        if (selectedSlot == 0) {
            tabOneLabel.setText(MarkupUtils.highlightText("@whi@", this.getShipNameForSlot(0)));
        } else if (selectedSlot == 1) {
            tabTwoLabel.setText(MarkupUtils.highlightText("@whi@", this.getShipNameForSlot(1)));
        } else {
            tabThreeLabel.setText(MarkupUtils.highlightText("@whi@", this.getShipNameForSlot(2)));
        }
    }

    private void changeSlot(int id) {
        switch (id) {
            case 187134:
                selectedSlot = 0;
                break;
            case 187135:
                selectedSlot = 1;
                break;
            default:
                selectedSlot = 2;
                break;
        }
        if (selectedVoyage != null)
            onListItemAction(selectedVoyage);
        writeOptionText();
        highlightSelectedSlot();
    }

    public PortUI(Player player) {
        super(player);
        this.registerButton(actionEvent -> changeSlot(187134), 187134);
        this.registerButton(actionEvent -> changeSlot(187135), 187135);
        this.registerButton(actionEvent -> changeSlot(187136), 187136);
    }

    private int selectedSlot;
    private Voyage selectedVoyage;
}
