package ethos.runehub.skill.artisan.herblore.potion;

public class Potion {

    public int getItemId() {
        return itemId;
    }

    public int[] getEffectId() {
        return effectId;
    }

    public int getFourDoseId() {
        return fourDoseId;
    }

    public int getThreeDoseId() {
        return threeDoseId;
    }

    public int getTwoDoseId() {
        return twoDoseId;
    }

    public int getOneDoseId() {
        return oneDoseId;
    }

    public Potion(int itemId, int fourDoseId, int threeDoseId, int twoDoseId, int oneDoseId, int...effectId) {
        this.itemId = itemId;
        this.effectId = effectId;
        this.fourDoseId = fourDoseId;
        this.threeDoseId = threeDoseId;
        this.twoDoseId = twoDoseId;
        this.oneDoseId = oneDoseId;
    }

    private final int itemId;
    private final int[] effectId;
    private final int fourDoseId, threeDoseId, twoDoseId, oneDoseId;
}
