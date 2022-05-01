package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import org.runehub.api.util.SkillDictionary;

public class FirstClickAbyssalAgilityAction extends FirstClickAbyssalNodeAction {


    @Override
    public void move() {
        if(this.getNodeId() == 26192) {
            this.getActor().getPA().movePlayer(3031,4842);
        } else if(this.getNodeId() == 26208) {
            this.getActor().getPA().movePlayer(3042,4844);
        } else if (this.getNodeId() == 26250) {
            this.getActor().getPA().movePlayer(3038,4853);
        }
    }

    public FirstClickAbyssalAgilityAction(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 3, nodeId, nodeX, nodeY,828,SkillDictionary.Skill.AGILITY.getId(),"You trip and fall!");
    }


}
