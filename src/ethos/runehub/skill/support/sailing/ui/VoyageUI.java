package ethos.runehub.skill.support.sailing.ui;

import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.RunehubConstants;
import ethos.runehub.TimeUtils;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.skill.support.sailing.SailingController;
import ethos.runehub.skill.support.sailing.SailingUtils;
import ethos.runehub.skill.support.sailing.ship.Ship;
import ethos.runehub.skill.support.sailing.voyage.TradeGood;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.ui.impl.SelectionParentUI;
import ethos.runehub.world.wushanko.island.Island;
import ethos.runehub.world.wushanko.island.IslandLoader;
import ethos.runehub.world.wushanko.region.IslandRegion;
import ethos.runehub.world.wushanko.region.IslandRegionLoader;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Map;

public class VoyageUI extends SelectionParentUI {

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Voyage Selection");
        listTitleLabel.setText("Available Voyages");
        rewardLabel.setText("Buying/Selling");
        this.loadVoyageList();
        this.loadVoyageListButtons();
        this.updateUI();
    }

    @Override
    protected void onClose() {
        for (int i = 0; i < this.getPlayer().getAttributes().getSelectedBuyOffers().length; i++) {
            this.getPlayer().getAttributes().setBuyOffer(i,0);
        }
        MerchantCache.getInstance().read(50001).getMerchandise().clear();
        super.onClose();
    }

    private void updateVoyageList() {
        Arrays.stream(listItemLabels).forEach(this::writeLine);
    }

    private void selectVoyage(int voyageIndex) {
        Voyage voyage = Voyage.fromLong(this.getPlayer().getSailingSaveData().getAvailabeVoyages()[voyageIndex]);
        Island island = IslandLoader.getInstance().read(voyage.getIsland());
        IslandRegion region = IslandRegionLoader.getInstance().read(voyage.getRegion());
        Ship ship = this.getPlayer().getSkillController().getSailing().getShip(slot);
        this.getPlayer().getAttributes().setSelectedVoyageIndex(voyageIndex);
        infoH1Label.setText(region.getName() + " - " + island.getName());
        infoH2Label.setText("Base XP: " + NumberFormat.getInstance().format(this.getPlayer().getSkillController().getSailing().getVoyageXP(voyage,slot)) + " XP");
        infoLine1Label.setText("Seafaring: " + SailingUtils.getSeafaringStatString(voyage, ship));
        infoLine2Label.setText("Morale: " + SailingUtils.getMoraleStatString(voyage, ship));
        infoLine3Label.setText("Combat: " + SailingUtils.getCombatStatString(voyage, ship));
        infoLine4Label.setText("Success Rate: " + SailingUtils.getSuccessRateString(voyage, ship));
        infoLine5Label.setText("Leagues: " + NumberFormat.getInstance().format(voyage.getDistance()));
        infoLine6Label.setText("Duration: " + this.getVoyageDurationString(voyage));

        this.voyageIndex = voyageIndex;
//        this.drawCargoItems();
        this.drawTradeItems();
        this.drawDefaultOptions(voyageIndex);
        this.updateUI();
    }

    private void drawTradeItems() {
        Map<Integer,TradeGood> encodedBoughtTradeGoods = this.getPlayer().getSailingSaveData().getBoughtTradeGoods(voyageIndex);
        Map<Integer,TradeGood> encodedSoldTradeGoods = this.getPlayer().getSailingSaveData().getSoldTradeGoods(voyageIndex);


        for (int j = 0; j < encodedBoughtTradeGoods.keySet().size(); j++) {
            TradeGood tradeGood = encodedBoughtTradeGoods.get(encodedBoughtTradeGoods.keySet().toArray(new Integer[0])[j]);
            if (tradeGood.getItemId() != 0)
                items[j] = new GameItem(tradeGood.getItemId(), tradeGood.getStock());
        }

        for (int j = 0; j < encodedSoldTradeGoods.keySet().size(); j++) {
            TradeGood tradeGood = encodedSoldTradeGoods.get(encodedSoldTradeGoods.keySet().toArray(new Integer[0])[j]);
            if (tradeGood.getItemId() != 0)
                items[j + encodedBoughtTradeGoods.keySet().size()] = new GameItem(tradeGood.getItemId(), tradeGood.getStock());
        }
    }

    private void drawFastCompleteOption(int voyageIndex) {
        final long availableVoyageId = this.getPlayer().getSailingSaveData().getAvailabeVoyages()[voyageIndex];
        final long activeVoyageId = this.getPlayer().getSailingSaveData().getActiveVoyages()[slot];
        final Ship ship = this.getPlayer().getSkillController().getSailing().getShip(slot);

        if (activeVoyageId == availableVoyageId) {
            if (ship.getStatus() == Ship.Status.ON_VOYAGE.ordinal()) {
                optionTwoLabel.setText("Complete Now");
                this.registerButton(actionEvent -> {
                    this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                                    .addOptions(
                                            new DialogOption("Complete for " + RunehubConstants.COMPLETE_VOYAGE_COST + " Jewels") {
                                                @Override
                                                public void onAction() {
                                                    if (getPlayer().getItems().playerHasItem(RunehubConstants.JEWEL_ID,RunehubConstants.COMPLETE_VOYAGE_COST)) {
                                                        getPlayer().getItems().deleteItem2(RunehubConstants.JEWEL_ID,RunehubConstants.COMPLETE_VOYAGE_COST);
                                                        Server.getEventHandler().stop("voyage_ship-" + slot +"-" + getPlayer().getId());
                                                        SailingController.getInstance().completeVoyage(slot,getPlayer().getContext().getId());
                                                    } else {
                                                        getPlayer().sendMessage("You do not have enough jewels.");
                                                    }
                                                    getPlayer().getAttributes().getActiveDialogSequence().next();
                                                }
                                            },
                                            new DialogOption("Nevermind") {
                                                @Override
                                                public void onAction() {
                                                    getPlayer().getAttributes().getActiveDialogSequence().next();
                                                    getPlayer().getPA().closeAllWindows();
                                                }
                                            }
                                    )
                            .build());
                }, 150112);
            }
        } else {
            optionTwoLabel.setText("");
            this.registerButton(actionEvent -> {
            }, 150112);
        }
    }

    private void drawDefaultOptions(int voyageIndex) {
        optionOneLabel.setText("Select");
        this.registerButton(actionEvent -> {
            MerchantCache.getInstance().read(50000).getMerchandise().clear();
            MerchantCache.getInstance().read(50001).getMerchandise().clear();
            this.getPlayer().sendUI(new SoldTradeGoodsUI(this.getPlayer(), voyageIndex));
        }, 150111);
        this.drawFastCompleteOption(voyageIndex);
    }

    private void loadVoyageListButtons() {
        int amountToLoad = this.getPlayer().getAttributes().isMember() ? 5 : 3;
        for (int i = 0; i < amountToLoad; i++) {
            int index = i;
            long vId = this.getPlayer().getSailingSaveData().getAvailabeVoyages()[i];
            if (vId != 0) {
//                this.registerButton(actionEvent -> selectVoyage(index),listItemButtons[i].getLineId());
                listItemButtons[i].addActionListener(actionEvent -> selectVoyage(index));
            }
        }
    }

    private String getVoyageDurationString(Voyage voyage) {
        final long voyageDurationMS = this.getPlayer().getSkillController().getSailing().getShip(slot).getVoyageDuration(voyage);
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

    private void loadVoyageList() {
        int amountToLoad = this.getPlayer().getAttributes().isMember() ? 5 : 3;
        for (int i = 0; i < amountToLoad; i++) {
            long vId = this.getPlayer().getSailingSaveData().getAvailabeVoyages()[i];
            if (vId != 0) {
                Voyage voyage = Voyage.fromLong(vId);
                Island island = IslandLoader.getInstance().read(voyage.getIsland());
                if (Arrays.stream(this.getPlayer().getSailingSaveData().getActiveVoyages()).anyMatch(value -> value == vId)
                        && this.getPlayer().getSkillController().getSailing().getShip(slot).getStatus() == Ship.Status.ON_VOYAGE.ordinal()) {
                    listItemLabels[i].setText("@yel@" + island.getName());
                } else if (Arrays.stream(this.getPlayer().getSailingSaveData().getActiveVoyages()).anyMatch(value -> value == vId)
                        && this.getPlayer().getSkillController().getSailing().getShip(slot).getStatus() != Ship.Status.AVAILABLE.ordinal()) {
                    listItemLabels[i].setText("@gre@" + island.getName());
                } else {
                    listItemLabels[i].setText(island.getName());
                }
            }
        }
        updateVoyageList();
    }

    public VoyageUI(Player player) {
        super(player);

    }

    private int slot;
    private int voyageIndex;
}
