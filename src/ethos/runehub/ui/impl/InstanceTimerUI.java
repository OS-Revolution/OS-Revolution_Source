package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.ui.GameUI;

public class InstanceTimerUI extends GameUI {


    @Override
    protected void onOpen() {

    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onEvent() {

    }

    public InstanceTimerUI(Player player) {
        super(47700, player);
        this.setWalkable(true);
    }
}
