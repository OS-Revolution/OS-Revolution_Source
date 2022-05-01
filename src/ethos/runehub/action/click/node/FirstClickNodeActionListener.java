package ethos.runehub.action.click.node;

import ethos.model.players.Player;
import ethos.runehub.action.click.node.impl.first.*;

public class FirstClickNodeActionListener {

    public static ClickNodeAction onClick(Player player, int nodeId, int nodeX, int nodeY, int nodeZ) {
        switch (nodeId) {
            case 14897:
            case 14898:
            case 14899:
            case 14900:
            case 14901:
            case 14902:
                return new FirstClickRunecraftingAltarAction(player,nodeId,nodeX,nodeY,nodeZ);
            case 7484:
            case 7453:
            case 7454:
            case 7487:
            case 7485:
            case 7486:
            case 7455:
            case 7488:
            case 7491:
            case 7458:
            case 11961:
            case 11960:
            case 11962:
            case 13709:
            case 9714:
            case 9716:
            case 11957:
            case 7494:
            case 7461:
            case 14175:
            case 7493:
            case 7492:
            case 7489:
                return new FirstClickRockAction(player,nodeId,nodeX,nodeY,nodeZ);
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
                return new FirstClickSkillCapeStandAction(player, nodeId, nodeX, nodeY);
            case 11775:
            case 11773:
            case 16902:
            case 4964:
                return new OpenDoorAction(player, nodeId, nodeX, nodeY, nodeZ);
            case 11774:
            case 11772:
            case 4963:
                return new CloseDoorAction(player, nodeId, nodeX, nodeY, nodeZ);
            case 10:
            case 11:
                return new LadderAction(player, nodeId, nodeX, nodeY, nodeZ);
//            case 16902:
//            case 16903:
//                return new FirstClickHallofHeroesDoorAction(player, nodeX, nodeY);
            case 11807:
            case 11799:
                return new FirstClickHallofHeroesStairsAction(player, nodeId, nodeX, nodeY, nodeZ);
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
            case 14847:
                return new FirstClickAbyssalPortalAction(player, nodeId, nodeX, nodeY);
            default:
                throw new NullPointerException("Nothing Interesting Happens.");
        }
    }
}
