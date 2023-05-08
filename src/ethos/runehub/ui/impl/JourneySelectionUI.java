package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.content.journey.Journey;
import ethos.runehub.content.journey.JourneyCache;
import ethos.runehub.content.journey.JourneyPath;
import ethos.runehub.content.journey.JourneyPathCache;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.markup.MarkupUtils;

public class JourneySelectionUI extends SelectionParentUI {

    @Override
    protected void onOpen() {
        super.onOpen();
        this.registerButton(actionEvent -> handleOptionOne(), 150111);
        this.registerButton(actionEvent -> handleOptionTwo(), 150112);
        this.drawPathsUI();
    }

    private void selectJourney(Journey journey) {
        this.selectedJourney = journey;
        infoH1Label.setText(journey.getName());
        infoH2Label.setText("Required Journeys");
        for (int i = 0; i < Math.min(journey.getRequiredJourney().length, 5); i++) {
            String name = JourneyCache.getInstance().read(journey.getRequiredJourney()[i]).getName();
            switch (i) {
                case 0:
                    infoLine1Label.setText(name);
                    break;
                case 1:
                    infoLine2Label.setText(name);
                    break;
                case 2:
                    infoLine3Label.setText(name);
                    break;
                case 3:
                    infoLine4Label.setText(name);
                    break;
                case 4:
                    infoLine5Label.setText(name);
                    break;
            }
        }
//        this.registerButton(actionEvent -> this.getPlayer().sendUI(new JourneyUI(this.getPlayer(), selectedJourney)), 150111);
//        this.registerButton(actionEvent -> this.getPlayer().getAttributes().getJourneyController().startJourney(selectedJourney), 150112);
        infoLine6Label.setText("Total Steps: " + journey.getSteps().length);
        this.updateUI();
    }

    private void selectPath(JourneyPath path) {
        this.selectedPath = path;
        infoH1Label.setText(path.getName());
        infoH2Label.setText("Required Paths");
        for (int i = 0; i < Math.min(path.getRequirement().length, 5); i++) {
            String name = JourneyPathCache.getInstance().read(path.getRequirement()[i]).getName();
            switch (i) {
                case 0:
                    infoLine1Label.setText(name);
                    break;
                case 1:
                    infoLine2Label.setText(name);
                    break;
                case 2:
                    infoLine3Label.setText(name);
                    break;
                case 3:
                    infoLine4Label.setText(name);
                    break;
                case 4:
                    infoLine5Label.setText(name);
                    break;
            }
        }
        infoLine6Label.setText("Total Journeys: " + path.getJourney().length);
        optionOneLabel.setText("View Path");
        optionTwoLabel.setText("Unlock Path");


        items[0] = new GameItem(7478, 1);
        this.updateUI();
    }


    private void drawJourneyList() {
        this.clearList();
        final int[] journeys = selectedPath.getJourney();
        for (int i = 0; i < journeys.length; i++) {
            Journey journey = JourneyCache.getInstance().read(journeys[i]);
            if (this.getPlayer().getContext().getPlayerSaveData().getCompetedJourney().contains(journey.getId())) {
                listItemLabels[i].setText(MarkupUtils.highlightText(MarkupUtils.GREEN, journey.getName()));
            } else if (this.getPlayer().getContext().getPlayerSaveData().getJourneyId() == journey.getId()) {
                listItemLabels[i].setText(MarkupUtils.highlightText(MarkupUtils.YELLOW, journey.getName()));
            } else {
                listItemLabels[i].setText(MarkupUtils.highlightText(MarkupUtils.RED, journey.getName()));
            }
            listItemButtons[i].getActionListeners().clear();
            listItemButtons[i].addActionListener(actionEvent -> selectJourney(journey));
        }
    }

