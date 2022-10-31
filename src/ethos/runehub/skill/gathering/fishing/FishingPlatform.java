package ethos.runehub.skill.gathering.fishing;

import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class FishingPlatform {

    public static final int MINNOW_SPOT_ID = 20930;
    public static final int SPOT_CAP = 20;

    public Rectangle getEastInterior() {
        return eastInterior;
    }

    public Rectangle getEastPlatform() {
        return eastPlatform;
    }

    public Rectangle getNePlatform() {
        return nePlatform;
    }

    public Rectangle getNwPlatform() {
        return nwPlatform;
    }

    public Rectangle getSePlatform() {
        return sePlatform;
    }

    public Rectangle getSwPlatform() {
        return swPlatform;
    }

    public Rectangle getWestPlatform() {
        return westPlatform;
    }

    public Rectangle getWestInterior() {
        return westInterior;
    }

    public FishingPlatform() {
        this.nwPlatform = new Rectangle(new Point(2607,3447),new Point(2614,3447));
        this.nePlatform = new Rectangle(new Point(2616,3447),new Point(2622,3447));
        this.eastPlatform = new Rectangle(new Point(2623,3446),new Point(2623,3442));
        this.sePlatform = new Rectangle(new Point(2617,3440),new Point(2621,3440));
        this.swPlatform = new Rectangle(new Point(2608,3440),new Point(2612,3440));
        this.westPlatform = new Rectangle(new Point(2606,3442),new Point(2606,3446));
        this.westInterior = new Rectangle(new Point(2609,3443),new Point(2612,3444));
        this.eastInterior = new Rectangle(new Point(2617,3443),new Point(2620,3444));
    }

    private final Rectangle nwPlatform,nePlatform,eastPlatform,westPlatform,swPlatform,sePlatform,eastInterior,westInterior;
}
