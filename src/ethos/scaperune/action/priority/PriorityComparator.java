package ethos.scaperune.action.priority;


import ethos.scaperune.action.impl.PriorityAction;

import java.util.Comparator;

public class PriorityComparator implements Comparator<PriorityAction> {


//    @Override
//    public int compare(PriorityAction o1, PriorityAction o2) {
//        return o1.getPriority().compareTo(o2.getPriority()); // Compare based on priority
//    }

    @Override
    public int compare(PriorityAction o1, PriorityAction o2) {
        return o1.getPriority().ordinal() - o2.getPriority().ordinal();
    }

//    @Override
//    public int compare(PriorityAction o1, PriorityAction o2) {
//        return o2.getPriority().ordinal() - o1.getPriority().ordinal();
//    }
}
