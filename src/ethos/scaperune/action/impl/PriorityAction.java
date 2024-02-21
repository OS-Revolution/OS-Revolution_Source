package ethos.scaperune.action.impl;


import ethos.scaperune.action.Action;
import ethos.scaperune.action.priority.Priority;

public abstract class PriorityAction implements Comparable<PriorityAction>, Action {



    @Override
    public int compareTo(PriorityAction action) {
        if (action.priority == this.priority) {
            return 0;
        } else if (this.priority.ordinal() < action.priority.ordinal()) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " - " + priority;
    }

    @Override
    public void stop() {
        stop = true;
    }

    public boolean isStopped() {
        return stop;
    }

    public Priority getPriority() {
        return priority;
    }

    public PriorityAction(Priority priority) {
        this.priority = priority;
    }

    private boolean stop;
    private final Priority priority;

}
