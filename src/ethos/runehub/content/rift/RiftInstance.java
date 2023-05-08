package ethos.runehub.content.rift;

import ethos.model.players.Player;
import ethos.runehub.content.instance.impl.TimedInstance;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class RiftInstance extends TimedInstance {

    public RiftInstance(int id, Player owner, Rectangle area, long durationMS, long instanceStartTimestamp, int floorId) {
        super(id, owner, area, durationMS, instanceStartTimestamp, floorId);
    }
}
