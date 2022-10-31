package ethos.runehub.content;

import ethos.model.players.Player;

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
