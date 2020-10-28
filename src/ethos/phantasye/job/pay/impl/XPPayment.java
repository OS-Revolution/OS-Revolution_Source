package ethos.phantasye.job.pay.impl;

import ethos.phantasye.job.pay.Payment;

public class XPPayment extends Payment {

    public XPPayment(int baseValue) {
        super(baseValue);
    }

    @Override
    public int make() {
        return this.getAmount().value();
    }
}
