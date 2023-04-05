package ethos.runehub.skill.support.thieving.action;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.model.items.ItemAssistant;
import ethos.model.npcs.animations.AttackAnimation;
import ethos.model.players.Player;
import ethos.model.players.combat.Hitmark;
import ethos.runehub.LootTableContainerUtils;
import ethos.runehub.event.player.StunPlayerEvent;
import ethos.runehub.skill.node.impl.support.PickpocketNode;
import ethos.runehub.skill.support.SupportSkillAction;
import ethos.runehub.skill.support.thieving.Pickpocket;
import ethos.runehub.skill.support.thieving.PickpocketCache;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.Loot;
import org.runehub.api.model.math.impl.IntegerRange;
import org.runehub.api.util.PreconditionUtils;
import org.runehub.api.util.SkillDictionary;

import java.util.Collection;

public class PickpocketAction extends SupportSkillAction {

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(881);
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.getActor().startAnimation(881);
        if (this.isSuccessful((int) (pickpocket.getSuccessMin() * this.getActor().getSkillController().getThieving().getPowerBonus()),
                (int) (pickpocket.getSuccessMax() * this.getActor().getSkillController().getThieving().getPowerBonus()))) {
            if (this.isEventTick()) {
                this.onEvent();
            } else {
                this.getLootRoll().forEach(loot -> this.getActor().getItems().addItem((int) loot.getId(), (int) loot.getAmount()));
                this.getActor().getSkillController().addXP(this.getSkillId(), pickpocket.getInteractionExperience());
                if (pickpocket.getId() == 3086) {
                    this.getActor().getAttributes().getAchievementController().completeAchievement(7411212671349704401L);
                }
            }

            if (!pickpocket.isRepeatable()) {
                this.stop();
            }
        } else {
            this.getActor().gfx100(80);
            Server.npcHandler.getNPCs()[npcIndex].forceChat("What do you think you're doing?");
            this.getActor().appendDamage(new IntegerRange(pickpocket.getStunDamageMin(), pickpocket.getStunDamageMax()).getRandomValue(), Hitmark.HIT);
            Server.getEventHandler().submit(new StunPlayerEvent(this.getActor(), pickpocket.getStunDuration() * 1000 / 600));

        }
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void validateInventory() {
        Preconditions.checkArgument(this.getActor().getItems().freeSlots() > 0, "You need at least 1 free inventory slot to do this.");
    }


    @Override
    protected void validateItemRequirements() {

    }

    @Override
    protected void validateWorldRequirements() {
        Preconditions.checkArgument(!this.getActor().getAttributes().isActionLocked(), "You've been stunned!");
        Preconditions.checkArgument(!this.getActor().getAttributes().isMovementResricted());
        Preconditions.checkArgument((!pickpocket.isMembers() || (pickpocket.isMembers() && this.getActor().getAttributes().isMember())), "You must be a member to do that.");
    }

    @Override
    protected void updateAnimation() {

    }

    @Override
    protected void onEvent() {
        this.getActor().startAnimation(881);
        this.getLootRoll().forEach(loot -> this.getActor().getItems().addItem((int) loot.getId(), (int) loot.getAmount()));
        this.getLootRoll().forEach(loot -> this.getActor().getItems().addItem((int) loot.getId(), (int) loot.getAmount()));
        this.getActor().getSkillController().addXP(this.getSkillId(), pickpocket.getInteractionExperience() * 2);
        this.getActor().sendMessage("You manage to get a double pickpocket!");
    }

    private Collection<Loot> getLootRoll() {
        return LootTableContainerUtils.open(LootTableLoader.getInstance().read(pickpocket.getLootTableId()),
                this.getActor().getAttributes().getMagicFind());
    }

    public PickpocketAction(Player actor, int mobId, int index) {
        super(actor, SkillDictionary.Skill.THIEVING.getId(), 4, PickpocketCache.getInstance().read(mobId));
        this.pickpocket = PickpocketCache.getInstance().read(mobId);
        this.npcIndex = index;
    }

    private final PickpocketNode pickpocket;
    private final int npcIndex;


}
