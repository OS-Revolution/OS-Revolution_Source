package ethos.runehub.content.instance.impl;

import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.Player;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class BossArenaInstance extends TimedGroupInstance {


    public InstanceBoss getInstanceBoss() {
        return instanceBoss;
    }

    public void setInstanceBoss(InstanceBoss instanceBoss) {
        this.instanceBoss = instanceBoss;
    }

    public List<NPC> getSpawnedNpcs() {
        return spawnedNpcs;
    }

    public BossArenaInstance(int id, Player owner, Rectangle area, long durationMS, long instanceStartTimestamp, int floorId) {
        super(id, owner, area, durationMS,instanceStartTimestamp,floorId);
        this.spawnedNpcs = new ArrayList<>();
    }

    private InstanceBoss instanceBoss;
    private final List<NPC> spawnedNpcs;
}
