package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.skill.support.sailing.ui.SoldTradeGoodsUI;
import ethos.runehub.ui.GameUI;

public class ShopUI extends GameUI {

    @Override
    public void open() {
        this.setShowing(true);
        this.setState(State.ACTIVE);
        this.getPlayer().getAttributes().setActiveUI(this);
        this.getPlayer().getPA().sendFrame248(64000, 3822);
        this.getPlayer().getPA().sendFrame126(name, 64003);
        this.getPlayer().getPA().sendFrame171(1, 64017);
    }

    @Override
    protected void onOpen() {

    }

    @Override
    protected void onClose() {
        if (this.getPlayer().myShopId == 50001 || this.getPlayer().myShopId == 50000) {
            this.getPlayer().sendUI(new SoldTradeGoodsUI(this.getPlayer(),this.getPlayer().getAttributes().getSelectedVoyageIndex()));
        } else {
            this.getPlayer().getPA().closeUI();
        }
    }

    @Override
    protected void onAction(int buttonId) {
//        switch (buttonId) {
//            case 250002:
////                this.close();
//                break;
//        }
    }

    @Override
    protected void onEvent() {

    }

    @Override
    public void close() {
        this.getPlayer().getAttributes().setActiveUI(null);
        this.setShowing(false);
        this.setState(State.CANCELLED);
        this.onClose();
    }

    public ShopUI(Player player, String name) {
        super(64000, player);
        this.name = name;
        this.registerButton(actionEvent -> close(),250002);
    }

    private final String name;
}
