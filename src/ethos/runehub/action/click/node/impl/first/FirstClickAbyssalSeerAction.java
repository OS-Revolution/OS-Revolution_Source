package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;

public class FirstClickAbyssalSeerAction extends ClickNodeAction {

    @Override
    protected void onActionStart() {
        if(this.getNodeId() == 10157) {
            this.getActor().getPA().spellTeleport(3018,4821,0,false);
            this.getActor().getAttributes().getAchievementController().completeAchievement(4681404242601001723L);
        } else if(this.getNodeId() == 26149) {
            this.getActor().getPA().spellTeleport(3115,3168,0,false);
        }
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {

        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    public FirstClickAbyssalSeerAction(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 4, nodeId, nodeX, nodeY);
    }
}
