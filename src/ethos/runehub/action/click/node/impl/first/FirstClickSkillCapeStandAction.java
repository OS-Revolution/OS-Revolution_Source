package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import ethos.runehub.HallOfHeroesUtils;
import ethos.runehub.action.click.node.ClickNodeAction;
import org.runehub.api.io.load.impl.ItemIdContextLoader;

public class FirstClickSkillCapeStandAction extends ClickNodeAction {

    private final static int CURRENCY = 995;
    private final static int COST = 99000;

    @Override
    protected void validate() {
    }

    @Override
    protected void onActionStart() {
        this.getActor().getDH().sendDialogues(100000,1);
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

    private String getCapeName() {
        return ItemIdContextLoader.getInstance().read(HallOfHeroesUtils.getCapeId(this.getNodeId(),this.getActor())).getName();
    }


    public FirstClickSkillCapeStandAction(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 1, nodeId, nodeX, nodeY);
    }
}
