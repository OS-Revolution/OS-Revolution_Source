package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.content.charter.Charter;
import ethos.runehub.content.charter.CharterCache;
import ethos.runehub.content.charter.Destination;
import ethos.runehub.content.charter.DestinationCache;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.Skill;
import org.runehub.api.util.math.geometry.Point;

import java.util.Arrays;

public class CharterUI extends SelectionParentUI{

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Charter a Ship");
        tabOneLabel.setText("");
        tabTwoLabel.setText("");
        tabThreeLabel.setText("");
        listTitleLabel.setText("Locations");
        rewardLabel.setText("Travel Fee");
        optionTwoLabel.setText("");
        optionThreeLabel.setText("");
        optionFourLabel.setText("");
        optionFiveLabel.setText("");
        this.fillList();
        this.updateUI();
    }

    private void selectCharter(Charter charter) {
        Destination destination = DestinationCache.getInstance().read(charter.getDestinationId());
        infoH1Label.setText(destination.getName());
        infoLine1Label.setText("Members: " + RunehubUtils.getBooleanAsYesOrNo(charter.isMembers()));
        for (int i = 0; i < charter.getFee().length; i++) {
            GameItem gameItem = new GameItem(charter.getFee()[i].getId(),charter.getFee()[i].getAmount());
            this.items[i] = gameItem;
        }
        optionOneLabel.setText("Charter Ship");
        this.registerButton(actionEvent -> {
            boolean hasFee = Arrays.stream(charter.getFee()).allMatch(item -> this.getPlayer().getItems().playerHasItem(item.getId(),item.getAmount()));
            if (hasFee) {
                Point point = destination.getArrivalArea().getAllPoints().get(Skill.SKILL_RANDOM.nextInt(destination.getArrivalArea().getAllPoints().size()));
                this.getPlayer().getPA().movePlayer(point.getX(),point.getY());
                this.getPlayer().getAttributes().getActiveUI().close();
                Arrays.stream(charter.getFee()).forEach(item -> this.getPlayer().getItems().deleteItem2(item.getId(),item.getAmount()));
            } else {
                this.getPlayer().sendMessage("You do not have the required fee.");
            }
        },150111);
        this.updateUI();
    }

    private void fillList() {
        Charter[] charters = CharterCache.getInstance().readAll().toArray(new Charter[0]);
        for (int i = 0; i < charters.length; i++) {
            Charter charter = charters[i];
            Destination destination = DestinationCache.getInstance().read(charter.getDestinationId());
            listItemLabels[i].setText(destination.getName());
            listItemButtons[i].addActionListener(actionEvent -> {
                selectCharter(charter);
            });
        }
    }

    public CharterUI(Player player) {
        super(player);
    }
}
