package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.content.instance.BossArenaInstanceController;
import ethos.runehub.entity.player.action.impl.node.*;
import ethos.runehub.skill.node.impl.support.SupportNode;
import ethos.runehub.skill.support.firemaking.action.LightBurnerAction;
import ethos.runehub.ui.impl.BossInstanceUI;
import org.runehub.api.util.SkillDictionary;

import java.util.logging.Logger;

public class FirstClickNodeActionFactory {

    public static ClickNodeAction getAction(Player player, int nodeX, int nodeY, int nodeId) {
        Logger.getGlobal().fine("First Click Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 13212:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.getSkillController().getFiremaking().train(new LightBurnerAction(player,nodeId,nodeX,nodeY,player.heightLevel));
                    }
                };
            case 11806:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.startAnimation(827);
                        player.getPA().movePlayer(3125,3240);
                    }
                };
            case 882:
            case 10321:
                return new ClickOpenManholeNodeAction(player, nodeX, nodeY, nodeId);
            case 881:
                return new ClickClosedManholeNodeAction(player, nodeX, nodeY, nodeId);
            case 27258:
            case 27257://stronghold slayer cave exit
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.getPA().movePlayer(2430, 3425, 0);
                    }
                };
            case 26709://stronghold slayer cave entrance
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.getPA().movePlayer(2429, 9825, 0);
                    }
                };
            case 6799:
                return new FirstClickPortableCrafter4Action(player,nodeX,nodeY,nodeId);
            case 878:
                return new FirstClickPortableWellStationAction(player,nodeX,nodeY,nodeId);
            case 13542:
                return new FirstClickPortableRangeStationAction(player, nodeX, nodeY, nodeId);
            case 13637:

                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.sendUI(new BossInstanceUI(player));
//                        BossArenaInstanceController.getInstance().createInstanceForPlayer(player);
                    }
                };
            case 13620:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.getPA().spellTeleport(1824 ,5165, BossArenaInstanceController.getInstance().getInstance(player.getAttributes().getInstanceId()).getFloodId(),false);
                    }
                };
            case 2031:
            case 6150:
                return new FirstClickAnvilAction(player, nodeX, nodeY, nodeId);
            case 20926:
            case 20927:
            case 20928:
            case 20929:
            case 20930:
                return new FirstClickFishingSpotAction(player,nodeX,nodeY,nodeId);
            case 30377:
                return new FirstClickFishingPlatformBoatExit(player, nodeX, nodeY, nodeId);
            case 30376:
                return new FirstClickFishingPlatformBoat(player, nodeX, nodeY, nodeId);
            case 15420:
                return new FirstClickLecternHotspotAction(player,nodeX,nodeY,nodeId);
            case 13642:
            case 13643:
            case 13644:
            case 13645:
            case 13646:
            case 13647:
            case 13648:
            case 18245:
                return new FirstClickLecternAction(player,nodeX,nodeY,nodeId);
        }
        return null;
    }


}
