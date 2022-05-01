package ethos.runehub.entity.mob;

import org.runehub.api.model.entity.EntityContext;
import org.runehub.api.model.entity.user.character.CharacterEntityAttribute;
import org.runehub.api.model.entity.user.character.mob.hostile.HostileMobCharacterEntity;
import org.runehub.api.model.world.Face;
import org.runehub.api.model.world.region.location.Location;

public class Mob implements HostileMobCharacterEntity {
    @Override
    public HostileMobContext getContext() {
        return null;
    }

    @Override
    public CharacterEntityAttribute getAttributes() {
        return null;
    }

    @Override
    public boolean walkTo(Location location) {
        return false;
    }

    @Override
    public boolean setLocation(Location location) {
        return false;
    }

    @Override
    public boolean face(Face face) {
        return false;
    }
}
