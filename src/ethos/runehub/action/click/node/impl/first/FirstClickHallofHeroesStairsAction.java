package ethos.runehub.action.click.node.impl.first;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class FirstClickHallofHeroesStairsAction extends ClickNodeAction {

    @Override
    protected void onActionStart() {
//        this.getActor().sendMessage("Welcome to the $Hall $of $Heroes!");
//        Region.getWorldObject(this.getNodeId(), this.getNodeX(), this.getNodeY(), this.getActor().height).get()

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        if (this.getActor().getX() <= this.getNodeX() && nodeZ == 0) {
            this.getActor().getPA().movePlayer(this.getNodeX(), this.getNodeY() + 3 ,1);
        } else if(this.getActor().getX() > this.getNodeX() && nodeZ == 0) {
            this.getActor().getPA().movePlayer(this.getNodeX() + 1, this.getNodeY() + 3,1);
        } else if (this.getActor().getX() <= this.getNodeX() && nodeZ == 1) {
            this.getActor().getPA().movePlayer(this.getNodeX(), this.getNodeY() - 2 ,0);
        } else {
            this.getActor().getPA().movePlayer(this.getNodeX() + 1, this.getNodeY() - 2,0);
        }
        this.stop();
    }

    @Override
    protected void onUpdate() {
    }

    public FirstClickHallofHeroesStairsAction(Player attachment, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(attachment, 1, nodeId, nodeX, nodeY);
        this.nodeZ = nodeZ;
    }

    private final int nodeZ;

}
