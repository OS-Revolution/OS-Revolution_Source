package ethos.runehub.entity.mob;

import ethos.model.npcs.NPC;
import ethos.runehub.RunehubConstants;
import ethos.runehub.entity.mob.hostile.HostileMob;
import ethos.util.Stream;
import org.runehub.api.model.entity.user.character.mob.MobCharacterEntity;
import org.runehub.api.model.world.region.location.Location;

public class MobController {

    private static MobController instance = null;

    public static MobController getInstance() {
        if (instance == null)
            instance = new MobController();
        return instance;
    }

    public MobCharacterEntity getMob(int index) {
        return capacity[index];
    }

    private MobController() {
        this.capacity = new MobCharacterEntity[1000];
    }

    private final MobCharacterEntity[] capacity;
}
