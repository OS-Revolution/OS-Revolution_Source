package ethos.runehub.entity.player;

import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import org.runehub.api.model.entity.user.character.CharacterEntityAttribute;
import org.runehub.api.model.entity.user.character.player.PlayerCharacterEntity;
import org.runehub.api.model.world.Face;
import org.runehub.api.model.world.region.location.Location;

public class PlayerCharacter implements PlayerCharacterEntity {

    @Override
    public PlayerCharacterContext getContext() {
        return context;
    }

    @Override
    public CharacterEntityAttribute getAttributes() {
        return attributes;
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

    public PlayerCharacter(long id) {
        this.attributes = new PlayerCharacterAttribute(this);
        this.context = PlayerCharacterContextDataAccessObject.getInstance().read(id);
    }

    private final PlayerCharacterAttribute attributes;
    private final PlayerCharacterContext context;
}
