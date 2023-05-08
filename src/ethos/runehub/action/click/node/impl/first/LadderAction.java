package ethos.runehub.action.click.node.impl.first;

import ethos.clip.Region;
import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import org.runehub.api.util.math.geometry.Point;

public class LadderAction extends ClickNodeAction {

    @Override
    protected void onActionStart() {
        if (this.getNodeId() == 10)
            this.getActor().startAnimation(827);
        else if (this.getNodeId() == 11)
            this.getActor().startAnimation(828);
//        this.getActor().sendMessage("Welcome to the $Hall $of $Heroes!");
//        Region.getWorldObject(this.getNodeId(), this.getNodeX(), this.getNodeY(), this.getActor().height).get()

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        if (this.getNodeId() == 11) {
            final Point out = this.getUpPoint();
            if (out != null)
                this.getActor().getPA().movePlayer(out.getX(), out.getY(), nodeZ + 1);
            else
                this.getActor().sendMessage("The way is blocked.");
        } else if (this.getNodeId() == 10) {
            final Point out = this.getDownPoint();
            if (out != null)
                this.getActor().getPA().movePlayer(out.getX(), out.getY(), nodeZ - 1);
            else
                this.getActor().sendMessage("The way is blocked.");
        } else if (this.getNodeId() == 19004 || this.getNodeId() == 20785 || this.getNodeId() == 23706 || this.getNodeId() == 23732) {
            if (this.getNodeId() != 23732 || (this.getNodeId() == 23732 && this.getNodeX() == 2350 && this.getNodeY() == 5215)) {
//                this.getActor().getAttributes().getRift().nextFloor(this.getActor());
                this.getActor().getAttributes().getRiftInstance().nextFloor(this.getActor());
            } else {
                this.getActor().getAttributes().getRiftInstance().previousFloor(this.getActor());
//                this.getActor().getAttributes().getRift().previousFloor(this.getActor());
            }

        } else if (this.getNodeId() == 19003 || this.getNodeId() == 20784 || this.getNodeId() == 23705) {
//            this.getActor().getAttributes().getRift().previousFloor(this.getActor());
            this.getActor().getAttributes().getRiftInstance().previousFloor(this.getActor());
        }
        this.stop();
    }

    @Override
    protected void onUpdate() {
    }

    private Point getUpPoint() {
        if (!Region.blockedNorth(this.getNodeX(), this.getNodeY(), nodeZ)) {//move south
            return new Point(this.getNodeX(), this.getNodeY() + 1);
        } else if (Region.blockedNorth(this.getNodeX(), this.getNodeY(), nodeZ) && !Region.blockedSouth(this.getNodeX(), this.getNodeY(), nodeZ)) {//move south
            return new Point(this.getNodeX(), this.getNodeY() - 1);
        } else if (Region.blockedNorth(this.getNodeX(), this.getNodeY(), nodeZ)
                && Region.blockedSouth(this.getNodeX(), this.getNodeY(), nodeZ)
                && !Region.blockedEast(this.getNodeX(), this.getNodeY(), nodeZ)
        ) {//move east
            return new Point(this.getNodeX() - 1, this.getNodeY());
        } else if (Region.blockedNorth(this.getNodeX(), this.getNodeY(), nodeZ)
                && Region.blockedSouth(this.getNodeX(), this.getNodeY(), nodeZ)
                && Region.blockedEast(this.getNodeX(), this.getNodeY(), nodeZ)
                && !Region.blockedWest(this.getNodeX(), this.getNodeY(), nodeZ)
        ) {//move west
            return new Point(this.getNodeX() + 1, this.getNodeY());
        }
        return null;
    }

    private Point getDownPoint() {
        if (!Region.blockedSouth(this.getNodeX(), this.getNodeY(), nodeZ)) {//move south
            return new Point(this.getNodeX(), this.getNodeY() - 1);
        } else if (!Region.blockedNorth(this.getNodeX(), this.getNodeY(), nodeZ) && Region.blockedSouth(this.getNodeX(), this.getNodeY(), nodeZ)) {//move south
            return new Point(this.getNodeX(), this.getNodeY() + 1);
        } else if (Region.blockedNorth(this.getNodeX(), this.getNodeY(), nodeZ)
                && Region.blockedSouth(this.getNodeX(), this.getNodeY(), nodeZ)
                && !Region.blockedEast(this.getNodeX(), this.getNodeY(), nodeZ)
        ) {//move east
            return new Point(this.getNodeX() - 1, this.getNodeY());
        } else if (Region.blockedNorth(this.getNodeX(), this.getNodeY(), nodeZ)
                && Region.blockedSouth(this.getNodeX(), this.getNodeY(), nodeZ)
                && Region.blockedEast(this.getNodeX(), this.getNodeY(), nodeZ)
                && !Region.blockedWest(this.getNodeX(), this.getNodeY(), nodeZ)
        ) {//move west
            return new Point(this.getNodeX() + 1, this.getNodeY());
        }
        return null;
    }

    public LadderAction(Player attachment, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(attachment, 2, nodeId, nodeX, nodeY);
        this.nodeZ = nodeZ;
    }

    private final int nodeZ;

}
