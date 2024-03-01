package ethos;

import ethos.clip.ObjectDef;
import ethos.clip.Region;
import ethos.event.CycleEventHandler;
import ethos.event.EventHandler;
import ethos.event.impl.BonusApplianceEvent;
import ethos.event.impl.DidYouKnowEvent;
import ethos.event.impl.WheatPortalEvent;
import ethos.model.content.godwars.GodwarsEquipment;
import ethos.model.content.godwars.GodwarsNPCs;
import ethos.model.content.tradingpost.Listing;
import ethos.model.content.trails.CasketRewards;
import ethos.model.content.wogw.Wogw;
import ethos.model.holiday.HolidayController;
import ethos.model.items.ItemDefinition;
import ethos.model.minigames.FightPits;
import ethos.model.minigames.pk_arena.Highpkarena;
import ethos.model.minigames.pk_arena.Lowpkarena;
import ethos.model.multiplayer_session.MultiplayerSessionListener;
import ethos.model.npcs.NPCHandler;
import ethos.model.npcs.drops.DropManager;
import ethos.model.players.PlayerHandler;
import ethos.model.players.PlayerSave;
import ethos.model.players.combat.monsterhunt.MonsterHunt;
import ethos.model.players.packets.Commands;
import ethos.model.players.packets.commands.admin.Debugslayer;
import ethos.net.PipelineFactory;
import ethos.punishments.PunishmentCycleEvent;
import ethos.punishments.Punishments;
import ethos.runehub.RunehubConstants;
import ethos.runehub.RunehubUtils;
import ethos.runehub.TimeUtils;
import ethos.runehub.combat.style.WeaponType;
import ethos.runehub.content.MobKillDatabase;
import ethos.runehub.content.achievement.Achievement;
import ethos.runehub.content.achievement.AchievementCache;
import ethos.runehub.content.achievement.AchievementDAO;
import ethos.runehub.content.charter.*;
import ethos.runehub.content.job.Job;
import ethos.runehub.content.job.JobUtils;
import ethos.runehub.content.journey.*;
import ethos.runehub.entity.combat.CombatController;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.entity.item.ItemInteractionDAO;
import ethos.runehub.entity.item.ItemInteractionLoader;
import ethos.runehub.entity.item.equipment.*;
import ethos.runehub.entity.mob.AnimationDefinition;
import ethos.runehub.entity.mob.AnimationDefinitionDAO;
import ethos.runehub.entity.mob.hostile.HostileMobContext;
import ethos.runehub.entity.mob.hostile.HostileMobContextDAO;
import ethos.runehub.entity.mob.hostile.HostileMobIdContextLoader;
import ethos.runehub.event.FixedScheduledEventController;
import ethos.runehub.event.chest.PrismaniaPromotionalChestEvent;
import ethos.runehub.event.chest.PromotionalChestEvent;
import ethos.runehub.skill.artisan.cooking.food.Brew;
import ethos.runehub.skill.artisan.cooking.food.BrewDAO;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryDAO;
import ethos.runehub.skill.combat.magic.spell.RuneIdentifier;
import ethos.runehub.skill.combat.magic.spell.Spell;
import ethos.runehub.skill.combat.magic.spell.SpellDAO;
import ethos.runehub.skill.gathering.fishing.FishingPlatformController;
import ethos.runehub.skill.gathering.tool.GatheringToolDAO;
import ethos.runehub.skill.gathering.tool.GatheringToolLoader;
import ethos.runehub.skill.node.io.*;
import ethos.runehub.skill.support.slayer.*;
import ethos.runehub.world.RegionCache;
import ethos.runehub.world.RegionLoader;
import ethos.runehub.world.WorldController;
import ethos.runehub.world.WorldSettingsController;
import ethos.runehub.world.wushanko.island.Island;
import ethos.runehub.world.wushanko.island.IslandDAO;
import ethos.server.data.ServerData;
import ethos.util.date.GameCalendar;
import ethos.util.log.Logger;
import ethos.world.ClanManager;
import ethos.world.ItemHandler;
import ethos.world.ShopHandler;
import ethos.world.objects.GlobalObjects;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.util.HashedWheelTimer;
import org.runehub.api.APISettingsController;
import org.runehub.api.io.data.impl.*;
import org.runehub.api.io.load.impl.*;
import org.runehub.api.model.entity.item.loot.LootTable;
import org.runehub.api.model.entity.item.loot.LootTableContainer;
import org.runehub.api.model.entity.item.loot.LootTableContainerDefinition;
import org.runehub.api.model.entity.item.loot.LootTableDefinition;
import org.runehub.api.model.world.region.RegionContext;
import org.runehub.api.model.world.region.RegionContextDataAccessObject;
import org.runehub.api.model.world.region.RegionDataAccessObject;
import org.runehub.api.util.APILogger;
import org.runehub.api.util.IDManager;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.IrregularPolygon;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * The main class needed to start the server.
 *
 * @author Sanity
 * @author Graham
 * @author Blake
 * @author Ryan Lmctruck30 Revised by Shawn Notes by Shawn
 */
public class Server {

    private static final Punishments PUNISHMENTS = new Punishments();

    private static final DropManager dropManager = new DropManager();

    /**
     * A class that will manage game events
     */
    private static final EventHandler events = new EventHandler();

