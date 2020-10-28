package ethos.phantasye.trans.impl.withdrawal;

import ethos.model.players.Player;
import org.rhd.api.item.Item;

public class CurrencyWithdrawal extends Withdrawal {

    public CurrencyWithdrawal(Player user, int transactionAmount ) {
        super(user, transactionAmount, user.getItems().getItemAmount(995));
    }

    @Override
    public void withdraw() {
        this.getTransactor().inventory().remove(new Item(995,this.getTransactionAmount()));
    }
}
