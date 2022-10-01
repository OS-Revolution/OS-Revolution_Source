package ethos.runehub.content.rift;

import ethos.clip.Region;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.Player;
import ethos.runehub.skill.Skill;
import org.runehub.api.model.world.region.location.Location;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.time.Duration;

public class Rift {

    public void start() {
        this.currentFloor = 0;
        this.spawnTrashMobs();
    }

    protected void onEnterNewRoom() {

    }

    protected void spawnTrashMobs() {
        this.getBoundingBoxForFloor().getAllPoints().stream().filter(point -> Region.getClipping(point.getX(),point.getY(),0) == 0)
                .filter(point -> !point.equals(new Point(player.absX,player.absY)))
                .filter(point -> spawnedMobs <= mobCap)
                .filter(point -> Skill.SKILL_RANDOM.nextInt(100) <= 3)
                .forEach(point -> {
//                    System.out.println("Can Spawn @ " + point);
//                    if (Skill.SKILL_RANDOM.nextInt(100) <= 3 && spawnedMobs <= mobCap) {
                        System.out.println("Spawning Mob " + spawnedMobs + " @ " + point);
                        NPCHandler.spawn(100,point.getX(),point.getY(),0,1,20,3,5,5,true);
                        spawnedMobs++;
//                    }
                });
        System.out.println("Available Spawning Spaces: " + this.getBoundingBoxForFloor().getAllPoints().stream()
                .filter(point -> Region.getClipping(point.getX(),point.getY(),0) == 0)
                .filter(point -> !point.equals(new Point(player.absX,player.absY))).count());
    }

    protected Location getEntryLocation(int startingFloor) {
        System.out.println("Getting Entry Location for floor: " + startingFloor);
        //1855 5184 sw corner floor one 1915 5247 ne corner floor one
        switch (startingFloor) {
            case 0:
                return new Location(1859,5243);
        }
        return new Location(1880,5233);
    }

    protected Rectangle getBoundingBoxForFloor() {
        System.out.println("Getting Bounding Box for floor: " + currentFloor);
        switch (currentFloor) {
            case 0:
                return new Rectangle(new Point(1855,5184),new Point(1915,5247)).getBoundingBox();
        }
        return new Rectangle(new Point(1855,5184),new Point(1915,5247)).getBoundingBox();
    }

    protected int getNextFloor() {
        int floor = this.getStartingFloor();
        return floor == currentFloor ? getNextFloor() : floor;
    }

    protected int getStartingFloor() {
        return Skill.SKILL_RANDOM.nextInt(4);
    }

    public int getElitePackCap() {
        return elitePackCap;
    }

    public int getMobCap() {
        return mobCap;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public Rift(Player player) {
        this(5,75, Duration.ofMinutes(10).toMillis(),player);
    }

    public Rift(int elitePackCap, int mobCap, long timeLimit, Player player) {
        this.elitePackCap = elitePackCap;
        this.mobCap = mobCap;
        this.timeLimit = timeLimit;
        this.player = player;
    }

    private int spawnedMobs;
    private int currentFloor;
    private final int elitePackCap;
    private final int mobCap;
    private final long timeLimit;
    private final Player player;
}
