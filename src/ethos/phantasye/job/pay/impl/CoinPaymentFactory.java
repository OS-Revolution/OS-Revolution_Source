package ethos.phantasye.job.pay.impl;


import ethos.phantasye.job.pay.AbstractPaymentFactory;
import ethos.phantasye.job.pay.Payment;

public class CoinPaymentFactory implements AbstractPaymentFactory {

    private final int basePay;

    public CoinPaymentFactory(int basePay) {
        this.basePay = basePay;
    }

    @Override
    public Payment makePayment() {
        return new CoinPayment(basePay);
    }
}
