package ethos.runehub.entity.merchant;

public class MerchandiseSlot implements Comparable<MerchandiseSlot> {

    @Override
    public int compareTo(MerchandiseSlot o) {
        return Integer.compare(itemId,o.itemId);
    }

    public int getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isSalePossible() {
        return salePossible;
    }

    public double getMaxDiscount() {
        return maxDiscount;
    }

    public boolean isMembers() {
        return members;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public MerchandiseSlot(int itemId, int amount, boolean salePossible, double maxDiscount, boolean members) {
        this.itemId = itemId;
        this.amount = amount;
        this.salePossible = salePossible;
        this.maxDiscount = maxDiscount;
        this.members = members;
    }

    private final int itemId;
    private int amount;
    private final boolean salePossible;
    private final double maxDiscount;
    private final boolean members;

}
