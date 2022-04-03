package ethos.runehub.skill;

import ethos.event.Event;
import ethos.model.players.Player;

import java.util.logging.Logger;

public class SkillAnimationEvent extends Event<Player> {

    @Override
    public void execute() {
        Logger.getGlobal().info("Performing Animation: " + animationId);
        actor.startAnimation(animationId);
    }

    @Override
    public void stop() {
        Logger.getGlobal().info("Stopping Animation: " + animationId);
        super.stop();
        if (attachment != null) {
            attachment.startAnimation(65535);
        }
    }

    public SkillAnimationEvent(Player actor, int animationId) {
        super("skillAnimation", actor, 1);
        this.actor = actor;
        this.animationId = animationId;
    }

    private final Player actor;
    private final int animationId;
}
