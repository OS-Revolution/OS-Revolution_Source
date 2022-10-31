package ethos.runehub.entity.mob.hostile;

import ethos.runehub.RunehubUtils;
import org.runehub.api.model.entity.user.character.CharacterEntity;
import org.runehub.api.model.entity.user.character.CharacterEntityAttribute;
import org.runehub.api.model.world.region.location.Location;
import org.runehub.api.util.math.geometry.Point;

public class HostileMobAttribute extends CharacterEntityAttribute {

    public int getRegionId() {
        return RunehubUtils.getRegionId(location.getXCoordinate(),location.getZCoordinate());
    }

    public Location getLocation() {
        return location;
    }

    public HostileMobAttribute(CharacterEntity owner) {
        super(owner);
    }

    private Location location;
}
