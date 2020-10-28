package ethos.phantasye.job.pay;

public class PaymentDecorator extends Payment {

    private final Payment payment;

    public PaymentDecorator(Payment payment) {
        super(payment.getAmount().value());
        this.payment = payment;
    }

    @Override
    public int make() {
        return this.payment.make();
    }
}
