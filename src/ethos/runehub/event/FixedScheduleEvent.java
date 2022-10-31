package ethos.runehub.event;

public abstract class FixedScheduleEvent implements Runnable {

    public abstract void execute();

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

    public FixedScheduleEvent(long rate, String name) {
        this.rate = rate;
        this.name = name;
    }

    private final long rate;
    private final String name;

}
