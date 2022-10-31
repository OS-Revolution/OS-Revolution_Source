package ethos.runehub.skill.gathering.fishing;

import ethos.Server;
import ethos.runehub.skill.Skill;
import ethos.world.objects.GlobalObject;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class FishingPlatformController {

    private static FishingPlatformController instance = null;

    public static FishingPlatformController getInstance() {
        if (instance == null)
            instance = new FishingPlatformController();
        return instance;
    }

    public void moveFishingSpots() {
        existingPoints.forEach(point -> Server.getGlobalObjects().add(new GlobalObject(-1,point.getX(),point.getY(),0)));
        existingPoints.forEach(point -> Server.getGlobalObjects().remove(FishingPlatform.MINNOW_SPOT_ID,point.getX(),point.getY(),0));
        existingPoints.clear();
        this.initializePlatform();
    }

    public void initializePlatform() {
        this.spawnFishingSpots(platform.getNePlatform());
        this.spawnFishingSpots(platform.getNwPlatform());
        this.spawnFishingSpots(platform.getSePlatform());
        this.spawnFishingSpots(platform.getSwPlatform());
        this.spawnFishingSpots(platform.getEastPlatform());
        this.spawnFishingSpots(platform.getWestPlatform());
        this.spawnFishingSpots(platform.getEastInterior());
        this.spawnFishingSpots(platform.getWestInterior());
    }

    private void spawnFishingSpots(Rectangle area) {
        area.getAllPoints().stream().filter(point -> spawnFishingSpot()).forEach(point -> {
            Server.getGlobalObjects().add(
                    new GlobalObject(FishingPlatform.MINNOW_SPOT_ID,point.getX(),point.getY(),0)
            );
            existingPoints.add(point);
        });
    }


    private boolean spawnFishingSpot() {
        return Skill.SKILL_RANDOM.nextInt(5) == 1 && existingPoints.size() < FishingPlatform.SPOT_CAP;
    }

    private FishingPlatformController() {
        this.platform = new FishingPlatform();
        this.existingPoints = new ArrayList<>();
    }

    private final FishingPlatform platform;
    private final List<Point> existingPoints;
}
