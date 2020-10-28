package ethos.phantasye.job.pay.impl;


import ethos.phantasye.job.pay.Payment;

public class CoinPayment extends Payment {

    public CoinPayment(int baseValue) {
        super(baseValue);
    }

    @Override
    public int make() {
        return this.getAmount().value();
    }
}
