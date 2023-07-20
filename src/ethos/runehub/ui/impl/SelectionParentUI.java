package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.ui.GameUI;
import ethos.runehub.ui.component.button.Button;
import ethos.runehub.ui.component.impl.TextComponent;

import java.util.Arrays;

public class SelectionParentUI extends GameUI {

    @Override
    protected void onOpen() {
        this.clearUI();
        this.clearListButtons();
        this.registerButton(new UICloseActionListener(), 148114);
    }

    @Override
    protected void onClose() {
        this.clearUI();
    }

    @Override
    protected void onEvent() {

    }

    protected void updateUI() {
        this.writeLine(titleLabel);
        this.writeLine(tabOneLabel);
        this.writeLine(tabTwoLabel);
        this.writeLine(tabThreeLabel);
        this.writeLine(listTitleLabel);
        this.writeLine(rewardLabel);
        this.writeLine(optionOneLabel);
        this.writeLine(optionTwoLabel);
        this.writeLine(optionThreeLabel);
        this.writeLine(optionFourLabel);
        this.writeLine(optionFiveLabel);
        this.writeLine(infoH1Label);
        this.writeLine(infoH2Label);
        this.writeLine(infoLine1Label);
        this.writeLine(infoLine2Label);
        this.writeLine(infoLine3Label);
        this.writeLine(infoLine4Label);
        this.writeLine(infoLine5Label);
        this.writeLine(infoLine6Label);
        Arrays.stream(listItemLabels).forEach(this::writeLine);

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null)
                this.getPlayer().getPA().itemOnInterface(items[i].getId(), items[i].getAmount(), 38521, i);
        }
        for (int i = 0; i < listItemButtons.length; i++) {
            if (listItemButtons[i] != null && !listItemButtons[i].getActionListeners().isEmpty()) {
                this.registerButton(listItemButtons[i]);
            }
        }
    }

    protected void clearListButtons() {
        for (int i = 0; i < listItemButtons.length; i++) {
            listItemButtons[i].getActionListeners().clear();
        }
    }

    protected void resetUI() {
        titleLabel.setText("Line ID: " + titleLabel.getLineId());
        tabOneLabel.setText("Line ID: " + tabOneLabel.getLineId());
        tabTwoLabel.setText("Line ID: " + tabTwoLabel.getLineId());
        tabThreeLabel.setText("Line ID: " + tabThreeLabel.getLineId());
        listTitleLabel.setText("Line ID: " + listTitleLabel.getLineId());
        rewardLabel.setText("Line ID: " + rewardLabel.getLineId());
        optionOneLabel.setText("Line ID: " + optionOneLabel.getLineId());
        optionTwoLabel.setText("Line ID: " + optionTwoLabel.getLineId());
        optionThreeLabel.setText("Line ID: " + optionThreeLabel.getLineId());
        optionFourLabel.setText("Line ID: " + optionFourLabel.getLineId());
        optionFiveLabel.setText("Line ID: " + optionFiveLabel.getLineId());
        infoH1Label.setText("Line ID: " + infoH1Label.getLineId());
        infoH2Label.setText("Line ID: " + infoH2Label.getLineId());
        infoLine1Label.setText("Line ID: " + infoLine1Label.getLineId());
        infoLine2Label.setText("Line ID: " + infoLine2Label.getLineId());
        infoLine3Label.setText("Line ID: " + infoLine3Label.getLineId());
        infoLine4Label.setText("Line ID: " + infoLine4Label.getLineId());
        infoLine5Label.setText("Line ID: " + infoLine5Label.getLineId());
        infoLine6Label.setText("Line ID: " + infoLine6Label.getLineId());
        Arrays.stream(listItemLabels).forEach(textComponent -> textComponent.setText("Line ID: " + textComponent.getLineId()));
        for (int i = 0; i < items.length; i++) {
            items[i] = new GameItem(-1, 0);
        }
        this.updateUI();
    }

    protected void clearList() {
        Arrays.stream(listItemLabels).forEach(textComponent -> textComponent.setText(""));
    }

    protected void clearUI() {
        titleLabel.setText("");
        tabOneLabel.setText("");
        tabTwoLabel.setText("");
        tabThreeLabel.setText("");
        listTitleLabel.setText("");
        rewardLabel.setText("");
        optionOneLabel.setText("");
        optionTwoLabel.setText("");
        optionThreeLabel.setText("");
        optionFourLabel.setText("");
        optionFiveLabel.setText("");
        infoH1Label.setText("");
        infoH2Label.setText("");
        infoLine1Label.setText("");
        infoLine2Label.setText("");
        infoLine3Label.setText("");
        infoLine4Label.setText("");
        infoLine5Label.setText("");
        infoLine6Label.setText("");
        Arrays.stream(listItemLabels).forEach(textComponent -> textComponent.setText(""));
        this.clearItems();
        this.updateUI();
    }

    protected void clearInfo() {
        infoH1Label.setText("");
        infoH2Label.setText("");
        infoLine1Label.setText("");
        infoLine2Label.setText("");
        infoLine3Label.setText("");
        infoLine4Label.setText("");
        infoLine5Label.setText("");
        infoLine6Label.setText("");
        this.updateUI();
    }

    protected void clearItems() {
        for (int i = 0; i < items.length; i++) {
            items[i] = new GameItem(-1, 0);
        }
    }

    private void createListItemLabels() {
        final int start = 38037;
        for (int i = start; i < (start + 100); i++) {
            listItemLabels[(i - start)] = new TextComponent(i);
        }
    }

    private void createListItemButtons() {
        final int start = 148149;
        for (int i = start; i < (start + 100); i++) {
            listItemButtons[(i - start)] = new Button(i);
        }
    }

    public SelectionParentUI(Player player) {
        super(38000, player);
        this.titleLabel = new TextComponent(38005);
        this.tabOneLabel = new TextComponent(48018);
        this.tabTwoLabel = new TextComponent(48019);
        this.tabThreeLabel = new TextComponent(48020);
        this.listTitleLabel = new TextComponent(38026);
        this.rewardLabel = new TextComponent(38550);
        this.optionOneLabel = new TextComponent(38511);
        this.optionTwoLabel = new TextComponent(38512);
        this.optionThreeLabel = new TextComponent(38513);
        this.optionFourLabel = new TextComponent(38514);
        this.optionFiveLabel = new TextComponent(38515);
        this.infoH1Label = new TextComponent(38501);
        this.infoH2Label = new TextComponent(38502);
        this.infoLine1Label = new TextComponent(38503);
        this.infoLine2Label = new TextComponent(38504);
        this.infoLine3Label = new TextComponent(38505);
        this.infoLine4Label = new TextComponent(38506);
        this.infoLine5Label = new TextComponent(38507);
        this.infoLine6Label = new TextComponent(38508);
        this.listItemLabels = new TextComponent[100];
        this.items = new GameItem[20];
        this.listItemButtons = new Button[100];
        this.createListItemLabels();
        this.createListItemButtons();
    }

    protected final TextComponent titleLabel, tabOneLabel, tabTwoLabel, tabThreeLabel, listTitleLabel, rewardLabel,
            optionOneLabel, optionTwoLabel, optionThreeLabel, optionFourLabel, optionFiveLabel,
            infoH1Label, infoH2Label, infoLine1Label, infoLine2Label, infoLine3Label, infoLine4Label, infoLine5Label, infoLine6Label;
    protected final TextComponent[] listItemLabels;
    protected final GameItem[] items;
    protected final Button[] listItemButtons;
}
