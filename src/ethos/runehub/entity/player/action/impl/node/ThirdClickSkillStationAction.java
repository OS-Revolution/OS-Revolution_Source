package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.world.WorldSettings;
import ethos.runehub.world.WorldSettingsController;

import java.time.Duration;

public class ThirdClickSkillStationAction extends ClickNodeAction
{

    @Override
    public void perform() {
        player.getAttributes().setSkillStationId(nodeId);
        player.getAttributes().setEnteringSkillStationTicket(true);
        player.getOutStream().createFrame(27);
    }

    public ThirdClickSkillStationAction(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);
    }
}
