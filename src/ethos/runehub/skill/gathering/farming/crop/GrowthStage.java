package ethos.runehub.skill.gathering.farming.crop;

public class GrowthStage  {

    private final int healthyId,diseasedId,deadId,wateredId;

    public GrowthStage(int healthyId, int diseasedId, int deadId, int wateredId) {
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
