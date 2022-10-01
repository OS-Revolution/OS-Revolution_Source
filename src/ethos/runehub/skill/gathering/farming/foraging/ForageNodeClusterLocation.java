package ethos.runehub.skill.gathering.farming.foraging;

import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.IrregularPolygon;

public enum ForageNodeClusterLocation {

    DRAYNOR(0,new IrregularPolygon(
            new Point(3109, 3212),
            new Point(3109, 3209),
            new Point(3100, 3209),
            new Point(3100, 3212),
            new Point(3109, 3212)
    )),
    TAVERLY(5,new IrregularPolygon(
            new Point(2918, 3442),
            new Point(2919, 3442),
            new Point(2919, 3444),
            new Point(2920, 3444),
            new Point(2920, 3445),
            new Point(2919, 3445),
            new Point(2919, 3446),
            new Point(2916, 3446),
            new Point(2916, 3445),
            new Point(2918, 3445),
            new Point(2918, 3442)
    )),
    WILDNERNESS_WEST_DRAGONS(1,new IrregularPolygon(
            new Point(2962, 3585),
            new Point(2962, 3594),
            new Point(2957, 3594),
            new Point(2957, 3585),
            new Point(2962, 3585)
    )),
    WILDNERNESS_SCORPIA(8,new IrregularPolygon(
            new Point(3229, 2925),
            new Point(3229, 2915),
            new Point(3219, 2915),
            new Point(3219, 2925),
            new Point(3229, 2925)
    )),
    CATHERBY_SOUTH_ALLOTMENT(0,new IrregularPolygon(
            new Point(2805,3459),
            new Point(2805, 3561),
            new Point(2806, 3561),
            new Point(2806, 3560),
            new Point(2814, 3560),
            new Point(2814, 3559),
            new Point(2805,3459)
    )),
    CATHERBY_NORTH_ALLOTMENT(0,new IrregularPolygon(
            new Point(2806,3466),
            new Point(2806, 3467),
            new Point(2814, 3467),
            new Point(2814, 3468),
            new Point(2805, 3468),
            new Point(2805, 3466),
            new Point(2806,3466)
    )),
    FALADOR_GARDEN(10,new IrregularPolygon(
            new Point(3003,3374),
            new Point(3003, 3372),
            new Point(3005, 3372),
            new Point(3005, 3374),
            new Point(3003, 3374)
    )),
    SEERS_VILLAGE(4,new IrregularPolygon(
            new Point(2722,3506),
            new Point(2722, 3503),
            new Point(2730, 3503),
            new Point(2730, 3506),
            new Point(2722, 3506)
    )),
    LUMBRIDGE_SWAMP(20,new IrregularPolygon(
            new Point(3215,3180),
            new Point(3218, 3180),
            new Point(3218, 3076),
            new Point(3215, 3076),
            new Point(3215,3180)
    )),
    FELDIP_HILLS(20,new IrregularPolygon(
            new Point(2544,2924),
            new Point(2538, 2924),
            new Point(2538, 2927),
            new Point(2544, 2927),
            new Point(2544,2924)
    ));

    public IrregularPolygon getSpawnArea() {
        return spawnArea;
    }

    public int getWeight() {
        return weight;
    }

    private ForageNodeClusterLocation(int weight,IrregularPolygon spawnArea) {
        this.weight = weight;
        this.spawnArea = spawnArea;
    }

    private final int weight;
    private final IrregularPolygon spawnArea;

}
