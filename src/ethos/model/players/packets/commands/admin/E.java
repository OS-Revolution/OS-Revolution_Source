package ethos.model.players.packets.commands.admin;

import ethos.Server;
import ethos.event.impl.PoisonEvent;
import ethos.model.entity.HealthStatus;
import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import ethos.runehub.RunehubConstants;
import ethos.runehub.RunehubUtils;
import ethos.runehub.TimeUtils;
import ethos.runehub.content.instance.BossArenaInstanceController;
import ethos.runehub.content.instance.impl.TimedInstance;
import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.entity.merchant.impl.exchange.ExchangePriceController;
import ethos.runehub.entity.mob.AnimationDefinitionCache;
import ethos.runehub.entity.mob.hostile.HostileMobIdContextLoader;
import ethos.runehub.event.FixedScheduledEventController;
import ethos.runehub.skill.artisan.construction.Hotspot;
import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.runehub.skill.gathering.farming.crop.Allotment;
import ethos.runehub.skill.gathering.farming.crop.CropCache;
import ethos.runehub.skill.gathering.farming.crop.Flower;
import ethos.runehub.skill.gathering.farming.crop.Herb;
import ethos.runehub.skill.gathering.farming.patch.PatchType;
import ethos.runehub.skill.support.sailing.SailingUtils;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.skill.support.sailing.voyage.VoyageContext;
import ethos.runehub.skill.support.sailing.voyage.VoyageDAO;
import ethos.runehub.ui.impl.InstanceTimerUI;
import ethos.runehub.ui.impl.JourneyUI;
import ethos.runehub.ui.impl.LootContainerUI;
import ethos.runehub.ui.impl.SelectionParentUI;
import ethos.runehub.ui.impl.construction.BuildNodeUI;
import ethos.runehub.ui.impl.smithing.SmithingUI;
import ethos.runehub.ui.impl.tab.player.InstanceTab;
import ethos.runehub.world.RegionCache;
import ethos.runehub.world.WorldSettingsController;
import ethos.runehub.world.wushanko.island.IslandLoader;
import ethos.util.Misc;
import org.runehub.api.io.data.impl.LootTableDAO;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableContainerDefinitionLoader;
import org.runehub.api.io.load.impl.LootTableContainerLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.GameItem;
import org.runehub.api.model.entity.item.loot.ContainerType;
import org.runehub.api.model.entity.item.loot.LootTable;
import org.runehub.api.model.entity.item.loot.LootTableEntry;
import org.runehub.api.model.entity.item.loot.Tier;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
//                2,
//                0,
//                false,
//                false,
//                nAllotment.ordinal(),
//
//        );
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

