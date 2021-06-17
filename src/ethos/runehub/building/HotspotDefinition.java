package ethos.runehub.building;

import org.rhd.api.model.Loot;

public enum HotspotDefinition {

    WORKBENCH(new HotspotUpgrade(15439,new Loot(995,10000))), //Multiple upgrades can be added and multiple materials can be added as seen below
    SW_PORTAL(new HotspotUpgrade(-1,new Loot(1040,1)),new HotspotUpgrade(0,new Loot(1042,2),new Loot(1050,1))), //
	CENTREPIECE(new HotspotUpgrade(13640,new Loot(995,10000))); //Multiple upgrades can be added and multiple materials can be added as seen below
    private final HotspotUpgrade[] upgrades;

    private HotspotDefinition(HotspotUpgrade...upgrades) {
        this.upgrades = upgrades;
    }

    public HotspotUpgrade[] getUpgrades() {
        return upgrades;
    }
}
