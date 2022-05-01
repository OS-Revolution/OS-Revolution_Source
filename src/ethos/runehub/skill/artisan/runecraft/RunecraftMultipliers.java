package ethos.runehub.skill.artisan.runecraft;

import java.util.Map;

public class RunecraftMultipliers {

    public Map<Integer, RunecraftMultiplier> getMultiplierMap() {
        return multiplierMap;
    }

    public RunecraftMultipliers(Map<Integer,RunecraftMultiplier> multiplierMap) {
        this.multiplierMap = multiplierMap;
    }

    private final Map<Integer,RunecraftMultiplier> multiplierMap;
}