    /**
     * Represents our calendar with a given delay using the TimeUnit class
     */
    private static GameCalendar calendar = new ethos.util.date.GameCalendar(
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"), "UTC-6:00");

    private static HolidayController holidayController = new HolidayController();

    private static MultiplayerSessionListener multiplayerSessionListener = new MultiplayerSessionListener();

    private static GlobalObjects globalObjects = new GlobalObjects();

    /**
     * ClanChat Added by Valiant
     */
    public static ClanManager clanManager = new ClanManager();

    /**
     * Sleep mode of the server.
     */
    public static boolean sleeping;
    /**
     * The test thingy
     */
    public static boolean canGiveReward;

    public static long lastReward = 0;
    /**
     * Calls the rate in which an event cycles.
     */
    private static final int cycleRate;

    /**
     * Server updating.
     */
    public static boolean UpdateServer = false;

    /**
     * Calls in which the server was last saved.
     */
    public static long lastMassSave = System.currentTimeMillis();

    private static long sleepTime;

    /**
     * Forced shutdowns.
     */
    private static boolean shutdownServer = false;
    public static boolean shutdownClientHandler;

    public static boolean canLoadObjects = false;

    /**
     * Used to identify the server port.
     */
    public static int serverlistenerPort;

    /**
     * Contains data which is saved between sessions.
     */
    private static ServerData serverData = new ServerData();

    /**
     * Calls the usage of player items.
     */
    public static ItemHandler itemHandler = new ItemHandler();

    /**
     * Handles logged in players.
     */
    public static PlayerHandler playerHandler = new PlayerHandler();

    /**
     * Handles global NPCs.
     */
    public static NPCHandler npcHandler = new NPCHandler();

    /**
     * Handles global shops.
     */
    public static ShopHandler shopHandler = new ShopHandler();

    /**
     * Handles the fightpits minigame.
     */
    public static FightPits fightPits = new FightPits();

    /**
     * Handles the main game processing.
     */
    private static final ScheduledExecutorService GAME_THREAD = Executors.newSingleThreadScheduledExecutor();

    private static final ScheduledExecutorService IO_THREAD = Executors.newSingleThreadScheduledExecutor();

    static {
        serverlistenerPort = Config.SERVER_STATE.getPort();
        cycleRate = 600;
        shutdownServer = false;
        sleepTime = 0;

    }

    private static void initSpells() {
        SpellDAO.getInstance().create(new Spell( ///varrock teleport
                0,
                4140,
                false,
                4,
                714,
                308,
                35,
                25,
                new int[][]{{RuneIdentifier.FIRE.ordinal(), 1}, {RuneIdentifier.AIR.ordinal(), 3}, {RuneIdentifier.LAW.ordinal(), 1}}
        ));
        SpellDAO.getInstance().create(new Spell( ///lumbridge teleport
                1,
                4143,
                false,
                4,
                714,
                308,
                41,
                31,
                new int[][]{{RuneIdentifier.EARTH.ordinal(), 1}, {RuneIdentifier.AIR.ordinal(), 3}, {RuneIdentifier.LAW.ordinal(), 1}}
        ));

    }

    private static void initEquipmment() {
        WeaponDAO.getInstance().create(
                new Weapon(
                        9174,
                        WeaponType.CROSSBOW.ordinal(),
                        CombatController.MELEE
                )
        );
        RangedWeaponDAO.getInstance().create(
                new RangedWeapon(
                        9174, new int[]{877}
                )
        );
        ProjectileDAO.getInstance().create(new Projectile(
                877,
                -1,
                27,
                -1
        ));
    }

    private static void initRegions() {
//        final long draynorId = -4977701290314478696L;
//        org.runehub.api.model.world.region.Region draynor = new org.runehub.api.model.world.region.Region(draynorId,
//                new IrregularPolygon(
//                        new Point(2980, 3105),
//                        new Point(2980, 3186),
//                        new Point(2915, 3186),
//                        new Point(2915, 3224),
//                        new Point(2880, 3224),
//                        new Point(2880, 3314),
//                        new Point(3263, 3314),
//                        new Point(3263, 3136),
//                        new Point(3008, 3136),
//                        new Point(3008, 3105),
//                        new Point(2980, 3105)
//
//                ));
//        RegionContext draynorCtx = new RegionContext(draynorId, "Draynor");
//
//        RegionDataAccessObject.getInstance().create(draynor);
//        RegionContextDataAccessObject.getInstance().create(draynorCtx);

        final long draynorSewersId = IDManager.getUUID();
        org.runehub.api.model.world.region.Region draynorSewers = new org.runehub.api.model.world.region.Region(draynorSewersId,
                new IrregularPolygon(
                        new Point(3154, 9856),
                        new Point(3154, 9920),
                        new Point(3294, 9920),
                        new Point(3294, 9856),
                        new Point(3154, 9856)

                ));

        RegionContext draynorSewersCtx = new RegionContext(draynorSewersId, "Draynor Sewers");

        RegionDataAccessObject.getInstance().create(draynorSewers);
        RegionContextDataAccessObject.getInstance().create(draynorSewersCtx);
    }

    private static void loadRegions() {
        RegionDataAccessObject.getInstance().getAllEntries().forEach(region -> RegionCache.getInstance().create(region.getId(), region));
    }

    private static void initAchievements() {
//        AchievementDAO.getInstance().create(
//                new Achievement(
//                        IDManager.getUUID(),
//                        0,
//                        new int[] {},
//                        -4977701290314478696L,
//                        "Harvest a Potato"
//                )
//        );

//        AchievementDAO.getInstance().create(
//                new Achievement(
//                        IDManager.getUUID(),
//                        0,
//                        new int[]{},
//                        -4977701290314478696L,
//                        "Get a Slayer task from Turael east of the market"
//                )
//        );

//        AchievementDAO.getInstance().create(
//                new Achievement(
//                        IDManager.getUUID(),
//                        0,
//                        new int[]{},
//                        -4977701290314478696L,
//                        "Learn about Farming from Martin the Master Farmer"
//                )
//        );

//        AchievementDAO.getInstance().create(
//                new Achievement(
//                        IDManager.getUUID(),
//                        0,
//                        new int[]{},
//                        -4977701290314478696L,
//                        "Enter the Abyssal Rift near the Wizards Tower"
//                )
//        );

//        AchievementDAO.getInstance().create(
//                new Achievement(
//                        IDManager.getUUID(),
//                        0,
//                        new int[]{},
//                        -4977701290314478696L,
//                        "Cast the @red@Home Teleport @blu@spell"
//                )
//        );

//        AchievementDAO.getInstance().create(
//                new Achievement(
//                        IDManager.getUUID(),
//                        0,
//                        new int[]{},
//                        -4977701290314478696L,
//                        "Fletch an @red@Oak shortbow @blu@"
//                )
//        );

//        AchievementDAO.getInstance().create(
//                new Achievement(
//                        IDManager.getUUID(),
//                        0,
//                        new int[]{},
//                        -4977701290314478696L,
//                        "Open the @red@Shiny chest @blu@ located in Draynor bank"
//                )
//        );

//        AchievementDAO.getInstance().create(
//                new Achievement(
//                        IDManager.getUUID(),
//                        0,
//                        new int[]{},
//                        -4977701290314478696L,
//                        "Talk to @red@Trader Stan @blu@ in Draynor market"
//                )
//        );

        AchievementDAO.getInstance().create(
                new Achievement(
                        IDManager.getUUID(),
                        0,
                        new int[]{},
                        -4977701290314478696L,
                        "Mine some clay from the Rimmington mine"
                )
        );

//        AchievementDAO.getInstance().create(
//                new Achievement(
//                        IDManager.getUUID(),
//                        0,
//                        new int[]{},
//                        -4977701290314478696L,
//                        "Sell an item to the Draynor General Store"
//                )
//        );

//        AchievementDAO.getInstance().create(
//                new Achievement(
//                        IDManager.getUUID(),
//                        0,
//                        new int[]{},
//                        -4977701290314478696L,
//                        "Catch some anchovies by the Draynor dock"
//                )
//        );

//        AchievementDAO.getInstance().create(
//                new Achievement(
//                        IDManager.getUUID(),
//                        0,
//                        new int[]{},
//                        -4977701290314478696L,
//                        "Grow and harvest @red@Marrentill @blu@ at the farm north of Draynor"
//                )
//        );

//        AchievementDAO.getInstance().create(
//                new Achievement(
//                        IDManager.getUUID(),
//                        0,
//                        new int[]{},
//                        -4977701290314478696L,
//                        "Cut down an Oak tree"
//                )
//        );

//        AchievementDAO.getInstance().create(
//                new Achievement(
//                        IDManager.getUUID(),
//                        0,
//                        new int[]{},
//                        -4977701290314478696L,
//                        "Smith a @red@Bronze kiteshied @blu@ west of Draynor market"
//                )
//        );

//        AchievementDAO.getInstance().create(
//                new Achievement(
//                        IDManager.getUUID(),
//                        0,
//                        new int[]{},
//                        -4977701290314478696L,
//                        "Pickpocket a farmer"
//                )
//        );
    }

    private static void learningTheRopesJourney() {
        JourneyStep step1 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Claim your starter package from the Draynor Representative",
                new GameItem[]{new GameItem(995, 500), new GameItem(85, 1), new GameItem(330, 5)},
                1,
                1,
                new int[]{3308},
                JourneyStepType.DIALOG.ordinal()
        );

        JourneyStep step2 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Purchase an item from the General store",
                new GameItem[]{new GameItem(995, 500), new GameItem(85, 1), new GameItem(330, 5)},
                1,
                1,
                new int[]{506},
                JourneyStepType.BUY_ANY_FROM_SHOP.ordinal()
        );

