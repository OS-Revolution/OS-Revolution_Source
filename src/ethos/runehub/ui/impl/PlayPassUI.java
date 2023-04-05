package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.LootTableContainerUtils;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.ui.GameUI;
import ethos.runehub.ui.UIUtils;
import ethos.runehub.ui.component.button.Button;
import ethos.runehub.ui.component.impl.ProgressBarComponent;
import ethos.runehub.ui.component.impl.TextComponent;
import ethos.runehub.ui.event.action.ActionListener;
import ethos.runehub.world.WorldSettingsController;
import org.runehub.api.util.StringUtils;
import org.runehub.api.util.math.MathUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlayPassUI extends GameUI {


    @Override
    protected void onOpen() {
        this.registerButton(new UICloseActionListener(), 215064);
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
        this.drawPage();
        this.drawSelectedReward(0);
        this.drawLevelProgress();
        passLevelLabel.setText("You are currently level: " + this.getPlayPassLevel());
        seasonLabel.setText("Season " + WorldSettingsController.getInstance().getWorldSettings().getActiveSeason());
        nextSeasonLabel.setText("Next Season in " + WorldSettingsController.getInstance().getTimeUntilNextSeason());
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
        if (currentPage < 5) {
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
        pageLabel.setText("Page " + currentPage + " (" + currentPage + "/5)");
        this.populatePageItems();
        this.drawPageItems();
        this.drawLevelProgress();
        this.writeLine(pageLabel);
    }

    private void populatePageItems() {
        for (int j = 0; j < pageItems.length; j++) {
            pageItems[j] = WorldSettingsController.getInstance().getWorldSettings().getActiveSeasonLoot()[this.getStartingIndex() + j][0];
        }
    }

    private void drawPageItems() {
        for (int i = 0; i < pageItems.length; i++) {
            this.getPlayer().getPA().itemOnInterface(pageItems[i].getId(), pageItems[i].getAmount(), playPassLevelRewardsFrame, i);
        }
    }

    private void drawSelectedReward(int itemIndex) {
        selectedIndex = itemIndex;
        GameItem[] items = WorldSettingsController.getInstance().getWorldSettings().getActiveSeasonLoot()[this.getStartingIndex() + itemIndex];
        this.getPlayer().getPA().itemOnInterface(items[0].getId(), items[0].getAmount(), selectedRewardFrame, 1);
        if (items.length > 1 && items[1] != null) {
            this.getPlayer().getPA().itemOnInterface(items[1].getId(), items[1].getAmount(), selectedRewardFrame, 0);
        }
        if (items.length > 2 && items[2] != null) {
            this.getPlayer().getPA().itemOnInterface(items[2].getId(), items[2].getAmount(), selectedRewardFrame, 2);
        }

        selectedPassLevelLabel.setText("Level " + getLevel(itemIndex));
        selectedPassLevelProgressLabel.setText(this.getSelectedLevelProgress(itemIndex) + "/10000 XP");
        this.drawLevelProgress();
        UIUtils.removeHighlight(levelProgressLabel[itemIndex]);
        UIUtils.highlightText("@whi@", levelProgressLabel[itemIndex]);
        this.drawClaimButtonText(itemIndex);
        this.writeLine(levelProgressLabel[itemIndex]);
        this.writeLine(selectedPassLevelLabel);
        this.writeLine(selectedPassLevelProgressLabel);
    }

    private void drawClaimButtonText(int selectedIndex) {

        if (this.getLevel(selectedIndex) > this.getPlayPassLevel()) {
            claimButtonLabel.setText("Unavailable");
        } else if (this.getPlayer().getContext().getPlayerSaveData().getClaimedPassLevel()[selectedIndex]) {
            claimButtonLabel.setText("Claimed");
        } else {
            claimButtonLabel.setText("Claim");
        }
        this.writeLine(claimButtonLabel);
    }

    private void drawLevelProgress() {

        final DecimalFormat df = new DecimalFormat("###.##");

        for (int i = 0; i < levelProgressLabel.length; i++) {

            UIUtils.removeHighlight(levelProgressLabel[i]);
            final double progress = (this.getSelectedLevelProgress(i) / 10000D) * 100;
            this.getPlayer().getPA().sendProgressUpdate(progressBarComponents[i].getLineId(),0, (int) progress);
            if (this.getLevel(i) <= (this.getPlayPassLevel() + 1)) {

//                System.out.println("Level: " + this.getLevel(i) + " Progress: " + progress + " Value: " + this.getSelectedLevelProgress(i));
                levelProgressLabel[i].setText(df.format(progress) + "%");
            } else {
                levelProgressLabel[i].setText(String.valueOf(this.getLevel(i)));
            }
        }

        Arrays.stream(levelProgressLabel).forEach(this::writeLine);
    }

    private int getSelectedLevelProgress(int index) {
        final int selectedLevel = this.getLevel(index);
        final int totalXPForSelectedLevel = selectedLevel * 10000;
        final int totalPlayerXP = this.getPlayer().getContext().getPlayerSaveData().getPlayPassXp();
        final int difference = totalXPForSelectedLevel - totalPlayerXP;
        if (selectedLevel <= (this.getPlayPassLevel() + 1))
            return difference < 0 ? 10000 : (10000 - difference);
        return 0;
    }

    private void claimReward() {
        if (!this.getPlayer().getContext().getPlayerSaveData().getClaimedPassLevel()[selectedIndex]) {
            if (this.getPlayPassLevel() >= this.getLevel(selectedIndex)) {
                final GameItem[] items = WorldSettingsController.getInstance().getWorldSettings().getActiveSeasonLoot()[this.getLevel(selectedIndex) - 1];
                this.getPlayer().getItems().addItemUnderAnyCircumstance(items[0].getId(), items[0].getAmount());
                if (items[1] != null && this.getPlayer().getAttributes().isMember()) {
                    this.getPlayer().getItems().addItemUnderAnyCircumstance(items[1].getId(), items[1].getAmount());
                }
                if (items[2] != null && this.getPlayer().getAttributes().isMember()) {
                    this.getPlayer().getItems().addItemUnderAnyCircumstance(items[2].getId(), items[2].getAmount());
                }
                this.getPlayer().getContext().getPlayerSaveData().updateClaimedPassLevel(selectedIndex, true);
                this.getPlayer().save();
                this.drawClaimButtonText(selectedIndex);
                this.getPlayer().sendMessage("You've claimed the $Play $Pass level $" + this.getLevel(selectedIndex) + " reward!");
            } else {
                this.getPlayer().sendMessage("You've not leveled up your Play Pass enough.");
            }
        } else {
            this.getPlayer().sendMessage("You've already claimed this.");
        }
    }

    private int getPlayPassLevel() {
        return this.getPlayer().getContext().getPlayerSaveData().getPlayPassXp() / 10000;
    }

    private int getNextLevelProgress() {
        return (int) Math.round(((this.getPlayer().getContext().getPlayerSaveData().getPlayPassXp() / 10000D) - this.getPlayPassLevel()) * 10000);
    }

    private int getLevel(int itemIndex) {
        return currentPage * 10 - (10 - (itemIndex + 1));
    }

    private int getStartingIndex() {
        return currentPage * 10 - 10;
    }

    public PlayPassUI(Player player) {
        super(55100, player);
        this.stepDescription = new TextComponent(56224,"Level up your Play Pass passively by training skills, fighting enemies, completing tasks, and playing minigames. With each levelup you will be able to claim a reward. Members may be able to claim additional rewards. Members will also receives a +20% Play Pass XP Boost.");
        this.titleLabel = new TextComponent(55102,"OS-Revolution Play-Pass");
        this.headerLabel = new TextComponent(56222, "Welcome to the OS-Revolution Play-Pass");
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
        this.levelProgressLabel = new TextComponent[]{
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
        this.progressBarComponents = new ProgressBarComponent[] {
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
    }

    private int selectedIndex;
    private int currentPage = 1;
    private GameItem[] pageItems;


    private final int playPassLevelRewardsFrame, selectedRewardFrame;
    private final ProgressBarComponent[] progressBarComponents;
    private final TextComponent[] levelProgressLabel;
    private final TextComponent titleLabel,headerLabel,stepDescription,seasonLabel, passLevelLabel, selectedPassLevelLabel, selectedPassLevelProgressLabel, pageLabel, claimButtonLabel, nextSeasonLabel;
}
