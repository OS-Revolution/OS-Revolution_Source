package ethos.model.players.packets.commands.admin;

import ethos.Server;
import ethos.event.Event;
import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import ethos.runehub.content.rift.Rift;
import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.runehub.skill.gathering.farming.action.RakePatchAction;
import ethos.runehub.skill.gathering.farming.crop.Allotment;
import ethos.runehub.skill.gathering.farming.crop.Flower;
import ethos.runehub.skill.gathering.farming.crop.Herb;

import java.util.List;

/**
 * Force the player to perform a given emote.
 *
 * @author Emiel
 * <p>
 * And log if args extend to 2.
 * @author Matt
 */
public class E extends Command {

//    private void doFullFarm(Player c) {
//        FarmingConfig allotmentNorthConfig = null;
//        FarmingConfig allotmentSouthConfig = null;
//
//        Allotment nAllotment = Allotment.WATERMELON;
//        Allotment sAllotment = Allotment.SWEETCORN;
//        Flower flower = Flower.LIMPWURT;
//        Herb herb = Herb.TOADFLAX;
//
//        allotmentNorthConfig = new FarmingConfig(
//                nAllotment.getStartIndex(),
//                2,
//                0,
//                true,
//                false,
//                0,
//                nAllotment.ordinal()
//        );
//        allotmentSouthConfig = new FarmingConfig(
//                sAllotment.getStartIndex(),
//                5,
//                8,
//                false,
//                false,
//                0,
//                sAllotment.ordinal()
//        );
//
//        FarmingConfig flowerConfig = new FarmingConfig(
//                flower.getStartIndex(),
//                1,
//                16,
//                true,
//                true,
//                1,
//                flower.ordinal()
//        );
//
//        FarmingConfig herbConfig = new FarmingConfig(
//                herb.getStartIndex(),
//                1,
//                24,
//                false,
//                false,
//                2,
//                herb.ordinal()
//        );
//
//
//        System.out.println(nAllotment + " child[" + Allotment.getChild(nAllotment.getStartIndex()) + "]");
//        System.out.println(sAllotment + " child[" + Allotment.getChild(sAllotment.getStartIndex()) + "]");
//        System.out.println(flower + " child[" + Flower.getChild(flower.getStartIndex()) + "]");
//        System.out.println(herb + " child[" + Herb.getChild(herb.getStartIndex()) + "]");
//        c.getContext().getPlayerSaveData().getFarmingConfig().put(10548, List.of(allotmentNorthConfig,allotmentSouthConfig,flowerConfig,herbConfig));
//        c.getPA().sendConfig(529, allotmentNorthConfig.getVarbit() + allotmentSouthConfig.getVarbit() + flowerConfig.getVarbit() + herbConfig.getVarbit());
//    }


//    private void doCustomFullFarm(String[] args, Player c) {
//        FarmingConfig allotmentNorthConfig = null;
//        FarmingConfig allotmentSouthConfig = null;
//
//
//        allotmentNorthConfig = new FarmingConfig(
//                Allotment.valueOf(args[0].toUpperCase()).getStartIndex(),
//                Integer.parseInt(args[1]),
//                Integer.parseInt(args[4]),
//                Integer.parseInt(args[2]) != 0,
//                Integer.parseInt(args[3]) != 0
//        );
//        allotmentSouthConfig = new FarmingConfig(
//                Allotment.valueOf(args[5].toUpperCase()).getStartIndex(),
//                Integer.parseInt(args[6]),
//                Integer.parseInt(args[9]),
//                Integer.parseInt(args[7]) != 0,
//                Integer.parseInt(args[8]) != 0
//        );
//        c.getPA().sendConfig(529, (allotmentNorthConfig == null ? 0 : allotmentNorthConfig.getVarbit()) + (allotmentSouthConfig == null ? 0 : allotmentSouthConfig.getVarbit()));
//    }

    @Override
    public void execute(Player c, String input) {
        String[] args = input.split(" ");
        Rift rift = new Rift(c);

rift.start();

//        c.getItems().sendWeight(5);
//        c.getPA().sendConfig(149,10);
//c.getPA().sendConfig(529,);
//        if (args.length == 1) {
////            c.getPA().sendConfig(529, Integer.parseInt(args[0]));
//            int patchId = Integer.parseInt(args[0]);
//            Server.getEventHandler().submit(new RakePatchAction(c, patchId));
//        } else if (args.length < 6) {
//            this.doCustomFullFarm(args, c);
//        }
//        Server.getGlobalObjects().updateRegionObjects(c);

    }
}
