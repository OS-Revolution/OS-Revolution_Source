package ethos.runehub.content;

import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.runehub.RunehubUtils;
import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.runehub.world.WorldSettingsController;

import java.util.Arrays;

public class PlayPassController {

    public void addPlayPassXPFromSkillXP(int baseXP) {

        int startingLevel = player.getContext().getPlayerSaveData().getPlayPassXp() / 10000;
        int quotient = this.getMemberBonus(1.2,baseXP) / playPassXPSkillXPThreshold;
        int remainder = this.getMemberBonus(1.2,baseXP) % playPassXPSkillXPThreshold;

        player.getContext().getPlayerSaveData().setPlayPassXp(player.getContext().getPlayerSaveData().getPlayPassXp() + quotient);
        playPassXPSkillXPThreshold -= remainder;
        int endingLevel = player.getContext().getPlayerSaveData().getPlayPassXp() / 10000;

        if (endingLevel > startingLevel) {
            player.sendMessage("^Play-Pass Level up! You are now level $" + endingLevel);
        }
    }

    public static void resetPreviousSeasonData() {
        PlayerCharacterContextDataAccessObject.getInstance().getAllEntries().forEach(ctx -> {
            if (PlayerHandler.isPlayerOn(ctx.getId())) {
                PlayerHandler.getPlayer(ctx.getId()).ifPresent(player -> {
                    player.getContext().getPlayerSaveData().setPlayPassXp(0);
                    Arrays.fill(player.getContext().getPlayerSaveData().getClaimedPassLevel(), false);
                    player.save();
                    player.sendMessage("^Play-Pass Season $" + WorldSettingsController.getInstance().getWorldSettings().getActiveSeason()
                    + " has ended.");
                });
            } else {
                ctx.getPlayerSaveData().setPlayPassXp(0);
                Arrays.fill(ctx.getPlayerSaveData().getClaimedPassLevel(), false);
                PlayerCharacterContextDataAccessObject.getInstance().update(ctx);
            }
        });
        RunehubUtils.getPlayPassHiscores().clear();
        PlayerHandler.executeGlobalMessage("^Play-Pass Season $" + WorldSettingsController.getInstance().getWorldSettings().getActiveSeason() + " Hi-scores have been reset.");
        WorldSettingsController.getInstance().getWorldSettings().setActiveSeason(WorldSettingsController.getInstance().getWorldSettings().getActiveSeason() + 1);
        WorldSettingsController.getInstance().saveSettings();
    }

    public void addPlayPassXPFromMonsterHP(int baseHP) {
        int startingLevel = player.getContext().getPlayerSaveData().getPlayPassXp() / 10000;
        int quotient = this.getMemberBonus(1.2,baseHP) / playPassXPEnemyHPThreshold;
        int remainder = this.getMemberBonus(1.2,baseHP) % playPassXPEnemyHPThreshold;

        player.getContext().getPlayerSaveData().setPlayPassXp(player.getContext().getPlayerSaveData().getPlayPassXp() + quotient);
        playPassXPEnemyHPThreshold -= remainder;
        int endingLevel = player.getContext().getPlayerSaveData().getPlayPassXp() / 10000;

        if (endingLevel > startingLevel) {
            player.sendMessage("^Play-Pass Level up! You are now level $" + endingLevel);
        }
    }

    private int getMemberBonus(double bonus, int baseValue) {
        if (player.getAttributes().isMember()) {
            return Math.toIntExact(Math.round(baseValue / bonus));
        }
        return baseValue;
    }

    public PlayPassController(Player player) {
        this.player = player;
    }

    private int playPassXPSkillXPThreshold = 10;
    private int playPassXPEnemyHPThreshold = 50;
    private final Player player;
}
