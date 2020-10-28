package ethos.phantasye.job;

public final class JobTarget {

    private final int skillId;
    private final Job.Difficulty difficulty;
    private final int itemId;

    public JobTarget(int skillId, int itemId, Job.Difficulty difficulty) {
        this.skillId = skillId;
        this.itemId = itemId;
        this.difficulty = difficulty;
    }

    public Job.Difficulty getDifficulty() {
        return difficulty;
    }

    public int getSkillId() {
        return skillId;
    }

    public int getItemId() {
        return itemId;
    }
}
