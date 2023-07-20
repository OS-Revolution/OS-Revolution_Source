package ethos.runehub.skill.support.sailing.ui;

import ethos.model.players.Player;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.skill.support.sailing.SailingUtils;
import ethos.runehub.skill.support.sailing.ship.Ship;
import ethos.runehub.skill.support.sailing.voyage.TradeGood;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.ui.impl.SelectionParentUI;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.entity.item.ItemContext;

import java.text.NumberFormat;

public class SoldTradeGoodsUI extends SelectionParentUI {

    @Override
    protected void onOpen() {
        Voyage voyage = Voyage.fromLong(this.getPlayer().getSailingSaveData().getAvailabeVoyages()[voyageIndex]);
        Ship ship = this.getPlayer().getSkillController().getSailing().getShip(slot);
        super.onOpen();
        titleLabel.setText("Voyage Trade Goods");
        listTitleLabel.setText("Trade Goods");
        rewardLabel.setText("Cargo Weights "
                + "(Outgoing: "
                + this.getPlayer().getSkillController().getSailing().getFormattedWeight(this.getPlayer().getSkillController().getSailing().getOutgoingCargoWeight() )
                + " / "
                + this.getPlayer().getSkillController().getSailing().getFormattedWeight(this.getPlayer().getSkillController().getSailing().getShip(slot).getWeightCapacity())
                + ") - "
                + "(Incoming: "
                + this.getPlayer().getSkillController().getSailing().getFormattedWeight(this.getPlayer().getSkillController().getSailing().getIncomingCargoWeight())
                + " / "
                + this.getPlayer().getSkillController().getSailing().getFormattedWeight(this.getPlayer().getSkillController().getSailing().getShip(slot).getWeightCapacity())
                + ")"
        );
        tabOneLabel.setText("Coffers: " + NumberFormat.getInstance().format(this.getPlayer().getSailingSaveData().getCoffer()) + " GP");
        tabTwoLabel.setText("Success Rate: " + SailingUtils.getSuccessRateString(voyage, ship));
        tabThreeLabel.setText("Estimated XP: " + NumberFormat.getInstance().format(this.getPlayer().getSkillController().getSailing().getVoyageXP(voyage,slot)));
        this.drawListLabels();
        this.showOpeningScreen();
        this.drawSellTradeGoodsOptions();
        this.updateUI();
    }


    protected void drawListLabels() {
        long[] encodedBuyTradeGoods = this.getPlayer().getAttributes().getSelectedBuyOffers();
        long[] encodedSellTradeGoods = this.getPlayer().getAttributes().getSelectedSellOffers();
        for (int j = 0; j < encodedBuyTradeGoods.length; j++) {
            TradeGood tradeGood = TradeGood.fromLong(encodedBuyTradeGoods[j]);
            if (tradeGood.getItemId() != 0) {
                ItemContext context = ItemIdContextLoader.getInstance().read(tradeGood.getItemId());
                listItemLabels[j].setText("@gre@" + context.getName());
                listItemButtons[j].addActionListener(actionEvent -> viewTradeGood(tradeGood,false));
                items[j] = new GameItem(tradeGood.getItemId(), tradeGood.getStock());
            }
        }
        for (int j = 0; j < encodedSellTradeGoods.length; j++) {
            int index = j + encodedBuyTradeGoods.length;
            TradeGood tradeGood = TradeGood.fromLong(encodedSellTradeGoods[j]);
            if (tradeGood.getItemId() != 0) {
                ItemContext context = ItemIdContextLoader.getInstance().read(tradeGood.getItemId());
                listItemLabels[index].setText("@red@" + context.getName());
                listItemButtons[index].addActionListener(actionEvent -> viewTradeGood(tradeGood,true));
                items[index] = new GameItem(tradeGood.getItemId(), tradeGood.getStock());
            }
        }
    }

    private void showOpeningScreen() {
        this.clearInfo();

        infoH1Label.setText("Voyage Trading");
        infoH2Label.setText("Buy and sell goods with islands you visit");
        infoLine1Label.setText("Buy Trade Goods: Select this to view what the island is selling");
        infoLine2Label.setText("Sell Trade Goods: Select this to view what the island is buying");
        infoLine3Label.setText("Sail: Select this to start the voyage");
        infoLine4Label.setText("");


        this.updateUI();
    }

