package ethos.runehub.action.click.node;

import ethos.model.players.Player;
import ethos.runehub.RunehubConstants;
import ethos.runehub.action.click.node.impl.first.*;
import ethos.runehub.skill.Skill;
import org.runehub.api.util.math.geometry.Point;

import java.util.logging.Logger;

public class FirstClickNodeActionListener {

    public static ClickNodeAction onClick(Player player, int nodeId, int nodeX, int nodeY, int nodeZ) {
        Logger.getGlobal().fine("First Click Action - " + nodeId + " " + nodeX + " " + nodeY + " " + nodeZ);
        switch (nodeId) {
            case 15658:
                if (player.getItems().playerHasItem(275)) {
                    player.getItems().deleteItem2(275,1);
                    return new FirstClickPortalAction(player, nodeId, nodeX, nodeY, nodeZ, 3213, 9937, 0);
                }
                else
                    return null;
            case 32535:
//                final Point point = RunehubConstants.HOME_TELEPORT_AREA.getAllPoints().get(Skill.SKILL_RANDOM.nextInt(RunehubConstants.HOME_TELEPORT_AREA.getAllPoints().size()));
                return new FirstClickPortalAction(player, nodeId, nodeX, nodeY, nodeZ, 3174, 9900, 0);
            case 733:
                return new FirstClickWebAction(player, nodeId, nodeX, nodeY);
            case 2282:
                return new FirstClickFurnace(player, nodeId, nodeX, nodeY);
            case 14897:
            case 14898:
            case 14899:
            case 14900:
            case 14901:
            case 14902:
                return new FirstClickRunecraftingAltarAction(player, nodeId, nodeX, nodeY, nodeZ);
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
            case 7471:
                return new FirstClickRockAction(player, nodeId, nodeX, nodeY, nodeZ);
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
            case 23972:
            case 1560:
            case 1558:
            case 2108:
            case 2111:
            case 2100:
            case 1535:
            case 8695:
                return new OpenDoorAction(player, nodeId, nodeX, nodeY, nodeZ);
            case 11774:
            case 11772:
            case 4963:
//            case 1560:
            case 1559:
                return new CloseDoorAction(player, nodeId, nodeX, nodeY, nodeZ);
            case 10:
            case 11:
            case 19004:
            case 20785:
            case 23706:
            case 23732:
            case 19003:
            case 20784:
            case 23705:
            case 23731:
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
            case 4126:
                return new ClickShinyChestAction(player, nodeId, nodeX, nodeY, nodeZ);
            case 2213:
            case 24101:
            case 3045:
            case 14367:
            case 3193:
            case 10517:
            case 11402:
            case 26972:
            case 4483:
            case 25808:
            case 11744:
            case 10060:
            case 12309:
            case 10058:
            case 2693:
            case 21301:
            case 6943:
            case 3194:
            case 10661:
            case 6945:
                return new OpenBankAction(player, nodeId, nodeX, nodeY);
            case 8550:
            case 8551:
            case 7847:
            case 8150:
            case 8554:
            case 8552:
            case 8556:
            case 8555:
            case 8553:
            case 8557:
            case 7849:
            case 7848:
            case 7850:
            case 8152:
            case 8151:
            case 8153:
            case 8175:
            case 8176:
            case 8173:
            case 8174:
            case 7577:
            case 7578:
            case 7580:
            case 7579:
            case 8391:
            case 8390:
            case 8389:
            case 8388:
            case 19147:
            case 7962:
            case 7965:
            case 7963:
            case 7964:
            case 26579:

                return new FirstClickFarmingPatchAction(player, nodeId, nodeX, nodeY);
            case 19206:
            case 19207:
            case 17100:
            case 17009:
            case 23653:
            case 23654:
            case 23727:
            case 23728:
                return new FirstClickRiftDoorAction(player, nodeId, nodeX, nodeY, nodeZ);
            case 13636:
                return new FirstClickRiftPortal(player, nodeId, nodeX, nodeY);
            case 13615:
                return new FirstClickOpenRiftPortal(player, nodeId, nodeX, nodeY);
            default:
                Logger.getGlobal().fine("First Click Action[Id:" + nodeId + ", X:" + nodeX + ", Y:" + nodeY + ", Z:" + nodeZ + "]");
                throw new NullPointerException("Nothing interesting happens.");
        }
    }
}
