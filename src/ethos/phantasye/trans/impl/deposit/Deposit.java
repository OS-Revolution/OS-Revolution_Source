package ethos.phantasye.trans.impl.deposit;

import ethos.model.players.Player;
import ethos.phantasye.trans.Transaction;

public abstract class Deposit extends Transaction {

    protected Deposit(Player user, int transactionAmount) {
        super(user, transactionAmount);
    }

    public abstract void deposit();

    @Override
    public boolean transact() {
        this.deposit();
        return true;
    }

}
