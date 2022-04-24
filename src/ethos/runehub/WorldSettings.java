package ethos.runehub;

import org.rhd.api.math.impl.AdjustableLong;

import java.util.HashMap;
import java.util.Map;

public class WorldSettings {

    private final double powerModifer;
    private final double efficiencyModifier;
    private final double gainsModifier;

    private final AdjustableLong bonusXpTimer;
    private final AdjustableLong doubleDropRateTimer;

    private final Map<Integer,AdjustableLong> skillEfficiencyTimer;
    private final Map<Integer,AdjustableLong> skillPowerTimer;
    private final Map<Integer,AdjustableLong> skillGainsTimer;

//    private final AdjustableLong woodcuttingEfficiencyTimer;
//    private final AdjustableLong woodcuttingPowerTimer;
//    private final AdjustableLong woodcuttingGainsTimer;
//
//    private final AdjustableLong miningEfficiencyTimer;
//    private final AdjustableLong miningPowerTimer;
//    private final AdjustableLong miningGainsTimer;
//
//    private final AdjustableLong fishingEfficiencyTimer;
//    private final AdjustableLong fishingPowerTimer;
//    private final AdjustableLong fishingGainsTimer;
//
//    private final AdjustableLong skillEfficiencyTimer;
//    private final AdjustableLong skillPowerTimer;
//    private final AdjustableLong skillGainsTimer;

    private long commodityTraderVisitTimestamp;

    public WorldSettings() {
        this.bonusXpTimer = new AdjustableLong(0L);
        this.doubleDropRateTimer = new AdjustableLong(0L);
//        this.woodcuttingEfficiencyTimer = new AdjustableLong(0L);
//        this.woodcuttingPowerTimer = new AdjustableLong(0L);
//        this.woodcuttingGainsTimer = new AdjustableLong(0L);
//
//        this.miningEfficiencyTimer = new AdjustableLong(0L);
//        this.miningPowerTimer = new AdjustableLong(0L);
//        this.miningGainsTimer = new AdjustableLong(0L);
//
//        this.fishingEfficiencyTimer = new AdjustableLong(0L);
//        this.fishingPowerTimer = new AdjustableLong(0L);
//        this.fishingGainsTimer = new AdjustableLong(0L);
//
//        this.skillEfficiencyTimer = new AdjustableLong(0L);
//        this.skillPowerTimer = new AdjustableLong(0L);
//        this.skillGainsTimer = new AdjustableLong(0L);

        this.powerModifer = 1.5D;
        this.efficiencyModifier = 1.5D;
        this.gainsModifier = 1.5D;

        this.skillEfficiencyTimer = new HashMap<>();
        this.skillGainsTimer = new HashMap<>();
        this.skillPowerTimer = new HashMap<>();
    }

    public Map<Integer, AdjustableLong> getSkillEfficiencyTimer() {
        return skillEfficiencyTimer;
    }

    public Map<Integer, AdjustableLong> getSkillGainsTimer() {
        return skillGainsTimer;
    }

    public Map<Integer, AdjustableLong> getSkillPowerTimer() {
        return skillPowerTimer;
    }

    public long getCommodityTraderVisitTimestamp() {
        return commodityTraderVisitTimestamp;
    }

    public void setCommodityTraderVisitTimestamp(long commodityTraderVisitTimestamp) {
        this.commodityTraderVisitTimestamp = commodityTraderVisitTimestamp;
    }

    public double getEfficiencyModifier() {
        return efficiencyModifier;
    }

    public double getGainsModifier() {
        return gainsModifier;
    }

    public double getPowerModifer() {
        return powerModifer;
    }

//    public AdjustableLong getWoodcuttingEfficiencyTimer() {
//        return woodcuttingEfficiencyTimer;
//    }
//
//    public AdjustableLong getWoodcuttingGainsTimer() {
//        return woodcuttingGainsTimer;
//    }
//
//    public AdjustableLong getWoodcuttingPowerTimer() {
//        return woodcuttingPowerTimer;
//    }
//
//    public AdjustableLong getMiningEfficiencyTimer() {
//        return miningEfficiencyTimer;
//    }
//
//    public AdjustableLong getMiningPowerTimer() {
//        return miningPowerTimer;
//    }
//
//    public AdjustableLong getMiningGainsTimer() {
//        return miningGainsTimer;
//    }
//
//    public AdjustableLong getFishingEfficiencyTimer() {
//        return fishingEfficiencyTimer;
//    }
//
//    public AdjustableLong getFishingPowerTimer() {
//        return fishingPowerTimer;
//    }
//
//    public AdjustableLong getFishingGainsTimer() {
//        return fishingGainsTimer;
//    }
//
//    public AdjustableLong getSkillEfficiencyTimer() {
//        return skillEfficiencyTimer;
//    }
//
//    public AdjustableLong getSkillPowerTimer() {
//        return skillPowerTimer;
//    }
//
//    public AdjustableLong getSkillGainsTimer() {
//        return skillGainsTimer;
//    }

    public AdjustableLong getBonusXpTimer() {
        return bonusXpTimer;
    }

    public AdjustableLong getDoubleDropRateTimer() {
        return doubleDropRateTimer;
    }
}
