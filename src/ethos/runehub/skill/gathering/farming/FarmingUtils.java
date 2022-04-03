package ethos.runehub.skill.gathering.farming;

import org.runehub.api.model.skill.farming.patch.*;
import org.runehub.api.util.math.geometry.Point;

public class FarmingUtils {

    public static PatchFactoryProducer.PatchType getPatchType(int objectId) {
        switch (objectId) {
            case 8550:
            case 8551:
                return PatchFactoryProducer.PatchType.ALLOTMENT;
            default:
                return null;
        }
    }

    public static PatchProperties getPatchProperties(int objectId, int x, int y) {
        PatchFactoryProducer.PatchType patchType = getPatchType(objectId);
        if(patchType != null) {
            return PatchFactoryProducer.getPatchFactory(patchType).getPatchProperties(objectId);
        }
        return null;
    }

    public static Patch getPatchFromLocation(int x, int y) {
        return PatchDatabase.getInstance().read(PatchPropertiesDatabase.getInstance().getAllEntries()
                .stream().filter(properties -> properties.getBoundary().contains(new Point(x,y)))
                .findAny().orElseThrow(NullPointerException::new).getId());
    }

}
