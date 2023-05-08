package ethos.runehub.content.instance.impl.rift;

import ethos.model.players.Player;
import ethos.runehub.content.rift.RiftDifficulty;
import ethos.runehub.content.rift.RiftFloor;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class NephalemRiftInstance extends RiftInstance{

    public NephalemRiftInstance(int id, Player owner, Rectangle area, long durationMS, long instanceStartTimestamp, int floorId, RiftDifficulty difficulty, int elitePackCap, int mobCap, RiftFloor floor) {
        super(id, owner, area, durationMS, instanceStartTimestamp, floorId, difficulty, elitePackCap, mobCap,floor);
    }
}
