package ethos.runehub.skill.gathering.fishing.action;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.clip.Region;
import ethos.clip.WorldObject;
import ethos.model.items.Item;
import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.Player;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.gathering.GatheringSkillAction;
import ethos.runehub.skill.gathering.fishing.FishLevel;
import ethos.runehub.skill.node.context.impl.FishingNodeContext;
import ethos.runehub.skill.node.impl.RenewableNode;
import ethos.runehub.skill.node.impl.gatherable.impl.FishingNode;
import ethos.runehub.skill.node.io.FishLevelLoader;
import ethos.world.objects.GlobalObject;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.math.AdjustableNumber;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.model.math.impl.IntegerRange;

import java.util.Optional;
import java.util.logging.Logger;

public class FishingSkillAction extends GatheringSkillAction {

    @Override
    protected void onRespawn() {
        int face = 0;
        Optional<WorldObject> worldObject = Region.getWorldObject(
                node.getSpotId(),
                targetedNodeContext.getX(),
                targetedNodeContext.getY(),
                targetedNodeContext.getZ()
        );
        if (worldObject.isPresent()) {
            face = worldObject.get().getFace();
        }
        Server.getGlobalObjects().add(
                new GlobalObject(
                        this.getRenewableNode().getDepletedNodeId(),
                        targetedNodeContext.getX(),
                        targetedNodeContext.getY(),
                        targetedNodeContext.getZ(),
                        face,
                        10,
                        this.getRenewableNode().getRespawnTime(),
                        node.getSpotId())
        );
    }

    @Override
    protected void onEvent() {
    }

    @Override
    protected void checkPreconditions() {
        try {
            super.checkPreconditions();
            Preconditions.checkArgument(node.getBaitId() == -1 || this.getActor().getItems().playerHasItem(node.getBaitId()), "You need " + Item.getItemName(node.getBaitId()) + " to catch anything here.");
        } catch (Exception e) {
            this.stop();
            this.getActor().sendMessage(e.getMessage());
        }
    }

    @Override
    protected void updateAnimation() {
        if (super.getElapsedTicks() == 4 || super.getElapsedTicks() % 4 == 0) {
            this.getActor().startAnimation(this.getActor().getSkillController().getFishing().getGetBestAvailableTool().getAnimationId());
        }
    }

    @Override
    protected boolean isSuccessful(int min, int max) {
        Logger.getGlobal().fine("harvest roll");
//        final int minRoll = this.getTargetedNodeContext().getNode().getGatherMinRoll();
//        final int playerBaseRoll = Skill.SKILL_RANDOM.nextInt(
//                Skill.SKILL_RANDOM.nextInt(this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getGatherOdds())
//                        + 1);
//        final int playerRollModifier = this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getPower();
//        final int playerRoll = playerBaseRoll + playerRollModifier;
        final AdjustableNumber<Integer> canCatch = new AdjustableInteger(0);
        LootTableLoader.getInstance().read(this.getTargetedNodeContext().getNode().getGatherableItemTableId()).roll(this.getFishingLevelBonus()).forEach(loot -> {
            final int itemId = Math.toIntExact(loot.getId());
            final FishLevel fish = FishLevelLoader.getInstance().read(itemId);
            if (canCatchFish(fish)) {
                canCatch.setValue(1);
            } else {
                canCatch.setValue(0);
            }
        });
        return canCatch.value() == 1 && super.isSuccessful(
                (int) (targetedNodeContext.getNode().getMinRoll() * this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getPower()),
                (int) (targetedNodeContext.getNode().getMaxRoll() * this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getPower()));
    }

    @Override
    protected void addXp(int baseAmount) {
        Logger.getGlobal().fine("Adding xp");
        final int baseXp = caughtFish.getXp();
        final double xpModifier = this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getXpGainsRate();
        final int totalXp = Math.toIntExact(Math.round(baseXp * xpModifier));
        this.getActor().getPA().addSkillXP(totalXp, this.getSkillId(), true);
    }

    @Override
    protected void addItems() {
        this.getActor().getItems().deleteItem(node.getBaitId(),1);
        this.getActor().getItems().addItem(caughtFish.getItemId(), 1);
    }

    @Override
    protected RenewableNode getRenewableNode() {
        FishingNode node = (FishingNode) targetedNodeContext.getNode();
        return this.getActor().getSkillController().getRenewableNode(node.getSpotId());
    }

    @Override
    protected boolean depleteNode() {
        return this.getElapsedTicks() >= durationTicks + this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getEfficiency();
    }

    private boolean canCatchFish(FishLevel fish) {
        if (fish != null) {
            if (this.getActor().getItems().playerHasItem(fish.getBaitId()) || fish.getBaitId() == -1) {
                if (fish.getLevelRequired() <= this.getActor().getSkillController().getLevel(this.getSkillId())) {
                    if (fish.getBaitId() == -1 || this.getActor().getItems().playerHasItem(fish.getBaitId())) {
                        caughtFish = fish;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private float getFishingLevelBonus() {
        float baseMagicFind = 1.0f;
        float adjustmentPerLevel = 3.267f;
        float playerLevelAdjustment = this.getActor().getSkillController().getLevel(this.getSkillId()) * adjustmentPerLevel;
        float magicFindBonus = playerLevelAdjustment * 0.001f;
        return baseMagicFind - magicFindBonus;
    }

    public FishingSkillAction(Player player, FishingNodeContext targetedNodeContext, int spotId) {
        super(player, 10, targetedNodeContext, 4, null);
        this.fishingSpot = NPCHandler.getNpc(spotId, targetedNodeContext.getX(), targetedNodeContext.getY());
        this.durationTicks = new IntegerRange(280, 530).getRandomValue();//280,530
        this.spotId = spotId;
    }

    private FishLevel caughtFish;
    private final int durationTicks;
    private final NPC fishingSpot;
    private final int spotId;
    private final FishingNode node = (FishingNode) this.getTargetedNodeContext().getNode();
}
