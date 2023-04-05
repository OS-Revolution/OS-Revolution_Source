package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.content.journey.Journey;
import ethos.runehub.content.journey.JourneyCache;
import ethos.runehub.content.journey.JourneyStep;
import ethos.runehub.content.journey.JourneyStepCache;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.ui.GameUI;
import ethos.runehub.ui.UIUtils;
import ethos.runehub.ui.component.impl.ProgressBarComponent;
import ethos.runehub.ui.component.impl.TextComponent;

import java.text.DecimalFormat;
import java.util.Arrays;

public class JourneyUI extends GameUI {


    @Override
    protected void onOpen() {
        this.drawPage();
        this.drawSelectedReward(this.getCurrentStepIndex());
        this.drawLevelProgress();
        passLevelLabel.setText("You are currently on step: " + (this.getCurrentStep()));
        seasonLabel.setText("");
        nextSeasonLabel.setText(this.getPlayer().getAttributes().getJourneyController().getJourneyTotalCompletion() + "% Complete");
        this.writeLine(passLevelLabel);
        this.writeLine(seasonLabel);
        this.writeLine(nextSeasonLabel);
        this.writeLine(stepDescription);
        this.writeLine(titleLabel);
        this.writeLine(headerLabel);
    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onEvent() {

    }

    private void nextPage() {
        if (currentPage < getPageCount()) {
            currentPage++;
            this.drawPage();
        }
    }

    private void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            this.drawPage();
        }
    }

    private void drawPage() {
        pageLabel.setText("Page " + currentPage + " (" + currentPage + "/" + getPageCount() + ")");
        this.drawPageItems();
        this.drawLevelProgress();
        this.writeLine(pageLabel);
    }

    private int getPageCount() {
        return (int) Math.ceil(journey.getSteps().length / 10.0);
    }

    /**
     * Sets the items displayed on the age
     */
    private void populatePageItems() {
        for (int j = 0; j < pageItems.length; j++) {
            pageItems[j] = JourneyStepCache.getInstance().read(journey.getSteps()[this.getStartingIndex() + j]).getCompletionRewards()[0];
        }
    }

    /**
     * draws the items set to the page
     */
    private void drawPageItems() {
        this.populatePageItems();
        for (int i = 0; i < pageItems.length; i++) {
            this.getPlayer().getPA().itemOnInterface(pageItems[i].getId(), pageItems[i].getAmount(), playPassLevelRewardsFrame, i);
        }
    }

    private void drawSelectedReward(int itemIndex) {
        selectedIndex = itemIndex;
        JourneyStep step = JourneyStepCache.getInstance().read(journey.getSteps()[this.getStartingIndex() + itemIndex]);
        GameItem[] items = step.getCompletionRewards();
        this.getPlayer().getPA().itemOnInterface(items[0].getId(), items[0].getAmount(), selectedRewardFrame, 1);
        if (items.length > 1 && items[1] != null) {
            this.getPlayer().getPA().itemOnInterface(items[1].getId(), items[1].getAmount(), selectedRewardFrame, 0);
        }
        if (items.length > 2 && items[2] != null) {
            this.getPlayer().getPA().itemOnInterface(items[2].getId(), items[2].getAmount(), selectedRewardFrame, 2);
        }

        stepDescription.setText(step.getDescription());
        selectedPassLevelLabel.setText("Step " + getLevel(itemIndex));
        if (selectedIndex < this.getCurrentStepIndex()) {
            selectedPassLevelProgressLabel.setText(step.getRequiredProgress() + "/" + step.getRequiredProgress() + " Complete");
        } else {
            selectedPassLevelProgressLabel.setText(this.getSelectedStepProgress(itemIndex) + "/" + step.getRequiredProgress() + " Complete");
        }

        this.drawLevelProgress();
        UIUtils.removeHighlight(stepProgressLabel[itemIndex]);
        UIUtils.highlightText("@whi@", stepProgressLabel[itemIndex]);
        this.drawClaimButtonText(itemIndex);
        this.writeLine(stepProgressLabel[itemIndex]);
        this.writeLine(selectedPassLevelLabel);
        this.writeLine(selectedPassLevelProgressLabel);
        this.writeLine(stepDescription);
    }

    private void drawClaimButtonText(int selectedIndex) {
        if (journey.getId() == this.getPlayer().getContext().getPlayerSaveData().getJourneyId()) {
            if (this.getLevel(selectedIndex) > this.getCurrentStepIndex() && !this.getPlayer().getAttributes().getJourneyController().isCurrentStepComplete()) {
                claimButtonLabel.setText("Unavailable");
            } else if (this.getPlayer().getContext().getPlayerSaveData().getClaimedJourneyStep().contains(journey.getSteps()[selectedIndex])) {
                claimButtonLabel.setText("Claimed");
            } else {
                claimButtonLabel.setText("Claim");
            }
        } else {
            claimButtonLabel.setText("Unavailable");
        }
        this.writeLine(claimButtonLabel);
    }

    /**
     * Draws the progress for each step
     */
    private void drawLevelProgress() {
        final DecimalFormat df = new DecimalFormat("###.##");
        for (int i = 0; i < stepProgressLabel.length; i++) {
            UIUtils.removeHighlight(stepProgressLabel[i]);
            if (this.getLevel(i) <= (this.getCurrentStep())) {
                this.getPlayer().getPA().sendProgressUpdate(progressBarComponents[i].getLineId(), 0, this.getProgressForStep(i));
                stepProgressLabel[i].setText(df.format(this.getProgressForStep(i)) + "%");
            } else {
                this.getPlayer().getPA().sendProgressUpdate(progressBarComponents[i].getLineId(), 0, 0);
                stepProgressLabel[i].setText(String.valueOf(this.getLevel(i)));
            }
        }
        Arrays.stream(stepProgressLabel).forEach(this::writeLine);
    }

    private int getProgressForStep(int stepIndex) {
        if (this.getCurrentStepIndex() > stepIndex) {
            return 100;
        }
        final JourneyStep step = JourneyStepCache.getInstance().read(journey.getSteps()[stepIndex]);
        final double stepProgressPercent = this.getPlayer().getAttributes().getJourneyController().getStepProgressPercent(step);
        return (int) stepProgressPercent;
    }

    private int getSelectedStepProgress(int index) {
        final int selectedStep = this.getLevel(index) - 1;
        final int totalXPForSelectedStep = JourneyStepCache.getInstance().read(journey.getSteps()[selectedStep]).getRequiredProgress();
        final int totalPlayerXP = this.getPlayer().getContext().getPlayerSaveData().getCurrentJourneyStepProgress();
        final int difference = totalXPForSelectedStep - totalPlayerXP;
        if (selectedStep <= (this.getCurrentStepIndex()) && journey.getId() == getPlayer().getContext().getPlayerSaveData().getJourneyId())
            return difference < 0 ? totalXPForSelectedStep : (totalXPForSelectedStep - difference);
        return 0;
    }

    private void claimReward() {
        if (journey.getId() == this.getPlayer().getContext().getPlayerSaveData().getJourneyId()) {
            if (!this.getPlayer().getContext().getPlayerSaveData().getClaimedJourneyStep().contains(journey.getSteps()[selectedIndex])) {
                if (this.getLevel(selectedIndex) <= this.getCurrentStepIndex() || this.getPlayer().getAttributes().getJourneyController().isCurrentStepComplete()) {
                    JourneyStep step = JourneyStepCache.getInstance().read(journey.getSteps()[selectedIndex]);
                    final GameItem[] items = step.getCompletionRewards();
                    this.getPlayer().getItems().addItemUnderAnyCircumstance(items[0].getId(), items[0].getAmount());
                    if (items[1] != null && this.getPlayer().getAttributes().isMember()) {
                        this.getPlayer().getItems().addItemUnderAnyCircumstance(items[1].getId(), items[1].getAmount());
                    }
                    if (items[2] != null && this.getPlayer().getAttributes().isMember()) {
                        this.getPlayer().getItems().addItemUnderAnyCircumstance(items[2].getId(), items[2].getAmount());
                    }
                    this.getPlayer().getContext().getPlayerSaveData().getClaimedJourneyStep().add(step.getId());
                    this.getPlayer().save();
                    this.drawClaimButtonText(selectedIndex);
                    this.getPlayer().sendMessage("You've claimed the step $" + this.getLevel(selectedIndex) + " Journey reward!");
                } else {
                    this.getPlayer().sendMessage("You've not completed this step yet.");
                }
            } else {
                this.getPlayer().sendMessage("You've already claimed this.");
            }
        } else {
            this.getPlayer().sendMessage("You can't claim reward for journeys you're not on.");
        }
    }

    private int getCurrentStepIndex() {
        if (journey.getId() == this.getPlayer().getContext().getPlayerSaveData().getJourneyId())
            return this.getPlayer().getContext().getPlayerSaveData().getJourneyStep();
        return 0;
    }

    private int getCurrentStep() {
        if (journey.getId() == this.getPlayer().getContext().getPlayerSaveData().getJourneyId())
            return this.getPlayer().getContext().getPlayerSaveData().getJourneyStep() + 1;
        return 1;
    }

    private int getLevel(int itemIndex) {
        return currentPage * 10 - (10 - (itemIndex + 1));
    }

    private int getStartingIndex() {
        return currentPage * 10 - 10;
    }

    private void registerButtons() {
        this.registerButton(actionEvent -> previousPage(), 215065);
        this.registerButton(actionEvent -> nextPage(), 215066);
        this.registerButton(actionEvent -> drawSelectedReward(0), 215067);
        this.registerButton(actionEvent -> drawSelectedReward(1), 215068);
        this.registerButton(actionEvent -> drawSelectedReward(2), 215069);
        this.registerButton(actionEvent -> drawSelectedReward(3), 215070);
        this.registerButton(actionEvent -> drawSelectedReward(4), 215071);
        this.registerButton(actionEvent -> drawSelectedReward(5), 215072);
        this.registerButton(actionEvent -> drawSelectedReward(6), 215073);
        this.registerButton(actionEvent -> drawSelectedReward(7), 215074);
        this.registerButton(actionEvent -> drawSelectedReward(8), 215075);
        this.registerButton(actionEvent -> drawSelectedReward(9), 215076);
        this.registerButton(actionEvent -> drawSelectedReward(10), 215077);
        this.registerButton(actionEvent -> claimReward(), 219140);
        this.registerButton(new UICloseActionListener(), 215064);
    }

    public JourneyUI(Player player, Journey journey) {
        super(55100, player);
        this.journey = JourneyCache.getInstance().read(journey.getId());
        this.stepDescription = new TextComponent(56224, JourneyStepCache.getInstance().read(journey.getSteps()[getCurrentStepIndex()]).getDescription());
        this.titleLabel = new TextComponent(55102, "OS-Revolution Journeys");
        this.headerLabel = new TextComponent(56222, journey.getName());
        this.seasonLabel = new TextComponent(56223);
        this.passLevelLabel = new TextComponent(56225);
        this.selectedPassLevelLabel = new TextComponent(56202);
        this.selectedPassLevelProgressLabel = new TextComponent(56203);
        this.pageLabel = new TextComponent(56200);
        this.playPassLevelRewardsFrame = 55103;
        this.selectedRewardFrame = 56206;
        this.pageItems = new GameItem[10];
        this.claimButtonLabel = new TextComponent(56205, "Unavailable");
        this.nextSeasonLabel = new TextComponent(56237);
        this.stepProgressLabel = new TextComponent[]{
                new TextComponent(56118, "Locked"),
                new TextComponent(56119, "Locked"),
                new TextComponent(56120, "Locked"),
                new TextComponent(56121, "Locked"),
                new TextComponent(56122, "Locked"),
                new TextComponent(56123, "Locked"),
                new TextComponent(56124, "Locked"),
                new TextComponent(56125, "Locked"),
                new TextComponent(56126, "Locked"),
                new TextComponent(56127, "Locked")
        };
        this.progressBarComponents = new ProgressBarComponent[]{
                new ProgressBarComponent(56238, 0),
                new ProgressBarComponent(56239, 0),
                new ProgressBarComponent(56240, 0),
                new ProgressBarComponent(56241, 0),
                new ProgressBarComponent(56242, 0),
                new ProgressBarComponent(56243, 0),
                new ProgressBarComponent(56244, 0),
                new ProgressBarComponent(56245, 0),
                new ProgressBarComponent(56246, 0),
                new ProgressBarComponent(56247, 0)
        };
        this.registerButtons();
    }

    private int selectedIndex;
    private int currentPage = 1;
    private GameItem[] pageItems;

    private final Journey journey;
    //    private final JourneyStep currentStep;
    private final int playPassLevelRewardsFrame, selectedRewardFrame;
    private final ProgressBarComponent[] progressBarComponents;
    private final TextComponent[] stepProgressLabel;
    private final TextComponent titleLabel, headerLabel, stepDescription, seasonLabel, passLevelLabel, selectedPassLevelLabel, selectedPassLevelProgressLabel, pageLabel, claimButtonLabel, nextSeasonLabel;
}
