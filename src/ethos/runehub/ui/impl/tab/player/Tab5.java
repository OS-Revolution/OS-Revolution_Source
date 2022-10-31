package ethos.runehub.ui.impl.tab.player;

import ethos.model.players.Player;

public class Tab5 extends InfoTab{


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

    public Tab5(Player player) {
        super(57400, player);

        registerButton(actionEvent -> refresh(),224061);
        registerButton(actionEvent -> getPlayer().sendUI(new QuestTab(player)),224058);
        registerButton(actionEvent -> getPlayer().sendUI(new AchievementTab(player)),224059);
        registerButton(actionEvent -> getPlayer().sendUI(new DistractionsTab(player)),224060);
        registerButton(actionEvent -> getPlayer().sendUI(new PlayerTabUI(player)),224057);
    }
}
