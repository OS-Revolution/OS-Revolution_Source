package ethos.runehub.skill.gathering.farming.foraging;

public enum ForageNodeCluster {

    GUAM(8132, 15, 0.8, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.CATHERBY_NORTH_ALLOTMENT,ForageNodeClusterLocation.CATHERBY_SOUTH_ALLOTMENT,
            ForageNodeClusterLocation.FALADOR_GARDEN),
    MARRENTILL(8133, 15, 0.75, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.CATHERBY_NORTH_ALLOTMENT,ForageNodeClusterLocation.CATHERBY_SOUTH_ALLOTMENT,
            ForageNodeClusterLocation.FALADOR_GARDEN),
    TARROMIN(8134, 15, 0.7, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.CATHERBY_NORTH_ALLOTMENT,ForageNodeClusterLocation.CATHERBY_SOUTH_ALLOTMENT,
            ForageNodeClusterLocation.FALADOR_GARDEN),
    HARRALANDER(8135, 10, 0.65, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.CATHERBY_NORTH_ALLOTMENT,ForageNodeClusterLocation.CATHERBY_SOUTH_ALLOTMENT),
    RANARR(8136, 10, 0.5, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.TAVERLY,ForageNodeClusterLocation.CATHERBY_NORTH_ALLOTMENT,ForageNodeClusterLocation.CATHERBY_SOUTH_ALLOTMENT,
            ForageNodeClusterLocation.WILDNERNESS_WEST_DRAGONS,ForageNodeClusterLocation.SEERS_VILLAGE,ForageNodeClusterLocation.FELDIP_HILLS),
    TOADFLAX(8137, 10, 0.45, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.TAVERLY,ForageNodeClusterLocation.WILDNERNESS_WEST_DRAGONS,ForageNodeClusterLocation.FELDIP_HILLS,
            ForageNodeClusterLocation.LUMBRIDGE_SWAMP,ForageNodeClusterLocation.FALADOR_GARDEN),
    IRIT(8138, 8, 0.4, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.CATHERBY_NORTH_ALLOTMENT,ForageNodeClusterLocation.WILDNERNESS_WEST_DRAGONS,ForageNodeClusterLocation.LUMBRIDGE_SWAMP,
            ForageNodeClusterLocation.FELDIP_HILLS),
    AVANTOE(8139, 8, 0.35, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.TAVERLY,ForageNodeClusterLocation.CATHERBY_SOUTH_ALLOTMENT,ForageNodeClusterLocation.FELDIP_HILLS,
            ForageNodeClusterLocation.LUMBRIDGE_SWAMP,ForageNodeClusterLocation.FALADOR_GARDEN),
    KWUARM(8140, 8, 0.3, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.FELDIP_HILLS,ForageNodeClusterLocation.LUMBRIDGE_SWAMP),
    SNAPDRAGON(8141, 5, 0.25, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.FELDIP_HILLS,ForageNodeClusterLocation.LUMBRIDGE_SWAMP,
            ForageNodeClusterLocation.WILDNERNESS_SCORPIA,ForageNodeClusterLocation.WILDNERNESS_WEST_DRAGONS),
    CADANTINE(8142, 5, 0.2, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.WILDNERNESS_SCORPIA,ForageNodeClusterLocation.WILDNERNESS_WEST_DRAGONS,ForageNodeClusterLocation.SEERS_VILLAGE),
    LANTADYME(8144, 5, 0.15, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.TAVERLY,ForageNodeClusterLocation.SEERS_VILLAGE,ForageNodeClusterLocation.WILDNERNESS_SCORPIA,ForageNodeClusterLocation.WILDNERNESS_WEST_DRAGONS,
            ForageNodeClusterLocation.LUMBRIDGE_SWAMP),
    DWARF_WEED(8145, 5, 0.1, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.TAVERLY,ForageNodeClusterLocation.FELDIP_HILLS,ForageNodeClusterLocation.WILDNERNESS_SCORPIA,ForageNodeClusterLocation.WILDNERNESS_WEST_DRAGONS),
    TORSTOL(8146, 5, 0.05, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.TAVERLY,ForageNodeClusterLocation.WILDNERNESS_SCORPIA,ForageNodeClusterLocation.WILDNERNESS_WEST_DRAGONS,ForageNodeClusterLocation.LUMBRIDGE_SWAMP,ForageNodeClusterLocation.FELDIP_HILLS),
    BLOODWEED(8147, 1, 0.005, ForageNodeClusterLocation.DRAYNOR,ForageNodeClusterLocation.TAVERLY,ForageNodeClusterLocation.WILDNERNESS_SCORPIA,ForageNodeClusterLocation.WILDNERNESS_WEST_DRAGONS,ForageNodeClusterLocation.LUMBRIDGE_SWAMP,ForageNodeClusterLocation.FELDIP_HILLS);

    public int getId() {
        return id;
    }

    public int getMaxSpawns() {
        return maxSpawns;
    }

    public double getMinSpawnRoll() {
        return chance;
    }

    public ForageNodeClusterLocation[] getLocations() {
        return locations;
    }

    private ForageNodeCluster(int id, int maxSpawns, double minSpawnRoll, ForageNodeClusterLocation... locations) {
        this.id = id;
        this.maxSpawns = maxSpawns;
        this.chance = minSpawnRoll;
        this.locations = locations;
    }

    private final ForageNodeClusterLocation[] locations;
    private final int maxSpawns;
    private final int id;
    private final double chance;
}
