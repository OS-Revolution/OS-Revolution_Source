package ethos.runehub.skill.gathering.farming.foraging;

import ethos.Server;
import ethos.world.objects.GlobalObject;
import org.runehub.api.model.collection.WeightedCollection;
import org.runehub.api.util.math.geometry.Point;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ForageNodeClusterController {

    public static final int SPAWN_TICK = 80;//2870;
    public static final int MAX_CLUSTERS = 5;

    private static ForageNodeClusterController instance = null;

    public static ForageNodeClusterController getInstance() {
        if (instance == null)
            instance = new ForageNodeClusterController();
        return instance;
    }

    public void spawnCluster() {
        spawningService.scheduleAtFixedRate(() -> {
            despawn();
            spawn();
        }, 0, SPAWN_TICK, TimeUnit.MINUTES);
    }

    private void spawn() {
        long startTime = System.currentTimeMillis();
        final List<ForageNodeClusterLocation> clusterLocations = getClusterLocations();
        clusterLocations.forEach(location -> {
            System.out.println("Spawning in: " + location.name());
            final Queue<Point> validPoints = new LinkedList<>(getValidPoints(location, getValidTileCountInCluster(location)));
            final List<Integer> herbs = new ArrayList<>(getClusterHerbIdsForLocation(location));

            herbs.forEach(herb -> {
                Point point = validPoints.poll();
                if (point != null) {
                    if (activeNodes.containsKey(herb)) {
                        activeNodes.get(herb).addAll(validPoints);
                    } else {
                        activeNodes.put(herb, new ArrayList<>(validPoints));
                    }
                    Server.getGlobalObjects().add(
                            new GlobalObject(
                                    herb,
                                    point.getX(),
                                    point.getY(),
                                    0,
                                    0,
                                    10
                            )
                    );
                }
            });
        });
        this.benchmark(startTime,"spawn");
    }

    private void despawn() {
        long startTime = System.currentTimeMillis();
        activeNodes.values().forEach(points -> {
            points.forEach(point -> {
                Server.getGlobalObjects().add(
                        new GlobalObject(
                                -1,
                                point.getX(),
                                point.getY(),
                                0,
                                0,
                                10
                        )
                );
//                Server.getGlobalObjects().remove(-1,point.getX(),point.getY(),0);
            });
        });
        activeNodes.clear();
        this.benchmark(startTime,"despawn");
    }

    private int getValidTileCountInCluster(ForageNodeClusterLocation clusterLocation) {
        if((random.nextInt(100) + clusterLocation.getWeight()) >= 75)
            return Math.toIntExact(Math.round(clusterLocation.getSpawnArea().getAllPoints().size() * 0.6D));
        return Math.toIntExact(Math.round(clusterLocation.getSpawnArea().getAllPoints().size() * 0.3D));
    }

    private List<Integer> getClusterHerbIdsForLocation(ForageNodeClusterLocation location) {
        long startTime = System.currentTimeMillis();
        final int maxSpawningTiles = this.getValidTileCountInCluster(location);
        final List<Point> validPoints = this.getValidPoints(location, maxSpawningTiles);
        final List<Integer> herbs = new ArrayList<>();
        validPoints.forEach(point -> {
            ForageNodeCluster cluster = this.getCluster(location);
            if (cluster != null) {
                int spawnCount = this.getSpawnCount(herbs, cluster.getId());
                if (Arrays.stream(cluster.getLocations()).anyMatch(spawnLocation -> spawnLocation.ordinal() == location.ordinal())) {
                    if (spawnCount < cluster.getMaxSpawns()) {
                        herbs.add(cluster.getId());
                    }
                }
            }
        });
        this.benchmark(startTime,"getClusterHerbIds");
        return herbs;
    }

    private ForageNodeCluster getCluster(ForageNodeClusterLocation location) {
//        ForageNodeCluster cluster = ForageNodeCluster.values()[random.nextInt(ForageNodeCluster.values().length)];
//        if (random.nextInt(100) + location.getWeight() >= cluster.getMinSpawnRoll()) {
//            return cluster;
//        }
        return ForageNodeCluster.values()[weightedCollection.next()];
    }

    private int getSpawnCount(List<Integer> herbs, int id) {
        return Math.toIntExact(herbs.stream()
                .filter(herbId -> herbId == id)
                .count());
    }

    private List<Point> getValidPoints(ForageNodeClusterLocation location, int max) {
        long startTime = System.currentTimeMillis();
        final List<Point> validPoints = new ArrayList<>();
        while (validPoints.size() < max) {
            final Point selectedPoint = location.getSpawnArea().getAllPoints().get(random.nextInt(location.getSpawnArea().getAllPoints().size()));
            if (!validPoints.contains(selectedPoint)) {
                validPoints.add(selectedPoint);
            }
        }
        this.benchmark(startTime,"getValidPoints");
        return validPoints;
    }

    private List<ForageNodeClusterLocation> getClusterLocations() {
        long startTime = System.currentTimeMillis();
        final List<ForageNodeClusterLocation> clusterLocations = new ArrayList<>();
        while (clusterLocations.size() < MAX_CLUSTERS) {
            final ForageNodeClusterLocation location = ForageNodeClusterLocation.values()[random.nextInt(ForageNodeClusterLocation.values().length)];
            if (!clusterLocations.contains(location)) {
                clusterLocations.add(location);
            }
        }
        this.benchmark(startTime,"getClusterLocations");
        return clusterLocations;
    }

    private void benchmark(long startTime, String identifier) {
        long endTime = System.currentTimeMillis();
        Logger.getGlobal().finest(identifier + " benchmark : " + (endTime - startTime) + "ms");

    }

    private ForageNodeClusterController() {
        this.random = new Random();
        this.activeNodes = new HashMap<>();
        this.weightedCollection = new WeightedCollection<>();

        Arrays.stream(ForageNodeCluster.values()).forEach(cluster -> weightedCollection.add(cluster.getMinSpawnRoll(),cluster.ordinal()));
    }

    private final WeightedCollection<Integer> weightedCollection;
    private final Map<Integer, List<Point>> activeNodes;
    private final Random random;
    private final ScheduledExecutorService spawningService = Executors.newSingleThreadScheduledExecutor();
}
