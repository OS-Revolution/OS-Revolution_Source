package ethos.runehub.skill.artisan.runecraft;

import java.util.Map;

public class RunecraftMultiplier {

    public int getRuneId() {
        return runeId;
    }

    public Map<Integer, Integer> getMultiplierMap() {
        return multiplierMap;
    }

    public RunecraftMultiplier(int runeId, Map<Integer,Integer> multiplierMap) {
        this.runeId = runeId;
        this.multiplierMap = multiplierMap;
    }

    private final int runeId;
    private final Map<Integer,Integer> multiplierMap;
}
