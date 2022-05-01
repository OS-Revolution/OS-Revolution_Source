package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import org.runehub.api.util.SkillDictionary;

public class FirstClickAbyssalThievingAction extends FirstClickAbyssalNodeAction {

    @Override
    public void move() {
        if(this.getNodeId() == 26191) {
            this.getActor().getPA().movePlayer(3027,4831);
        } else if(this.getNodeId() == 26251) {
            this.getActor().getPA().movePlayer(3052, 4836);
        }
    }

    public FirstClickAbyssalThievingAction(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 4, nodeId, nodeX, nodeY,401,SkillDictionary.Skill.THIEVING.getId(),"You trip and fall!");
    }


}
