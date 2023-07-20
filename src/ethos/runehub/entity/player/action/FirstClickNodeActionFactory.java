package ethos.runehub.entity.player.action;

import ethos.clip.Region;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.content.instance.BossArenaInstanceController;
import ethos.runehub.content.instance.impl.tomb.TombInstanceController;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.entity.node.Node;
import ethos.runehub.entity.player.action.impl.node.*;
import ethos.runehub.skill.artisan.actions.ManualMilkCowAction;
import ethos.runehub.skill.node.impl.support.SupportNode;
import ethos.runehub.skill.support.firemaking.action.LightBurnerAction;
import ethos.runehub.ui.impl.BossInstanceUI;
import ethos.runehub.ui.impl.ItemUpgradeUI;
import ethos.runehub.ui.impl.TombRaiderUI;
import ethos.runehub.ui.impl.cooking.BrewingUI;
import ethos.runehub.ui.impl.cooking.CookingUI;
import org.runehub.api.model.world.Face;
import org.runehub.api.util.SkillDictionary;

import java.util.logging.Logger;

public class FirstClickNodeActionFactory {

    public static ClickNodeAction getAction(Player player, int nodeX, int nodeY, int nodeId) {
        Logger.getGlobal().fine("First Click Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 2279:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.sendUI(new ItemUpgradeUI(player));
                    }
                };
            case 13619:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.getPA().movePlayer(3232, 9293 + 1, TombInstanceController.getInstance().getInstance(player.getAttributes().getInstanceId()).getFloodId());
                    }
                };
            case 13638:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.sendUI(new TombRaiderUI(player,nodeX,nodeY));
                    }
                };
            case 6555:
            case 6553:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.getPA().closeAllWindows();
                        player.getOutStream().createFrame(29);
                    }
                };
            case 6512:
            case 6513:
            case 6514:
            case 6515:
            case 6517:
            case 6516:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                      TombInstanceController.getInstance().searchSarcophagus(
                              player.getAttributes().getInstanceId(),
                              nodeId,
                              nodeX,
                              nodeY
                      );
                    }
                };
            case 7528:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.sendUI(new BrewingUI(player));
                    }
                };
            case 11695:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.getPA().sendFrame164(15336);
                    }
                };
            case 114:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.sendUI(new CookingUI(player,new ItemInteractionContext(nodeX,nodeY,player.heightLevel,-1,nodeId,1,1)));
                    }
                };
            case 15931:
                return new ClickLogDropOffBox(player, nodeX, nodeY, nodeId);
            case 2611:
                return new ClickCateringTable(player, nodeX, nodeY, nodeId);
            case 13212:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.getSkillController().getFiremaking().train(new LightBurnerAction(player,nodeId,nodeX,nodeY,player.heightLevel));
                    }
                };
            case 8689:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        player.getSkillController().getFarming().train(new ManualMilkCowAction(player));
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
