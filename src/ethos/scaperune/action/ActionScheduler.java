package ethos.scaperune.action;


import ethos.scaperune.action.impl.PriorityAction;
import ethos.scaperune.action.priority.PriorityComparator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Logger;

public class ActionScheduler {

    private final ExecutorService priorityJobPoolExecutor;
    private final ExecutorService priorityJobScheduler = Executors.newSingleThreadExecutor();
    private final PriorityBlockingQueue<PriorityAction> priorityQueue;
    private Future<?> result;
    private PriorityAction currentAction;

    public ActionScheduler(Integer poolSize, Integer queueSize) {
        priorityJobPoolExecutor = Executors.newFixedThreadPool(poolSize);
        priorityQueue = new PriorityBlockingQueue<>(queueSize, new PriorityComparator());
        startScheduling();
    }

    public void schedule(PriorityAction action) {
        System.out.println("Scheduling Action: " + action);
        System.out.println(currentAction != null ? (action.compareTo(currentAction) > 0) : "Current Action is Null");
        if (currentAction == null || action.compareTo(currentAction) > 0) {
            if (currentAction != null && !action.isRepeatable()) {
                System.out.println("Cancelling current action");
                // Cancel the current action if it's non-repeatable and a higher-priority action is scheduled.
                currentAction.onCancelled();
                // Interrupt the current action's thread to cancel it
                if (result != null && !result.isDone()) {
                    result.cancel(true);
                }
            }
//            currentAction = action;
//            action.onScheduled(); // Call onScheduled for the newly scheduled action.
//            priorityQueue.add(action);
        }
        action.onScheduled(); // Call onScheduled for the newly scheduled action.
        priorityQueue.add(action);
    }

    public void startScheduling() {
        priorityJobScheduler.submit(() -> {
            while (true) {
                try {
                    final PriorityAction nextAction = priorityQueue.take();
                    currentAction = nextAction;
                    result = priorityJobPoolExecutor.submit(() -> {
                        try {
                            runRepeatableAction(nextAction);
                            nextAction.onSuccess(); // Execute the onSuccess method on successful completion
                        } catch (Exception e) {
                            // Handle exceptions here, if needed
                        } finally {
                            currentAction = null;
                        }
                    });
                } catch (InterruptedException e) {
                    System.out.println("THIS");
                    break;
                }
            }
        });
    }

    private void runRepeatableAction(PriorityAction action) throws InterruptedException {
        long repeatInterval = action.getActionDelay();
        if (repeatInterval <= 0) {
            // This action is not repeatable; execute it once and return
            action.run();
            return;
        }

        while (!Thread.currentThread().isInterrupted()) {
            if (!action.isStopped()) {
                action.run();
                Thread.sleep(repeatInterval);
            } else {
                break;
            }
        }
    }
}
