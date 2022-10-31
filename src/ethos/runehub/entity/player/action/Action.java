package ethos.runehub.entity.player.action;

public abstract class Action implements Comparable<Action>, Runnable {

    public abstract void perform();

    public void onInterrupt() {
        System.out.println(this.getClass().getName() + " Action interrupted");
    }

    @Override
    public void run() {
        this.perform();
    }

    public ActionPriority getPriority() {
        return priority;
    }

    public boolean isRunParallel() {
        return runParallel;
    }

    public boolean isInterrupt() {
        return interrupt;
    }

    public boolean isCycle() {
        return cycle;
    }

    public Action(ActionPriority priority, boolean runParallel, boolean interrupt, boolean cycle) {
        this.priority = priority;
        this.runParallel = runParallel;
        this.interrupt = interrupt;
        this.cycle = cycle;
    }

    public Action(ActionPriority priority) {
        this(priority,false,true,false);
    }

    @Override
    public int compareTo(Action o) {
        return o.priority == priority ? 0 :( o.priority.ordinal() > priority.ordinal()) ? 1 : -1;
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }

    private final ActionPriority priority;
    private final boolean runParallel;
    private final boolean interrupt;
    private final boolean cycle;

}
