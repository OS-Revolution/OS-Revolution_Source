package ethos.runehub.entity.mob.hostile;

import org.runehub.api.model.entity.user.character.mob.hostile.HostileMobCharacterEntity;
import org.runehub.api.model.world.Face;
import org.runehub.api.model.world.region.location.Location;

public class HostileMob implements HostileMobCharacterEntity {
    @Override
    public HostileMobContext getContext() {
        return context;
    }

    @Override
    public HostileMobAttribute getAttributes() {
        return attribute;
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

    public HostileMob(int id) {
        this.attribute = new HostileMobAttribute(this);
        this.context = HostileMobIdContextLoader.getInstance().read(id);
    }

    private final HostileMobContext context;
    private final HostileMobAttribute attribute;
}