    private void viewTradeGood(TradeGood tradeGood,boolean outgoing) {
        this.clearInfo();
        this.selectedTradeGood = tradeGood;

        ItemContext context = ItemIdContextLoader.getInstance().read(tradeGood.getItemId());

        infoH1Label.setText(context.getName());
        infoH2Label.setText(context.getExamine());
        infoLine1Label.setText("Shipment Type: " + (outgoing ? "@red@Outgoing" : "@gre@Incoming"));
        infoLine2Label.setText("Stock: " + NumberFormat.getInstance().format(tradeGood.getStock()));
        infoLine3Label.setText("Total Weight: " + (ItemIdContextLoader.getInstance().read(tradeGood.getItemId()).getWeight() * tradeGood.getStock()) + " Kg");
        if (outgoing) {
            infoLine4Label.setText("Total Profit: " + NumberFormat.getInstance().format((long) tradeGood.getBasePrice() * tradeGood.getStock()));
        } else {
            infoLine4Label.setText("Total Cost: " + NumberFormat.getInstance().format((long) tradeGood.getBasePrice() * tradeGood.getStock()));
        }
        Voyage voyage = Voyage.fromLong(this.getPlayer().getSailingSaveData().getAvailabeVoyages()[voyageIndex]);

        this.updateUI();
    }

    private void drawSellTradeGoodsOptions() {
        optionOneLabel.setText((this.getPlayer().getSkillController().getSailing().getShip(slot).getStatus() == Ship.Status.AVAILABLE.ordinal() ?
                "Sail" : "Dock"));
        optionTwoLabel.setText("Buy Trade Goods");
        optionThreeLabel.setText("Sell Trade Goods");
        optionFourLabel.setText("Back");
        this.registerButton(actionEvent -> {
            Ship.Status status = Ship.Status.values()[this.getPlayer().getSkillController().getSailing().getShip(slot).getStatus()];
            if (status == Ship.Status.AVAILABLE) {
                //TODO start voyage
                Voyage voyage = Voyage.fromLong(this.getPlayer().getSailingSaveData().getAvailabeVoyages()[voyageIndex]);
                this.getPlayer().getSkillController().getSailing().startVoyage(slot,voyage.toLong());
                this.close();
            } else if (status == Ship.Status.RETURN_SUCCESS) {
                //TODO claim rewards
                this.getPlayer().getSkillController().getSailing().collectVoyageTradeGoods(slot);
                this.close();
            } else if(status == Ship.Status.RETURN_FAILED) {
                //TODO penalize
            } else {
                this.getPlayer().sendMessage("Your ship is currently on a voyage.");
            }
        }, 150111);
        this.registerButton(actionEvent -> {
            MerchantCache.getInstance().read(50001).openShop(this.getPlayer());
        }, 150112);
        this.registerButton(actionEvent -> {
            MerchantCache.getInstance().read(50000).openShop(this.getPlayer());
        }, 150113);
        this.registerButton(actionEvent -> {
            selectedTradeGood = null;
            this.clearListButtons();
            this.clearUI();
//            MerchantCache.getInstance().read(50001).getMerchandise().clear();
            for (int i = 0; i < this.getPlayer().getAttributes().getSelectedBuyOffers().length; i++) {
                this.getPlayer().getAttributes().setBuyOffer(i,0);
            }

            for (int i = 0; i < this.getPlayer().getAttributes().getSelectedSellOffers().length; i++) {
                this.getPlayer().getAttributes().setSellOffer(i,0);
            }
            this.getPlayer().sendUI(new VoyageUI(this.getPlayer()));
        }, 150114);
    }

    public SoldTradeGoodsUI(Player player, int voyageIndex) {
        super(player);
        this.voyageIndex = voyageIndex;
    }

    private int slot;
    private int voyageIndex;
    private TradeGood selectedTradeGood;
}
