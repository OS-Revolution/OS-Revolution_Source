package ethos.runehub.skill.combat.magic.action;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.combat.magic.MagicTablet;
import ethos.world.objects.GlobalObject;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class CreateMagicTabletSkillAction extends SkillAction {


    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.updateAnimation();
        this.getActor().getItems().addOrDropItem(tablet.getItemId(),1);
        this.getActor().getSkillController().addXP(this.getSkillId(),tablet.getXp());
        Arrays.stream(tablet.getMaterials()).forEach(gameItem -> {
            this.getActor().getItems().deleteItem2(gameItem.getId(),gameItem.getAmount());
        });
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void validateInventory() {
    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(this.getActor().getSkillController().getLevel(this.getSkillId()) >= tablet.getLevel(),"You need a $"
        + RunehubUtils.getSkillName(this.getSkillId()) + " level of $"
        + tablet.getLevel() + " to do this.");
    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(Arrays.stream(tablet.getMaterials()).allMatch(gameItem -> this.getActor().getItems().playerHasItem(gameItem.getId(),gameItem.getAmount())), "You do not have all the required materials.");
    }

    @Override
    protected void validateWorldRequirements() {
//        Preconditions.checkArgument(this.getActor().getContext().getPlayerSaveData().getLecternHotspot() > 0, "You must be at a lectern to do this.");
//        Preconditions.checkArgument(Server.getGlobalObjects().exists(this.getActor().getContext().getPlayerSaveData().getLecternHotspot(),  3092, 3223),"You must be at a lectern to do this.");
    }

    @Override
    protected void updateAnimation() {
        this.getActor().startAnimation(793);
    }

    @Override
    protected void addItems(int id, int amount) {

    }

    public CreateMagicTabletSkillAction(Player actor, MagicTablet tablet) {
        super(actor, SkillDictionary.Skill.MAGIC.getId(), 4);
        this.tablet = tablet;
    }

    private final MagicTablet tablet;
}
