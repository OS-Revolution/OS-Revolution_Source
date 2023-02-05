package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.node.ClickNodeAction;
import ethos.runehub.ui.impl.smithing.SmithingUI;

public class FirstClickAnvilAction extends ClickNodeAction {


    @Override
    public void perform() {
        this.player.sendUI(new SmithingUI(player,player.getSkillController().getSmithing().getBestSmeltableInInventoryId()));
    }

    public FirstClickAnvilAction(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);
        //2615 3440 boat to platform 3081 3211 from platform
    }

    private final int toll = 500;
}
