package ethos.runehub.skill.support.sailing.voyage;

import ethos.model.players.Player;
import ethos.runehub.skill.SkillUtils;
import ethos.util.Misc;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class VoyageContext {

    public boolean playerHasAllLevels(Player player) {
        boolean value = true;
        for (int i = 0; i < levels.length; i++) {
            if(player.getSkillController().getLevel(i) < levels[i])
                value = false;
        }
        return value;
    }

    public int[] getMissingLevelIndices(Player player) {
        int[] value = new int[24];
        for (int i = 0; i < levels.length; i++) {
            value[i] = -1;
            if(player.getSkillController().getLevel(i) < levels[i]) {
                System.out.println("Missing:  " + i + " Req: " + levels[i] + " PLVL: " + player.getSkillController().getLevel(i));
                value[i] = i;
            }
        }
        return value;
    }

    public String getMissingLevelString(Player player) {
        final StringBuilder sb = new StringBuilder();
        for (int missingLevelIndex : this.getMissingLevelIndices(player)) {
            if (missingLevelIndex != -1) {
//            sb.append(player.getSkillController().getSkill(missingLevelIndex).toString())
                sb.append(SkillUtils.getSkillName(missingLevelIndex))
                        .append(" ")
                        .append(levels[missingLevelIndex])
                        .append(" ");
            }
        }
        return sb.toString();
    }

    public long getContainerId(long id) {
        return Arrays.stream(containerIds).filter(value -> value == id).findAny().orElse(-1);
    }

    public long getContainerId(int index) {
        return containerIds[index];
    }

    public int[] getContainerIds() {
        return containerIds;
    }

    public int[] getLevels() {
        return levels;
    }

    public VoyageContext(int[] levels, int[] containerIds) {
        this.levels = levels;
        this.containerIds = containerIds;
    }

//    public VoyageContext(int[] containerIds) {
//        this(new int[24],containerIds);
//    }

    private final int[] containerIds;
    private final int[] levels;
}
