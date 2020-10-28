package ethos.phantasye.job.pay;

import org.rhd.api.math.impl.AdjustableInteger;

public abstract class Payment {

    private final AdjustableInteger amount;

    public Payment(int baseValue) {
        this.amount =  new AdjustableInteger(baseValue);
    }

    public abstract int make();

    public AdjustableInteger getAmount() {
        return amount;
    }
}
