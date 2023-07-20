package ethos.runehub.content.job;

public class Job {

    public static Job fromBitArray(long bits) {
        int skillId = (int) (bits & 0x1F);
        int basePay = (int) ((bits >> 5) & 0xFFFFF);
        int difficulty = (int) ((bits >> 25) & 0xF);
        int collected = (int) ((bits >> 29) & 0x3FF);
        int quota = (int) ((bits >> 39) & 0x3FF);
        int targetId = (int) ((bits >> 49) & 0x7FFFF);

        return new Job(targetId, quota, collected, difficulty, basePay, skillId);
    }

    public long toBitArray() {
        long bits = 0;
        bits |= (long) skillId;
        bits |= (long) basePay << 5;
        bits |= (long) difficulty << 25;
        bits |= (long) collected << 29;
        bits |= (long) quota << 39;
        bits |= (long) targetId << 49;

        return bits;
    }



    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getCollected() {
        return collected;
    }

    public void setCollected(int collected) {
        this.collected = collected;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getBasePay() {
        return basePay;
    }

    public void setBasePay(int basePay) {
        this.basePay = basePay;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Override
    public String toString() {
        return "Target: " + targetId
                + "\nQuota: " + quota
                + "\nCollected: " + collected
                + "\nDifficulty: " + difficulty
                + "\nBase Pay: " + basePay
                + "\nSkill: " + skillId;
    }

    public Job(int targetId, int quota, int collected, int difficulty, int basePay, int skillId) {
        this.targetId = targetId;
        this.quota = quota;
        this.collected = collected;
        this.difficulty = difficulty;
        this.basePay = basePay;
        this.skillId = skillId;
    }

    private int targetId;
    private int quota;
    private int collected;
    private int difficulty;
    private int basePay;
    private int skillId;
}
