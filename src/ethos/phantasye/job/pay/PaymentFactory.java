package ethos.phantasye.job.pay;

public class PaymentFactory {

    public static Payment getPayment(AbstractPaymentFactory factory) {
        return factory.makePayment();
    }
}
