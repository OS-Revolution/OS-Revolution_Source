package ethos.runehub.action.click;

import ethos.model.players.Player;
import ethos.runehub.skill.Skill;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class InstantHomeTeleportAction extends HomeTeleportAction {

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(714);
    }

    @Override
    protected void onActionStop() {
        this.getActor().startAnimation(715);
        this.getActor().gfx0(-1);
        this.getActor().teleporting = false;
    }

    @Override
    protected void onTick() {
        final Point selectedTeleportPoint = teleportArea.getAllPoints().get(Skill.SKILL_RANDOM.nextInt(teleportArea.getAllPoints().size()));
        this.getActor().getPA().movePlayer(selectedTeleportPoint.getX(),selectedTeleportPoint.getY(),0);
        if (this.getActor().getContext().getPlayerSaveData().getInstantTeleportCharges().value() <= 0) {
            this.getActor().getContext().getPlayerSaveData().setLastHomeTeleportTimestamp(System.currentTimeMillis());
        } else {
            this.getActor().getContext().getPlayerSaveData().getInstantTeleportCharges().decrement();
            this.getActor().sendMessage("#" + this.getActor().getContext().getPlayerSaveData().getInstantTeleportCharges().value() + " instant teleport charges remaining.");
        }
        this.getActor().getAttributes().getAchievementController().completeAchievement(-5365918924791133965L);
//        this.getActor().getAttributes().getJourneyController().checkJourney(4640797210886423903L,1);
    }

    @Override
    protected void onUpdate() {
        if (this.getElapsedTicks() == 2) {
            this.getActor().gfx100(308);
        }
    }

    public InstantHomeTeleportAction(Player attachment) {
        super(attachment,4);
        this.teleportArea = new Rectangle(new Point(3102,3248),new Point(3106,3251));
    }


    private final Rectangle teleportArea;
}
