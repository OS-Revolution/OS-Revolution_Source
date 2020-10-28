package ethos.phantasye.trans.factory.impl;


import ethos.model.players.Player;
import ethos.phantasye.trans.Transaction;
import ethos.phantasye.trans.factory.AbstractTransactionFactory;
import ethos.phantasye.trans.impl.deposit.CurrencyDeposit;

public class CurrencyDepositFactory implements AbstractTransactionFactory {
    private final Player transactor;
    private final int transactionAmount;

    public CurrencyDepositFactory(Player transactor, int transactionAmount) {
        this.transactor  = transactor;
        this.transactionAmount = transactionAmount;
    }

    @Override
    public Transaction createTransaction() {
        return new CurrencyDeposit(transactor,transactionAmount);
    }
}
