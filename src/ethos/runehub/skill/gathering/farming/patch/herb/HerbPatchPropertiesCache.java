package ethos.runehub.skill.gathering.farming.patch.herb;

import org.runehub.api.io.load.LazyLoader;
import org.runehub.api.model.skill.farming.patch.PatchFactoryProducer;
import org.runehub.api.model.skill.farming.patch.herb.HerbPatchProperties;

public class HerbPatchPropertiesCache extends LazyLoader<Integer, org.runehub.api.model.skill.farming.patch.herb.HerbPatchProperties> {

    private static HerbPatchPropertiesCache instance = null;

    public static HerbPatchPropertiesCache getInstance() {
        if (instance == null)
            instance = new HerbPatchPropertiesCache();
        return instance;
    }

    private HerbPatchPropertiesCache() {
        super(null);
    }

    @Override
    public org.runehub.api.model.skill.farming.patch.herb.HerbPatchProperties load(Integer key) {
        return (HerbPatchProperties) PatchFactoryProducer.getPatchFactory(PatchFactoryProducer.PatchType.HERB).getPatchProperties(key);
    }
}
