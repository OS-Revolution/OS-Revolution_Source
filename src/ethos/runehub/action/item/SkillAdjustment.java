package ethos.runehub.action.item;

public class SkillAdjustment {

    public int getSkillId() {
        return skillId;
    }

    public float getModifier() {
        return modifier;
    }

    public int getBaseChange() {
        return baseChange;
    }

    public int getBoostCap() {
        return boostCap;
    }

    public SkillAdjustment(int skillId, int baseChange, int modifier, int boostCap) {
        this.skillId = skillId;
        this.baseChange = baseChange;
        this.modifier = modifier;
        this.boostCap = boostCap;
    }

    private final int baseChange;
    private final float modifier;
    private final int boostCap;
    private final int skillId;
}
