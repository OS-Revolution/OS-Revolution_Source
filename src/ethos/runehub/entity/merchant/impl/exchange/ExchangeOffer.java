package ethos.runehub.entity.merchant.impl.exchange;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "offers")
public class ExchangeOffer {

    public static final int BUY = 0;
    public static final int SELL = 1;

    public long getOfferId() {
        return offerId;
    }

    public long getUserId() {
        return userId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public int getPricePerItem() {
        return pricePerItem;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getOfferType() {
        return offerType;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public ExchangeOffer(long offerId, long userId, int itemId, int itemQuantity, int pricePerItem, long timestamp, int offerType)
    {
        this.offerId = offerId;
        this.userId = userId;
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
        this.pricePerItem = pricePerItem;
        this.timestamp = timestamp;
        this.offerType = offerType;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long offerId;
    @StoredValue(type = SqlDataType.BIGINT)
    private final long userId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int itemId;
    @StoredValue(type = SqlDataType.INTEGER)
    private int itemQuantity;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int pricePerItem;
    @StoredValue(type = SqlDataType.BIGINT)
    private final long timestamp;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int offerType;
}
