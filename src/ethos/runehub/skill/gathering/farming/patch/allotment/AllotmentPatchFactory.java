package ethos.runehub.skill.gathering.farming.patch.allotment;

import org.runehub.api.model.skill.farming.patch.AbstractPatchFactory;
import org.runehub.api.model.skill.farming.patch.allotment.AllotmentPatchProperties;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.IrregularPolygon;

public class AllotmentPatchFactory extends AbstractPatchFactory {

    @Override
    public AllotmentPatchProperties getPatchProperties(int id) {
        switch (id) {
            case 8550:
                return
                        new AllotmentPatchProperties(id,
                                new IrregularPolygon(
                                        new Point(3050, 3307),
                                        new Point(3050, 3312),
                                        new Point(3054, 3312),
                                        new Point(3054, 3311),
                                        new Point(3051, 3311),
                                        new Point(3051, 3307),
                                        new Point(3050,3307)
                                ));
            case 8551:
                return
                        new AllotmentPatchProperties(id,
                                new IrregularPolygon(
                                        new Point(3055, 3303),
                                        new Point(3059, 3303),
                                        new Point(3059, 3308),
                                        new Point(3058, 3308),
                                        new Point(3058, 3304),
                                        new Point(3055, 3304),
                                        new Point(3055, 3303)
                                ));
            default:
                return null;
        }
    }
}
