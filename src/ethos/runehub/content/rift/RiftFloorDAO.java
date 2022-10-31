package ethos.runehub.content.rift;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class RiftFloorDAO extends BetaAbstractDataAcessObject<RiftFloor> {

    private static RiftFloorDAO instance = null;

    public static RiftFloorDAO getInstance() {
        if (instance == null)
            instance = new RiftFloorDAO();
        return instance;
    }

    protected RiftFloorDAO() {
        super(RunehubConstants.RIFT_DB, RiftFloor.class);
        create(new RiftFloor(0,
                1859,
                5243,
                new Rectangle(new Point(1855, 5184), new Point(1915, 5247)).getBoundingBox(),
                new int[]{7021, 3353, 109},
                new int[]{2510, 106, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 2481, 2482, 2483}
        ));
        create(new RiftFloor(1,
                2042, 5244,
                new Rectangle(new Point(1983, 5183), new Point(2049, 5249)).getBoundingBox(),
                new int[]{720, 794, 138},
                new int[]{2479, 26,27,28,2498}
        ));
        create(new RiftFloor(2,
                2123, 5251,
                new Rectangle(new Point(2113, 5248), new Point(2175, 5312)).getBoundingBox(),
                new int[]{3204, 3353, 1443},
                new int[]{3021,3017,3020,2479,2474,2831}
        ));
        create(new RiftFloor(3,
                2309, 5239,
                new Rectangle(new Point(2303, 5183), new Point(2368, 5248)).getBoundingBox(),
                new int[]{240,2025,680,7020},
                new int[]{5633,70,71,72,85,2514,2005}
        ));
    }
}
