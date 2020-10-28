package ethos.phantasye.job.pay.impl;


import ethos.phantasye.job.pay.AbstractPaymentFactory;
import ethos.phantasye.job.pay.Payment;

public class XPPaymentFactory implements AbstractPaymentFactory {

    private final int baseXp;

    public XPPaymentFactory(int baseXp) {
        this.baseXp = baseXp;
    }

    @Override
    public Payment makePayment() {
        return new XPPayment(baseXp);
    }
}
