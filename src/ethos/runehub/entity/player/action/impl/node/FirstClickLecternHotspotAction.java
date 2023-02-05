package ethos.runehub.entity.player.action.impl.node;

import ethos.clip.Region;
import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.node.ClickNodeAction;
import ethos.runehub.skill.artisan.construction.Hotspot;
import ethos.runehub.ui.impl.construction.BuildNodeUI;

public class FirstClickLecternHotspotAction extends ClickNodeAction {


    @Override
    public void perform() {
        player.sendUI(new BuildNodeUI(player, Hotspot.LECTERN,nodeX,nodeY, Region.getWorldObject(nodeId, nodeX, nodeY, player.heightLevel).get().face));
    }

    public FirstClickLecternHotspotAction(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);
    }
}
