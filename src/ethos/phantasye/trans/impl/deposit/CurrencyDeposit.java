package ethos.phantasye.trans.impl.deposit;

import ethos.model.players.Player;
import org.rhd.api.item.Item;

public class CurrencyDeposit extends Deposit {

    public CurrencyDeposit(Player user, int transactionAmount) {
        super(user, transactionAmount);
    }

    @Override
    public void deposit() {
        this.getTransactor().inventory().add(new Item(995,this.getTransactionAmount()));
    }
}
