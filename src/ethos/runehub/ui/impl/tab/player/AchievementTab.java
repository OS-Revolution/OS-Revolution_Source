package ethos.runehub.ui.impl.tab.player;

import ethos.model.players.Player;

public class AchievementTab extends InfoTab{


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

    public AchievementTab(Player player) {
        super(57200, player);

        registerButton(actionEvent -> refresh(),223115);
        registerButton(actionEvent -> getPlayer().sendUI(new QuestTab(player)),223114);
        registerButton(actionEvent -> getPlayer().sendUI(new PlayerTabUI(player)),223113);
        registerButton(actionEvent -> getPlayer().sendUI(new DistractionsTab(player)),223116);
        registerButton(actionEvent -> getPlayer().sendUI(new Tab5(player)),223117);
    }
}
