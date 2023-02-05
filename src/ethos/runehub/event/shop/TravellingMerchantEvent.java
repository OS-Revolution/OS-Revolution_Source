package ethos.runehub.event.shop;

import ethos.event.Event;
import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.runehub.entity.merchant.Merchant;
import org.runehub.api.model.world.region.location.Location;

import java.util.LinkedList;
import java.util.Queue;

public abstract class TravellingMerchantEvent extends Event<Merchant> {

    @Override
    public void execute() {
        relocate();
    }

    @Override
    public void initialize() {
        super.initialize();
        this.spawn();
    }

    protected void spawn() {
        this.currentPoint = this.getNextSpawnPoint();
        NPCHandler.spawn(attachment.getMerchantId(), currentPoint.getXCoordinate(), currentPoint.getZCoordinate(), currentPoint.getYCoordinate(), 0, 1000, 0, 0, 0, false);
        this.onSpawn();
    }

    protected void relocate() {
        NPC npc = NPCHandler.getNpc(attachment.getMerchantId());
        spawnPoints.offer(currentPoint);
        this.currentPoint = this.getNextSpawnPoint();
        npc.absX = currentPoint.getXCoordinate();
        npc.absY = currentPoint.getZCoordinate();
        npc.heightLevel = currentPoint.getYCoordinate();
        this.onRelocate();
    }

    protected Location getNextSpawnPoint() {
//        final Point point = spawnPoints.get(Skill.SKILL_RANDOM.nextInt(spawnPoints.size()));
        return spawnPoints.poll();
    }

    protected abstract void onSpawn();

    protected abstract void onRelocate();

    public Location getCurrentPoint() {
        return currentPoint;
    }

    public TravellingMerchantEvent(Merchant merchant, int ticks, LinkedList<Location> spawnPoints) {
        super(merchant, ticks);
        this.spawnPoints = spawnPoints;
    }

    private Location currentPoint;
    private final Queue<Location> spawnPoints;
}
