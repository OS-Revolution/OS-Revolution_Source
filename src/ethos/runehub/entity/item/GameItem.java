package ethos.runehub.entity.item;

public class GameItem {

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
