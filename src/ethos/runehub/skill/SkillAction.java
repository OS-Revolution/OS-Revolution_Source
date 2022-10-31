package ethos.runehub.skill;


import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.event.Event;
import ethos.model.players.Player;
import ethos.util.PreconditionUtils;

import java.util.logging.Logger;

public abstract class SkillAction extends Event<Player> {

    protected abstract void onActionStart();

    protected abstract void onActionStop();

    protected abstract void onTick();

    protected abstract void onUpdate();

    protected abstract void validateInventory();

    protected abstract void validateLevelRequirements();

    protected abstract void validateItemRequirements();

    protected abstract void validateWorldRequirements();

    protected abstract void updateAnimation();

    protected abstract void addItems(int id, int amount);

    protected void checkPreconditions() {
        try {
            Preconditions.checkArgument(PreconditionUtils.notNull(this.getActor()), "Actor is Null");
            Preconditions.checkArgument(PreconditionUtils.notNull(this.getActor().getSession()), "Session is Null");
            Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().disconnected), "Actor is Disconnected");
            Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().hitUpdateRequired), "You can't do this while in combat");
            this.validateLevelRequirements();
            this.validateInventory();
            this.validateItemRequirements();
            this.validateWorldRequirements();
        } catch (Exception e) {
            this.stop();
            this.getActor().getPA().closeAllWindows();
            this.getActor().sendMessage(e.getMessage());

        }
    }

    @Override
    public void update() {
        Logger.getGlobal().fine("Updating Event Tick");
        super.update();
        this.checkPreconditions();
        this.onUpdate();
    }

    @Override
    public void execute() {
        Logger.getGlobal().fine("Executing Event Tick");
        this.checkPreconditions();
        this.onTick();
    }

    @Override
    public void stop() {
        Logger.getGlobal().fine("Stopping Event");
        super.stop();
        this.onActionStop();
        if (attachment != null) {
            this.getActor().stopAnimation();
        }
    }

    @Override
    public void initialize() {
        Logger.getGlobal().fine("Starting Event");
        Server.getEventHandler().stop("teleport");
        super.initialize();
        this.checkPreconditions();
        this.onActionStart();
    }

    protected void addXp(int baseAmount) {
        Logger.getGlobal().fine("Adding xp");
        final double xpModifier = this.getActor().getSkillController().getSkill(this.getSkillId()).getGainsBonus();
        final int totalXp = Math.toIntExact(Math.round(baseAmount * xpModifier));
        this.getActor().getPA().addSkillXP(totalXp, this.getSkillId(), true);
    }

    protected float getRollModifier() {
        return 1.0f;
    }

    protected boolean isSuccessful(int min, int max) {
        final float roll = this.getBaseRoll() / this.getRollModifier();
        return roll <= this.getBaseSuccessChance(min, max);
    }

    protected float getBaseRoll() {
        return Skill.SKILL_RANDOM.nextFloat();
    }

    protected double getBaseSuccessChance(int min, int max) {
        return actor.getSkillController().getSkill(skillId).getActionSuccessChance(min, max);
    }

    public Player getActor() {
        return actor;
    }

    public int getSkillId() {
        return skillId;
    }


    private SkillAction(String signature, Player actor, int skillId, int ticks) {
        super(signature, actor, ticks);
        this.actor = actor;
        this.ticks = ticks;
        this.skillId = skillId;
    }

    public SkillAction(Player actor, int skillId, int ticks) {
        this("skillAction", actor, skillId, ticks);
    }

    private final int skillId;
    private final Player actor;
}
