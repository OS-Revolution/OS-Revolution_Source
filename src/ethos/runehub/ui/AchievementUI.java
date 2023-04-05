package ethos.runehub.ui;

import ethos.model.players.Player;
import ethos.runehub.content.achievement.Achievement;
import ethos.runehub.content.achievement.AchievementCache;
import ethos.runehub.content.achievement.AchievementDifficulty;
import ethos.runehub.ui.component.impl.TextComponent;
import ethos.runehub.ui.impl.JournalUI;
import ethos.runehub.world.RegionCache;
import ethos.runehub.world.RegionContextCache;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AchievementUI extends JournalUI {

    @Override
    public void onOpen() {
        super.onOpen();
        titleTextComponent.setText(RegionContextCache.getInstance().read(regionId).getName() + " " + AchievementDifficulty.values()[difficulty].toString() + " Diary");
        this.drawAchievements();
        this.updateUI();
    }

    private void drawAchievements() {
        List<Achievement> achievements = AchievementCache.getInstance().readAll().stream()
                .filter(achievement -> achievement.getRegionId() == regionId)
                .filter(achievement -> achievement.getDifficulty() == difficulty)
                .collect(Collectors.toList());
        for (int i = 0; i < achievements.size(); i++) {
            Achievement achievement = achievements.get(i);
            TextComponent textComponent = entryTextComponent[1 + i];
            if (this.getPlayer().getContext().getPlayerSaveData().getCompletedAchievements().contains(achievement.getId())) {
                textComponent.setText("@blu@<str=1>" + achievement.getDescription() + "</str>");
            } else {
                textComponent.setText("@blu@" + achievement.getDescription());
            }
            this.updateUI();
        }
    }

    public AchievementUI(Player player, long regionId, int difficulty) {
        super(player);
        this.regionId = regionId;
        this.difficulty = difficulty;
    }

    private final long regionId;
    private final int difficulty;
}
