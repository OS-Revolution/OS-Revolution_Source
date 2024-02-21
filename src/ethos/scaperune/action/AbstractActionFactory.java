package ethos.scaperune.action;

import ethos.scaperune.action.impl.PriorityAction;

public abstract class AbstractActionFactory {

    public abstract  <T> PriorityAction getAction(Class<? extends PriorityAction> action);
}
