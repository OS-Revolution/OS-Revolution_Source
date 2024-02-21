package ethos.runehub.world;

import ethos.runehub.TimeUtils;
import ethos.runehub.entity.item.GameItem;
import org.runehub.api.model.entity.item.Item;
import org.runehub.api.model.entity.item.loot.Loot;
import org.runehub.api.model.math.impl.AdjustableLong;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;

public class WorldSettings {

    public static final int CRAFTING_STATION = 0;
    public static final int COOKING_STATION = 1;
    public static final int HERBLORE_STATION = 2;
    public static final int SAWMILL_STATION = 3;
    public static final int FIREMAKING_STATION = 4;

    private final double powerModifer;
    private final double efficiencyModifier;
    private final double gainsModifier;

    private final AdjustableLong bonusXpTimer;
    private final AdjustableLong doubleDropRateTimer;

    private final Map<Integer, AdjustableLong> skillEfficiencyTimer;
    private final Map<Integer, AdjustableLong> skillPowerTimer;
    private final Map<Integer, AdjustableLong> skillGainsTimer;

    private long lastDailyResetTimestamp;
    private long weeklyResetTimestamp;
    private long commodityTraderVisitTimestamp;

    private AdjustableLong[] skillStationExpirationTimeMS;

    private int activeSeason;
    private GameItem[][] activeSeasonLoot;

    private int currentEventId;
    private int weekendEventId;

    private long lastChestEventTimestamp;

    public WorldSettings() {
        this.bonusXpTimer = new AdjustableLong(0L);
        this.doubleDropRateTimer = new AdjustableLong(0L);
        this.powerModifer = 1.5D;
        this.efficiencyModifier = 1.5D;
        this.gainsModifier = 1.5D;
        this.lastDailyResetTimestamp = LocalTime.of(0, 0).getLong(ChronoField.HOUR_OF_DAY);
        this.weeklyResetTimestamp = LocalTime.of(0, 0).getLong(ChronoField.HOUR_OF_DAY);
        this.activeSeason = 1;
        this.activeSeasonLoot = new GameItem[50][3];
        this.skillEfficiencyTimer = new HashMap<>();
        this.skillGainsTimer = new HashMap<>();
        this.skillPowerTimer = new HashMap<>();
        this.skillStationExpirationTimeMS = new AdjustableLong[] {new AdjustableLong(0L),new AdjustableLong(0L),new AdjustableLong(0L),new AdjustableLong(0L),new AdjustableLong(0L)};
    }

    public long getWeeklyResetTimestamp() {
        return weeklyResetTimestamp;
    }

    public void setWeeklyResetTimestamp(long weeklyResetTimestamp) {
        this.weeklyResetTimestamp = weeklyResetTimestamp;
    }

    public void setLastChestEventTimestamp(long lastChestEventTimestamp) {
        this.lastChestEventTimestamp = lastChestEventTimestamp;
    }

    public long getLastChestEventTimestamp() {
        return lastChestEventTimestamp;
    }

    public void setActiveSeasonLoot(GameItem[][] activeSeasonLoot) {
        this.activeSeasonLoot = activeSeasonLoot;
    }

    public AdjustableLong[] getSkillStationExpirationTimeMS() {
        return skillStationExpirationTimeMS;
    }

    public void setSkillStationExpirationTimeMS(AdjustableLong[] skillStationExpirationTimeMS) {
        this.skillStationExpirationTimeMS = skillStationExpirationTimeMS;
    }

    public void setSkillStationExpirationTimeMS(int station, long ms) {
        switch (station) {
            case 6799:
                if (skillStationExpirationTimeMS[CRAFTING_STATION] == null)
                    skillStationExpirationTimeMS[CRAFTING_STATION] = new AdjustableLong(ms);
                else
                    skillStationExpirationTimeMS[CRAFTING_STATION].add(ms);
                break;
            case 13542:
                if (skillStationExpirationTimeMS[COOKING_STATION] == null)
                    skillStationExpirationTimeMS[COOKING_STATION] = new AdjustableLong(ms);
                else
                    skillStationExpirationTimeMS[COOKING_STATION].add(ms);
                break;
            case 878:
                if (skillStationExpirationTimeMS[HERBLORE_STATION] == null)
                    skillStationExpirationTimeMS[HERBLORE_STATION] = new AdjustableLong(ms);
                else
                    skillStationExpirationTimeMS[HERBLORE_STATION].add(ms);
                break;
            case 15468:
                if (skillStationExpirationTimeMS[SAWMILL_STATION] == null)
                    skillStationExpirationTimeMS[SAWMILL_STATION] = new AdjustableLong(ms);
                else
                    skillStationExpirationTimeMS[SAWMILL_STATION].add(ms);
                break;
            case 11017:
                if (skillStationExpirationTimeMS[FIREMAKING_STATION] == null)
                    skillStationExpirationTimeMS[FIREMAKING_STATION] = new AdjustableLong(ms);
                else
                    skillStationExpirationTimeMS[FIREMAKING_STATION].add(ms);
                break;
        }
    }


    public GameItem[][] getActiveSeasonLoot() {
        return new GameItem[][]{
                //Level 1 - 10
                {new GameItem(1731, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1333, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1359, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(3103, 50), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(6199, 1), new GameItem(1459, 5), new GameItem(995, 5000)},
                {new GameItem(6824, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1275, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(892, 50), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(995, 15000), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(13192, 1), new GameItem(85, 2), new GameItem(995, 5000)},
//Level 11-20
                {new GameItem(1464, 5), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1459, 50), new GameItem(85, 2), new GameItem(995, 5000)},
//Level 21-30
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1459, 100), new GameItem(85, 2), new GameItem(995, 5000)},
//Level 31-40
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(12718, 1), new GameItem(85, 2), new GameItem(1459, 50)},
//Level 41-50
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(1040, 1), new GameItem(85, 2), new GameItem(995, 5000)},
                {new GameItem(4691, 1), new GameItem(85, 2), new GameItem(995, 5000)},
        };
    }

    public int getActiveSeason() {
        return activeSeason;
    }

    public void setActiveSeason(int activeSeason) {
        this.activeSeason = activeSeason;
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

    public long getLastDailyResetTimestamp() {
        return lastDailyResetTimestamp;
    }

    public void setLastDailyResetTimestamp(long lastDailyResetTimestamp) {
        this.lastDailyResetTimestamp = lastDailyResetTimestamp;
    }

    public AdjustableLong getBonusXpTimer() {
        return bonusXpTimer;
    }

    public AdjustableLong getDoubleDropRateTimer() {
        return doubleDropRateTimer;
    }

    public int getCurrentEventId() {
        return currentEventId;
    }

    public void setCurrentEventId(int currentEventId) {
        this.currentEventId = currentEventId;
    }

    public int getWeekendEventId() {
        return weekendEventId;
    }

    public void setWeekendEventId(int weekendEventId) {
        this.weekendEventId = weekendEventId;
    }
}
