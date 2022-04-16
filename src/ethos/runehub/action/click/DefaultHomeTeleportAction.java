package ethos.runehub.action.click;

import ethos.Config;
import ethos.model.players.Player;

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
        this.getActor().getPA().movePlayer(3092, 3249);
        if (this.getActor().getContext().getInstantTeleportCharges().value() <= 0) {
            this.getActor().getContext().setLastHomeTeleportTimestamp(System.currentTimeMillis());
        } else {
            this.getActor().getContext().getInstantTeleportCharges().decrement();
            this.getActor().sendMessage("#" + this.getActor().getContext().getInstantTeleportCharges().value() + " instant teleport charges remaining.");
        }
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
        super(attachment);
    }
}
