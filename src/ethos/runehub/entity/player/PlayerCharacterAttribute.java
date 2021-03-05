package ethos.runehub.entity.player;

import org.rhd.api.entity.user.character.CharacterEntity;
import org.rhd.api.entity.user.character.CharacterEntityAttribute;
import org.rhd.api.entity.user.character.player.PlayerCharacterEntity;

public class PlayerCharacterAttribute extends CharacterEntityAttribute {


    private boolean usingLootBox;

    public PlayerCharacterAttribute(CharacterEntity owner) {
        super(owner);
    }

    public boolean isUsingLootBox() {
        return usingLootBox;
    }

    public void setUsingLootBox(boolean usingLootBox) {
        this.usingLootBox = usingLootBox;
    }
}
