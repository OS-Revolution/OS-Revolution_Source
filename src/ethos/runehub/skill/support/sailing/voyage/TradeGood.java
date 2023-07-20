package ethos.runehub.skill.support.sailing.voyage;

public class TradeGood {

    private static final int ITEM_ID_BITS = 15;
    private static final int STOCK_BITS = 12;
    private static final int BASE_PRICE_BITS = 25;

    public long toLong() {
        long bits = 0L;
        bits |= ((long) this.itemId & ((1L << ITEM_ID_BITS) - 1)) << 0;
        bits |= ((long) this.stock & ((1L << STOCK_BITS) - 1)) << ITEM_ID_BITS;
        bits |= ((long) this.basePrice & ((1L << BASE_PRICE_BITS) - 1)) << (ITEM_ID_BITS + STOCK_BITS);
        return bits;
    }

    public static TradeGood fromLong(long bits) {
        int itemId = (int) ((bits >> 0) & ((1L << ITEM_ID_BITS) - 1));
        int stock = (int) ((bits >> ITEM_ID_BITS) & ((1L << STOCK_BITS) - 1));
        int basePrice = (int) ((bits >> (ITEM_ID_BITS + STOCK_BITS)) & ((1L << BASE_PRICE_BITS) - 1));
        return new TradeGood(itemId, stock, basePrice);
    }

    public int getItemId() {
        return itemId;
    }

    public int getStock() {
        return stock;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void increaseStock(int stock) {
        int oldVal = this.stock;
        int newVal = oldVal + stock;
        this.stock = Math.min(newVal,1000000);
    }

    public void decreaseStock(int stock) {
        int oldVal = this.stock;
        int newVal = oldVal - stock;
        this.stock = Math.max(newVal,0);
    }

    @Override
    public String toString() {
        return "TradeGood{itemId=" + itemId + ",stock=" + stock +",basePrice=" + basePrice +"}";
    }

    public TradeGood(int itemId, int stock, int basePrice) {
        this.itemId = itemId;
        this.stock = stock;
        this.basePrice = basePrice;
    }

    private final int itemId;
    private int stock;
    private final int basePrice;
}
