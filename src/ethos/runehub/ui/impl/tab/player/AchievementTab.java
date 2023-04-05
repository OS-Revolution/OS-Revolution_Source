package ethos.runehub.ui.impl.tab.player;

import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.content.achievement.Achievement;
import ethos.runehub.content.achievement.AchievementCache;
import ethos.runehub.content.achievement.AchievementDifficulty;
import ethos.runehub.event.FixedScheduledEventController;
import ethos.runehub.ui.AchievementUI;
import ethos.runehub.ui.component.impl.ProgressBarComponent;
import ethos.runehub.ui.component.impl.TextComponent;
import org.runehub.api.util.math.MathUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AchievementTab extends InfoTab {


    @Override
    protected void onOpen() {
        Arrays.stream(areaProgressLabel).forEach(this::writeLine);
        updateProgress();
        Arrays.stream(areaProgressBar).forEach(progressBarComponent -> this.getPlayer().getPA().sendProgressUpdate(progressBarComponent.getLineId(),0,progressBarComponent.getProgress()));
    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected void refresh() {
        Arrays.stream(areaProgressLabel).forEach(this::writeLine);
        updateProgress();
        Arrays.stream(areaProgressBar).forEach(progressBarComponent -> this.getPlayer().getPA().sendProgressUpdate(progressBarComponent.getLineId(),0,progressBarComponent.getProgress()));
    }

    private void updateProgress() {
        areaProgressBar[0].setProgress(getProgress(-4977701290314478696L, AchievementDifficulty.EASY.ordinal()));
        areaProgressBar[1].setProgress(getProgress(-4977701290314478696L, AchievementDifficulty.MEDIUM.ordinal()));
        areaProgressBar[2].setProgress(getProgress(-4977701290314478696L, AchievementDifficulty.HARD.ordinal()));
    }


    private void registerAreaProgressLabels() {
        areaProgressLabel[0] = new TextComponent(startIndex + (totalChildren * 2), "Draynor Easy");
        areaProgressLabel[1] = new TextComponent(startIndex + (totalChildren * 2) + 1, "Draynor Medium");
        areaProgressLabel[2] = new TextComponent(startIndex + (totalChildren * 2) + 2, "Draynor Hard");
    }

    private void registerAreaProgressBars() {
        areaProgressBar[0] = new ProgressBarComponent(startIndex + (totalChildren), getProgress(-4977701290314478696L, AchievementDifficulty.EASY.ordinal()));
        areaProgressBar[1] = new ProgressBarComponent(startIndex + (totalChildren) + 1, getProgress(-4977701290314478696L, AchievementDifficulty.MEDIUM.ordinal()));
        areaProgressBar[2] = new ProgressBarComponent(startIndex + (totalChildren) + 2, getProgress(-4977701290314478696L, AchievementDifficulty.HARD.ordinal()));
    }

    private int getProgress(long regionId, int difficulty) {
        List<Long> achievements = AchievementCache.getInstance().readAll().stream()
                .filter(achievement -> achievement.getRegionId() == regionId)
                .filter(achievement -> achievement.getDifficulty() == difficulty)
                .map(Achievement::getId)
                .collect(Collectors.toList());
        long completed = this.getPlayer().getContext().getPlayerSaveData().getCompletedAchievements().stream()
                .filter(achievements::contains)
                .count();
        double progress = completed == 0 ? 0 : (double) completed / achievements.size();
        System.out.println("\nTotal Achievements: " + achievements.size());
        System.out.println("Completed: " + completed);
        System.out.println(AchievementDifficulty.values()[difficulty].toString() + " progress: " + progress);
        return (int) (progress * 100);
    }


    public AchievementTab(Player player) {
        super(57200, player);

        registerButton(actionEvent -> refresh(), 223115);
        registerButton(actionEvent -> getPlayer().sendUI(new QuestTab(player)), 223114);
        registerButton(actionEvent -> getPlayer().sendUI(new PlayerTabUI(player)), 223113);
        registerButton(actionEvent -> getPlayer().sendUI(new DistractionsTab(player)), 223116);
        registerButton(actionEvent -> getPlayer().sendUI(new Tab5(player)), 223117);

        registerButton(actionEvent -> getPlayer().sendUI(new AchievementUI(player,-4977701290314478696L,0)),223127);

        this.areaProgressLabel = new TextComponent[totalChildren];
        this.areaProgressBar = new ProgressBarComponent[totalChildren];
        this.registerAreaProgressLabels();
        this.registerAreaProgressBars();
    }

    private final int totalChildren = 3;
    private final int startIndex = 57215;
    private final TextComponent[] areaProgressLabel;
    private final ProgressBarComponent[] areaProgressBar;
}