        JourneyStep step3 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Talk to Aubury east of the Draynor market",
                new GameItem[]{new GameItem(995, 500), new GameItem(85, 1), new GameItem(330, 5)},
                1,
                1,
                new int[]{637},
                JourneyStepType.DIALOG.ordinal()
        );

        JourneyStep step4 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Talk to Martin the master farmer east of the Draynor market",
                new GameItem[]{new GameItem(995, 500), new GameItem(85, 1), new GameItem(330, 5)},
                1,
                1,
                new int[]{5832},
                JourneyStepType.DIALOG.ordinal()
        );

        JourneyStep step5 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Talk to Death in the Draynor bank",
                new GameItem[]{new GameItem(1464, 1), new GameItem(85, 1), new GameItem(1464, 4)},
                1,
                1,
                new int[]{5567},
                JourneyStepType.OPEN_SHOP.ordinal()
        );

        JourneyStep step6 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Rake up 5 weeds from the farm northwest of Draynor",
                new GameItem[]{new GameItem(6037, 5), new GameItem(85, 1), new GameItem(995, 200)},
                5,
                1,
                new int[]{6055},
                JourneyStepType.COLLECTION.ordinal()
        );

        JourneyStep step7 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Purchase 5 buckets of compost from Martin the master farmer",
                new GameItem[]{new GameItem(6033, 10), new GameItem(85, 1), new GameItem(995, 200)},
                5,
                5,
                new int[]{6033},
                JourneyStepType.BUY_ID_FROM_SHOP.ordinal()
        );

        JourneyStep step8 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Have Aubury teleport you to the Rune essence mine",
                new GameItem[]{new GameItem(1437, 50), new GameItem(85, 1), new GameItem(1438, 1)},
                1,
                1,
                new int[]{637},
                JourneyStepType.NPC_TELEPORT_ACTION.ordinal()
        );

        JourneyStep step9 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Cast the Home teleport spell",
                new GameItem[]{new GameItem(2396, 5), new GameItem(85, 1), new GameItem(2396, 5)},
                1,
                1,
                new int[]{4171, 117048, 84237, 75010, 50056},
                JourneyStepType.CAST_SPELL.ordinal()
        );

        JourneyStep step10 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Claim bonus XP in any skill using a star",
                new GameItem[]{new GameItem(6825, 1), new GameItem(85, 1), new GameItem(1459, 10)},
                1,
                1,
                new int[]{6822, 6823, 6824, 6825, 22009, 22010, 22011, 22012, 22013, 22014, 22015, 22016, 22017, 22018, 22019, 22020, 22021, 22022, 22023, 22024, 22025, 22026, 22027, 22028, 22029, 22030, 22031, 22032, 22033, 22034, 22035, 22036, 22037, 22038, 22039, 22040, 22041, 22042, 22043, 22044, 22045, 22046, 22047, 22048, 22049, 22050, 22051, 22052, 22053, 22054, 22055, 22056, 22057, 22058, 22059, 22060, 22061, 22062, 22063, 22064, 22065, 22066, 22067, 22068, 22069, 22070, 22071, 22072, 22073, 22074, 22075, 22076, 22077, 22078, 22079, 22080, 22081, 22082, 22083, 22084, 22085, 22086, 22087, 22088, 22089, 22090, 22091, 22092, 22191, 22227, 22228, 22229, 22230, 21873, 21874, 21875},
                JourneyStepType.CONSUME_ITEM.ordinal()
        );

        Journey journey = new Journey(
                1,
                new Long[]{
                        step1.getId(),
                        step2.getId(),
                        step3.getId(),
                        step4.getId(),
                        step5.getId(),
                        step6.getId(),
                        step7.getId(),
                        step8.getId(),
                        step9.getId(),
                        step10.getId()
                },
                new Integer[0],
                "Learning the Ropes"
        );

        JourneyStepDAO.getInstance().create(step1);
        JourneyStepDAO.getInstance().create(step2);
        JourneyStepDAO.getInstance().create(step3);
        JourneyStepDAO.getInstance().create(step4);
        JourneyStepDAO.getInstance().create(step5);
        JourneyStepDAO.getInstance().create(step6);
        JourneyStepDAO.getInstance().create(step7);
        JourneyStepDAO.getInstance().create(step8);
        JourneyStepDAO.getInstance().create(step9);
        JourneyStepDAO.getInstance().create(step10);
        JourneyDAO.getInstance().create(journey);
    }

    private static void apprenticeLumberjackJourney() {
        JourneyStep step1 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Equip an iron axe",
                new GameItem[]{new GameItem(1353, 1), new GameItem(22041, 1), new GameItem(7797, 1)},
                1,
                1,
                new int[]{1349},
                JourneyStepType.EQUIP_ID.ordinal()
        );

        JourneyStep step2 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Cut 30 Logs",
                new GameItem[]{new GameItem(22041, 1), new GameItem(85, 1), new GameItem(7797, 1)},
                30,
                1,
                new int[]{1511},
                JourneyStepType.COLLECTION.ordinal()
        );

        JourneyStep step3 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Cut down 25 Dead trees",
                new GameItem[]{new GameItem(22041, 1), new GameItem(85, 1), new GameItem(995, 250)},
                25,
                1,
                new int[]{1282, 1289},
                JourneyStepType.DEPLETION.ordinal()
        );

        JourneyStep step4 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Get a bird's nest while woodcutting",
                new GameItem[]{new GameItem(22041, 1), new GameItem(85, 1), new GameItem(995, 250)},
                1,
                1,
                new int[]{5075, 5073, 5074, 5072, 5071, 5070},
                JourneyStepType.COLLECTION.ordinal()
        );

        JourneyStep step5 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Get 15 Oak logs from a single Oak tree",
                new GameItem[]{new GameItem(1361, 1), new GameItem(85, 1), new GameItem(10933, 1)},
                1,
                15,
                new int[]{1521},
                JourneyStepType.CHALLENGE.ordinal()
        );

        JourneyStep step6 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Cut down 15 Evergreen trees",
                new GameItem[]{new GameItem(7797, 1), new GameItem(85, 1), new GameItem(995, 250)},
                15,
                1,
                new int[]{2091, 2092},
                JourneyStepType.DEPLETION.ordinal()
        );

        JourneyStep step7 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Cut down 5 Abyssal Tendrils in the Rift near the Wizards tower",
                new GameItem[]{new GameItem(995, 1000), new GameItem(85, 1), new GameItem(995, 250)},
                5,
                1,
                new int[]{26191, 26251},
                JourneyStepType.NODE_INTERACTION.ordinal()
        );

        JourneyStep step8 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Cut 100 Logs",
                new GameItem[]{new GameItem(22042, 1), new GameItem(85, 1), new GameItem(995, 1000)},
                100,
                1,
                new int[]{1511},
                JourneyStepType.COLLECTION.ordinal()
        );

        JourneyStep step9 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Cut 50 Oak Logs",
                new GameItem[]{new GameItem(995, 5000), new GameItem(85, 1), new GameItem(22041, 1)},
                50,
                1,
                new int[]{1521},
                JourneyStepType.COLLECTION.ordinal()
        );

        JourneyStep step10 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Cut 50 Willow Logs",
                new GameItem[]{new GameItem(1355, 1), new GameItem(85, 2), new GameItem(10941, 1)},
                50,
                1,
                new int[]{1519},
                JourneyStepType.COLLECTION.ordinal()
        );

        JourneyStep step11 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Get a birds nest while chopping a Willow tree",
                new GameItem[]{new GameItem(7797, 1), new GameItem(85, 1), new GameItem(995, 250)},
                1,
                1,
                new int[]{1750},
                JourneyStepType.SKILL_EVENT_FROM_ID.ordinal()
        );

        JourneyStep step12 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Get 15 logs from one Willow tree",
                new GameItem[]{new GameItem(22041, 1), new GameItem(85, 1), new GameItem(995, 250)},
                1,
                15,
                new int[]{1519},
                JourneyStepType.CHALLENGE.ordinal()
        );

        JourneyStep step13 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Cut down 15 Willow trees",
                new GameItem[]{new GameItem(22041, 1), new GameItem(85, 1), new GameItem(995, 250)},
                15,
                1,
                new int[]{1750},
                JourneyStepType.DEPLETION.ordinal()
        );

        JourneyStep step14 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Get 5 Birds nests while woodcutting",
                new GameItem[]{new GameItem(995, 1000), new GameItem(85, 1), new GameItem(22042, 1)},
                5,
                1,
                new int[]{5075, 5073, 5074, 5072, 5071, 5070},
                JourneyStepType.COLLECTION.ordinal()
        );

        JourneyStep step15 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Cut 35 Maple logs",
                new GameItem[]{new GameItem(1357, 1), new GameItem(85, 1), new GameItem(10940, 1)},
                35,
                1,
                new int[]{1517},
                JourneyStepType.COLLECTION.ordinal()
        );

        JourneyStep step16 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Cut 500 logs",
                new GameItem[]{new GameItem(22042, 1), new GameItem(85, 1), new GameItem(995, 1000)},
                500,
                1,
                new int[]{1511},
                JourneyStepType.COLLECTION.ordinal()
        );

        JourneyStep step17 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Cut 500 Oak logs",
                new GameItem[]{new GameItem(22042, 1), new GameItem(85, 1), new GameItem(995, 1000)},
                500,
                1,
                new int[]{1521},
                JourneyStepType.COLLECTION.ordinal()
        );

        JourneyStep step18 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Cut 500 Willow",
                new GameItem[]{new GameItem(22042, 1), new GameItem(85, 1), new GameItem(995, 1000)},
                500,
                1,
                new int[]{1519},
                JourneyStepType.COLLECTION.ordinal()
        );

        JourneyStep step19 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Get 10 Birds nests while woodcutting",
                new GameItem[]{new GameItem(22042, 1), new GameItem(85, 1), new GameItem(995, 1000)},
                10,
                1,
                new int[]{5075, 5073, 5074, 5072, 5071, 5070},
                JourneyStepType.COLLECTION.ordinal()
        );

        JourneyStep step20 = new JourneyStep(
                IDManager.getUUID(),
                new int[0],
                0,
                "Cut 1000 logs",
                new GameItem[]{new GameItem(1359, 1), new GameItem(85, 1), new GameItem(10939, 1)},
                1000,
                1,
                new int[]{1511},
                JourneyStepType.COLLECTION.ordinal()
        );

        Journey journey = new Journey(
                2,
                new Long[]{
                        step1.getId(),
                        step2.getId(),
                        step3.getId(),
                        step4.getId(),
                        step5.getId(),
                        step6.getId(),
                        step7.getId(),
                        step8.getId(),
                        step9.getId(),
                        step10.getId(),
                        step11.getId(),
                        step12.getId(),
                        step13.getId(),
                        step14.getId(),
                        step15.getId(),
                        step16.getId(),
                        step17.getId(),
                        step18.getId(),
                        step19.getId(),
                        step20.getId(),
                },
                new Integer[1],
                "Apprentice Lumberjack"
        );

        JourneyStepDAO.getInstance().create(step1);
        JourneyStepDAO.getInstance().create(step2);
        JourneyStepDAO.getInstance().create(step3);
        JourneyStepDAO.getInstance().create(step4);
        JourneyStepDAO.getInstance().create(step5);
        JourneyStepDAO.getInstance().create(step6);
        JourneyStepDAO.getInstance().create(step7);
        JourneyStepDAO.getInstance().create(step8);
        JourneyStepDAO.getInstance().create(step9);
        JourneyStepDAO.getInstance().create(step10);
        JourneyStepDAO.getInstance().create(step11);
        JourneyStepDAO.getInstance().create(step12);
        JourneyStepDAO.getInstance().create(step13);
        JourneyStepDAO.getInstance().create(step14);
        JourneyStepDAO.getInstance().create(step15);
        JourneyStepDAO.getInstance().create(step16);
        JourneyStepDAO.getInstance().create(step17);
        JourneyStepDAO.getInstance().create(step18);
        JourneyStepDAO.getInstance().create(step19);
        JourneyStepDAO.getInstance().create(step20);
        JourneyDAO.getInstance().create(journey);
    }


    private static void loadJourneys() {
        JourneyStepDAO.getInstance().getAllEntries().forEach(region -> JourneyStepCache.getInstance().create(region.getId(), region));
        JourneyDAO.getInstance().getAllEntries().forEach(region -> JourneyCache.getInstance().create(region.getId(), region));
    }

    private static void loadAchievements() {
        AchievementDAO.getInstance().getAllEntries().forEach(region -> AchievementCache.getInstance().create(region.getId(), region));
    }

    private static void initializeLoaders() {
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(0, "Broader Fletching", 300, "Learn to fletch broad arrows, broad bolts, and amethyst broad bolts. ", false));
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(1, "Malevolent Masquerade", 400, "Learn to assemble a Slayer helmet, which requires level 55 Crafting. ", false));
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(2, "Ring Bling", 300, "Learn to craft a Slayer ring, which requires level 75 Crafting. ", false));
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(3, "Seeing Red", 50, "Konar, Duradel, and Nieve will be able to assign you red dragons as your task. ", false));
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(4, "I hope you're mith me", 80, "Konar, Duradel, and Nieve will be able to assign you mithril dragons as your task. ", false));
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(5, "Watch the birdie ", 80, "Konar, Duradel, Nieve, Chaeldar, and Krystilia will be able to assign you Aviansie as your task. ", false));
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(6, "Hot stuff", 100, "Duradel, Nieve, and Chaeldar will be able to assign TzHaar as your task. You may also be offered a chance to slay TzTok-Jad.", false));
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(7, "Reptile got ripped", 75, "Konar, Duradel, Nieve, and Chaeldar will be able to assign Lizardmen as your task.", false));
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(8, "Like a boss", 200, "Konar, Duradel, Krystilia, and Nieve will be able to assign boss monsters as your task, excluding the Corporeal Beast.", false));
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(9, "Bigger and Badder", 150, "Certain slayer monsters will have the chance of spawning a superior version whilst on a slayer task.", false));

        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(10, "King black bonnet", 1000, "Learn how to combine a KBD head with your slayer helm to colour it black.", false));
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(11, "Kalphite khat", 1000, "Learn how to combine a Kalphite Queen head with your slayer helm to colour it green.", false));
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(12, "Unholy helmet", 1000, "Learn how to combine an Abyssal Demon head with your slayer helm to colour it red.", false));
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(13, "Dark Mantle", 1000, "Learn how to combine a Dark Claw with your slayer helm to colour it purple.", false));
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(14, "Undead Head", 1000, "Learn how to combine Vorkath's head with your slayer helm to colour it turquoise.", false));
        initSpells();
        initEquipmment();
        SlayerKnowledgeRewardDAO.getInstance().create(new SlayerKnowledgeReward(15, "Duly noted", 200, "Mithril dragons drop mithril bars in banknote form while killed on assignment.", false));
        AnimationDefinitionDAO.getInstance().create(new AnimationDefinition(8195, new int[]{4649, 4652, 4651, 4653}));
