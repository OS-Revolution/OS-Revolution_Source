package ethos.phantasye.trans.impl.withdrawal;

import ethos.model.players.Player;
import ethos.phantasye.trans.Transaction;

public abstract class Withdrawal extends Transaction {

    private final long source;

    public Withdrawal(Player user, int transactionAmount, long source) {
        super(user, transactionAmount);
        this.source = source;
    }

    public abstract void withdraw();

    @Override
    public boolean transact() {
        if (source >= this.getTransactionAmount()) {
            this.withdraw();
            return true;
        }
        return false;
    }

    public long getSource() {
        return source;
    }
}
