package ethos.runehub.action.click.node;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.action.click.node.impl.*;
import ethos.runehub.skill.gathering.foraging.action.ActiveForagingSkillAction;

public class FirstClickNodeActionListener {

    public static ClickNodeAction onClick(Player player, int nodeId, int nodeX, int nodeY, int nodeZ) {
        switch (nodeId) {
            case 10:
            case 11:
                return new LadderAction(player,nodeId,nodeX,nodeY,nodeZ);
            case 16902:
            case 16903:
                return new FirstClickHallofHeroesDoorAction(player,nodeX,nodeY);
            case 11807:
            case 11799:
                return new FirstClickHallofHeroesStairsAction(player,nodeId,nodeX,nodeY,nodeZ);
            case 10157:
            case 26149:
                return new FirstClickAbyssalSeerAction(player, nodeId, nodeX, nodeY);
            case 26190:
            case 26252:
                return new FirstClickAbyssalBoilAction(player, nodeId, nodeX, nodeY);
            case 26189:
            case 26253:
                return new FirstClickAbyssalWoodcuttingAction(player, nodeId, nodeX, nodeY);
            case 26191:
            case 26251:
                return new FirstClickAbyssalThievingAction(player, nodeId, nodeX, nodeY);
            case 26192:
            case 26208:
            case 26250:
                return new FirstClickAbyssalAgilityAction(player, nodeId, nodeX, nodeY);
            case 26574:
            case 26188:
                return new FirstClickAbyssalMiningAction(player, nodeId, nodeX, nodeY);
            case 25378: //air
            case 25379: //mind
            case 25376://water
            case 24972://earth
            case 24971://fire
            case 24973://body
            case 24974://cosmic
            case 24976://chaos
            case 24975://nature
            case 25034://law
            case 25035://death
            case 14892://exit
            case 14841:
            case 14842:
            case 14843:
            case 14844:
            case 14845:
            case 14846:
            case 14893:
            case 14848:
            case 14894:
                return new FirstClickAbyssalPortalAction(player, nodeId, nodeX, nodeY);
            default:
                throw new NullPointerException("Nothing Interesting Happens.");
        }
    }
}
