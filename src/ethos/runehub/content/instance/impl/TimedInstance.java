package ethos.runehub.content.instance.impl;

import ethos.model.players.Player;
import ethos.runehub.content.instance.Instance;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class TimedInstance extends Instance {

    public long getDurationMS() {
        return durationMS;
    }

    public long getInstanceStartTimestamp() {
        return instanceStartTimestamp;
    }

    public TimedInstance(int id, Player owner, Rectangle area, long durationMS, long instanceStartTimestamp, int floorId) {
        super(id, owner, area,floorId);
        this.durationMS = durationMS;
        this.instanceStartTimestamp = instanceStartTimestamp;
    }

    private final long durationMS;
    private final long instanceStartTimestamp;
}
