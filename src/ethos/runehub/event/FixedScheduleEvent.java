package ethos.runehub.event;

import java.util.logging.Logger;

public abstract class FixedScheduleEvent implements Runnable {

    public abstract void execute();

    protected void onInitialize() {
        Logger.getGlobal().info("Initialized " + name);
    }

    @Override
    public void run() {
        execute();
    }

    public long getRate() {
        return rate;
    }

    public String getName() {
        return name;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public FixedScheduleEvent(long rate, String name) {
        this.rate = rate;
        this.name = name;
        this.onInitialize();
    }

    private long rate;
    private final String name;

}
