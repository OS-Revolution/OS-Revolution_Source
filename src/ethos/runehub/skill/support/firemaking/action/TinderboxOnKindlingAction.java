package ethos.runehub.skill.support.firemaking.action;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.clip.Region;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import ethos.runehub.skill.support.firemaking.kindling.Kindling;
import ethos.runehub.skill.support.firemaking.kindling.KindlingCache;
import ethos.util.Misc;
import ethos.util.PreconditionUtils;
import ethos.world.objects.GlobalObject;
import org.runehub.api.util.SkillDictionary;

public class TinderboxOnKindlingAction extends SkillAction {

    @Override
    protected void onActionStart() {
        this.getActor().getItems().deleteItem(context.getUsedWithId(), 1);
        Server.itemHandler.createGroundItem(this.getActor(), context.getUsedWithId(), context.getX(), context.getY(), context.getZ(), 1);
        this.getActor().startAnimation(733);
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        try {
            Preconditions.checkArgument(PreconditionUtils.isTrue(Server.itemHandler.itemExists(kindling.getItemId(), context.getX(), context.getY(), context.getZ())), "You must have logs to light.");
            this.getActor().startAnimation(733);
            if (this.isSuccessful(reaction.getLow(), reaction.getHigh())) {
                int ticks = 90 + Misc.random(this.getActor().getSkillController().getLevel(this.getSkillId()));
                Server.itemHandler.removeGroundItem(this.getActor(), kindling.getItemId(), context.getX(), context.getY(), context.getZ(), false);
                this.walk();
                Server.getGlobalObjects().add(new GlobalObject(reaction.getProductItemId(), context.getX(), context.getY(), context.getZ(), 0, 10, ticks, -1));
                Server.getEventHandler().submit(new FireBurningAction(context, ticks));
                this.getActor().sendMessage("You successfully light the @" + kindling.getItemId());
                this.getActor().getSkillController().addXP(this.getSkillId(), kindling.getBaseXp());
                this.stop();
            }
        } catch (Exception e) {
            this.getActor().sendMessage(e.getMessage());
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
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getLevel(this.getSkillId()) >= kindling.getLevelRequirement()),
                "You need a $"
                        + RunehubUtils.getSkillName(this.getSkillId())
                        + " level of at least $"
                        + kindling.getLevelRequirement()
                        + " to do this.");
    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(590), "You need a @" + 590 + " to do this.");
    }

    @Override
    protected void validateWorldRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isFalse(Region.getClipping(this.getActor().absX, this.getActor().absY, this.getActor().heightLevel) != 0), "You can not light a fire here.");
        Preconditions.checkArgument(PreconditionUtils.isFalse(Server.getGlobalObjects().anyExists(this.getActor().absX, this.getActor().absY, this.getActor().heightLevel)), "You can not light a fire here.");
//        Preconditions.checkArgument(this.getActor().inBank(),"You can not light a fire here.");

    }

    @Override
    protected void updateAnimation() {

    }

    @Override
    protected void addItems(int id, int amount) {

    }

    private void walk() {
        if (Region.getClipping(this.getActor().getX() - 1, this.getActor().getY(), this.getActor().heightLevel, -1, 0)) {
            this.getActor().getPA().playerWalk(context.getX() - 1, context.getY());
        } else if (Region.getClipping(this.getActor().getX() + 1, this.getActor().getY(), this.getActor().heightLevel, 1, 0)) {
            this.getActor().getPA().playerWalk(context.getX() + 1, context.getY());
        } else if (Region.getClipping(this.getActor().getX(), this.getActor().getY() - 1, this.getActor().heightLevel, 0, -1)) {
            this.getActor().getPA().playerWalk(context.getX(), context.getY() - 1);
        } else if (Region.getClipping(this.getActor().getX(), this.getActor().getY() + 1, this.getActor().heightLevel, 0, 1)) {
            this.getActor().getPA().playerWalk(context.getX(), context.getY() + 1);
        } else {
            System.out.println("No where to walk");
        }
    }

    public TinderboxOnKindlingAction(Player actor, ItemInteractionContext context, ArtisanSkillItemReaction reaction) {
        super(actor, SkillDictionary.Skill.FIREMAKING.getId(), 3);
        this.context = context;
        this.kindling = KindlingCache.getInstance().read(context.getUsedWithId());
        this.reaction = reaction;
    }

    private final ItemInteractionContext context;
    private final Kindling kindling;
    private final ArtisanSkillItemReaction reaction;
}
