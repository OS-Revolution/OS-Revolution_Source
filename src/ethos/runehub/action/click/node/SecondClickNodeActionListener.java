package ethos.runehub.action.click.node;

import ethos.model.players.Player;
import ethos.runehub.action.click.node.impl.first.ClickStallAction;
import ethos.runehub.action.click.node.impl.second.SecondClickShinyChest;
import ethos.runehub.action.click.node.impl.second.SecondClickSkillCapeStandAction;

public class SecondClickNodeActionListener {

    public static ClickNodeAction onClick(Player player, int nodeId, int nodeX, int nodeY, int nodeZ) {
        switch (nodeId) {
            case 11730: 	
            case 6574:
            case 635:
            case 20350:
            case 11729:
            case 7053:
            case 11732:
            case 4277:
            case 11734:
            case 11733:
            case 11731:
                return new ClickStallAction(player,nodeId,nodeX,nodeY,nodeZ);
            case 29183:
            case 29191:
            case 29207:
            case 29211:
            case 29213:
            case 29221:
            case 29203:
            case 29193:
            case 29199:
            case 29197:
            case 29195:
            case 29189:
            case 29219:
            case 29209:
            case 29201:
            case 29181:
            case 29223:
            case 29217:
            case 29215:
            case 29185:
            case 29187:
            case 29225:
                return new SecondClickSkillCapeStandAction(player, nodeId, nodeX, nodeY);
            case 4126:
                return new SecondClickShinyChest(player,nodeId,nodeX,nodeY);
            default:
                throw new NullPointerException("Nothing Interesting Happens.");
        }
    }
}
