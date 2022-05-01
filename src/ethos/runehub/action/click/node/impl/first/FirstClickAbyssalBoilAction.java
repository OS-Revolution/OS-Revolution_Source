package ethos.runehub.action.click.node.impl.first;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import org.runehub.api.util.SkillDictionary;

public class FirstClickAbyssalBoilAction extends FirstClickAbyssalNodeAction {

    @Override
    protected void validate() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(590),"You need a @590 .");
    }

    @Override
    public void move() {
        if(this.getNodeId() == 26190) {
            this.getActor().getPA().movePlayer(3027,4831);
        } else if(this.getNodeId() == 26252) {
            this.getActor().getPA().movePlayer(3052, 4836);
        }
    }

    public FirstClickAbyssalBoilAction(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 4, nodeId, nodeX, nodeY,733,SkillDictionary.Skill.FIREMAKING.getId(),"You burned yourself!");
    }


}
