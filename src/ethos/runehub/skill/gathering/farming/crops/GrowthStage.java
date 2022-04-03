package ethos.runehub.skill.gathering.farming.crops;

import java.io.Serializable;

public class GrowthStage implements Serializable {

    private final int healthyId,diseasedId,deadId,wateredId;

    public GrowthStage(Integer healthyId, Integer diseasedId, Integer deadId, Integer wateredId) {
        this.healthyId = healthyId;
        this.diseasedId = diseasedId;
        this.deadId = deadId;
        this.wateredId = wateredId;
    }

    public int getWateredId() {
        return wateredId;
    }

    public int getHealthyId() {
        return healthyId;
    }

    public int getDiseasedId() {
        return diseasedId;
    }

    public int getDeadId() {
        return deadId;
    }
}
