package ethos.runehub.event.dnd;

import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.runehub.entity.merchant.Merchant;
import ethos.runehub.event.FixedScheduleEvent;
import org.runehub.api.model.world.region.location.Location;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Queue;

public abstract class TravellingMerchantEvent extends FixedScheduleEvent {

    protected void spawn() {
        this.currentPoint = this.getNextSpawnPoint();
        NPCHandler.spawn(merchant.getMerchantId(), currentPoint.getXCoordinate(), currentPoint.getZCoordinate(), currentPoint.getYCoordinate(), 0, 1000, 0, 0, 0, false);
        this.onSpawn();
    }

    protected void relocate() {
        NPC npc = NPCHandler.getNpc(merchant.getMerchantId());
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

    public TravellingMerchantEvent(Merchant merchant, Duration duration, LinkedList<Location> spawnPoints) {
        super(duration.toMillis(), merchant.getName());
        this.merchant = merchant;
        this.spawnPoints = spawnPoints;
    }

    private Location currentPoint;
    protected final Merchant merchant;
    private final Queue<Location> spawnPoints;
}
