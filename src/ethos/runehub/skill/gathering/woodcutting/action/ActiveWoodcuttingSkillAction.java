package ethos.runehub.skill.gathering.woodcutting.action;

import ethos.Server;
import ethos.model.items.GroundItem;
import ethos.model.npcs.drops.DropManager;
import ethos.model.players.Player;
import ethos.model.players.skills.firemake.Firemaking;
import ethos.model.players.skills.firemake.LogData;
import ethos.runehub.content.journey.JourneyStepType;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.gathering.GatheringSkillAction;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.node.context.impl.WoodcuttingNodeContext;
import ethos.runehub.world.WorldSettingsController;
import ethos.util.Misc;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.Loot;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.util.SkillDictionary;

import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ActiveWoodcuttingSkillAction extends GatheringSkillAction {


    @Override
    protected void onDeplete() {
        this.getActor().getAttributes().getJourneyController().checkJourney(targetedNodeContext.getNodeId(), 1, JourneyStepType.DEPLETION);
        this.onRespawn();
    }

    @Override
    protected GatheringTool getGetBestAvailableTool() throws NullPointerException {
        return this.getActor().getSkillController().getWoodcutting().getGetBestAvailableTool();
    }

    @Override
    protected void onGather() {
        gathered++;
        if (this.isEventTick()) {
            this.onEvent();
        }

        if (this.isException()) {
            this.onException();
        } else {
            if (this.getActor().getSkillController().getWoodcutting().wearingSkillRing()) {
                if (Skill.SKILL_RANDOM.nextInt(100) <= 25) {
                    this.getActor().sendMessage("Your ring shines brightly and burns the log instantly.");
                    final AdjustableInteger logId = new AdjustableInteger(0);
                    LootTableLoader.getInstance().read(this.getTargetedNodeContext().getNode().getGatherableItemTableId()).roll(this.getActor().getAttributes().getMagicFind()).forEach(loot -> {
                        logId.setValue(Math.toIntExact(loot.getId()));
                    });
                    this.getActor().getSkillController().addXP(SkillDictionary.Skill.FIREMAKING.getId(), (int) Objects.requireNonNull(LogData.getLogData(this.getActor(), logId.value())).getExperience());
                } else {
                    this.addItems();
                }
            } else {
                this.addItems();
            }
        }
        this.addXp(this.getTargetedNodeContext().getNode().getInteractionExperience());
        if (this.depleteNode()) {
            this.onDeplete();
        }
    }

    @Override
    protected void addItems() {
        LootTableLoader.getInstance().read(this.getTargetedNodeContext().getNode().getGatherableItemTableId()).roll(this.getActor().getAttributes().getMagicFind()).forEach(loot -> {
            int itemId = Math.toIntExact(loot.getId());
            this.getActor().getAttributes().getJourneyController().checkJourney(itemId, Math.toIntExact(loot.getAmount()), JourneyStepType.COLLECTION);
            this.getActor().getAttributes().getJourneyController().checkJourney(itemId, gathered, JourneyStepType.CHALLENGE);

            if (WorldSettingsController.getInstance().getWorldSettings().getCurrentEventId() == 3) {
                if (Skill.SKILL_RANDOM.nextInt(200) <= 10) {
                    int amount = this.getActor().getAttributes().isMember() ? Misc.random(20) + 1 : Misc.random(10) + 1;
                    this.getActor().getItems().addOrDropItem(2384, amount);
                    this.getActor().sendMessage("You have earned $" + amount + " @" + 2384);
                }
            }
            if (this.getActor().getSkillController().getWoodcutting().wearingEliteSkillRing()) {
                if (Skill.SKILL_RANDOM.nextInt(100) <= 10 && ItemIdContextLoader.getInstance().read(itemId).isNoteable()) {
                    this.getActor().getItems().addItem(itemId + 1, (int) loot.getAmount());
                    this.getActor().sendMessage("Your ring notes the logs for you.");
                } else {
                    this.getActor().getItems().addItem(Math.toIntExact(loot.getId()), Math.toIntExact(loot.getAmount()));
                }
            } else {
                this.getActor().getItems().addItem(Math.toIntExact(loot.getId()), Math.toIntExact(loot.getAmount()));
            }
        });
    }

    @Override
    protected void onEvent() {
        Logger.getGlobal().fine("Dropping Birds Nest");
        final Loot loot = LootTableLoader.getInstance().read(4937229515252058548L).roll(1).stream().collect(Collectors.toList()).get(0);
        final int itemId = Math.toIntExact(loot.getId());
        final int amount = Math.toIntExact(loot.getId());
        this.getActor().sendMessage("@red@A bird nest falls from the tree");

        Server.itemHandler.createGroundItem(this.getActor(),itemId, this.getActor().getX(), this.getActor().getY(),this.getActor().heightLevel, amount);
        this.getActor().getAttributes().getJourneyController().checkJourney(itemId, amount, JourneyStepType.COLLECTION);
        this.getActor().getAttributes().getJourneyController().checkJourney(targetedNodeContext.getNodeId(), amount, JourneyStepType.SKILL_EVENT_FROM_ID);
    }

    @Override
    protected void updateAnimation() {
        if (super.getElapsedTicks() == 4 || super.getElapsedTicks() % 4 == 0) {
            this.getActor().startAnimation(this.getActor().getContext().getPlayerSaveData().getSkillAnimationOverrideMap().containsKey(this.getSkillId()) ?
                    this.getActor().getContext().getPlayerSaveData().getSkillAnimationOverrideMap().get(this.getSkillId()) :
                    this.getActor().getSkillController().getWoodcutting().getGetBestAvailableTool().getAnimationId());
        }
    }

    public ActiveWoodcuttingSkillAction(Player player, int skillId, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(player, skillId, new WoodcuttingNodeContext(nodeId, nodeX, nodeY, nodeZ), 4);
    }

    private int gathered = 0;
}
