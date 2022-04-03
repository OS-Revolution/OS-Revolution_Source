package ethos.runehub.skill.gathering.farming.patch.allotment;

import org.runehub.api.io.load.LazyLoader;
import org.runehub.api.model.skill.farming.patch.PatchFactoryProducer;
import org.runehub.api.model.skill.farming.patch.allotment.AllotmentPatchProperties;

public class AllotmentPatchPropertiesCache extends LazyLoader<Integer, org.runehub.api.model.skill.farming.patch.allotment.AllotmentPatchProperties> {

    private static AllotmentPatchPropertiesCache instance = null;

    public static AllotmentPatchPropertiesCache getInstance() {
        if (instance == null)
            instance = new AllotmentPatchPropertiesCache();
        return instance;
    }

    private AllotmentPatchPropertiesCache() {
        super(null);
    }

    @Override
    public org.runehub.api.model.skill.farming.patch.allotment.AllotmentPatchProperties load(Integer key) {
        return (AllotmentPatchProperties) PatchFactoryProducer.getPatchFactory(PatchFactoryProducer.PatchType.ALLOTMENT).getPatchProperties(key);
    }
}
