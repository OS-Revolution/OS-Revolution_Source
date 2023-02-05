package ethos.runehub.content.instance.impl;

import ethos.model.players.Player;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class TimedGroupInstance extends TimedInstance{

    public List<Player> getParticipants() {
        return participants;
    }

    public TimedGroupInstance(int id, Player owner, Rectangle area, long durationMS, long instanceStartTimestamp, int floorId) {
        super(id, owner, area, durationMS,instanceStartTimestamp,floorId);
        this.participants = new ArrayList<>();
    }

    private final List<Player> participants;

}
