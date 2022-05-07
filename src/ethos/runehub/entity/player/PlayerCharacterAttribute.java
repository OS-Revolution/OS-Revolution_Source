package ethos.runehub.entity.player;

import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.item.ItemReactionProcessor;
import org.rhd.api.entity.user.character.CharacterEntity;
import org.rhd.api.entity.user.character.CharacterEntityAttribute;

import java.util.ArrayList;
import java.util.List;

public class PlayerCharacterAttribute extends CharacterEntityAttribute {


    public PlayerCharacterAttribute(CharacterEntity owner) {
        super(owner);
        this.itemReactionProcessor = new ItemReactionProcessor();
        this.guardsAttacking = new ArrayList<>();
    }

    public List<Integer> getGuardsAttacking() {
        return guardsAttacking;
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

    public boolean isEnteringValue() {
        return enteringValue;
    }

    public void setEnteringValue(boolean enteringValue) {
        this.enteringValue = enteringValue;
    }

    public float getTeleportRechargeReduction() {
        return teleportRechargeReduction;
    }

    public void setTeleportRechargeReduction(float teleportRechargeReduction) {
        this.teleportRechargeReduction = teleportRechargeReduction;
    }

    public DialogSequence getActiveDialogSequence() {
        return activeDialogSequence;
    }

    public void setActiveDialogSequence(DialogSequence activeDialogSequence) {
        this.activeDialogSequence = activeDialogSequence;
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }

    public boolean isMovementResricted() {
        return movementResricted;
    }

    public void setMovementResricted(boolean movementResricted) {
        this.movementResricted = movementResricted;
    }

    public int getInteractingWithNodeId() {
        return interactingWithNodeId;
    }

    public void setInteractingWithNodeId(int interactingWithNodeId) {
        this.interactingWithNodeId = interactingWithNodeId;
    }

    public boolean isActionLocked() {
        return actionLocked;
    }

    public void setActionLocked(boolean interruptableAction) {
        this.actionLocked = interruptableAction;
    }

    public long getCaughtThievingTimestamp() {
        return caughtThievingTimestamp;
    }

    public void setCaughtThievingTimestamp(long caughtThievingTimestamp) {
        this.caughtThievingTimestamp = caughtThievingTimestamp;
    }



    private boolean movementResricted,actionLocked,usingLootBox,enteringValue;
    private float magicFind,teleportRechargeReduction;
    private int integerInput = 0;
    private final ItemReactionProcessor itemReactionProcessor;
    private DialogSequence activeDialogSequence;
    private int selectedOption,interactingWithNodeId;
    private long caughtThievingTimestamp;
    private final List<Integer> guardsAttacking;

}
