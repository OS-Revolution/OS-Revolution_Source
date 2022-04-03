package ethos.runehub.skill.gathering.farming.patch.herb;

import org.runehub.api.model.skill.farming.patch.AbstractPatchFactory;
import org.runehub.api.model.skill.farming.patch.herb.HerbPatchProperties;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class HerbPatchFactory extends AbstractPatchFactory {

    @Override
    public HerbPatchProperties getPatchProperties(int id) {
        switch (id) {
            case 8150:
                return
                        new HerbPatchProperties(id, new Rectangle(new Point(3058, 3311), new Point(3059, 3312)));
            default:
                return null;
        }
    }
}
