package ethos.runehub.skill;

import ethos.model.players.Player;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.entity.item.ItemInteraction;
import ethos.runehub.entity.item.ItemInteractionLoader;
import ethos.runehub.skill.artisan.fletching.*;
import ethos.runehub.skill.artisan.fletching.bows.UnstrungBow;
import ethos.runehub.skill.artisan.fletching.bows.UnstrungBowCache;
import ethos.runehub.skill.artisan.fletching.bows.UnstrungBowDAO;
import ethos.runehub.skill.combat.prayer.remains.*;
import ethos.runehub.skill.node.impl.support.PickpocketNode;
import ethos.runehub.skill.support.firemaking.kindling.*;
import ethos.runehub.skill.support.thieving.PickpocketDAO;
import ethos.util.Misc;
import org.runehub.api.util.IDManager;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class SkillUtils {

    public static int getRuneSource(Player player, int runeId) {
        return -1;
    }

    public static int hasRune(Player player, int runeId, int amount) {
        boolean[] waterChecks = {player.getItems().isWearingItem(1383) //water staff
                , player.getItems().isWearingItem(1395) //water bstaff
                , player.getItems().isWearingItem(1403) //mystic water staff
                , player.getItems().isWearingItem(11787) //steam bstaff
                , player.getItems().isWearingItem(11789) //steam mstaff
                , player.getItems().isWearingItem(12795) //steam bstaff or
                , player.getItems().isWearingItem(12796) //steam mstaff or
                , player.getItems().isWearingItem(6562) //mud bstaff
                , player.getItems().isWearingItem(6563) //mud mstaff
                , player.getItems().isWearingItem(6726) //mud bstaff
                , player.getItems().isWearingItem(6728) //mud mstaff
                , player.getItems().isWearingItem(20730) //mist bstaff
                , player.getItems().isWearingItem(20733) //mist mstaff
                , player.getItems().playerHasItem(4694, amount) //steam rune
                , player.getItems().playerHasItem(4698, amount) //mud rune
                , player.getItems().playerHasItem(4695, amount) //mist rune
                , player.getItems().playerHasItem(555, amount) //steam rune
        };
        switch (runeId) {
            case 555:
                for (int i = 0; i < waterChecks.length; i++) {
                    if (waterChecks[i]) {
                        switch (i) {
                            case 0:
                                return 1383;
                            case 1:
                                return 1395;
                            case 2:
                                return 1403;
                            case 3:
                                return 11787;
                        }
                    }
                }
//                        return (
//                                player.getItems().isWearingItem(1383) //water staff
//                                        || player.getItems().isWearingItem(1395) //water bstaff
//                                        || player.getItems().isWearingItem(1403) //mystic water staff
//                                        || player.getItems().isWearingItem(11787) //steam bstaff
//                                        || player.getItems().isWearingItem(11789) //steam mstaff
//                                        || player.getItems().isWearingItem(12795) //steam bstaff or
//                                        || player.getItems().isWearingItem(12796) //steam mstaff or
//                                        || player.getItems().isWearingItem(6562) //mud bstaff
//                                        || player.getItems().isWearingItem(6563) //mud mstaff
//                                        || player.getItems().isWearingItem(6726) //mud bstaff
//                                        || player.getItems().isWearingItem(6728) //mud mstaff
//                                        || player.getItems().isWearingItem(20730) //mist bstaff
//                                        || player.getItems().isWearingItem(20733) //mist mstaff
//                                        || player.getItems().playerHasItem(4694, amount) //steam rune
//                                        || player.getItems().playerHasItem(4698, amount) //mud rune
//                                        || player.getItems().playerHasItem(4695, amount) //mist rune
//                                        || player.getItems().playerHasItem(555, amount) //steam rune
//                        );
        }
        return -1;
    }

    public static String getSkillName(int skillId) {
        if (Arrays.stream(SkillDictionary.Skill.values()).anyMatch(skill -> skill.getId() == skillId)) {
            if (skillId == SkillDictionary.Skill.FARMING.getId()) {
                return "Foraging";
            }
            return Misc.capitalize(SkillDictionary.getSkillNameFromId(skillId).toLowerCase());
        } else if (skillId == 23) {
            return "Sailing";
        }
        return "N/A";
    }

    private static int getLow(int startingLevel, double startingChance) {
        for (int low = 0; low < 256; low++) {
            double chance = Math.floor(low * (99 - startingLevel) / 98.0) / 256;
            if (chance == startingChance) {
                return low;
            }
        }
        return 1;
    }

    private static int getHigh(int startingLevel, double startingChance, int low) {
        double brOne = Math.floor(low * (99 - startingLevel) / 98.0);
        for (int high = 0; high < 1000; high++) {
            double brTwo = Math.floor(high * (startingLevel - 1) / 98.0);
            double top = 1 + brOne + brTwo;
            double value = top / 256;
            if (value == startingChance) {
                return high;
            }
        }
        return 1;
    }

    private static double getActionSuccessChance(int min, int max, int pLvl) {
        double brOne = Math.floor(min * (99 - pLvl) / 98.0);
        double brTwo = Math.floor(max * (pLvl - 1) / 98.0);
        double top = 1 + brOne + brTwo;
        double value = top / 256;
        return value;
    }

    private static void loadRemainsDB() {
        RemainsDAO.getInstance().create(new Remains(526, 5)); //bones
        RemainsDAO.getInstance().create(new Remains(528, 5)); //burnt bones
        RemainsDAO.getInstance().create(new Remains(2859, 5)); //wolf bones
        RemainsDAO.getInstance().create(new Remains(3179, 5)); //monkey bones
        RemainsDAO.getInstance().create(new Remains(3180, 5)); //monkey bones
        RemainsDAO.getInstance().create(new Remains(3183, 5)); //monkey bones
        RemainsDAO.getInstance().create(new Remains(530, 6)); //bat bones
        RemainsDAO.getInstance().create(new Remains(532, 15)); //big bones
        RemainsDAO.getInstance().create(new Remains(3125, 15)); //jogre bones
        RemainsDAO.getInstance().create(new Remains(4812, 23)); //zogre bones
        RemainsDAO.getInstance().create(new Remains(3123, 25)); //shaikahan bones
        RemainsDAO.getInstance().create(new Remains(534, 30)); //babydragon bones
        RemainsDAO.getInstance().create(new Remains(536, 72)); //dragon bones
        RemainsDAO.getInstance().create(new Remains(6812, 72)); //wyvern bones
        RemainsDAO.getInstance().create(new Remains(4830, 84)); //fayrg bones
        RemainsDAO.getInstance().create(new Remains(11943, 85)); //lava dragon bones
        RemainsDAO.getInstance().create(new Remains(4832, 96)); //raurg bones
        RemainsDAO.getInstance().create(new Remains(6729, 125)); //dagannoth bones
        RemainsDAO.getInstance().create(new Remains(4834, 140)); //ourg bones
        RemainsDAO.getInstance().create(new Remains(10891, 150)); //superior dragon bones
    }

    private static void loadTinderboxOnKindlingInteractions(int usedWithId) {
        final long uuid = IDManager.getUUID();
        final long reactionUuid = IDManager.getUUID();
        final Kindling kindling = KindlingCache.getInstance().read(usedWithId);
        ItemInteraction interaction = new ItemInteraction(
                uuid,
                590,
                usedWithId,
                SkillDictionary.Skill.FIREMAKING.getId(),
                reactionUuid,
                ItemInteraction.ITEM_INTERACTION
        );

        KindlingItemReaction reaction = new KindlingItemReaction(
                reactionUuid,
                15,
                kindling.getLevelRequirement(),
                65,
                kindling.getFirePropId(),
                0,
                -1,
                kindling.getBaseXp(),
                false,
                true,
                256
        );

        KindlingItemReactionLoader.getInstance().createAndPush(reactionUuid, reaction);

        ItemInteractionLoader.getInstance().createAndPush(uuid, interaction);
    }

    private static void loadKindlingOnTinderboxInteractions(int usedId) {
        final long uuid = IDManager.getUUID();
        final long reactionUuid = IDManager.getUUID();
        final Kindling kindling = KindlingCache.getInstance().read(usedId);
        ItemInteraction interaction = new ItemInteraction(
                uuid,
                usedId,
                590,
                SkillDictionary.Skill.FIREMAKING.getId(),
                reactionUuid,
                ItemInteraction.ITEM_INTERACTION
        );

        KindlingItemReaction reaction = new KindlingItemReaction(
                reactionUuid,
                16,
                kindling.getLevelRequirement(),
                65,
                kindling.getFirePropId(),
                0,
                -1,
                kindling.getBaseXp(),
                false,
                true,
                256
        );

        KindlingItemReactionLoader.getInstance().createAndPush(reactionUuid, reaction);

        ItemInteractionLoader.getInstance().createAndPush(uuid, interaction);
    }

    private static void loadKindlingDB() {
        KindlingDAO.getInstance().create(new Kindling(
                1511,
                5249,
                40,
                false,
                1
        ));
        KindlingDAO.getInstance().create(new Kindling(
                2862,
                5249,
                40,
                false,
                1
        ));
        KindlingDAO.getInstance().create(new Kindling(
                1521,
                5249,
                60,
                false,
                15
        ));
        KindlingDAO.getInstance().create(new Kindling(
                1519,
                5249,
                40,
                false,
                90
        ));
        KindlingDAO.getInstance().create(new Kindling(
                6333,
                5249,
                105,
                true,
                35
        ));
        KindlingDAO.getInstance().create(new Kindling(
                10810,
                5249,
                125,
                false,
                42
        ));
        KindlingDAO.getInstance().create(new Kindling(
                1517,
                5249,
                135,
                false,
                45
        ));
        KindlingDAO.getInstance().create(new Kindling(
                6332,
                5249,
                158,
                true,
                50
        ));
        KindlingDAO.getInstance().create(new Kindling(
                1515,
                5249,
                203,
                false,
                60
        ));
        KindlingDAO.getInstance().create(new Kindling(
                1513,
                5249,
                304,
                true,
                75
        ));
        KindlingDAO.getInstance().create(new Kindling(
                19669,
                5249,
                350,
                true,
                90
        ));
    }

    private static void loadBoneOnAltarInteractions(int usedId, int usedWithId) {
        final long uuid = IDManager.getUUID();
        final long reactionUuid = IDManager.getUUID();

        ItemInteraction interaction = new ItemInteraction(
                uuid,
                usedId,
                usedWithId,
                SkillDictionary.Skill.PRAYER.getId(),
                reactionUuid,
                ItemInteraction.OBJECT_INTERACTION
        );

        RemainsItemReaction reaction = new RemainsItemReaction(
                reactionUuid,
                14, 1, 256, -1, 0, -1,
                RemainsCache.getInstance().read(usedId).getBaseXp(),
                true, false, 256
        );

        RemainsItemReactionLoader.getInstance().createAndPush(reactionUuid, reaction);

        ItemInteractionLoader.getInstance().createAndPush(uuid, interaction);
    }

    private static void loadPickpocketDB() {
        //Man/Woman
        PickpocketDAO.getInstance().create(
                new PickpocketNode(6988, 1, 8, SkillDictionary.Skill.THIEVING.getId(), 1, 1, 5, 180, 240, false, -180961708398394499L, false)
        );
        PickpocketDAO.getInstance().create(
                new PickpocketNode(3265, 1, 8, SkillDictionary.Skill.THIEVING.getId(), 1, 1, 5, 180, 240, false, -180961708398394499L, false)
        );
        PickpocketDAO.getInstance().create(
                new PickpocketNode(6992, 1, 8, SkillDictionary.Skill.THIEVING.getId(), 1, 1, 5, 180, 240, false, -180961708398394499L, false)
        );
        PickpocketDAO.getInstance().create(
                new PickpocketNode(3015, 1, 8, SkillDictionary.Skill.THIEVING.getId(), 1, 1, 5, 180, 240, false, -180961708398394499L, false)
        );
        PickpocketDAO.getInstance().create(
                new PickpocketNode(3268, 1, 8, SkillDictionary.Skill.THIEVING.getId(), 1, 1, 5, 180, 240, false, -180961708398394499L, false)
        );
        PickpocketDAO.getInstance().create(
                new PickpocketNode(6989, 1, 8, SkillDictionary.Skill.THIEVING.getId(), 1, 1, 5, 180, 240, false, -180961708398394499L, false)
        );
        PickpocketDAO.getInstance().create(
                new PickpocketNode(6987, 1, 8, SkillDictionary.Skill.THIEVING.getId(), 1, 1, 5, 180, 240, false, -180961708398394499L, false)
        );
        //Farmers
        PickpocketDAO.getInstance().create(
                new PickpocketNode(3086, 10, 15, SkillDictionary.Skill.THIEVING.getId(), 1, 1, 5, 150, 240, false, 1044711505392649918L, false)
        );
    }

    private static void loadFletchableDB() {
        FletchableDAO.getInstance().create( //arrow shaft
                new Fletchable(
                        52,
                        new GameItem[]{new GameItem(1511, 1)},
                        false,
                        5,
                        1,
                        15,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //jav shaft
                new Fletchable(
                        19584,
                        new GameItem[]{new GameItem(1511, 1)},
                        true,
                        5,
                        3,
                        15,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //shortbow
                new Fletchable(
                        50,
                        new GameItem[]{new GameItem(1511, 1)},
                        false,
                        5,
                        5,
                        1,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //longbow
                new Fletchable(
                        48,
                        new GameItem[]{new GameItem(1511, 1)},
                        false,
                        10,
                        10,
                        1,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //crossbow stock
                new Fletchable(
                        9440,
                        new GameItem[]{new GameItem(1511, 1)},
                        false,
                        6,
                        9,
                        1,
                        1248
                )
        );

        FletchableDAO.getInstance().create( //oak sb
                new Fletchable(
                        54,
                        new GameItem[]{new GameItem(1521, 1)},
                        false,
                        17,
                        20,
                        1,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //oak lb
                new Fletchable(
                        56,
                        new GameItem[]{new GameItem(1521, 1)},
                        false,
                        25,
                        25,
                        1,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //oak stock
                new Fletchable(
                        9442,
                        new GameItem[]{new GameItem(1521, 1)},
                        false,
                        16,
                        24,
                        1,
                        1248
                )
        );

        FletchableDAO.getInstance().create( //willow sb
                new Fletchable(
                        60,
                        new GameItem[]{new GameItem(1519, 1)},
                        false,
                        34,
                        35,
                        1,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //willow lb
                new Fletchable(
                        58,
                        new GameItem[]{new GameItem(1519, 1)},
                        false,
                        42,
                        40,
                        1,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //willow stock
                new Fletchable(
                        9444,
                        new GameItem[]{new GameItem(1519, 1)},
                        false,
                        22,
                        39,
                        1,
                        1248
                )
        );

        FletchableDAO.getInstance().create( //maple sb
                new Fletchable(
                        64,
                        new GameItem[]{new GameItem(1517, 1)},
                        false,
                        50,
                        50,
                        1,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //maple lb
                new Fletchable(
                        62,
                        new GameItem[]{new GameItem(1517, 1)},
                        false,
                        59,
                        55,
                        1,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //maple stock
                new Fletchable(
                        9448,
                        new GameItem[]{new GameItem(1517, 1)},
                        false,
                        32,
                        54,
                        1,
                        1248
                )
        );

        FletchableDAO.getInstance().create( //yew sb
                new Fletchable(
                        68,
                        new GameItem[]{new GameItem(1515, 1)},
                        false,
                        68,
                        65,
                        1,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //yew lb
                new Fletchable(
                        66,
                        new GameItem[]{new GameItem(1515, 1)},
                        false,
                        75,
                        70,
                        1,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //yew stock
                new Fletchable(
                        9452,
                        new GameItem[]{new GameItem(1515, 1)},
                        false,
                        50,
                        69,
                        1,
                        1248
                )
        );

        FletchableDAO.getInstance().create( //magic sb
                new Fletchable(
                        72,
                        new GameItem[]{new GameItem(1513, 1)},
                        true,
                        84,
                        80,
                        1,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //magic lb
                new Fletchable(
                        70,
                        new GameItem[]{new GameItem(1513, 1)},
                        true,
                        92,
                        85,
                        1,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //magic stock
                new Fletchable(
                        21952,
                        new GameItem[]{new GameItem(1513, 1)},
                        true,
                        70,
                        78,
                        1,
                        1248
                )
        );

        FletchableDAO.getInstance().create( //teak stock
                new Fletchable(
                        9446,
                        new GameItem[]{new GameItem(6333, 1)},
                        true,
                        27,
                        46,
                        1,
                        1248
                )
        );
        FletchableDAO.getInstance().create( //mahogany stock
                new Fletchable(
                        9450,
                        new GameItem[]{new GameItem(6332, 1)},
                        true,
                        41,
                        61,
                        1,
                        1248
                )
        );

        FletchableDAO.getInstance().create( //sb
                new Fletchable(
                        841,
                        new GameItem[]{new GameItem(50, 1),new GameItem(1777,1)},
                        false,
                        5,
                        5,
                        1,
                        6678
                )
        );
        FletchableDAO.getInstance().create( //lb
                new Fletchable(
                        839,
                        new GameItem[]{new GameItem(48, 1),new GameItem(1777,1)},
                        false,
                        10,
                        10,
                        1,
                        6684
                )
        );
        FletchableDAO.getInstance().create( //oak sb
                new Fletchable(
                        843,
                        new GameItem[]{new GameItem(54, 1),new GameItem(1777,1)},
                        false,
                        17,
                        20,
                        1,
                        6679
                )
        );
        FletchableDAO.getInstance().create( //oak lb
                new Fletchable(
                        845,
                        new GameItem[]{new GameItem(56, 1),new GameItem(1777,1)},
                        false,
                        25,
                        25,
                        1,
                        6685
                )
        );
        FletchableDAO.getInstance().create( //willow sb
                new Fletchable(
                        849,
                        new GameItem[]{new GameItem(60, 1),new GameItem(1777,1)},
                        false,
                        34,
                        35,
                        1,
                        6680
                )
        );
        FletchableDAO.getInstance().create( //willow lb
                new Fletchable(
                        847,
                        new GameItem[]{new GameItem(58, 1),new GameItem(1777,1)},
                        false,
                        42,
                        40,
                        1,
                        6686
                )
        );

        FletchableDAO.getInstance().create( //maple sb
                new Fletchable(
                        853,
                        new GameItem[]{new GameItem(64, 1),new GameItem(1777,1)},
                        false,
                        50,
                        50,
                        1,
                        6681
                )
        );
        FletchableDAO.getInstance().create( //maple lb
                new Fletchable(
                        851,
                        new GameItem[]{new GameItem(62, 1),new GameItem(1777,1)},
                        false,
                        59,
                        55,
                        1,
                        6687
                )
        );

        FletchableDAO.getInstance().create( //yew sb
                new Fletchable(
                        857,
                        new GameItem[]{new GameItem(68, 1),new GameItem(1777,1)},
                        false,
                        68,
                        65,
                        1,
                        6682
                )
        );
        FletchableDAO.getInstance().create( //yew lb
                new Fletchable(
                        855,
                        new GameItem[]{new GameItem(66, 1),new GameItem(1777,1)},
                        false,
                        75,
                        70,
                        1,
                        6688
                )
        );

        FletchableDAO.getInstance().create( //magic sb
                new Fletchable(
                        861,
                        new GameItem[]{new GameItem(72, 1),new GameItem(1777,1)},
                        true,
                        84,
                        80,
                        1,
                        6683
                )
        );
        FletchableDAO.getInstance().create( //magic lb
                new Fletchable(
                        859,
                        new GameItem[]{new GameItem(70, 1),new GameItem(1777,1)},
                        true,
                        92,
                        85,
                        1,
                        6689
                )
        );

//        FletchableDAO.getInstance().create( //bronze cb  u
//                new Fletchable(
//                        859,
//                        new GameItem[]{new GameItem(70, 1),new GameItem(1777,1)},
//                        true,
//                        92,
//                        85,
//                        1,
//                        6689
//                )
//        );
    }

    private static void loadKnifeOnLogInteractions(int itemId) {
        final long uuid = IDManager.getUUID();
        final long reactionUuid = IDManager.getUUID();
//        final Fletchable item = FletchableCache.getInstance().read(itemId);
        ItemInteraction interaction = new ItemInteraction(
                uuid,
                946,
                itemId,
                SkillDictionary.Skill.FLETCHING.getId(),
                reactionUuid,
                ItemInteraction.ITEM_INTERACTION
        );

        FletchingItemReaction reaction = new FletchingItemReaction(
                reactionUuid,
                17,
                1,
                256,
                -1,
                0,
                -1,
                0,
                false,
                true,
                256
        );

        FletchingItemReactionCache.getInstance().createAndPush(reactionUuid, reaction);

        ItemInteractionLoader.getInstance().createAndPush(uuid, interaction);
    }

    private static void loadLogOnKnifeInteractions(int itemId) {
        final long uuid = IDManager.getUUID();
        final long reactionUuid = IDManager.getUUID();
//        final Fletchable item = FletchableCache.getInstance().read(itemId);
        ItemInteraction interaction = new ItemInteraction(
                uuid,
                itemId,
                946,
                SkillDictionary.Skill.FLETCHING.getId(),
                reactionUuid,
                ItemInteraction.ITEM_INTERACTION
        );

        FletchingItemReaction reaction = new FletchingItemReaction(
                reactionUuid,
                17,
                1,
                256,
                -1,
                0,
                -1,
                0,
                false,
                true,
                256
        );

        FletchingItemReactionCache.getInstance().createAndPush(reactionUuid, reaction);

        ItemInteractionLoader.getInstance().createAndPush(uuid, interaction);
    }

    private static void stringOnBow(int itemId) {
        final long uuid = IDManager.getUUID();
        final long reactionUuid = IDManager.getUUID();
        ItemInteraction interaction = new ItemInteraction(
                uuid,
                1777,
                itemId,
                SkillDictionary.Skill.FLETCHING.getId(),
                reactionUuid,
                ItemInteraction.ITEM_INTERACTION
        );

        FletchingItemReaction reaction = new FletchingItemReaction(
                reactionUuid,
                18,
                1,
                256,
                -1,
                0,
                -1,
                0,
                false,
                true,
                256
        );

        FletchingItemReactionCache.getInstance().createAndPush(reactionUuid, reaction);

        ItemInteractionLoader.getInstance().createAndPush(uuid, interaction);
    }

    private static void bowOnString(int itemId) {
        final long uuid = IDManager.getUUID();
        final long reactionUuid = IDManager.getUUID();
        ItemInteraction interaction = new ItemInteraction(
                uuid,
                itemId,
                1777,
                SkillDictionary.Skill.FLETCHING.getId(),
                reactionUuid,
                ItemInteraction.ITEM_INTERACTION
        );

        FletchingItemReaction reaction = new FletchingItemReaction(
                reactionUuid,
                19,
                1,
                256,
                -1,
                0,
                -1,
                0,
                false,
                true,
                256
        );

        FletchingItemReactionCache.getInstance().createAndPush(reactionUuid, reaction);

        ItemInteractionLoader.getInstance().createAndPush(uuid, interaction);
    }

    public static void main(String[] args) {
        int startingLevel = 1;
        double bonus = 1.0;
        int min = (int) (65 * bonus);
        int max = (int) (256 * bonus);
        for (int i = startingLevel; i < 99; i++) {
//            System.out.println(getActionSuccessChance(2,130,i));
            System.out.println(getActionSuccessChance(min, max, i));
        }
//        stringOnBow(48);
//        stringOnBow(50);
//
//        stringOnBow(54);
//        stringOnBow(56);
//
//        stringOnBow(58);
//        stringOnBow(60);
//
//        stringOnBow(62);
//        stringOnBow(64);
//
//        stringOnBow(66);
//        stringOnBow(68);
//
//        stringOnBow(70);
//        stringOnBow(72);
//
//        bowOnString(48);
//        bowOnString(50);
//
//        bowOnString(54);
//        bowOnString(56);
//
//        bowOnString(58);
//        bowOnString(60);
//
//        bowOnString(62);
//        bowOnString(64);
//
//        bowOnString(66);
//        bowOnString(68);
//
//        bowOnString(70);
//        bowOnString(72);
        loadFletchableDB();
//loadKindlingDB();
//        KindlingDAO.getInstance().getAllEntries().forEach(kindling -> loadKindlingOnTinderboxInteractions(kindling.getItemId()));
//        FixedScheduleEvent hourlyEvent = new SkillOfTheHourFixedScheduleEvent();
//        FixedScheduleEvent seasonEvent = new PlayPassStartSeasonEvent();
//        FixedScheduleEvent xmas = new StartChristmasFixedScheduleEvent();
//        FixedScheduleEvent xmasEnd = new EndChristmasFixedScheduleEvent();
//        ZonedDateTime d1 = ZonedDateTime.now(ZoneId.of("UTC")).withMonth(12).withDayOfMonth(25).withHour(0).withMinute(0).withSecond(0);
//        ZonedDateTime d2 = ZonedDateTime.of(LocalDate.of(ZonedDateTime.now(ZoneId.of("UTC")).getYear(), 12, 25), LocalTime.of(0, 0), ZoneId.of("UTC"));
//        long msUntilXmas = TimeUtils.getMSUntilDate(ZonedDateTime.now(ZoneId.of("UTC")).withMonth(12).withDayOfMonth(25).withHour(0).withMinute(0).withSecond(0));
//        Duration duration1 = TimeUtils.getDurationBetween(ZonedDateTime.now(ZoneId.of("UTC")).withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0), d1);
//        Duration duration2 = TimeUtils.getDurationBetween(ZonedDateTime.now(ZoneId.of("UTC")).withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0), ZonedDateTime.now(ZoneId.of("UTC")));
//        System.out.println("XMAS: " + TimeUtils.getZDTString(ZonedDateTime.ofInstant(Instant.ofEpochMilli(msUntilXmas), ZoneId.of("UTC"))));
//        System.out.println(TimeUtils.getDurationString(msUntilXmas - ZonedDateTime.now(ZoneId.of("UTC")).toInstant().toEpochMilli()));
//        System.out.println("Duration: " + TimeUtils.getDurationString(duration1.minus(duration2)));
//        System.out.println("First: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getFirstRun(hourlyEvent)));
//        System.out.println("Next: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getNextCycle(hourlyEvent)));
//        System.out.println(" ");
//        System.out.println("First: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getFirstRun(seasonEvent)));
//        System.out.println("Next: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getNextCycle(seasonEvent)));
//        System.out.println(" ");
//        System.out.println("First: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getFirstRun(xmas)));
//        System.out.println("Next: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getNextCycle(xmas)));
//        System.out.println(" ");
//        System.out.println("First: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getFirstRun(xmasEnd)));
//        System.out.println("Next: " + TimeUtils.getZDTString(FixedScheduledEventController.getInstance().getNextCycle(xmasEnd)));
////            int startLevel = 1;
////            int endLevel = 99;
////
////            double startChance = 0.5;
////            double endChance = 1.0;
////
//////        System.out.println("Low: " + getLow(startLevel, startChance));
//////        System.out.println("High: " + getHigh(endLevel, endChance, getLow(startLevel, startChance)));
////
////            int increment = 16;
////            String commandFirstHalf = "fill ~ ~ ~ ~" + increment + " ~" + increment + " ~" + increment + " air replace";
////            String firstCommand = "fill ~ ~ ~ ~15 ~15 ~15 air replace";
//////        String command = "fill ~" + increment + " ~" + increment + " ~" + increment + " ~15 ~15 ~15 air replace";
////
////            int perimeterLength = 21;
////            int perimeterWidth = 21;
////            int worldHeightChunks = 20;
////            int relativeX = 1;
////            int relativeY = 1;
////            int relativeZ = 1;
////            System.out.println("tp ~ -64 ~");
////            z:
////            for (int z = 0; z < perimeterWidth; z++) {
////                x:
////                for (int x = 0; x < perimeterLength; x++) {
////                    y:
////                    for (int y = 0; y < worldHeightChunks; y++) {
////                        String command = "fill ~ ~ ~ " + "~" + (16 * relativeX) + " ~" + (16 * relativeY) + " ~" + (16 * relativeZ) + " air replace";
////                        System.out.println(command);
////                        relativeY++;
//////                    if(y % 2 == 0 && y != 0) {
//////                        System.out.println("tp ~ ~" + (16 * y) + " ~");
//////                        relativeY = 1;
//////                    }
////
////                    }
////                    relativeY = 1;
////                    relativeX++;
//////                System.out.println("tp ~16 -63 ~");
////                }
////                relativeZ++;
////                relativeX = 1;
//////            System.out.println("tp ~-336 ~ ~16");
////            }
////            System.out.println("tp ~ 100 ~");
//////        for (int i = 0; i < perimeterSize; i++) {
//////            for (int verticalChunk = 0; verticalChunk < worldHeightChunks; verticalChunk++) {
//////                String command = "fill ~" + 0 + " ~" + 0 + " ~" + 0 + " ~" + (relativeX + 16) + " ~" + (relativeY + 16) + " ~" + (relativeZ + 16) + " air replace";
//////                relativeX = 0;
//////                System.out.println(command);
//////                System.out.println("tp ~" + relativeX + " ~" + 16 + " ~" + relativeZ);
//////            }
//////            relativeX = increment;
//////            System.out.println("tp ~" + 16 + " -64" + " ~" + relativeZ);
//////        }
//////            System.out.println("tp ~" + relativeX + " -64 ~" + relativeZ);
//////            for (int xChunk = 0; xChunk < perimeterSize; xChunk++) {
//////                relativeY = 0;
//////                relativeZ = 0;
////////                System.out.println("tp ~" + relativeX + " -64 ~");
//////                for (int verticalChunk = 0; verticalChunk < worldHeightChunks; verticalChunk++) {
//////                    String command = "fill ~" + 0 + " ~" + 0 + " ~" + 0 + " ~" + (relativeX + 16) + " ~" + (relativeY + 16) + " ~" + (relativeZ + 16) + " air replace";
//////                    System.out.println(command);
//////                    System.out.println("tp ~" + relativeX + " ~" + relativeY + " ~" + relativeZ);
//////                }
//////                relativeX = increment;
//////            }
//////            relativeX = -21 * 16;
//////            relativeZ = increment;
//////            System.out.println("\n");
//////        }
////
//////        System.out.println(firstCommand);
//////        for (int horizontalChunk = 0; horizontalChunk < perimeterSize; horizontalChunk++) {
//////            for (int verticalChunk = 0; verticalChunk < worldHeightChunks; verticalChunk++) {
//////                if(verticalChunk % 4 == 0) {
//////                    System.out.println("tp ~" + relativeX + " ~" + relativeY + " ~" + relativeZ);
//////                    relativeY = 0;
//////                }
//////                String command = "fill ~" + relativeX + " ~" + relativeY + " ~" + relativeZ + " ~" + (relativeX + 16) + " ~" + (relativeY + 16) + " ~" + (relativeZ+16) + " air replace";
//////                relativeY += increment;
//////                System.out.println(command);
//////            }
//////            if(horizontalChunk % 4 == 0) {
////////                System.out.println("#HORIZONTAL");
//////                relativeX = 0;
////////                relativeZ = 0;
//////            }
//////            relativeX += increment;
//////            relativeZ += increment;
//////            relativeY = -64;
//////            System.out.println("\n");
//////        }

    }
}
