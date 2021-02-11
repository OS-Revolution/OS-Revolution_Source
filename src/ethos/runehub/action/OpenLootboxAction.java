package ethos.runehub.action;

import org.rhd.api.action.Action;
import org.rhd.api.action.priority.Priority;

public class OpenLootboxAction extends Action<Void> {


    public OpenLootboxAction() {
        super(Priority.MEDIUM, false, false);
    }

    @Override
    public void onCancelled() {
        System.out.println("Action Cancelled");
    }

    @Override
    public void onFailed() {
        System.out.println("Action Failed");
    }

    @Override
    public void onScheduled() {
        System.out.println("Action Scheduled");
    }

    @Override
    public void onRunning() throws InterruptedException {
        System.out.println("Action Running");
    }

    @Override
    public void onSucceeded() {
        System.out.println("Action Succeeded");
    }
}
