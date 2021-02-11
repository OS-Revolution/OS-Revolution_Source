package ethos.runehub.loot;

import org.rhd.api.math.AdjustableNumber;
import org.rhd.api.math.impl.AdjustableLong;

public class LootMetric {

    private final long metricId,timestamp;
    private final String userId;
    private final int containerId, tableId, itemId, amount,type;
    private final AdjustableNumber<Long> rolls;
    private final double tier,magicFind;

    public LootMetric(long metricId, long timestamp, String userId, int containerId,
                      int tableId, int itemId, int amount, double tier, long rolls, int type,
                      double magicFind) {
        this.metricId = metricId;
        this.tableId = tableId;
        this.timestamp = timestamp;
        this.tier = tier;
        this.userId = userId;
        this.containerId = containerId;
        this.itemId = itemId;
        this.amount = amount;
        this.rolls = new AdjustableLong(rolls);
        this.type = type;
        this.magicFind = magicFind;
    }

    public double getMagicFind() {
        return magicFind;
    }

    public int getType() {
        return type;
    }

    public int getTableId() {
        return tableId;
    }

    public int getContainerId() {
        return containerId;
    }

    public long getMetricId() {
        return metricId;
    }

    public AdjustableNumber<Long> getRolls() {
        return rolls;
    }

    public int getAmount() {
        return amount;
    }

    public int getItemId() {
        return itemId;
    }

    public double getTier() {
        return tier;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUserId() {
        return userId;
    }
}
