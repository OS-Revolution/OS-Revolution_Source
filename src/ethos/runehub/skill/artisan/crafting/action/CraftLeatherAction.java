package ethos.runehub.skill.artisan.crafting.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.artisan.crafting.armor.leather.Leather;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.util.Misc;
import org.runehub.api.util.SkillDictionary;
import org.runehub.api.util.math.MathUtils;

import java.util.Arrays;

public class CraftLeatherAction extends SkillAction {


    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(1249);
    }

    @Override
    protected void onActionStop() {
        this.getActor().getAttributes().setSkillStationId(-1);
    }

    @Override
    protected void onTick() {
        if (actions == 0 || actionsDone < actions) {
            this.craft();
        } else {
            this.stop();
        }
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void validateInventory() {

    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(this.getActor().getSkillController().getLevel(this.getSkillId()) >= leather.getLevel(), "You need a $"
                + RunehubUtils.getSkillName(this.getSkillId()) + " level of $"
                + leather.getLevel() + " to do this.");
    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.hasNeedle(), "You need a @" + 1733);
        Preconditions.checkArgument(Arrays.stream(leather.getMaterials()).allMatch(gameItem -> this.getActor().getItems().playerHasItem(gameItem.getId(), gameItem.getAmount())), "You do not have all the required materials.");
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

    private boolean hasNeedle() {
        return this.getActor().getItems().playerHasItem(1733) || this.getActor().getItems().playerHasItem(1804) || this.getActor().getItems().playerHasItem(2951);
    }

    private void deleteMaterials() {
        Arrays.stream(leather.getMaterials()).forEach(gameItem -> {
            if (gameItem.getId() == 1734 && actionsDone % 5 == 0) {
                this.getActor().getItems().deleteItem2(gameItem.getId(), 1);
            } else if (gameItem.getId() != 1734) {
                if (this.getActor().getAttributes().getSkillStationId() == 6799 && Misc.random(100) <= 10) {
                    this.getActor().getItems().deleteItem2(gameItem.getId(), gameItem.getAmount() - 1);
                    this.getActor().sendMessage("portable crafter saves you 1x @" + gameItem.getId() + " .");
                } else {
                    this.getActor().getItems().deleteItem2(gameItem.getId(), gameItem.getAmount());
                }
            }
        });
    }

    private void craft() {
        this.getActor().startAnimation(1249);
        this.deleteMaterials();
        this.getActor().getItems().addItem(leather.getProductId(), 1);
        this.getActor().getSkillController().addXP(this.getSkillId(), leather.getXp());
        actionsDone++;
    }

    public CraftLeatherAction(Player actor, Leather leather, int actions) {
        super(actor, SkillDictionary.Skill.CRAFTING.getId(), 3);
        this.leather = leather;
        this.actions = actions;
    }

    private int actionsDone;
    private final int actions;
    private final Leather leather;
}