//    private int getBestBar(Player player) {
//        Arrays.stream(player.playerItems).filter(itemId -> Arrays.stream(SmithingUI.BARS).anyMatch(barId -> barId == itemId))
//                .
//    }

    private Voyage getVoyage(int id, int region, int island) {
        return new Voyage(
                id,
                IslandLoader.getInstance().read(island).getName(),
                SailingUtils.getStatRangeBasedOnRegion(region),
                SailingUtils.getStatRangeBasedOnRegion(region),
                SailingUtils.getStatRangeBasedOnRegion(region),
                SailingUtils.getDistanceFromRegion(region),
                region,
                island,
                false,
                false,
                new VoyageContext(
                        new int[]{
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0, 0, 0, 0
                        },
                        new int[]{SailingUtils.getLootTableContainerIdForRegion(region), SailingUtils.getLootTableContainerIdForIsland(island)}
                )
        );
    }

    @Override
    public void execute(Player c, String input) {
        String[] args = input.split(" ");
//        Point point = RegionCache.getInstance().read(-4977701290314478696L).getBoundingShape().getAllPoints().get(
//                Misc.random(RegionCache.getInstance().read(-4977701290314478696L).getBoundingShape().getAllPoints().size())
//        );
////        c.getPA().movePlayer(point.getX(),point.getY());
//        System.out.println(RegionCache.getInstance().read(-4977701290314478696L).getBoundingShape().contains(
//                new Point(c.absX,c.absY)
//        ));
//        FarmingConfig config = c.getContext().getPlayerSaveData().farmingConfig().get(RunehubUtils.getRegionId(c.absX,c.absY)).get(3);
//        config.setDiseased(!config.diseased());
//        config.setWatered(!config.watered());
//        System.out.println("Patch: " + config.getPatch());
//        System.out.println("Diseased: " + config.diseased());
//        System.out.println("Watered: " + config.watered());
//        System.out.println("Sending bits: " + config.varbit());
//        c.getPA().sendConfig(529,config.varbit());
//        int water = Integer.parseInt(args[0]);
//        int disease = Integer.parseInt(args[1]);
//        int child = Integer.parseInt(args[2]);
//        int stage = Integer.parseInt(args[3]);
//        int patch = Integer.parseInt(args[4]);
//                FarmingConfig config = new FarmingConfig(
//                stage,
//                patch,
//                water == 1,
//                disease == 1,
//                PatchType.HERB.ordinal(),
//                5291,
//                0
//        );
////        System.out.println("Child: " + Herb.values()[(config.isWatered() << 6 | config.isDiseased() << 7)]);
//        System.out.println(child + config.getStage() + ((config.isWatered() << 6 | config.isDiseased()) << 7));
//        int val = (child + config.getStage() + (config.isWatered() << 6 | config.isDiseased() << 7) << config.getPatch());
//        System.out.println("Val: " + val);
////        System.out.println("Child: " + Herb.children[val]);
//        System.out.println("Varbit: " + config.varbit());
//        System.out.println("Stage: " + config.getStage());
//        System.out.println("Water: " + config.isWatered());
//        System.out.println("Disease: " + (config.isWatered() << 6 | config.isDiseased() << 7));
//        System.out.println("Patch: " + config.getPatch());
//        c.getPA().sendConfig(529,val);
//        Server.getEventHandler().submit(new PoisonEvent(c,3, Optional.empty()));
//        c.getSkillController().getFletching().sendItemSelectionFrame(1511);
//        System.out.println(TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getNextCycle(FixedScheduledEventController.getInstance().getFixedScheduleEvents()[3])));
//        LootTable lootTable = LootTableLoader.getInstance().read(-2682234896335024906L);
//        List<LootTableEntry> newEntries = new ArrayList<>();
//        List<LootTableEntry> removedEntries = new ArrayList<>();
//        lootTable.getLootTableEntries().stream().filter(lootTableEntry -> RunehubConstants.STAR_IDS.contains(lootTableEntry.getId())).forEach(star -> {
//            System.out.println(ItemIdContextLoader.getInstance().read(star.getId()).getName() + " is Tier: " + Tier.getTier(star.getChance()) + " @ " + star.getChance() + "%");
//            String name = ItemIdContextLoader.getInstance().read(star.getId()).getName();
//            if (name.toLowerCase().contains("dull")) {
//                newEntries.add(new LootTableEntry(star.getId(),star.getAmount(),0.6D));
//            } else if (name.toLowerCase().contains("shining")) {
//                newEntries.add(new LootTableEntry(star.getId(),star.getAmount(),0.006D));
//            } else if (name.toLowerCase().contains("glorious")) {
//                newEntries.add(new LootTableEntry(star.getId(),star.getAmount(),0.0006D));
//            } else {
//                newEntries.add(new LootTableEntry(star.getId(),star.getAmount(),0.03D));
//            }
//            removedEntries.add(star);
//        });
//        removedEntries.forEach(lootTableEntry -> lootTable.getLootTableEntries().remove(lootTableEntry));
//        lootTable.getLootTableEntries().addAll(newEntries);
//
//        LootTableDAO.getInstance().update(lootTable);

        
//        for (int i = 0; i < 52; i++) {
//            if (i < 7) {
//                VoyageDAO.getInstance().create(this.getVoyage(i, 0, i));
//            } else if (i < 13) {
//                VoyageDAO.getInstance().create(this.getVoyage(i, 1, i));
//            } else if (i < 22) {
//                VoyageDAO.getInstance().create(this.getVoyage(i, 2, i));
//            } else if (i < 27) {
//                VoyageDAO.getInstance().create(this.getVoyage(i, 3, i));
//            }  else if (i < 34) {
//                VoyageDAO.getInstance().create(this.getVoyage(i, 4, i));
//            } else if (i < 41) {
//                VoyageDAO.getInstance().create(this.getVoyage(i, 5, i));
//            }  else {
//                VoyageDAO.getInstance().create(this.getVoyage(i, 6, i));
//            }
//        }
//
//        VoyageDAO.getInstance().create(this.getVoyage(2,0,0));
//        VoyageDAO.getInstance().create(this.getVoyage(3,0,0));
//        VoyageDAO.getInstance().create(this.getVoyage(4,0,0));
//        VoyageDAO.getInstance().create(this.getVoyage(5,0,0));
//        VoyageDAO.getInstance().create(this.getVoyage(6,0,0));
//        VoyageDAO.getInstance().create(this.getVoyage(1,0,0));
//        VoyageDAO.getInstance().create(this.getVoyage(1,0,0));
//        ExchangePriceController.getInstance().updatePrices();
//        TimedInstance instance = new TimedInstance(6,c,new Rectangle(new Point(0,0),new Point(0,0)),10000);
//        c.getAttributes().setInstanceDurationMS(instance.getDurationMS());
//        c.getAttributes().setInstanceStartTimestamp(System.currentTimeMillis());
//        c.sendUI(new InstanceTab(c,instance));
//        BossArenaInstanceController.getInstance().createInstanceForPlayer(c);
//        RunehubUtils.getPlayPassHiscores().forEach(aLong -> System.out.println("ID: " + aLong + " score: " + PlayerCharacterContextDataAccessObject.getInstance().read(aLong).getPlayerSaveData().getPlayPassXp()));
//        c.getPA().showInterface(50100);
//
//        c.getPA().showInterface(49000);
//        for (int i = 0; i < 15; i++) {
//            c.getPA().itemOnInterface(1038 + (i * 2),1,49006,i);
//        }

    }
}
