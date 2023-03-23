package ethos.runehub.skill.support.slayer;

import org.runehub.api.model.math.impl.IntegerRange;

public class SlayerUtils {

    public static SlayerTask createTaskForCategory(String category, int combatLevel, int weight, long[] locations, IntegerRange range, int unlockId) {
        return new SlayerTask(SlayerAssignmentDAO.getInstance().getAllEntries().stream()
                .filter(assignment -> assignment.getCategory().equalsIgnoreCase(category))
                .findFirst().get().getId(),
                combatLevel,
                weight,
                locations,
                range,
                unlockId
        );
    }
}
