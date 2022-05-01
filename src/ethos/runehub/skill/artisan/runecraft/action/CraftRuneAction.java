package ethos.runehub.skill.artisan.runecraft.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.artisan.runecraft.Runecraft;
import ethos.runehub.skill.artisan.runecraft.RunecraftMultiplierLoader;
import ethos.runehub.skill.artisan.runecraft.RunecraftingAltarNodeLoader;
import ethos.runehub.skill.node.impl.artisan.RunecraftingAltarNode;
import ethos.util.PreconditionUtils;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;
import java.util.List;

public class CraftRuneAction extends SkillAction {

    @Override
    protected void validateWorldRequirements() {
    }

    @Override
    protected void updateAnimation() {

    }

    @Override
    protected void addItems(int id, int amount) {
        this.getActor().getItems().addItem(id,amount);
    }

    @Override
    protected void validateItemRequirements() {
        if (node.getEssence() == 1436) {
            Preconditions.checkArgument((this.getActor().getItems().playerHasItem(node.getEssence(), 1) || this.getActor().getItems().playerHasItem(7936, 1)), "You do not have the items needed to do this.");
        } else {
            Preconditions.checkArgument(this.getActor().getItems().playerHasItem(node.getEssence(), 1), "You do not have the items needed to do this.");

        }
    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getLevel(this.getSkillId()) >= node.getLevelRequirement()),
                "You need a "
                        + SkillDictionary.getSkillNameFromId(this.getSkillId())
                        + " level of at least "
                        + node.getLevelRequirement()
                        + " to do this.");

    }

    @Override
    protected void validateInventory() {
        Preconditions.checkArgument(this.getActor().getItems().freeSlots() >= 1, "You must have at least " + 1 + " free inventory slot to do this.");
    }

    @Override
    protected void onActionStart() {
        this.getActor().gfx100(Runecraft.RUNECRAFTING_GRAPHIC);
        this.getActor().startAnimation(Runecraft.RUNECRAFTING_ANIMATION);
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        final int totalEssenceUsed = this.getEssenceCount();
        essenceTypes.forEach(itemId -> this.getActor().getItems().deleteItem2(itemId, totalEssenceUsed));
        this.addXp(totalEssenceUsed * node.getInteractionExperience());
        this.addItems(node.getRuneId(),totalEssenceUsed * this.getMultiplier());
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    private int getEssenceCount() {
        return (int) Arrays.stream(this.getActor().playerItems).filter(itemId -> essenceTypes.contains(itemId - 1)).count();
    }

    private int getMultiplier() {
        try {
            return RunecraftMultiplierLoader.getInstance().read(node.getRuneId()).getMultiplierMap()
                    .get(this.getActor().getSkillController().getLevel(this.getSkillId()));
        } catch (Exception e) {
            return 1;
        }
    }


    public CraftRuneAction(Player actor, int altarId) {
        super(actor, SkillDictionary.Skill.RUNECRAFTING.getId(), 4);
        this.node = RunecraftingAltarNodeLoader.getInstance().read(altarId);
        this.essenceTypes = node.getEssence() == 1436 ? List.of(1436, 7936) : List.of(node.getEssence());
    }

    private final RunecraftingAltarNode node;
    private final List<Integer> essenceTypes;
}
