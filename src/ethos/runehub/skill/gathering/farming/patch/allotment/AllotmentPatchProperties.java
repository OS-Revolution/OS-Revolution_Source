package ethos.runehub.skill.gathering.farming.patch.allotment;

import org.runehub.api.model.skill.farming.patch.PatchProperties;
import org.runehub.api.util.math.geometry.impl.Polygon;

public class AllotmentPatchProperties extends PatchProperties {
    public AllotmentPatchProperties(int id, Polygon boundary, long growthCycle, boolean waterable) {
        super(id, boundary, growthCycle,waterable);
    }

    public AllotmentPatchProperties(int id, Polygon boundary) {
        this(id, boundary, 60000,true);
    }
}
