package ethos.phantasye.job.pay.impl;


import ethos.phantasye.job.pay.Payment;
import ethos.phantasye.job.pay.PaymentDecorator;

import java.util.Random;

public class RandomPaymentModifier extends PaymentDecorator {

    private final int bound;

    public RandomPaymentModifier(Payment payment, int bound) {
        super(payment);
        this.bound = bound;
    }

    @Override
    public int make() {
        return super.make() + new Random().nextInt(bound);

    }
}