//        learningTheRopesJourney();
//        apprenticeLumberjackJourney();
        JourneyPathDAO.getInstance().getAllEntries().forEach(path -> JourneyPathCache.getInstance().create(path.getId(), path));
        CharterDAO.getInstance().getAllEntries().forEach(charter -> CharterCache.getInstance().create(charter.getId(), charter));
        DestinationDAO.getInstance().getAllEntries().forEach(destination -> DestinationCache.getInstance().create(destination.getId(), destination));
//        CharterDAO.getInstance().create(new Charter(IDManager.getUUID(),1103196256114526128L,new ethos.model.items.GameItem[]{new ethos.model.items.GameItem(995,500)},false));
        loadJourneys();


//        initRegions();
        loadRegions();


//        initAchievements();
        loadAchievements();

        TierDAO.getInstance().getAllEntries().forEach(tier -> {
            TierLoader.getInstance().create(tier.getId(), tier);
        });

//        BrewDAO.getInstance().create(new Brew(
//                5763, 5765, new GameItem[]{
//                new GameItem(5992, 4),
//                new GameItem(1919, 1),
//                new GameItem(5767, 1)
//        }, 14, 182, false
//        ));
//        BrewDAO.getInstance().create(new Brew(
//                1913, 5747, new GameItem[]{new GameItem(5994, 4),
//                new GameItem(1919, 1),
//                new GameItem(1929, 2),
//                new GameItem(6008, 2),
//                new GameItem(5767, 1)}, 19, 215, false
//        ));
//        BrewDAO.getInstance().create(new Brew(
//                1905, 5739, new GameItem[]{new GameItem(5996, 4),
//                new GameItem(1919, 1),
//                new GameItem(1929, 2),
//                new GameItem(6008, 2),
//                new GameItem(5767, 1)}, 24, 248, false
//        ));
//        BrewDAO.getInstance().create(new Brew(
//                1909, 5743, new GameItem[]{new GameItem(255, 4),
//                new GameItem(1919, 1),
//                new GameItem(1929, 2),
//                new GameItem(6008, 2),
//                new GameItem(5767, 1)}, 29, 281, false
//        ));
//        BrewDAO.getInstance().create(new Brew(
//                1907, 5741, new GameItem[]{new GameItem(5998, 4),
//                new GameItem(1919, 1),
//                new GameItem(1929, 2),
//                new GameItem(6008, 2),
//                new GameItem(5767, 1)}, 34, 314, false
//        ));
//        BrewDAO.getInstance().create(new Brew(
//                1911, 5745, new GameItem[]{new GameItem(6000, 4),
//                new GameItem(1919, 1),
//                new GameItem(1929, 2),
//                new GameItem(6008, 2),
//                new GameItem(5767, 1)}, 39, 347, false
//        ));
//        BrewDAO.getInstance().create(new Brew(
//                2955, 5749, new GameItem[]{new GameItem(6004, 4),
//                new GameItem(1919, 1),
//                new GameItem(1929, 2),
//                new GameItem(6008, 2),
//                new GameItem(5767, 1)}, 44, 380, false
//        ));
//        BrewDAO.getInstance().create(new Brew(
//                5751, 5753, new GameItem[]{new GameItem(6043, 1),
//                new GameItem(1919, 1),
//                new GameItem(1929, 2),
//                new GameItem(6008, 2),
//                new GameItem(5767, 1)}, 49, 413, false
//        ));
//        BrewDAO.getInstance().create(new Brew(
//                5755, 5757, new GameItem[]{new GameItem(1975, 4),
//                new GameItem(1919, 1),
//                new GameItem(1929, 2),
//                new GameItem(6008, 2),
//                new GameItem(5767, 1)}, 54, 413, false
//        ));
//        BrewDAO.getInstance().create(new Brew(
//                5759, 5761, new GameItem[]{new GameItem(6002, 4),
//                new GameItem(1919, 1),
//                new GameItem(1929, 2),
//                new GameItem(6008, 2),
//                new GameItem(5767, 1)}, 59, 479, false
//        ));

