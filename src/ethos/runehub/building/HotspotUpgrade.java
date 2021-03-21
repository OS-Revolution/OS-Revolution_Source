package ethos.runehub.building;

import ethos.model.items.Item;
import org.rhd.api.model.Loot;

import java.util.Arrays;
import java.util.List;

public class HotspotUpgrade {

    private final List<Loot> materials;
    private final int upgradedObjectId;

    public HotspotUpgrade(int upgradedObjectId,Loot...materials) {
        this.upgradedObjectId = upgradedObjectId;
        this.materials = Arrays.asList(materials);
    }

    public int getUpgradedObjectId() {
        return upgradedObjectId;
    }

    public List<Loot> getMaterials() {
        return materials;
    }
}
