package ethos.runehub.skill.artisan.smithing.action;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.artisan.smithing.SmithingItem;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.gathering.tool.GatheringToolLoader;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SmithItemAction extends SkillAction {

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(tool.getAnimationId());
//        this.getActor().startAnimation(tool.getAnimationId());

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        if (actions == 0 || actionsDone < actions) {
            tool = this.getGetBestAvailableTool();
            this.craft();
        } else {
            this.stop();
        }
    }

    @Override
    protected void onUpdate() {
        this.removeTicksForEfficiency();
    }

    @Override
    protected void validateInventory() {
    }

    @Override
    protected void validateLevelRequirements() {

        Preconditions.checkArgument(this.getActor().getSkillController().getLevel(this.getSkillId()) >= item.getLevelRequired(), "You need a $"
                + RunehubUtils.getSkillName(this.getSkillId()) + " level of $"
                + item.getLevelRequired() + " to do this.");
    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getGetBestAvailableTool() != null, "You need a $Hammer.");
        Preconditions.checkArgument(Arrays.stream(item.getMaterials()).allMatch(gameItem -> this.getActor().getItems().playerHasItem(gameItem.getId(), gameItem.getAmount())), "You do not have all the required materials.");
    }

    @Override
    protected void validateWorldRequirements() {

    }

    @Override
    protected void updateAnimation() {

    }

    @Override
    protected void addItems(int id, int amount) {

    }

    private void craft() {
        this.getActor().startAnimation(tool.getAnimationId());
        Arrays.stream(item.getMaterials()).forEach(gameItem -> this.getActor().getItems().deleteItem2(gameItem.getId(), gameItem.getAmount()));
        if (Skill.SKILL_RANDOM.nextInt((int) (100 / tool.getBasePower())) == 0 && tool.getBasePower() > 1) {
            this.getActor().sendMessage("You managed to smith another item.");
            this.getActor().getItems().addItem(item.getItemId(), item.getAmountProduced() * 2);
        } else {
            this.getActor().getItems().addItem(item.getItemId(), item.getAmountProduced());
        }
        if (item.getItemId() == 1189) {
            this.getActor().getAttributes().getAchievementController().completeAchievement(-9158659835708620785L);
        }
        this.getActor().getSkillController().addXP(this.getSkillId(), item.getXp() * item.getMaterials()[0].getAmount());
        actionsDone++;
    }

    protected GatheringTool getGetBestAvailableTool() {
        final List<GatheringTool> availableTools = GatheringToolLoader.getInstance().readAll().stream()
                .filter(tool -> tool.getSkillId() == this.getSkillId())
                .filter(tool -> this.getActor().getItems().playerHasItem(tool.getItemId()) || this.getActor().getItems().isWearingItem(tool.getItemId()))
                .filter(tool -> this.getActor().getSkillController().getLevel(this.getSkillId()) >= tool.getLevelRequired())
                .collect(Collectors.toList());
        final int bestToolLevel = availableTools.stream().mapToInt(GatheringTool::getLevelRequired).max().orElse(1);
        GatheringTool bestTool = availableTools.stream().filter(tool -> tool.getLevelRequired() == bestToolLevel).findFirst().orElse(null);
        return bestTool;
    }

    private void removeTicksForEfficiency() {
        for (int i = 0; i < tool.getBaseEfficiency(); i++) {
            this.removeTick();
        }
    }

    public SmithItemAction(Player actor, SmithingItem item, int actions) {
        super(actor, SkillDictionary.Skill.SMITHING.getId(), 5);
        this.item = item;
        this.actions = actions;
        this.tool = this.getGetBestAvailableTool();
    }

    private GatheringTool tool;
    private int actionsDone;
    private final int actions;
    private final SmithingItem item;
}
