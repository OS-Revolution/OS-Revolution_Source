package ethos.runehub.entity.merchant.impl.exchange;

import java.util.Comparator;

public class ExchangeOfferTimestampComparator implements Comparator<ExchangeOffer> {
    @Override
    public int compare(ExchangeOffer o1, ExchangeOffer o2) {
        return Long.compare(o1.getTimestamp(), o2.getTimestamp());
    }
}
