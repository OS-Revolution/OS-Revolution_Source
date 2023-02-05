package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.world.WorldSettings;
import ethos.runehub.world.WorldSettingsController;

import java.time.Duration;

public class SecondClickSkillStationAction extends ClickNodeAction
{

    @Override
    public void perform() {
        player.sendMessage(this.getExpirationString(this.getExpiration()));
    }

    private String getExpirationString(Duration duration) {
        if (duration.isNegative() || duration.isZero())
            return "This station is inactive.";
        return "This station is active for another " + TimeUtils.getDurationString(duration);
    }

    private Duration getExpiration() {
        try {
            switch (nodeId) {
                case 6799:
                    return TimeUtils.getDurationFromMS(WorldSettingsController.getInstance().getWorldSettings().getSkillStationExpirationTimeMS()[WorldSettings.CRAFTING_STATION].value());
                case 13542:
                    return TimeUtils.getDurationFromMS(WorldSettingsController.getInstance().getWorldSettings().getSkillStationExpirationTimeMS()[WorldSettings.COOKING_STATION].value());
                case 878:
                    return TimeUtils.getDurationFromMS(WorldSettingsController.getInstance().getWorldSettings().getSkillStationExpirationTimeMS()[WorldSettings.HERBLORE_STATION].value());
                case 15468:
                    return TimeUtils.getDurationFromMS(WorldSettingsController.getInstance().getWorldSettings().getSkillStationExpirationTimeMS()[WorldSettings.SAWMILL_STATION].value());
                case 11017:
                    return TimeUtils.getDurationFromMS(WorldSettingsController.getInstance().getWorldSettings().getSkillStationExpirationTimeMS()[WorldSettings.FIREMAKING_STATION].value());
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return Duration.ZERO;
        }
        return Duration.ZERO;
    }

    public SecondClickSkillStationAction(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);
    }
}
