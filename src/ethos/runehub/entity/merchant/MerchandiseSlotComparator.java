package ethos.runehub.entity.merchant;

import java.util.Comparator;

public class MerchandiseSlotComparator implements Comparator<MerchandiseSlot> {
    @Override
    public int compare(MerchandiseSlot o1, MerchandiseSlot o2) {
        return Integer.compare(o1.getItemId(),o2.getItemId());
    }
}
