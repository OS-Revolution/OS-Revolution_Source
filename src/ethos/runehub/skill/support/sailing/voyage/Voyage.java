package ethos.runehub.skill.support.sailing.voyage;

import ethos.runehub.world.wushanko.island.IslandLoader;

public class Voyage {

    public long toLong() {
        long result = 0;
        result |= ((long) seafaring) << 0;
        result |= ((long) morale) << 12;
        result |= ((long) combat) << 24;
        result |= ((long) distance) << 36;
        result |= ((long) region) << 49;
        result |= ((long) island) << 56;
        return result;
    }

    public static Voyage fromLong(long longValue) {
        int seafaring = (int) (longValue >> 0) & 0xFFF;
        int morale = (int) (longValue >> 12) & 0xFFF;
        int combat = (int) (longValue >> 24) & 0xFFF;
        int distance = (int) (longValue >> 36) & 0x1FFF;
        int region = (int) (longValue >> 49) & 0x7F;
        int island = (int) (longValue >> 56) & 0xFF;
        return new Voyage(seafaring, morale, combat, distance, region, island);
    }




    public int getCombat() {
        return combat;
    }

    public int getMorale() {
        return morale;
    }

    public int getSeafaring() {
        return seafaring;
    }

    public int getDistance() {
        return distance;
    }

    public int getIsland() {
        return island;
    }

    public int getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "Voyage{" +
                "seafaring=" + seafaring +
                ", morale=" + morale +
                ", combat=" + combat +
                ", distance=" + distance +
                ", region=" + region +
                ", island=" + island +
                ", islandName=" + IslandLoader.getInstance().read(island).getName() +
                '}';
    }


    public Voyage(int seafaring, int morale, int combat, int distance, int region, int island) {
        this.seafaring = seafaring;
        this.morale = morale;
        this.combat = combat;
        this.distance = distance;
        this.island = island;
        this.region = region;
    }
    private final int seafaring;
    private final int morale;
    private final int combat;
    private final int distance;
    private final int region;
    private final int island;
}
