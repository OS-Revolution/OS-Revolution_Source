package ethos.runehub;

import ethos.runehub.entity.merchant.MerchandiseSlot;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.util.*;
import java.util.logging.Level;

public class RunehubConstants {

    public static boolean DEBUG = false;
    public static Level DEBUG_LEVEL = Level.SEVERE;

    public static final String ROOT = "./Data/runehub/";
    public static final String DB_ROOT = ROOT + "db/";
    public static final String EXCHANGE_DB = DB_ROOT + "exchange.db";
    public static final String PLAYER_DB = DB_ROOT + "players.db";
    public static final String UPGRADE_DB = DB_ROOT + "upgrades.db";
    public static final String METRICS_DB = DB_ROOT + "metrics.db";
    public static final String JOURNEY_DB = DB_ROOT + "journey.db";
    public static final String ITEM_INTERACTION_DB = DB_ROOT + "item-interactions.db";
    public static final String OS_DEFINTIONS_DB = DB_ROOT + "os-definitions.db";
    public static final String NODE_DB = DB_ROOT + "nodes.db";
    public static final String ITEM_ACTION_DB = DB_ROOT + "item-actions.db";
    public static final String EFFECT_DB = DB_ROOT + "effects.db";
    public static final String TOOL_DB = DB_ROOT + "tools.db";
    public static final String LOOT_DB = DB_ROOT + "loot.db";
    public static final String REGION_DB = DB_ROOT + "region.db";
    public static final String SAILING_DB = DB_ROOT + "sailing.db";
    public static final String FARMING_DB = DB_ROOT + "farming.db";
    public static final String RIFT_DB = DB_ROOT + "rift.db";
    public static final String EQUIPMENT = DB_ROOT + "equipment.db";
    public static final String SKILL_DB = DB_ROOT + "skills.db";
    public static final String WORLD_SETTINGS = ROOT + "world-settings.json";


    public static final Rectangle HOME_TELEPORT_AREA = new Rectangle(new Point(3102,3248),new Point(3106,3251));

    public static final List<MerchandiseSlot> GENERAL_STORE_ITEMS = new ArrayList<>();

    public final static List<Integer> STAR_IDS = Arrays.asList(22009, 22010, 22011, 22012, 22013, 22014, 22015, 22016, 22017, 22018, 22019, 22020, 22021, 22022, 22023, 22024, 22025, 22026, 22027, 22028, 22029, 22030, 22031, 22032, 22033, 22034, 22035, 22036, 22037, 22038, 22039, 22040, 22041, 22042, 22043, 22044, 22045, 22046, 22047, 22048, 22049, 22050, 22051, 22052, 22053, 22054, 22055, 22056, 22057, 22058, 22059, 22060, 22061, 22062, 22063, 22064, 22065, 22066, 22067, 22068, 22069, 22070, 22071, 22072, 22073, 22074, 22075, 22076, 22077, 22078, 22079, 22080, 22081, 22082, 22083, 22084, 22085, 22086, 22087, 22088, 22089, 22090, 22091, 22092,22191,22227,22228,22229,22230,21873,21874,21875);
    public final static List<Integer> LAMP_IDS = Arrays.asList(21399,21400,21401,21402,21403,21404,21405,21406,21407,21408,21409,21411,21412,21413,21414,21415,21416,21417,21418,21419,21420,21421,21422,21423,21424,21425,21426,21427,21841,21842,21843,21844,21845,21846,21861,21862,21863,21864,21865,21866,21867,21868,21869,21870,21871,20854,20855,20856,20857,20858,20859,20860,20861,20862,20863,20864,20865,20867,20868,20869,20870,20871,20872,20873,20874,20875,20876,20877,20878,20879,20880,20881,20882,20883,20532,20533,20534,20535,20536,22000,22001,21827,21828,21829,21830,21831,21832,21833,21834,21428,21429,21430);
    public static final int CANCEL_TASK_COST = 30;
    public static final int EXTEND_TASK_COST = 30;
    public static final int BLOCK_TASK_COST = 100;
    public static final int PREFER_TASK_COST = 100;
    public static final int UNBLOCK_TASK_COST = 5000;

    public static final int[] CBOW = {9174,9185,11785,21012};

    public static final int JEWEL_ID = 1459;

    public static final int COMPLETE_VOYAGE_COST = 80;
    public static final int ADVANCE_PATCH_GROWTH_COST = 10;
}
