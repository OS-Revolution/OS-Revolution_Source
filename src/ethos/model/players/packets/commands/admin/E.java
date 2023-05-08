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
import ethos.runehub.content.job.Job;
import ethos.runehub.content.job.JobUtils;
import ethos.runehub.content.journey.JourneyPath;
import ethos.runehub.content.journey.JourneyPathCache;
import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.entity.merchant.impl.exchange.ExchangePriceController;
import ethos.runehub.entity.mob.AnimationDefinitionCache;
import ethos.runehub.entity.mob.hostile.HostileMobIdContextLoader;
import ethos.runehub.event.FixedScheduleEvent;
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
import ethos.runehub.ui.impl.*;
import ethos.runehub.ui.impl.construction.BuildNodeUI;
import ethos.runehub.ui.impl.sailing.PortUI;
import ethos.runehub.ui.impl.smithing.SmithingUI;
import ethos.runehub.ui.impl.tab.player.InstanceTab;
import ethos.runehub.world.Chunk;
import ethos.runehub.world.RegionCache;
import ethos.runehub.world.WorldController;
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
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.time.Duration;
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

    int[] anims = {
            13, 14, 15, 22, 23, 24, 85, 87, 88, 179, 244, 245, 246, 247, 248, 334, 335, 346, 347, 363, 364, 365, 368, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 637, 642, 643, 644, 645, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 733, 734, 735, 736, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 780, 781, 782, 783, 784, 785, 791, 792, 793, 794, 795, 796, 797, 798, 799, 800, 801, 802, 803, 804, 805, 806, 807, 808, 809, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829, 830, 831, 832, 833, 834, 835, 836, 837, 838, 839, 840, 841, 842, 843, 844, 845, 846, 847, 848, 849, 850, 851, 852, 853, 854, 855, 856, 857, 858, 859, 860, 861, 862, 863, 864, 865, 866, 867, 868, 869, 870, 871, 872, 873, 874, 875, 877, 878, 879, 881, 882, 883, 884, 885, 886, 887, 888, 889, 890, 891, 892, 893, 894, 895, 896, 897, 898, 899, 904, 905, 906, 908, 909, 910, 911, 915, 916, 918, 919, 920, 921, 923, 929, 931, 1026, 1056, 1057, 1058, 1060, 1062, 1064, 1067, 1068, 1074, 1077, 1078, 1084, 1100, 1105, 1106, 1110, 1113, 1114, 1115, 1116, 1117, 1118, 1119, 1120, 1121, 1122, 1123, 1124, 1125, 1128, 1129, 1130, 1131, 1132, 1133, 1137, 1144, 1145, 1146, 1147, 1148, 1156, 1157, 1161, 1162, 1163, 1164, 1165, 1166, 1167, 1168, 1169, 1191, 1192, 1193, 1194, 1195, 1196, 1203, 1205, 1206, 1207, 1208, 1209, 1210, 1236, 1237, 1238, 1239, 1240, 1241, 1248, 1249, 1252, 1254, 1255, 1256, 1258, 1263, 1264, 1265, 1266, 1267, 1268, 1275, 1280, 1306, 1307, 1309, 1317, 1318, 1319, 1320, 1321, 1322, 1323, 1324, 1325, 1326, 1327, 1328, 1329, 1330, 1331, 1332, 1333, 1335, 1336, 1337, 1350, 1351, 1352, 1353, 1354, 1363, 1364, 1365, 1366, 1367, 1368, 1369, 1370, 1371, 1374, 1376, 1377, 1378, 1379, 1407, 1408, 1409, 1421, 1422, 1423, 1424, 1425, 1426, 1427, 1428, 1429, 1440, 1441, 1457, 1458, 1459, 1460, 1461, 1462, 1463, 1464, 1465, 1466, 1467, 1468, 1469, 1470, 1481, 1482, 1483, 1484, 1485, 1486, 1487, 1499, 1500, 1501, 1502, 1539, 1540, 1541, 1542, 1572, 1574, 1576, 1601, 1602, 1603, 1604, 1605, 1606, 1633, 1634, 1648, 1649, 1650, 1651, 1652, 1654, 1655, 1656, 1658, 1659, 1660, 1661, 1662, 1663, 1664, 1665, 1666, 1667, 1670, 1671, 1672, 1673, 1696, 1697, 1698, 1699, 1700, 1701, 1702, 1703, 1704, 1705, 1706, 1707, 1708, 1709, 1710, 1711, 1712, 1713, 1719, 1720, 1728, 1739, 1740, 1743, 1744, 1745, 1746, 1755, 1762, 1763, 1764, 1765, 1766, 1767, 1768, 1769, 1770, 1771, 1772, 1809, 1810, 1811, 1816, 1818, 1819, 1820, 1824, 1825, 1826, 1827, 1830, 1831, 1832, 1833, 1834, 1835, 1836, 1837, 1850, 1851, 1852, 1871, 1872, 1877, 1878, 1879, 1880, 1894, 1895, 1897, 1902, 1903, 1904, 1905, 1906, 1907, 1914, 1950, 1952, 1953, 1954, 1955, 1972, 1978, 1979, 1989, 1992, 1993, 1994, 1995, 1996, 2009, 2010, 2040, 2041, 2042, 2043, 2044, 2045, 2046, 2047, 2048, 2049, 2050, 2059, 2060, 2061, 2062, 2063, 2064, 2065, 2066, 2067, 2068, 2074, 2075, 2076, 2077, 2078, 2079, 2080, 2081, 2082, 2092, 2093, 2094, 2095, 2096, 2097, 2098, 2105, 2106, 2107, 2108, 2109, 2110, 2111, 2112, 2113, 2114, 2115, 2116, 2117, 2127, 2128, 2139, 2140, 2141, 2142, 2143, 2144, 2145, 2146, 2147, 2148, 2149, 2163, 2164, 2171, 2239, 2240, 2241, 2242, 2243, 2244, 2245, 2246, 2247, 2248, 2249, 2250, 2251, 2252, 2253, 2254, 2255, 2256, 2257, 2258, 2259, 2262, 2263, 2264, 2265, 2266, 2269, 2270, 2271, 2272, 2273, 2274, 2275, 2276, 2277, 2278, 2279, 2280, 2281, 2282, 2283, 2284, 2285, 2286, 2287, 2288, 2289, 2290, 2291, 2292, 2293, 2295, 2304, 2305, 2306, 2310, 2311, 2316, 2317, 2318, 2319, 2320, 2321, 2322, 2323, 2324, 2325, 2326, 2327, 2332, 2333, 2334, 2338, 2339, 2340, 2343, 2376, 2377, 2378, 2382, 2383, 2384, 2385, 2386, 2387, 2388, 2389, 2390, 2393, 2394, 2400, 2401, 2402, 2403, 2409, 2410, 2411, 2412, 2418, 2420, 2421, 2422, 2423, 2424, 2425, 2426, 2427, 2428, 2429, 2430, 2431, 2432, 2433, 2441, 2442, 2443, 2450, 2553, 2554, 2555, 2556, 2557, 2561, 2562, 2563, 2565, 2566, 2569, 2570, 2572, 2573, 2574, 2575, 2576, 2577, 2578, 2579, 2580, 2581, 2582, 2583, 2584, 2585, 2586, 2587, 2588, 2589, 2590, 2591, 2592, 2593, 2594, 2595, 2614, 2661, 2685, 2692, 2693, 2695, 2696, 2697, 2698, 2700, 2701, 2702, 2709, 2710, 2712, 2713, 2717, 2720, 2721, 2724, 2726, 2727, 2728, 2741, 2750, 2751, 2752, 2753, 2754, 2755, 2756, 2757, 2758, 2759, 2760, 2761, 2762, 2763, 2764, 2769, 2770, 2771, 2779, 2780, 2781, 2782, 2783, 2784, 2785, 2786, 2787, 2788, 2793, 2794, 2795, 2796, 2797, 2801, 2810, 2811, 2812, 2813, 2815, 2816, 2817, 2818, 2819, 2820, 2829, 2831, 2833, 2835, 2836, 2837, 2838, 2839, 2840, 2843, 2844, 2845, 2846, 2847, 2876, 2879, 2880, 2881, 2888, 2890, 2891, 2902, 2903, 2909, 2910, 2911, 2912, 2913, 2914, 2925, 2926, 2927, 2928, 2929, 2939, 2940, 2966, 2967, 2968, 2993, 2994, 2995, 2999, 3000, 3001, 3002, 3003, 3004, 3005, 3006, 3011, 3012, 3013, 3015, 3025, 3032, 3036, 3039, 3040, 3041, 3042, 3043, 3044, 3045, 3046, 3053, 3054, 3055, 3056, 3057, 3058, 3059, 3060, 3061, 3062, 3063, 3064, 3065, 3066, 3067, 3068, 3069, 3071, 3090, 3091, 3094, 3102, 3103, 3114, 3115, 3116, 3128, 3129, 3130, 3131, 3132, 3133, 3134, 3135, 3136, 3137, 3140, 3141, 3142, 3143, 3157, 3170, 3171, 3175, 3176, 3177, 3178, 3181, 3182, 3183, 3184, 3185, 3186, 3187, 3191, 3192, 3194, 3195, 3238, 3239, 3240, 3243, 3256, 3257, 3265, 3266, 3268, 3269, 3270, 3271, 3272, 3273, 3274, 3275, 3276, 3277, 3278, 3279, 3280, 3281, 3282, 3283, 3284, 3285, 3286, 3287, 3288, 3289, 3290, 3291, 3292, 3293, 3294, 3295, 3296, 3297, 3298, 3299, 3300, 3301, 3302, 3303, 3331, 3332, 3333, 3334, 3335, 3337, 3338, 3339, 3340, 3341, 3342, 3348, 3353, 3356, 3358, 3359, 3360, 3361, 3362, 3363, 3364, 3365, 3366, 3367, 3368, 3369, 3370, 3371, 3372, 3373, 3374, 3375, 3396, 3397, 3399, 3410, 3413, 3414, 3415, 3416, 3417, 3418, 3419, 3420, 3421, 3422, 3423, 3450, 3451, 3452, 3471, 3475, 3495, 3496, 3497, 3541, 3543, 3544, 3547, 3550, 3551, 3552, 3553, 3555, 3557, 3563, 3564, 3565, 3566, 3571, 3572, 3592, 3593, 3594, 3595, 3596, 3597, 3598, 3599, 3600, 3602, 3603, 3605, 3606, 3608, 3609, 3611, 3621, 3622, 3623, 3624, 3625, 3626, 3627, 3630, 3634, 3635, 3636, 3639, 3640, 3641, 3645, 3649, 3651, 3652, 3653, 3654, 3655, 3656, 3657, 3658, 3659, 3660, 3661, 3662, 3663, 3664, 3665, 3666, 3667, 3668, 3669, 3670, 3671, 3672, 3673, 3674, 3675, 3676, 3677, 3678, 3679, 3680, 3683, 3684, 3685, 3686, 3687, 3688, 3689, 3690, 3691, 3692, 3693, 3694, 3695, 3696, 3697, 3701, 3702, 3703, 3704, 3705, 3719, 3734, 3735, 3739, 3740, 3741, 3745, 3747, 3804, 3806, 3807, 3838, 3839, 3844, 3845, 3846, 3850, 3851, 3852, 3854, 3858, 3859, 3864, 3865, 3866, 3867, 3869, 3872, 3873, 3874, 3926, 3931, 3945, 3950, 3970, 3971, 3972, 3973, 3996, 3997, 3999, 4001, 4003, 4004, 4017, 4018, 4019, 4023, 4024, 4028, 4029, 4030, 4031, 4036, 4043, 4044, 4045, 4046, 4047, 4048, 4049, 4050, 4051, 4052, 4053, 4054, 4055, 4056, 4057, 4058, 4059, 4060, 4061, 4062, 4063, 4064, 4065, 4066, 4067, 4068, 4069, 4071, 4073, 4074, 4075, 4076, 4077, 4078, 4079, 4080, 4081, 4082, 4083, 4084, 4085, 4086, 4087, 4088, 4089, 4090, 4091, 4092, 4093, 4094, 4095, 4096, 4097, 4098, 4099, 4100, 4101, 4102, 4103, 4104, 4105, 4106, 4107, 4108, 4109, 4110, 4111, 4112, 4113, 4114, 4115, 4116, 4117, 4136, 4141, 4142, 4143, 4144, 4145, 4146, 4147, 4148, 4149, 4150, 4151, 4152, 4153, 4154, 4166, 4167, 4168, 4169, 4170, 4171, 4172, 4173, 4174, 4175, 4177, 4178, 4179, 4180, 4181, 4182, 4183, 4184, 4185, 4188, 4190, 4191, 4192, 4193, 4194, 4195, 4196, 4197, 4198, 4199, 4200, 4207, 4208, 4209, 4226, 4227, 4228, 4230, 4238, 4250, 4254, 4255, 4256, 4257, 4258, 4275, 4276, 4278, 4280, 4282, 4283, 4285, 4316, 4329, 4330, 4340, 4341, 4342, 4343, 4344, 4345, 4348, 4349, 4350, 4352, 4365, 4366, 4367, 4368, 4369, 4370, 4371, 4378, 4379, 4380, 4381, 4382, 4400, 4401, 4402, 4406, 4409, 4411, 4412, 4413, 4424, 4425, 4426, 4427, 4428, 4429, 4430, 4432, 4433, 4434, 4435, 4436, 4437, 4438, 4439, 4440, 4441, 4442, 4455, 4456, 4457, 4458, 4459, 4460, 4462, 4464, 4465, 4466, 4467, 4468, 4470, 4471, 4480, 4481, 4482, 4483, 4503, 4504, 4505, 4506, 4511, 4512, 4513, 4514, 4519, 4540, 4544, 4546, 4547, 4549, 4551, 4552, 4553, 4558, 4581, 4586, 4591, 4592, 4593, 4594, 4597, 4598, 4602, 4603, 4604, 4606, 4607, 4608, 4614, 4615, 4616, 4617, 4626, 4646, 4682, 4709, 4710, 4712, 4713, 4718, 4719, 4720, 4721, 4722, 4723, 4724, 4726, 4727, 4728, 4731, 4750, 4751, 4757, 4758, 4759, 4760, 4761, 4762, 4763, 4764, 4765, 4766, 4771, 4772, 4779, 4780, 4785, 4786, 4787, 4788, 4789, 4790, 4791, 4793, 4795, 4797, 4804, 4805, 4806, 4807, 4809, 4824, 4825, 4826, 4834, 4835, 4838, 4839, 4841, 4847, 4850, 4851, 4852, 4853, 4855, 4857, 4859, 4861, 4862, 4863, 4884, 4885, 4905, 4909, 4911, 4937, 4939, 4941, 4943, 4945, 4947, 4949, 4951, 4953, 4955, 4957, 4959, 4961, 4963, 4965, 4967, 4969, 4971, 4973, 4975, 4977, 4979, 4981, 5006, 5015, 5037, 5038, 5039, 5040, 5041, 5042, 5043, 5046, 5047, 5048, 5049, 5050, 5051, 5052, 5054, 5056, 5057, 5059, 5061, 5063, 5067, 5079, 5080, 5081, 5108, 5140, 5142, 5146, 5147, 5148, 5149, 5150, 5151, 5152, 5153, 5155, 5158, 5160, 5161, 5162, 5163, 5164, 5165, 5166, 5167, 5168, 5206, 5207, 5208, 5209, 5210, 5211, 5212, 5213, 5215, 5216, 5217, 5236, 5243, 5244, 5245, 5246, 5247, 5248, 5249, 5250, 5251, 5252, 5253, 5254, 5255, 5256, 5257, 5258, 5259, 5283, 5293, 5298, 5299, 5300, 5311, 5312, 5313, 5315, 5316, 5349, 5352, 5354, 5355, 5361, 5362, 5363, 5364, 5365, 5370, 5371, 5372, 5373, 5374, 5375, 5376, 5377, 5378, 5379, 5383, 5384, 5400, 5407, 5416, 5417, 5418, 5419, 5428, 5436, 5438, 5439, 5440, 5441, 5442, 5443, 5444, 5471, 5472, 5473, 5474, 5475, 5476, 5524, 5525, 5602, 5608, 5609, 5611, 5620, 5633, 5637, 5714, 5716, 5718, 5746, 5752, 5753, 5754, 5756, 5759, 5760, 5761, 5762, 5763, 5776, 5777, 5778, 5796, 5799, 5800, 5812, 5814, 5815, 5816, 5817, 5818, 5819, 5821, 5822, 5823, 5827, 5845, 5846, 5860, 5862, 5863, 5864, 5865, 5866, 5867, 5868, 5869, 5870, 5907, 6063, 6064, 6066, 6067, 6068, 6075, 6076, 6083, 6085, 6086, 6087, 6095, 6096, 6098, 6099, 6100, 6101, 6102, 6103, 6104, 6106, 6108, 6109, 6111, 6112, 6113, 6118, 6122, 6124, 6128, 6129, 6130, 6131, 6132, 6143, 6146, 6147, 6197, 6198, 6209, 6213, 6217, 6268, 6275, 6276, 6277, 6278, 6279, 6280, 6281, 6282, 6283, 6284, 6285, 6286, 6287, 6288, 6289, 6290, 6291, 6292, 6293, 6294, 6295, 6296, 6297, 6298, 6299, 6300, 6301, 6303, 6304, 6305, 6361, 6362, 6363, 6364, 6365, 6366, 6367, 6378, 6379, 6380, 6381, 6382, 6383, 6384, 6385, 6386, 6387, 6388, 6389, 6390, 6391, 6392, 6393, 6394, 6395, 6396, 6397, 6398, 6399, 6400, 6401, 6402, 6403, 6404, 6405, 6406, 6407, 6408, 6409, 6424, 6425, 6459, 6462, 6464, 6465, 6468, 6469, 6478, 6479, 6480, 6486, 6487, 6488, 6489, 6490, 6525, 6526, 6527, 6528, 6529, 6530, 6531, 6532, 6554, 6575, 6584, 6592, 6593, 6594, 6595, 6600, 6601, 6603, 6604, 6605, 6606, 6607, 6608, 6609, 6610, 6611, 6631, 6632, 6633, 6634, 6649, 6650, 6651, 6654, 6655, 6657, 6658, 6659, 6660, 6661, 6662, 6663, 6664, 6665, 6671, 6672, 6673, 6674, 6675, 6676, 6677, 6678, 6679, 6680, 6681, 6682, 6683, 6684, 6685, 6686, 6687, 6688, 6689, 6695, 6696, 6700, 6702, 6703, 6704, 6705, 6706, 6707, 6708, 6709, 6710, 6711, 6712, 6713, 6714, 6715, 6716, 6717, 6718, 6719, 6720, 6723, 6724, 6738, 6739, 6740, 6741, 6742, 6743, 6744, 6745, 6746, 6747, 6748, 6749, 6750, 6751, 6752, 6753, 6754, 6755, 6756, 6757, 6758, 6838, 6839, 6840, 6841, 6842, 6843, 6844, 6845, 6846, 6847, 6848, 6849, 6863, 6864, 6865, 6866, 6867, 6887, 6896, 6897, 6912, 6913, 6914, 6915, 6918, 6919, 6922, 6923, 6926, 6927, 6928, 6929, 6932, 6933, 6936, 6937, 6938, 6941, 6942, 6943, 6944, 6945, 6983, 6984, 6993, 6994, 6995, 6996, 6997, 6998, 6999, 7004, 7012, 7013, 7014, 7015, 7041, 7043, 7044, 7045, 7046, 7047, 7048, 7049, 7052, 7053, 7054, 7055, 7056, 7057, 7059, 7066, 7083, 7087, 7088, 7089, 7090, 7091, 7092, 7093, 7094, 7118, 7121, 7122, 7131, 7132, 7133, 7134, 7136, 7137, 7138, 7139, 7140, 7151, 7153, 7154, 7155, 7163, 7171, 7172, 7174, 7185, 7186, 7187, 7188, 7189, 7190, 7197, 7198, 7199, 7200, 7201, 7202, 7203, 7208, 7209, 7210, 7211, 7212, 7214, 7215, 7218, 7219, 7220, 7221, 7222, 7223, 7245, 7262, 7263, 7264, 7265, 7266, 7267, 7268, 7271, 7272, 7273, 7274, 7275, 7276, 7278, 7279, 7281, 7282, 7283, 7284, 7285, 7286, 7287, 7288, 7289, 7290, 7291, 7292, 7293, 7294, 7295, 7296, 7297, 7298, 7299, 7300, 7305, 7319, 7327, 7328, 7329, 7330, 7331, 7332, 7384, 7386, 7387, 7388, 7391, 7392, 7393, 7394, 7401, 7402, 7403, 7405, 7406, 7507, 7508, 7509, 7510, 7511, 7512, 7514, 7515, 7516, 7517, 7518, 7519, 7520, 7521, 7526, 7527, 7528, 7529, 7531, 7533, 7534, 7535, 7536, 7537, 7538, 7539, 7540, 7541, 7542, 7544, 7551, 7552, 7553, 7554, 7555, 7556, 7557, 7558, 7617, 7618, 7619, 7620, 7621, 7622, 7623, 7624, 7625, 7626, 7627, 7628, 7629, 7630, 7631, 7632, 7633, 7638, 7639, 7640, 7641, 7642, 7643, 7644, 7645, 7697, 7699, 7700, 7701, 7702, 7703, 7704, 7705, 7706, 7707, 7708, 7709, 7710, 7738, 7739, 7741, 7745, 7746, 7747, 7751, 7753, 7754
    };

    @Override
    public void execute(Player c, String input) {
        String[] args = input.split(" ");
//        JourneyPathCache.getInstance().createAndPush(1,new JourneyPath(1,new int[] {2},new int[]{},"The Lumberjack"));
//        c.getPA().walkableInterface(53000);
//        int type = Integer.parseInt(args[0]);
//        if (type == 0) {
//            c.getContext().getPlayerSaveData().setActiveJob(JobUtils.generateWoodcuttingJob(
//                    c.getSkillController().getLevel(8), 8, c.getContext().getPlayerSaveData().getJobScore()).toBitArray());
//        } else {
//            System.out.println(Job.fromBitArray(c.getContext().getPlayerSaveData().getActiveJob()));
//        }
        //        List<Job> jobs = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            jobs.add(JobUtils.generateWoodcuttingJob(c.getSkillController().getLevel(8),
//                    8,Float.parseFloat(args[0])));
//        }
//        System.out.println("Score tested: " + c.getContext().getPlayerSaveData().getJobScore());
//        System.out.println("Logs: " + jobs.stream().filter(job -> job.getTargetId() == 1511).count());
//        System.out.println("Oak Logs: " + jobs.stream().filter(job -> job.getTargetId() == 1521).count());
//        System.out.println("Willow Logs: " + jobs.stream().filter(job -> job.getTargetId() == 1519).count());
//        System.out.println("Difficulty 0: " + jobs.stream().filter(job -> job.getDifficulty() == 0).count());
//        System.out.println("Difficulty 1: " + jobs.stream().filter(job -> job.getDifficulty() == 1).count());
//        System.out.println("Difficulty 2: " + jobs.stream().filter(job -> job.getDifficulty() == 2).count());
//        System.out.println("Difficulty 3: " + jobs.stream().filter(job -> job.getDifficulty() == 3).count());
//        System.out.println("Difficulty 4: " + jobs.stream().filter(job -> job.getDifficulty() == 4).count());
//        JobUtils.generateWoodcuttingJob(c.getSkillController().getLevel(8),8,c.getContext().getPlayerSaveData().getJobScore());
//        AdjustableInteger index = new AdjustableInteger(0);
//
//        FixedScheduledEventController.getInstance().startEvent(new FixedScheduleEvent(Duration.ofSeconds(4).toMillis(),"anim") {
//            @Override
//            public void execute() {
//                c.sendMessage("Performing anim: " + anims[index.value()]);
//                c.startAnimation(anims[index.value()]);
//                index.increment();
//            }
//        });

//        c.sendUI(new JourneySelectionUI(c));

//        c.getSkillController().getSailing2().assignShipToSlot(100,0);
//        c.getSkillController().getSailing2().assignShipToSlot(2,1);
//        c.getSkillController().getSailing2().assignShipToSlot(6,2);
//        c.getSkillController().getSailing2().generateDailyVoyages();
//        c.sendUI(new PortUI(c));






//        int slot = Integer.parseInt(args[0]);
//        if (args.length > 1) {
//            int id = Integer.parseInt(args[1]);
//            c.getSkillController().getSailing2().assignShipToSlot(id,slot);
//        } else {
//            getShip(c,slot);
//        }
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
