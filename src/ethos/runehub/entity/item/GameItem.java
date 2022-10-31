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
