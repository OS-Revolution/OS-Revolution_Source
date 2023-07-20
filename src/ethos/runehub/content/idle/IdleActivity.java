package ethos.runehub.content.idle;

import java.time.Instant;

public abstract class IdleActivity {

    public abstract void onOfflineCompletion();

    public abstract void onOnlineCompletion();

    public boolean isDone() {
        return Instant.ofEpochMilli(endTimestamp).isBefore(Instant.now());
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public IdleActivity(long ownerId, long duration) {
        this(ownerId,Instant.now().toEpochMilli(),Instant.now().toEpochMilli() + duration);
    }

    public IdleActivity(long ownerId, long startTimestamp, long endTimestamp) {
        this.ownerId = ownerId;
        this.endTimestamp = endTimestamp;
        this.startTimestamp = startTimestamp;
    }

    private final long ownerId;
    private final long startTimestamp;
    private final long endTimestamp;
}
