package ethos.runehub;

import ethos.model.players.Player;
import org.runehub.api.util.SkillDictionary;

public class LevelRequirement {

    public boolean playerHasAllLevels(Player player) {
        boolean value = true;
        for (int i = 0; i < level.length; i++) {
            if(player.getSkillController().getLevel(i) < level[i])
                value = false;
        }
        return value;
    }

    public int[] getMissingLevelIndices(Player player) {
        int[] value = new int[24];
        for (int i = 0; i < level.length; i++) {
            value[i] = -1;
            if(player.getSkillController().getLevel(i) < level[i])
                value[i] = i;
        }
        return value;
    }

    public String getMissingLevelString(Player player) {
        final StringBuilder sb = new StringBuilder();
        for (int missingLevelIndex : this.getMissingLevelIndices(player)) {
            sb.append(player.getSkillController().getSkill(missingLevelIndex).toString())
                    .append("\\s")
                    .append(level[missingLevelIndex])
                    .append("\\s");
        }
        return sb.toString();
    }

    public int[] getLevel() {
        return level;
    }

    public LevelRequirement(int[] level) {
        this.level = level;
    }

    public LevelRequirement() {
        this(new int[24]);
    }

    private final int[] level;
}