//		final Map<String,Integer> requirements = new HashMap<>();
//		final int itemId = 9703;
//		ItemEquipmentContextDAO.getInstance().create(new ItemEquipmentContext.ItemEquipmentContextBuilder(itemId)
//						.withAttackStab(4)
//						.withAttackSlash(3)
//						.withAttackCrush(-2)
//						.withAttackMagic(0)
//						.withAttackRanged(0)
//						.withDefenceStab(0)
//						.withDefenceSlash(2)
//						.withDefenceCrush(1)
//						.withDefenceMagic(0)
//						.withDefenceRanged(0)
//						.withMeleeStrength(5)
//						.withRangedStrength(0)
//						.withSlot("weapon")
//						.withPrayerBonus(0)
//						.withMagicDamage(0)
//						.withRequirements(itemId,requirements)
//				.build()
//		);
        final List<LootTable> contexts = LootTableDAO.getInstance().getAllEntries();
        final List<LootTableDefinition> definitions = LootTableDefinitionDAO.getInstance().getAllEntries();
        final List<LootTableContainer> containers = LootTableContainerDAO.getInstance().getAllEntries();
        final List<LootTableContainerDefinition> containerDefinitions = LootContainerDefinitionDAO.getInstance().getAllEntries();
        final List<LootTable> tables = LootTableDAO.getInstance().getAllEntries();
        final List<LootTableDefinition> tableDefinitions = LootTableDefinitionDAO.getInstance().getAllEntries();

        containers.forEach(entry -> {
            LootTableContainerLoader.getInstance().create(entry.getId(), entry);
        });
        containerDefinitions.forEach(entry -> {
            LootTableContainerDefinitionLoader.getInstance().create(entry.getId(), entry);
        });
        contexts.forEach(itemContext -> {
            LootTableLoader.getInstance().create(itemContext.getId(), itemContext);
        });
        definitions.forEach(itemContext -> {
            LootTableDefinitionLoader.getInstance().create(itemContext.getId(), itemContext);
        });

        GatheringToolDAO.getInstance().getAllEntries().forEach(gatheringTool -> {
            GatheringToolLoader.getInstance().create(gatheringTool.getItemId(), gatheringTool);
        });

        RenewableNodeDAO.getInstance().getAllEntries().forEach(renewableNode -> {
            RenewableNodeLoader.getInstance().create(renewableNode.getId(), renewableNode);
        });

        MiningNodeDAO.getInstance().getAllEntries().forEach(miningNode -> {
            MiningNodeLoader.getInstance().create(miningNode.getId(), miningNode);
        });

        WoodcuttingNodeDAO.getInstance().getAllEntries().forEach(woodcuttingNode -> {
            WoodcuttingNodeLoader.getInstance().create(woodcuttingNode.getId(), woodcuttingNode);
        });

        FishingNodeDAO.getInstance().getAllEntries().forEach(node -> {
            FishingNodeLoader.getInstance().create(node.getId(), node);
        });

        FishLevelDAO.getInstance().getAllEntries().forEach(fishLevel -> {
            FishLevelLoader.getInstance().create(fishLevel.getItemId(), fishLevel);
        });

        FishingSpotNodeDAO.getInstance().getAllEntries().forEach(node -> {
            FishingSpotNodeLoader.getInstance().create(node.getId(), node);
        });

        ForagingNodeDAO.getInstance().getAllEntries().forEach(node -> {
            ForagingNodeLoader.getInstance().create(node.getId(), node);
        });

        ItemInteractionDAO.getInstance().getAllEntries().forEach(node -> {
            ItemInteractionLoader.getInstance().create(node.getUuid(), node);
        });

