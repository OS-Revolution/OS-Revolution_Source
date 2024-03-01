package ethos.runehub.entity.combat.event;

import ethos.event.Event;
import ethos.model.npcs.NPC;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class CheckForNearbyPlayerEvent extends Event<NPC> {
    @Override
    public void execute() {
        int nearestPlayerIndex = this.getNearestPlayer();

        if (target == null) {
            this.target = PlayerHandler.getPlayer(nearestPlayerIndex).orElse(null);
        } else {
            /**
             * TODO Add Checks For Multi
             * TODO Add Checks for exceptions
             */
            if (targetIsInRange() && !attachment.isDead && !attachment.underAttack) {
                if (target.underAttackBy == 0 || target.underAttackBy2 == 0 || attachment.inMulti()) {
                    System.out.println("Targeting player");
                    attachment.killerId = target.getIndex();
//                    target.underAttackBy = attachment.getIndex();
//                    target.underAttackBy2 = attachment.getIndex();
                }
            } else if(!targetIsInRange() && elapsed == 8) {
                attachment.killerId = -1;
                System.out.println("Resetting aggression");
            }
        }
    }

    private boolean targetIsInRange() {
        final int targetingRange = 5;
        final Rectangle targetingArea = new Rectangle(
                new Point(attachment.getX() - targetingRange, attachment.getY() - targetingRange),
                new Point(attachment.getX() + targetingRange, attachment.getY() + targetingRange)
        );
        return targetingArea.contains(new Point(target.absX, target.absY));
    }

    private int getNearestPlayer() {
        if (!PlayerHandler.getPlayers().isEmpty()) {
            final int targetingRange = 5;
            final Rectangle targetingArea = new Rectangle(
                    new Point(attachment.getX() - targetingRange, attachment.getY() - targetingRange),
                    new Point(attachment.getX() + targetingRange, attachment.getY() + targetingRange)
            );
            final Player nearestPlayer = PlayerHandler.getPlayers().stream().filter(player -> targetingArea.contains(new Point(player.absX, player.absY))).findFirst().orElse(null);
            if (nearestPlayer != null) {
                return nearestPlayer.getIndex();
            }
        }
        return -1;
    }

    public CheckForNearbyPlayerEvent(NPC attachment) {
        super(attachment, 1);
    }

    private Player target;
}
