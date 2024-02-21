package ethos.scaperune.action.impl;

import ethos.scaperune.action.priority.Priority;

public class WalkToLocationAction extends PriorityAction {
    @Override
    public void onScheduled() {
        System.out.println("Scheduled Walk-to Action");
    }

    @Override
    public void onCancelled() {
        System.out.println("Walk-to action cancelled");
    }

    @Override
    public void onSuccess() {
        System.out.println("Successfully Walked to destination");
    }

    public WalkToLocationAction() {
        super(Priority.LOW);
    }

    @Override
    public void run() {
        System.out.println("Walking");
    }
}
