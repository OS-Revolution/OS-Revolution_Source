package ethos.runehub.entity.player;

import ethos.runehub.entity.item.ItemReactionProcessor;
import org.rhd.api.entity.user.character.CharacterEntity;
import org.rhd.api.entity.user.character.CharacterEntityAttribute;

public class PlayerCharacterAttribute extends CharacterEntityAttribute {


    public PlayerCharacterAttribute(CharacterEntity owner) {
        super(owner);
        this.itemReactionProcessor = new ItemReactionProcessor();
    }

    public boolean isUsingLootBox() {
        return usingLootBox;
    }

    public void setUsingLootBox(boolean usingLootBox) {
        this.usingLootBox = usingLootBox;
    }

    public float getMagicFind() {
        return magicFind;
    }

    public void setMagicFind(float magicFind) {
        this.magicFind = magicFind;
    }

    public int getIntegerInput() {
        return integerInput;
    }

    public ItemReactionProcessor getItemReactionProcessor() {
        return itemReactionProcessor;
    }

    public void setIntegerInput(int integerInput) {
        this.integerInput = integerInput;
    }

    private boolean usingLootBox;
    private float magicFind;
    private int integerInput = -1;
    private final ItemReactionProcessor itemReactionProcessor;

}
