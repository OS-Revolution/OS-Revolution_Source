package ethos.runehub.skill.gathering.farming.patch.herb;

import org.runehub.api.model.skill.farming.patch.PatchProperties;
import org.runehub.api.util.math.geometry.impl.Polygon;

public class HerbPatchProperties extends PatchProperties {
    public HerbPatchProperties(int id, Polygon boundary, long growthCycle,boolean waterable) {
        super(id, boundary, growthCycle,waterable);
    }

    public HerbPatchProperties(int id, Polygon boundary) {
        this(id, boundary, 1000,false);
    }
}
