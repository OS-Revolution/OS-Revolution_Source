package ethos.runehub.content.instance.impl;

import ethos.model.players.Player;
import ethos.runehub.content.instance.Instance;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class GroupInstance extends Instance {

    public List<Player> getParticipants() {
        return participants;
    }

    public GroupInstance(int id, Player owner, Rectangle area, int floorId) {
        super(id, owner, area,floorId);
        this.participants = new ArrayList<>();
    }

    private final List<Player> participants;
}
