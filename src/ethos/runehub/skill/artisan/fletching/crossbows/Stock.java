package ethos.runehub.skill.artisan.fletching.crossbows;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "crossbow_stock")
public class Stock {

    public int getStockId() {
        return stockId;
    }

    public int getLogId() {
        return logId;
    }

    public boolean isMembers() {
        return members;
    }

    public int getBaseXp() {
        return baseXp;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public Stock(int stockId, int logId, boolean members, int baseXp, int levelRequirement) {
        this.stockId = stockId;
        this.logId = logId;
        this.members = members;
        this.baseXp = baseXp;
        this.levelRequirement = levelRequirement;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int stockId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int logId;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int baseXp;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int levelRequirement;
}
