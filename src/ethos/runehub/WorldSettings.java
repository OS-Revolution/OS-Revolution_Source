package ethos.runehub;

import org.rhd.api.math.impl.AdjustableLong;

public class WorldSettings {

    private final AdjustableLong bonusXpTimer;
    private final AdjustableLong doubleDropRateTimer;

    public WorldSettings() {
        this.bonusXpTimer = new AdjustableLong(0L);
        this.doubleDropRateTimer = new AdjustableLong(0L);
    }

    public AdjustableLong getBonusXpTimer() {
        return bonusXpTimer;
    }

    public AdjustableLong getDoubleDropRateTimer() {
        return doubleDropRateTimer;
    }
}
