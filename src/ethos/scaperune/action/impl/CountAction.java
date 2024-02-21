package ethos.scaperune.action.impl;

import ethos.scaperune.action.priority.Priority;

public class CountAction extends PriorityAction {
    @Override
    public void onScheduled() {
        System.out.println("Scheduled Count to " + count + " Action");
    }

    @Override
    public void onCancelled() {
        System.out.println("Failed to count to " + count);
    }

    @Override
    public void onSuccess() {
        System.out.println("Successfully counted to " + count);
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @Override
    public long getActionDelay() {
        return 1000;
    }

    @Override
    public void run() {
        if (currentCount < count) {
            currentCount += 1;
            System.out.println("Counting: " + currentCount);
        } else {
            this.stop();
        }
    }

    public CountAction() {
        super(Priority.LOW);
    }

    private int currentCount = 0;
    private final int count = 5;
}
