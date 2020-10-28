package ethos.phantasye.trans;

import ethos.model.players.Player;

public abstract class Transaction {

    private final Player transactor;
    private final int transactionAmount;

    protected Transaction(Player user, int transactionAmount) {
        this.transactor = user;
        this.transactionAmount = transactionAmount;
    }

    public abstract boolean transact();

    public Player getTransactor() {
        return transactor;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }
}
