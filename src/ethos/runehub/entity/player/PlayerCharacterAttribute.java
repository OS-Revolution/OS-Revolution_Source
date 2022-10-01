package ethos.runehub.entity.player;

import ethos.runehub.content.gambling.cards.Card;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.item.ItemReactionProcessor;
import ethos.runehub.loot.Lootbox;
import ethos.runehub.ui.GameUI;
import org.runehub.api.model.entity.user.character.CharacterEntity;
import org.runehub.api.model.entity.user.character.CharacterEntityAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class PlayerCharacterAttribute extends CharacterEntityAttribute {


    public PlayerCharacterAttribute(CharacterEntity owner) {
        super(owner);
        this.itemReactionProcessor = new ItemReactionProcessor();
        this.guardsAttacking = new ArrayList<>();
    }

    public List<Integer> getGuardsAttacking() {
        return guardsAttacking;
    }

    public Lootbox getActiveLootBox() {
        return activeLootBox;
    }

    public void setActiveLootBox(Lootbox activeLootBox) {
        this.activeLootBox = activeLootBox;
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

    public String getEnteredString() {
        return enteredString;
    }

    public void setEnteredString(String enteredString) {
        this.enteredString = enteredString;
    }

    public boolean isUsingStar() {
        return usingStar;
    }

    public void setUsingStar(boolean usingStar) {
        this.usingStar = usingStar;
    }

    public int getSkillSelected() {
        return skillSelected;
    }

    public void setSkillSelected(int skillSelected) {
        this.skillSelected = skillSelected;
    }

    public GameUI getActiveUI() {
        return activeUI;
    }

    public void setActiveUI(GameUI activeUI) {
        this.activeUI = activeUI;
    }

    public ScheduledExecutorService getFarmTickExecutorService() {
        return farmTickExecutorService;
    }

    private boolean movementResricted,actionLocked,enteringValue,usingStar;
    private float magicFind,teleportRechargeReduction;
    private int integerInput = 0;
    private final ItemReactionProcessor itemReactionProcessor;
    private DialogSequence activeDialogSequence;
    private int selectedOption,interactingWithNodeId,skillSelected = -1;
    private long caughtThievingTimestamp;
    private final List<Integer> guardsAttacking;
    private Lootbox activeLootBox;
    private String enteredString;
    private GameUI activeUI;

    private final ScheduledExecutorService farmTickExecutorService = Executors.newScheduledThreadPool(1);

}
