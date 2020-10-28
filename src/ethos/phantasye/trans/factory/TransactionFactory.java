package ethos.phantasye.trans.factory;

import ethos.phantasye.trans.Transaction;

public class TransactionFactory {

    public static Transaction getTransaction(AbstractTransactionFactory abstractTransactionFactory) {
        return abstractTransactionFactory.createTransaction();
    }
}
