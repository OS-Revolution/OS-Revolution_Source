package ethos.runehub.content;

public class MobKill {

    public int getMobId() {
        return mobId;
    }

    public int getAmountKilled() {
        return amountKilled;
    }

    public long getKillerId() {
        return killerId;
    }

    public void setAmountKilled(int amountKilled) {
        this.amountKilled = amountKilled;
    }

    public void increase() {
        this.amountKilled ++;
    }

    public MobKill(long killerId, int amountKilled, int mobId) {
        this.killerId = killerId;
        this.amountKilled = amountKilled;
        this.mobId = mobId;
    }

    private final long killerId;
    private int amountKilled;
    private final int mobId;
}
