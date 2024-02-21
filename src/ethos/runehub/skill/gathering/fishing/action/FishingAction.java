package ethos.runehub.skill.gathering.fishing.action;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.clip.Region;
import ethos.clip.WorldObject;
import ethos.model.players.Player;
import ethos.runehub.LootTableContainerUtils;
import ethos.runehub.skill.gathering.GatheringSkillAction;
import ethos.runehub.skill.gathering.fishing.FishLevel;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.gathering.tool.GatheringToolLoader;
import ethos.runehub.skill.node.context.impl.GatheringNodeContext;
import ethos.runehub.skill.node.impl.RenewableNode;
import ethos.runehub.skill.node.impl.gatherable.impl.FishingNode;
import ethos.runehub.skill.node.io.FishLevelLoader;
import ethos.util.PreconditionUtils;
import ethos.world.objects.GlobalObject;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.Loot;
import org.runehub.api.model.math.AdjustableNumber;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.model.math.impl.IntegerRange;
import org.runehub.api.util.SkillDictionary;

import java.util.*;
import java.util.stream.Collectors;

public abstract class FishingAction extends GatheringSkillAction {

    @Override
    protected void updateAnimation() {
        this.getActor().startAnimation(this.getGetBestAvailableTool().getAnimationId());

    }

    @Override
    protected GatheringTool getGetBestAvailableTool() throws NullPointerException {
        final List<GatheringTool> availableTools = GatheringToolLoader.getInstance().readAll().stream().filter(tool -> tool.getSkillId() == this.getSkillId()).filter(tool -> isNodeTool(tool.getItemId())).filter(tool -> hasNodeTool(tool.getItemId())).filter(tool -> this.getActor().getSkillController().getLevel(this.getSkillId()) >= tool.getLevelRequired()).collect(Collectors.toList());
        final int bestToolLevel = availableTools.stream().mapToInt(GatheringTool::getLevelRequired).max().orElse(1);

        return availableTools.stream().filter(tool -> tool.getLevelRequired() == bestToolLevel).findFirst().orElse(null);
    }

    private boolean isNodeTool(int id) {
        final int nodeToolId = ((FishingNode) this.getTargetedNodeContext().getNode()).getToolId();
        if (id == nodeToolId) {
            return true;
        } else return isHarpoon(id) && isHarpoon(nodeToolId);
    }

    private boolean hasNodeTool(int id) {
        final int nodeToolId = ((FishingNode) this.getTargetedNodeContext().getNode()).getToolId();
        if (this.playerHasItem(id)) {
            return true;
        } else return isHarpoon(id) && isHarpoon(nodeToolId) && playerHasItem(id);
    }

    private boolean playerHasItem(int id) {
        return this.getActor().getItems().playerHasItem(id) || this.getActor().getItems().isWearingItem(id);
    }

    private boolean isHarpoon(int id) {
        return id == 311 | id == 10129 | id == 21028 | id == 21031;
    }

    @Override
    protected boolean isSuccessful(int min, int max) {
//        Collection<Loot> lootCollection = LootTableContainerUtils.open(
//                LootTableLoader.getInstance().read(this.getTargetedNodeContext().getNode().getGatherableItemTableId()),
//                this.getFishingLevelBonus()
//        );
        final List<Loot> fishLoot = new ArrayList<>(LootTableContainerUtils.open(
                LootTableLoader.getInstance().read(this.getTargetedNodeContext().getNode().getGatherableItemTableId()),
                this.getFishingLevelBonus()
        ));
        System.out.println(this.getFishingLevelBonus());
        System.out.println(fishLoot.size());
        if (!fishLoot.isEmpty()) {
            int fish = Math.toIntExact(fishLoot.get(0).getId());
            boolean canCatch = this.canCatchFish(FishLevelLoader.getInstance().read(fish));
            int minRoll = (int) (targetedNodeContext.getNode().getMinRoll() * this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getPowerBonus());
            int maxRoll = (int) (targetedNodeContext.getNode().getMaxRoll() * this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getPowerBonus());
            return canCatch && super.isSuccessful(minRoll, maxRoll);
        }
        return false;
    }

    @Override
    protected void addXp(int baseAmount) {
        final int baseXp = caughtFish.getXp();
        final double xpModifier = this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getGainsBonus();
        final int totalXp = Math.toIntExact(Math.round(baseXp * xpModifier));
        this.getActor().getPA().addSkillXP(totalXp, this.getSkillId(), true);
    }

    @Override
    protected void addItems() {
        this.getActor().getItems().deleteItem(node.getBaitId(), 1);
        this.getActor().getItems().addItem(caughtFish.getItemId(), 1);
        if (caughtFish.getItemId() == 321) {
            this.getActor().getAttributes().getAchievementController().completeAchievement(5566337423378434124L);
        }
    }

    @Override
    protected RenewableNode getRenewableNode() {
        FishingNode node = (FishingNode) targetedNodeContext.getNode();
        return this.getActor().getSkillController().getRenewableNode(node.getSpotId());
    }

    @Override
    protected boolean depleteNode() {
        return this.getElapsedTicks() >= durationTicks + this.getActor().getSkillController().getGatheringSkill(this.getSkillId()).getEfficiencyBonus();
    }

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
//        float baseMagicFind = 1.0f;
        float adjustmentPerLevel = 3.267f;
        float playerLevelAdjustment = this.getActor().getSkillController().getLevel(this.getSkillId()) * adjustmentPerLevel;
        float magicFindBonus = playerLevelAdjustment * 0.001f;
        return magicFindBonus;
    }

    @Override
    protected void validateWorldRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isFalse(Server.getGlobalObjects().exists(-1, targetedNodeContext.getX(), targetedNodeContext.getY())),
                "");
    }

    @Override
    protected void onEvent() {
        if (this.getActor().getAttributes().isMember()) {
            this.getActor().sendMessage("You catch a strange looking fish");
            this.getActor().getItems().addOrDropItem(3742, 1);
        }
    }


    public FishingAction(Player player, GatheringNodeContext<?> targetedNodeContext, int ticks) {
        super(player, SkillDictionary.Skill.FISHING.getId(), targetedNodeContext, ticks);
        this.durationTicks = new IntegerRange(280, 530).getRandomValue();//280,530
//        this.getActor().getSkillController().getFishing().setGains(this.getActor().getSkillController().getFishing().getGains() + this.getEquipmentBonuses());
        System.out.println("Fishing Gains: " + this.getActor().getSkillController().getFishing().getGainsBonus());
    }

    private FishLevel caughtFish;
    private final int durationTicks;
    private final FishingNode node = (FishingNode) this.getTargetedNodeContext().getNode();
}