    private void drawPathList() {
        this.clearList();
        final JourneyPath[] paths = JourneyPathCache.getInstance().readAll().toArray(new JourneyPath[0]);
        for (int i = 0; i < paths.length; i++) {
            JourneyPath path = paths[i];
            if (this.getPlayer().getContext().getPlayerSaveData().getCompletedPath().contains(path.getId())) {
                listItemLabels[i].setText(MarkupUtils.highlightText(MarkupUtils.GREEN, path.getName()));
            } else if (this.getPlayer().getContext().getPlayerSaveData().getUnlockedPath().contains(path.getId())) {
                listItemLabels[i].setText(MarkupUtils.highlightText(MarkupUtils.YELLOW, path.getName()));
            } else {
                listItemLabels[i].setText(MarkupUtils.highlightText(MarkupUtils.RED, path.getName()));
            }
            listItemButtons[i].getActionListeners().clear();
            listItemButtons[i].addActionListener(actionEvent -> selectPath(path));
        }
    }


    private void drawPathUI() {
        titleLabel.setText("Journey Paths");
        tabOneLabel.setText("Back");
        tabTwoLabel.setText(selectedPath.getName());
        tabThreeLabel.setText("");
        listTitleLabel.setText("Journeys");
        rewardLabel.setText("Cost to Start");
        optionOneLabel.setText("View Journey");
        optionTwoLabel.setText("Start Journey");
        optionThreeLabel.setText("");
        optionFourLabel.setText("");
        optionFiveLabel.setText("");
        infoH1Label.setText("Select a Journey");
        infoH2Label.setText("View details on individual journeys");
        infoLine1Label.setText("");
        infoLine2Label.setText("");
        infoLine3Label.setText("");
        infoLine4Label.setText("");
        infoLine5Label.setText("");
        infoLine6Label.setText("");
        this.registerButton(actionEvent -> {
            drawPathsUI();
            selectPath(selectedPath);
            selectedJourney = null;
        }, 187134);
        this.drawJourneyList();
        this.clearItems();
        this.updateUI();
    }

    private void drawPathsUI() {
        titleLabel.setText("Journey Paths");
        tabOneLabel.setText("");
        tabTwoLabel.setText("");
        tabThreeLabel.setText("");
        listTitleLabel.setText("Journey Paths");
        rewardLabel.setText("Cost to Start");
        optionOneLabel.setText("");
        optionTwoLabel.setText("");
        optionThreeLabel.setText("");
        optionFourLabel.setText("");
        optionFiveLabel.setText("");
        infoH1Label.setText("Select a Journey Path");
        infoH2Label.setText("View the path to see the individual journeys");
        infoLine1Label.setText("");
        infoLine2Label.setText("");
        infoLine3Label.setText("");
        infoLine4Label.setText("");
        infoLine5Label.setText("");
        infoLine6Label.setText("");
        this.drawPathList();


//        this.registerButton(actionEvent -> this.getPlayer().getAttributes().getJourneyController().unlockPath(selectedPath), 150112);
        this.updateUI();
    }

    private void handleOptionOne() {
        if (selectedJourney == null) {
            drawPathUI();
            selectJourney(JourneyCache.getInstance().read(selectedPath.getJourney()[0]));
        } else {
            this.getPlayer().sendUI(new JourneyUI(this.getPlayer(), selectedJourney));
        }
    }

    private void handleOptionTwo() {
        if (selectedJourney == null) {
            this.getPlayer().getAttributes().getJourneyController().unlockPath(selectedPath);
            this.drawPathList();
        } else {
            this.getPlayer().getAttributes().getJourneyController().startJourney(selectedJourney);
            this.drawJourneyList();
        }
        this.updateUI();
    }

    private void handleListAction(Object obj) {
        if (selectedJourney == null) {
            this.selectPath((JourneyPath) obj);
        } else {
            this.selectJourney((Journey) obj);
        }
    }

    public JourneySelectionUI(Player player) {
        super(player);

    }

    private JourneyPath selectedPath;
    private Journey selectedJourney;

}
