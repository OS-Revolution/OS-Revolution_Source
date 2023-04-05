package ethos.runehub.skill.gathering.farming.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.gathering.farming.Farming;
import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.util.PreconditionUtils;
import org.runehub.api.util.SkillDictionary;

public class ApplyPlantCureAction extends SkillAction {


    @Override
    protected void updateAnimation() {}

    @Override
    protected void addItems(int id, int amount) {}


    @Override
    protected void validateItemRequirements() {
        System.out.println("Watered: " + config.isWatered());
        System.out.println("Diseased: " + config.isDiseased());
        System.out.println("Dead: " + (config.isWatered() == 1 && config.isDiseased() == 1));
        Preconditions.checkArgument(PreconditionUtils.isFalse((config.isWatered() == 1 && config.isDiseased() == 1)),"This patch is dead.");
        Preconditions.checkArgument(config.isDiseased() == 1,"This patch is not diseased.");
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(6036),"You need a plant cure.");
    }

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(2288);
    }

    @Override
    protected void onActionStop() {}

    @Override
    public void onTick() {
        this.getActor().getItems().deleteItem(interactionContext.getUsedId(),1);
        this.getActor().getItems().addOrDropItem(229,1);
        config.setDiseased(false);
        this.stop();
    }

    @Override
    protected void onUpdate() {}

    @Override
    protected void validateInventory() {
    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getLevel(this.getSkillId()) >= 1),
                "You need a ?"
                        + SkillDictionary.getSkillNameFromId(this.getSkillId())
                        + " level of at least #"
                        + 1
                        + " to do this.");

    }

    @Override
    protected void validateWorldRequirements() {}

    public ApplyPlantCureAction(Player player, ItemInteractionContext interactionContext) {
        super(player,SkillDictionary.Skill.FARMING.getId(),6);
        this.interactionContext = interactionContext;
        this.config = player.getSkillController().getFarming().getConfig(interactionContext.getUsedWithId(), RunehubUtils.getRegionId(interactionContext.getX(), interactionContext.getY())).orElse(null);
    }

    private final ItemInteractionContext interactionContext;
    private final FarmingConfig config;
}
