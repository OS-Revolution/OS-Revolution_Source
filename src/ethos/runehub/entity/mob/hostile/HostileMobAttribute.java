package ethos.runehub.entity.mob.hostile;

import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.mob.AnimationDefinition;
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

    public AnimationDefinition getAnimationDefinition() {
        return animationDefinition;
    }

    public void setAnimationDefinition(AnimationDefinition animationDefinition) {
        this.animationDefinition = animationDefinition;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public HostileMobAttribute(CharacterEntity owner) {
        super(owner);
    }

    private Location location;
    private AnimationDefinition animationDefinition;
    private int hitpoints;
}
