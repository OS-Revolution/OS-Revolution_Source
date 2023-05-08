package ethos.runehub.skill.artisan.cooking.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.skill.SkillAction;

import java.util.List;

public class ChurnDairyAction extends SkillAction {


    @Override
    protected void onActionStart() {
        this.getActor().getPA().closeAllWindows();
        this.getActor().startAnimation(894);
//        this.getActor().sendGraphic(459, 0);
        this.getActor().getPA().stillGfx(459,this.getActor().absX,this.getActor().absY + 1, this.getActor().heightLevel,10);
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        int ingredientId = this.getIngredientId();
        if (ingredientId != 0) {
            this.getActor().getItems().deleteItem2(ingredientId,1);
            this.getActor().getItems().addItem(productId, 1);
            this.getActor().startAnimation(894);
            this.getActor().getPA().stillGfx(459,this.getActor().absX,this.getActor().absY + 1, this.getActor().heightLevel,10);
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

    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(requiredIds.stream().anyMatch(itemId -> this.getActor().getItems().playerHasItem(itemId)),"You do not have the items required to do this.");
    }

    @Override
    protected void validateWorldRequirements() {

    }

    @Override
    protected void updateAnimation() {
//        this.getActor().startAnimation(894);
//        this.getActor().sendGraphic(459, 0);
    }

    @Override
    protected void addItems(int id, int amount) {

    }

    private int getIngredientId() {
        return requiredIds.stream().filter(integer -> this.getActor().getItems().playerHasItem(integer))
                .findAny().orElse(0);
    }

    public ChurnDairyAction(Player actor, int ticks, int productId) {
        super(actor, 7, ticks);
        this.productId = productId;
        if (productId == 1985) {
            requiredIds = List.of(1927,2130,6697);
        } else if (productId == 6697) {
            requiredIds = List.of(1927,2130);
        }else if (productId == 2130) {
            requiredIds = List.of(1927);
        }
    }

    private List<Integer> requiredIds;
    private final int productId;
}
