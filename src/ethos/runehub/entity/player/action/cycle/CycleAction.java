package ethos.runehub.entity.player.action.cycle;


import ethos.runehub.entity.player.action.Action;
import ethos.runehub.entity.player.action.ActionPriority;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class CycleAction extends Action {

//    public abstract void onCycle();

    @Override
    public void onInterrupt() {
        super.onInterrupt();
        executorService.shutdownNow();
    }

//    @Override
//    public void perform() {
//          future = executorService.scheduleAtFixedRate(this::onCycle,delayMS,periodMS, TimeUnit.MILLISECONDS);
//    }

    protected void stop() {
        future.cancel(true);
        this.onInterrupt();
    }

    protected CycleAction(ActionPriority priority, boolean runParallel, boolean interrupt,
                          long delayMS, long periodMS) {
        super(priority,runParallel,interrupt,true);
        this.delayMS = delayMS;
        this.periodMS = periodMS;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    protected CycleAction(ActionPriority priority, long delayMS, long periodMS) {
        this(priority,false,true,delayMS,periodMS);
    }

    private ScheduledFuture<?> future;
    private final long delayMS;
    private final long periodMS;
    private final ScheduledExecutorService executorService;

}
