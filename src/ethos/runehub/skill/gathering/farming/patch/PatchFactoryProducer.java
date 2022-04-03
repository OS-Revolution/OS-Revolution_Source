package ethos.runehub.skill.gathering.farming.patch;


import org.runehub.api.model.skill.farming.patch.AbstractPatchFactory;
import org.runehub.api.model.skill.farming.patch.allotment.AllotmentPatchFactory;
import org.runehub.api.model.skill.farming.patch.herb.HerbPatchFactory;

public class PatchFactoryProducer {

    public static enum PatchType { HERB,ALLOTMENT,FLOWER,TREE,FRUIT_TREE,BUSH,HOPS,SPECIAL;}

    public static AbstractPatchFactory getPatchFactory(PatchType patchType) {
        switch (patchType) {
            case HERB:
                return new HerbPatchFactory();
            case ALLOTMENT:
                return new AllotmentPatchFactory();
            default: throw new NullPointerException("No Patch Factory");
        }
    }
}
