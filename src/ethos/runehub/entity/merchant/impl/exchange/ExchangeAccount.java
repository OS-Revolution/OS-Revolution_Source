package ethos.runehub.entity.merchant.impl.exchange;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "accounts")
public class ExchangeAccount {

    public long getUserId() {
        return userId;
    }

    public long getAvailableRevenue() {
        return availableRevenue;
    }

    public void setAvailableRevenue(long availableRevenue) {
        this.availableRevenue = availableRevenue;
    }

    public long getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(long totalSales) {
        this.totalSales = totalSales;
    }

    public long getTotalPurchases() {
        return totalPurchases;
    }

    public void setTotalPurchases(long totalPurchases) {
        this.totalPurchases = totalPurchases;
    }

    public long getTotalExpenditure() {
        return totalExpenditure;
    }

    public void setTotalExpenditure(long totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    public long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public long getTotalActiveOffers() {
        return ExchangeOfferDatabase.getInstance().getAllEntries().stream().filter(offer -> offer.getUserId() == userId).count();
    }

    public ExchangeAccount(long userId, long availableRevenue, long totalSales, long totalPurchases, long totalExpenditure, long totalRevenue) {
        this.userId = userId;
        this.availableRevenue = availableRevenue;
        this.totalSales = totalSales;
        this.totalPurchases = totalPurchases;
        this.totalExpenditure = totalExpenditure;
        this.totalRevenue = totalRevenue;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long userId;
    @StoredValue(type = SqlDataType.BIGINT)
    private long availableRevenue;
    @StoredValue(type = SqlDataType.BIGINT)
    private long totalSales;
    @StoredValue(type = SqlDataType.BIGINT)
    private long totalPurchases;
    @StoredValue(type = SqlDataType.BIGINT)
    private long totalExpenditure;
    @StoredValue(type = SqlDataType.BIGINT)
    private long totalRevenue;
}
