package ethos.runehub.content.achievement;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.world.RegionCache;
import ethos.runehub.world.RegionContextCache;
import org.runehub.api.util.math.geometry.Point;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AchievementController {

    public void completeAchievement(long id) {
        if (!player.getContext().getPlayerSaveData().getCompletedAchievements().contains(id)) {
            if (RegionCache.getInstance().read(AchievementCache.getInstance().read(id).getRegionId()).getBoundingShape().contains(new Point(
                    player.getX(),
                    player.getY()
            ))
                    && (AchievementCache.getInstance().read(id).getValidChunks().length == 0
                    || Arrays.stream(AchievementCache.getInstance().read(id).getValidChunks())
                    .anyMatch(value -> value == RunehubUtils.getRegionId(player.getX(), player.getY())))) {
                player.getContext().getPlayerSaveData().getCompletedAchievements().add(id);
                player.sendMessage("@mag@Your Achievement Diary has been updated.");
                if (this.isDiaryComplete(id)) {
                    player.sendMessage("@mag@You've completed the "
                            + RegionContextCache.getInstance().read(AchievementCache.getInstance().read(id).getRegionId()).getName()
                            + " "
                            + AchievementDifficulty.values()[AchievementCache.getInstance().read(id).getDifficulty()].toString()
                            + " achievement diary!");
                }
            }
        }
    }

    private boolean isDiaryComplete(long achievementId) {
        Achievement completedAchievement = AchievementCache.getInstance().read(achievementId);
        List<Long> achievements = AchievementCache.getInstance().readAll().stream()
                .filter(achievement -> achievement.getRegionId() == completedAchievement.getRegionId())
                .filter(achievement -> achievement.getDifficulty() == completedAchievement.getDifficulty())
                .map(Achievement::getId)
                .collect(Collectors.toList());
        return player.getContext().getPlayerSaveData().getCompletedAchievements().containsAll(achievements);
    }


    public AchievementController(Player player) {
        this.player = player;
    }

    private final Player player;
}
