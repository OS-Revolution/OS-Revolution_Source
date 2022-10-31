package ethos.runehub.entity.player;

import ethos.model.npcs.NPC;
import ethos.model.players.Player;
import ethos.runehub.content.PlayPassController;
import ethos.runehub.content.rift.RiftFloorDAO;
import ethos.runehub.content.rift.impl.NephalemRift;
import ethos.runehub.content.rift.party.Party;
import ethos.runehub.content.rift.Rift;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.combat.CombatController;
import ethos.runehub.entity.combat.impl.PvECombatController;
import ethos.runehub.entity.item.ItemReactionProcessor;
import ethos.runehub.entity.item.equipment.Equipment;
import ethos.runehub.entity.item.equipment.EquipmentCache;
import ethos.runehub.entity.item.equipment.EquipmentSlot;
import ethos.runehub.entity.mob.hostile.HostileMob;
import ethos.runehub.entity.node.Node;
import ethos.runehub.entity.player.action.ActionController;
import ethos.runehub.entity.player.action.impl.EquipmentSlotUpdateAction;
import ethos.runehub.entity.player.action.impl.EquipmentUpdateAction;
import ethos.runehub.entity.player.action.impl.ItemRenderUpdateAction;
import ethos.runehub.loot.Lootbox;
import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.runehub.skill.gathering.farming.patch.PatchType;
import ethos.runehub.skill.node.context.impl.GatheringNodeContext;
import ethos.runehub.ui.ActionDispatcher;
import ethos.runehub.ui.GameUI;
import ethos.runehub.ui.impl.tab.TabUI;
import ethos.runehub.ui.impl.tab.player.AchievementTab;
import ethos.runehub.ui.impl.tab.player.DistractionsTab;
import ethos.runehub.ui.impl.tab.player.PlayerTabUI;
import ethos.runehub.ui.impl.tab.player.QuestTab;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.entity.user.character.CharacterEntity;
import org.runehub.api.model.entity.user.character.CharacterEntityAttribute;
import org.runehub.api.util.math.geometry.Point;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class PlayerCharacterAttribute extends CharacterEntityAttribute {


    public PlayerCharacterAttribute(Player owner) {
        super(owner);
        this.itemReactionProcessor = new ItemReactionProcessor();
        this.guardsAttacking = new ArrayList<>();
        this.instanceNodes = new HashMap<>();
        this.playPassController = new PlayPassController(owner);
        this.actionDispatcher = new ActionDispatcher();
        this.actionController = new ActionController();
        this.pvECombatController = new PvECombatController(owner);

        this.actionDispatcher.registerButton(actionEvent -> this.getOwner().getPA().closeAllWindows(),250002);
    }

    public CombatController<Player, NPC> getPvECombatController() {
        return pvECombatController;
    }

    public ActionController getActionController() {
        return actionController;
    }

    public ActionDispatcher getActionDispatcher() {
        return actionDispatcher;
    }

    public PlayPassController getPlayPassController() {
        return playPassController;
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

//    public float getMagicFind() {
//        return magicFind;
//    }

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

    public TabUI getTabUI() {
        return tabUI;
    }

    public void setTabUI(TabUI tabUI) {
        this.tabUI = tabUI;
    }

    public ScheduledExecutorService getFarmTickExecutorService() {
        return farmTickExecutorService;
    }

    public boolean isInRift() {
        return inRift;
    }

    public void setInRift(boolean inRift) {
        this.inRift = inRift;
    }

    public Rift getRift() {
        return rift;
    }

    public void setRift(Rift rift) {
        this.rift = rift;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public Map<Integer, List<Node>> getInstanceNodes() {
        return instanceNodes;
    }

    public void addInstanceNode(int regionId, Node node) {
        if (instanceNodes.containsKey(regionId)) {
            instanceNodes.get(regionId).add(node);
        } else {
            instanceNodes.put(regionId, List.of(node));
        }
    }

    public void removeInstanceNode(int regionId, Node node) {
        if (instanceNodes.containsKey(regionId)) {
            List<Node> update = new ArrayList<>();
            instanceNodes.get(regionId).stream().filter(n -> n.getId() != node.getId()).forEach(update::add);
            instanceNodes.get(regionId).clear();
            instanceNodes.get(regionId).addAll(update);
        }
    }

    public void dropNodesForRegion(int region) {
        instanceNodes.remove(region);
    }

    public int getStartingFloor() {
        return startingFloor;
    }

    public void setStartingFloor(int startingFloor) {
        this.startingFloor = startingFloor;
    }

    public boolean isMember() {
        return this.getOwner().getContext().getPlayerSaveData().getMembershipDurationMS() > 0;
    }

    public float getMagicFind() {
        float mf = (float) (magicFind + this.getOwner().getContext().getPlayerSaveData().getMagicFind().value());
        if (isMember()) {
            mf += 0.05f;
        }
        if (rift != null && RiftFloorDAO.getInstance().getAllEntries().stream().anyMatch(riftFloor -> riftFloor.getBoundingBox().contains(new Point(this.getOwner().getX(),this.getOwner().getY())))) {
            mf += 0.05f * rift.getDifficulty().ordinal();
        }
        System.out.println("Magic Find: " + mf);
        return mf;
    }

    public int getExchangeSlots() {
        int slots = this.getOwner().getContext().getPlayerSaveData().getExchangeSlots();
        if (isMember()) {
            slots += 3;
        }
        return slots;
    }

    public String getName() {
        return this.getOwner().playerName.replaceAll(" ", "_");
    }

    @Override
    public Player getOwner() {
        return (Player) super.getOwner();
    }

    private boolean movementResricted, actionLocked, enteringValue, usingStar, inRift;
    private float magicFind, teleportRechargeReduction;
    private int integerInput = 0;
    private final ItemReactionProcessor itemReactionProcessor;
    private DialogSequence activeDialogSequence;
    private int selectedOption, interactingWithNodeId, skillSelected = -1;
    private long caughtThievingTimestamp;
    private final List<Integer> guardsAttacking;
    private Lootbox activeLootBox;
    private String enteredString;
    private GameUI activeUI;
    private TabUI tabUI;

    private final ScheduledExecutorService farmTickExecutorService = Executors.newScheduledThreadPool(1);

    private final Map<Integer, List<Node>> instanceNodes;

    private Rift rift;
    private int startingFloor;
    private Party party;

    private final PlayPassController playPassController;
    private final ActionDispatcher actionDispatcher;
    private final ActionController actionController;
    private final CombatController<Player, NPC> pvECombatController;
}
