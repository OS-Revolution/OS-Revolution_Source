package ethos.runehub.skill.combat.prayer.action;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.clip.Region;
import ethos.clip.WorldObject;
import ethos.model.players.Player;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.combat.prayer.remains.Remains;
import ethos.runehub.skill.combat.prayer.remains.RemainsCache;
import org.runehub.api.util.SkillDictionary;

import java.util.Optional;

public class SacrificeRemainsOnAltarAction extends SkillAction {

    @Override
    protected void onActionStart() {
        this.updateAnimation();
//        this.getActor().getOutStream().createFrame(27);
//        this.getActor().settingUnnoteAmount = false;
//        this.getActor().boneOnAltar = true;
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        if (amount > 0) {
            this.updateAnimation();
            double xpBonus = xpModifier;
            for (boolean burner : burners) {
                if (burner) {
                    xpBonus += 0.5D;
                }
            }
            this.getActor().getSkillController().addXP(this.getSkillId(), (int) (remains.getBaseXp() * xpBonus));
            this.getActor().getItems().deleteItem(remains.getItemId(), 1);
            amount--;
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
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(remains.getItemId()), "You've run out of @" + remains.getItemId());
    }

    @Override
    protected void validateWorldRequirements() {
        final boolean burner1 = Server.getGlobalObjects().exists(13213, 3084, 3232, 0);
        final boolean burner2 = Server.getGlobalObjects().exists(13213, 3084, 3236, 0);
        burners[0] = burner1;
        burners[1] = burner2;
    }

    @Override
    protected void updateAnimation() {
        this.getActor().startAnimation(896);
        this.getActor().getPA().stillGfx(624, ctx.getX(), ctx.getY(), ctx.getZ(), 1);
    }

    @Override
    protected void addItems(int id, int amount) {

    }

    public SacrificeRemainsOnAltarAction(Player actor, int itemId, ItemInteractionContext context) {
        super(actor, SkillDictionary.Skill.PRAYER.getId(), 3);
        this.remains = RemainsCache.getInstance().read(itemId);
        this.amount = actor.getItems().getItemAmount(itemId);
        this.ctx = context;
    }

    private double xpModifier = 2.5D;
    private final Remains remains;
    private final ItemInteractionContext ctx;
    private int amount;
    private final boolean[] burners = {false,false};
}
