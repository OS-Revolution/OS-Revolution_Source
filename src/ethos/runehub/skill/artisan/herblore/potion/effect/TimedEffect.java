package ethos.runehub.skill.artisan.herblore.potion.effect;

public abstract class TimedEffect extends Effect {

    public long getDurationMs() {
        return durationMs;
    }

    public TimedEffect(int id, long durationMs) {
        super(id);
        this.durationMs = durationMs;
    }

    private final long durationMs;
}
