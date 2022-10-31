package ethos.runehub.ui.impl.tab;

import ethos.model.players.Player;
import ethos.runehub.ui.GameUI;
import ethos.runehub.ui.component.button.Button;
import ethos.runehub.ui.event.action.ActionListener;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public abstract class TabUI extends GameUI {

    protected abstract void refresh();

    @Override
    public void open() {
        this.onOpen();
        this.setShowing(true);
        this.setState(State.ACTIVE);
        this.getPlayer().getAttributes().setTabUI(this);
        this.getPlayer().setSidebarInterface(sideBarId, this);
    }

    public void action(int buttonId) {
        Logger.getGlobal().fine("Button Press on Tab: " + sideBarId + " - ActionID: " + buttonId);
        this.onAction(buttonId);
    }





    public TabUI(int id, Player player, int sideBarId) {
        super(id, player);
        this.sideBarId = sideBarId;

    }

    private final int sideBarId;
}
