package ethos.runehub.entity.item;

import org.runehub.api.model.entity.item.container.ItemContainer;

public class GameItem {

    public static GameItem decodeGameItem(long encoded) {
        int id = (int) (encoded >>> 32);
        int amount = (int) (encoded & 0xFFFFFFFFL);
        return new GameItem(id, amount);
    }

    public long encodeGameItem() {
        long encoded = this.getId();
        encoded <<= 32;
        encoded |= (this.getAmount() & 0xFFFFFFFFL);
        return encoded;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "GameItem{id=" + id + ",amount=" + amount +"}";
    }

    @Override
    public boolean equals(Object gameItem) {
        return gameItem instanceof GameItem && ((GameItem) gameItem).getId() == this.id;
    }

    public GameItem(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public GameItem(int id) {
        this(id,1);
    }

    private int amount;
    private final int id;
}
