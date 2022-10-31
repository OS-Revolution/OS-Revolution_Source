package ethos.runehub.entity.player.action;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class ActionController {

    public void handle() {
        Action action;
        while ((action = actionPriorityQueue.poll()) != null) {
            Logger.getGlobal().info("Queueing Action: " + action);
            action.perform();
//            try {
//                Future<?> future;
//                if (action.isRunParallel()) {
//                    future = primaryActionService.submit(action);
//                    runningActionQueue.add(future);
//                    continue;
//                }
//
//                if (!runningActionQueue.isEmpty() && !action.isInterrupt()) {
//                    runningActionQueue.poll().get();
//                } else if (!runningActionQueue.isEmpty() && action.isInterrupt()) {
//                    Future<?> cancelledFuture = runningActionQueue.poll();
//                    if (cancelledFuture != null) {
//                        action.onInterrupt();
//                        Logger.getGlobal().info("Cancelling " + action);
//                        cancelledFuture.cancel(true);
//                        continue;
//                    }
//                }
//                future = primaryActionService.submit(action);
//                runningActionQueue.add(future);
//                cycles++;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }

    }

    public void submit(Action action) {
        actionPriorityQueue.add(action);
    }

    public ActionController() {
        this.actionPriorityQueue = new PriorityQueue<>(new ActionComparator());
        this.primaryActionService = Executors.newScheduledThreadPool(2);
        this.runningActionQueue = new LinkedList<>();
//        primaryActionService.scheduleAtFixedRate(this::handle, 0, 20, TimeUnit.MILLISECONDS);
    }

    private int cycles;
    private final PriorityQueue<Action> actionPriorityQueue;
    private final ScheduledExecutorService primaryActionService;
    private final Queue<Future<?>> runningActionQueue;
}