//        Debugslayer.generateTuraelAssignments();
//        populateSuperiorSlayerMonsters();
//        PromotionalChestEvent.resetShinyChestToDefault();
        SuperiorSlayerMonsterDAO.getInstance().getAllEntries().forEach(superiorSlayerMonster -> SuperiorSlayerMonsterCache.getInstance().create(superiorSlayerMonster.getMobId(), superiorSlayerMonster));
        HostileMobIdContextLoader.getInstance().readAll().stream()
                .filter(Objects::nonNull)
                .filter(hostileMobContext -> hostileMobContext.getCategory().stream().anyMatch(s -> s.contains("bosses")))
                .forEach(hostileMobContext -> System.out.println("Name: " + hostileMobContext.getName() + " ID: " + hostileMobContext.getId() + " Cat.: " + hostileMobContext.getCategory()));
//        List<HostileMobContext> mobs = HostileMobContextDAO.getInstance().getAllEntries().stream()
//                .filter(hostileMobContext -> hostileMobContext.getRespawnTicks() == 5)
//                .collect(Collectors.toList());
//
//        mobs.forEach(hostileMobContext -> hostileMobContext.setRespawnTicks(50));
//
//        mobs.forEach(hostileMobContext -> HostileMobContextDAO.getInstance().update(hostileMobContext));
    }

    private static void populateSuperiorSlayerMonsters() {
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7388,
                        "crawling hands",
                        new int[0]
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7389,
                        "cave crawlers",
                        new int[0]
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7390,
                        "banshees",
                        new int[0]
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7391,
                        "banshees",
                        new int[]{7272}
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7392,
                        "rockslugs",
                        new int[0]
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7393,
                        "cockatrice",
                        new int[0]
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7394,
                        "pyrefiends",
                        new int[0]
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7395,
                        "basilisks",
                        new int[0]
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7396,
                        "infernal mages",
                        new int[0]
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7397,
                        "bloodvelds",
                        new int[0]
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7398,
                        "bloodvelds",
                        new int[]{7276}
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7399,
                        "jellies",
                        new int[0]
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7400,
                        "jellies",
                        new int[]{7277}
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7401,
                        "cave horrors",
                        new int[0]
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7402,
                        "aberrant spectres",
                        new int[0]
                )
        );
        SuperiorSlayerMonsterDAO.getInstance().create(
                new SuperiorSlayerMonster(
                        7403,
                        "aberrant spectres",
                        new int[]{7279}
                )
        );
    }

    private static void populateRegionDB() {
        long strongholdSlayerCaveID = IDManager.getUUID();
        RegionDataAccessObject.getInstance().create(
                new org.runehub.api.model.world.region.Region(
                        strongholdSlayerCaveID,
                        new IrregularPolygon(new Point(2381, 9764),
                                new Point(2381, 9837),
                                new Point(2497, 9837),
                                new Point(2497, 9764),
                                new Point(2381, 9764))
                )
        );
        RegionContextDataAccessObject.getInstance().create(new RegionContext(
                strongholdSlayerCaveID, "Stronghold Slayer Cave"
        ));
    }

    private static final Runnable SERVER_TASKS = () -> {
        try {
            itemHandler.process();
            playerHandler.process();
            npcHandler.process();
            shopHandler.process();
            Highpkarena.process();
            Lowpkarena.process();
            globalObjects.pulse();
            CycleEventHandler.getSingleton().process();
            events.process();
            serverData.processQueue();
        } catch (Throwable t) {
            t.printStackTrace();
            t.getCause();
            t.getMessage();
            t.fillInStackTrace();
            System.out.println("Server tasks - Check for error");
            PlayerHandler.stream().filter(Objects::nonNull).forEach(PlayerSave::save);
        }
    };

    private static final Runnable IO_TASKS = () -> {
        try {
            WorldSettingsController.getInstance().updateTimers();
            // TODO tasks(players online, etc)
        } catch (Throwable t) {
            t.printStackTrace();
        }
    };

    private static final Runnable HOURLY_IO_TASKS = () -> {
        try {
            MobKillDatabase.getInstance().pushCacheToDisk();
            // TODO tasks(players online, etc)
        } catch (Throwable t) {
            t.printStackTrace();
        }
    };






    public static void main(java.lang.String[] args) {
//		System.out.println(GamblingManMob.getInstance().getBlackjack().getEmptyPlayingPosition());
        APILogger.debug_level = RunehubConstants.DEBUG_LEVEL;
        APILogger.initialize();
        APISettingsController.getInstance().getApiSettings().setItemDatabaseLocation(RunehubConstants.OS_DEFINTIONS_DB);
        APISettingsController.getInstance().getApiSettings().setLootDatabase(RunehubConstants.LOOT_DB);
        APISettingsController.getInstance().getApiSettings().setRegionDatabaseLocation(RunehubConstants.REGION_DB);
        APISettingsController.getInstance().getApiSettings().setRegionContextDatabaseLocation(RunehubConstants.REGION_DB);
        LootTableContainerLoader.getInstance().read(6014538874343540882L).getLootTables().forEach(lootTableContainerEntry -> {
            LootTableLoader.getInstance().read(lootTableContainerEntry.getId()).getLootTableEntries().forEach(lootTableEntry -> {
                System.out.println(lootTableEntry.getId());
            });
        });
//		ShipDAO.getInstance().create(new Ship(2,"Blazing Lantern",15,15,15,15));
//		ShipDAO.getInstance().create(new Ship(3,"Pin's Ship",7,7,7,7));
//		ShipDAO.getInstance().create(new Ship(4,"Dinghy",1,0,0,0));
//		ShipDAO.getInstance().create(new Ship(5,"Trawler",2,2,1,2));
//		ShipDAO.getInstance().create(new Ship(6,"Frigate",3,1,3,2));
//		ShipDAO.getInstance().create(new Ship(7,"Catamaran",2,3,0,3));
//		ShipDAO.getInstance().create(new Ship(8,"Coracle",0,0,0,0));
//		ShipDAO.getInstance().create(new Ship(9,"Masula",2,1,1,2));

//		VoyageDAO.getInstance().create(new Voyage(
//				500002,
//				"Warring Khans",
//				25,25,25,150,0,1,
//				true,false,
//				new VoyageContext(new int[] {
//						0,0,0,0,0,
//						0,0,0,0,0,
//						0,0,0,0,0,
//						0,0,0,0,0,
//						0,0,0,0
//				}
//		, new int[]{70000})));

//		System.out.println(Arrays.toString(VoyageDAO.getInstance().read(500002).getContext().getLevels()));
        try {
            long startTime = System.currentTimeMillis();
            System.setOut(extracted());
            PUNISHMENTS.initialize();
            events.submit(new DidYouKnowEvent());
            events.submit(new WheatPortalEvent());
            events.submit(new BonusApplianceEvent());
            events.submit(new PunishmentCycleEvent(PUNISHMENTS, 50));
//            events.submit(new TravellingCommodityMerchantEvent());
//			events.submit(new ExchangePriceUpdateEvent());
//			ForageNodeClusterController.getInstance().spawnCluster();
            FishingPlatformController.getInstance().initializePlatform();
            Listing.loadNextSale();
            Wogw.init();
            ItemDefinition.load();
//			DoorDefinition.load();
            GodwarsEquipment.load();
            GodwarsNPCs.load();
//			dropManager.read();
            CasketRewards.read();
            ObjectDef.loadConfig();
            globalObjects.loadGlobalObjectFile();
            Region.load();
            bindPorts();
            MonsterHunt.spawnNPC();
            holidayController.initialize();
            Runtime.getRuntime().addShutdownHook(new ShutdownHook());
            Commands.initializeCommands();
            WorldController.getInstance().startUpdateThread();
            long endTime = System.currentTimeMillis();
            long elapsed = endTime - startTime;

            initializeLoaders();
            Arrays.stream(FixedScheduledEventController.getInstance().getFixedScheduleEvents()).forEach(event -> FixedScheduledEventController.getInstance().startEvent(event));

            System.out.println(Config.SERVER_NAME + " has successfully started up in " + elapsed + " milliseconds.");
            GAME_THREAD.scheduleAtFixedRate(SERVER_TASKS, 0, 600, TimeUnit.MILLISECONDS);
            IO_THREAD.scheduleAtFixedRate(IO_TASKS, 0, 60, TimeUnit.SECONDS);
            IO_THREAD.scheduleAtFixedRate(HOURLY_IO_TASKS, 60, 60, TimeUnit.MINUTES);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Logger extracted() {
        return new Logger(System.out);
    }

    /**
     * Gets the sleep mode timer and puts the server into sleep mode.
     */
    public static long getSleepTimer() {
        return sleepTime;
    }

    public static MultiplayerSessionListener getMultiplayerSessionListener() {
        return multiplayerSessionListener;
    }

    /**
     * Java connection. Ports.
     */
    private static void bindPorts() {
        ServerBootstrap serverBootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        serverBootstrap.setPipelineFactory(new PipelineFactory(new HashedWheelTimer()));
        serverBootstrap.bind(new InetSocketAddress(serverlistenerPort));
    }

    public static GameCalendar getCalendar() {
        return calendar;
    }

    public static HolidayController getHolidayController() {
        return holidayController;
    }

    public static ServerData getServerData() {
        return serverData;
    }

    public static GlobalObjects getGlobalObjects() {
        return globalObjects;
    }

    public static EventHandler getEventHandler() {
        return events;
    }

    public static DropManager getDropManager() {
        return dropManager;
    }

    public static Punishments getPunishments() {
        return PUNISHMENTS;
    }

    public static String getStatus() {
        return "IO_THREAD\n" + "\tShutdown? " + IO_THREAD.isShutdown() + "\n" + "\tTerminated? "
                + IO_THREAD.isTerminated();
    }

    private static final ExecutorService connectionHandlerService = Executors.newSingleThreadExecutor();
}