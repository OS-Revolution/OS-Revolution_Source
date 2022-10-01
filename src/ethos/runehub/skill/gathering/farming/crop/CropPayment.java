package ethos.runehub.skill.gathering.farming.crop;

public class CropPayment {

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public CropPayment(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    private final int id,amount;
}
