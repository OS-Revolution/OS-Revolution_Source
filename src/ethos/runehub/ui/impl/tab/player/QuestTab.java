package ethos.runehub.ui.impl.tab.player;

import ethos.model.players.Player;

public class QuestTab extends InfoTab{


    @Override
    protected void onOpen() {

    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected void refresh() {

    }

    public QuestTab(Player player) {
        super(57100, player);

        registerButton(actionEvent -> refresh(),223014);
        registerButton(actionEvent -> getPlayer().sendUI(new PlayerTabUI(player)),223013);
        registerButton(actionEvent -> getPlayer().sendUI(new AchievementTab(player)),223015);
        registerButton(actionEvent -> getPlayer().sendUI(new DistractionsTab(player)),223016);
        registerButton(actionEvent -> getPlayer().sendUI(new Tab5(player)),223017);
    }
}
