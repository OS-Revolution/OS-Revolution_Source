package ethos.runehub.action.click;

import ethos.Config;
import ethos.model.players.Player;
import ethos.runehub.skill.Skill;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Polygon;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class DefaultHomeTeleportAction extends HomeTeleportAction {

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(4850);
    }

    @Override
    protected void onActionStop() {
        this.getActor().startAnimation(65535);
        this.getActor().gfx0(-1);
        this.getActor().teleporting = false;
    }

    @Override
    protected void onTick() {
        final Point selectedTeleportPoint = teleportArea.getAllPoints().get(Skill.SKILL_RANDOM.nextInt(teleportArea.getAllPoints().size()));
        this.getActor().getPA().movePlayer(selectedTeleportPoint.getX(),selectedTeleportPoint.getY(),0);
        if (this.getActor().getContext().getPlayerSaveData().getTeleportCharges().value() <= 0) {
            this.getActor().getContext().getPlayerSaveData().setLastHomeTeleportTimestamp(System.currentTimeMillis());
        } else {
            this.getActor().getContext().getPlayerSaveData().getTeleportCharges().decrement();
            this.getActor().sendMessage("#" + this.getActor().getContext().getPlayerSaveData().getTeleportCharges().value() + " teleport charges remaining.");
        }
        this.getActor().getAttributes().getAchievementController().completeAchievement(-5365918924791133965L);
//        this.getActor().getAttributes().getJourneyController().checkJourney(4640797210886423903L,1);
    }

    @Override
    protected void onUpdate() {
        if (this.getElapsedTicks() == 3) {
            this.getActor().startAnimation(4853);
            this.getActor().gfx0(802);
        } else if (this.getElapsedTicks() == 6) {
            this.getActor().startAnimation(4855);
            this.getActor().gfx0(803);
        } else if (this.getElapsedTicks() == 9) {
            this.getActor().startAnimation(4857);
            this.getActor().gfx0(804);
        }
    }

    public DefaultHomeTeleportAction(Player attachment) {
        super(attachment,12);
        this.teleportArea = new Rectangle(new Point(3102,3248),new Point(3106,3251));
    }


    private final Rectangle teleportArea;
}
