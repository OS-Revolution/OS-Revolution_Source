package ethos.runehub.skill.artisan.crafting.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.util.Misc;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class CraftJewelleryAction extends SkillAction {

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(899);
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
        Preconditions.checkArgument(this.getActor().getSkillController().getLevel(this.getSkillId()) >= jewellery.getLevel(), "You need a $"
                + RunehubUtils.getSkillName(this.getSkillId()) + " level of $"
                + jewellery.getLevel() + " to do this.");
    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(jewellery.getMouldId()), "You need a @" + jewellery.getMouldId());
        Preconditions.checkArgument(Arrays.stream(jewellery.getMaterials()).allMatch(gameItem -> this.getActor().getItems().playerHasItem(gameItem.getId(), gameItem.getAmount())), "You do not have all the required materials.");
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

    private void deleteMaterials() {
        Arrays.stream(jewellery.getMaterials()).forEach(gameItem -> {
            if (this.getActor().getAttributes().getSkillStationId() == 6799 && Misc.random(100) <= 10) {
                this.getActor().getItems().deleteItem2(gameItem.getId(), gameItem.getAmount() - 1);
                this.getActor().sendMessage("portable crafter saves you 1x @" + gameItem.getId() + " .");
            } else {
                this.getActor().getItems().deleteItem2(gameItem.getId(), gameItem.getAmount());
            }
        });
    }

    private void craft() {
        this.getActor().startAnimation(899);
//        Arrays.stream(jewellery.getMaterials()).forEach(gameItem -> this.getActor().getItems().deleteItem2(gameItem.getId(), gameItem.getAmount()));
        this.deleteMaterials();
        if (jewellery.getProductId() == 19502 || jewellery.getProductId() == 19539 || jewellery.getProductId() == 19536)
            this.getActor().getItems().addItem(jewellery.getProductId() - 1, 1);
        else
            this.getActor().getItems().addItem(jewellery.getProductId(), 1);
        this.getActor().getSkillController().addXP(this.getSkillId(), jewellery.getXp());
        actionsDone++;
    }

    public CraftJewelleryAction(Player actor, Jewellery jewellery, int actions) {
        super(actor, SkillDictionary.Skill.CRAFTING.getId(), 4);
        this.jewellery = jewellery;
        this.actions = actions;
    }

    private int actionsDone;
    private final int actions;
    private final Jewellery jewellery;
}
