package ethos.runehub.skill.artisan.crafting;

import ethos.runehub.entity.item.GameItem;
import ethos.runehub.entity.item.ItemInteraction;
import ethos.runehub.entity.item.ItemInteractionLoader;
import ethos.runehub.skill.artisan.crafting.armor.leather.Leather;
import ethos.runehub.skill.artisan.crafting.armor.leather.LeatherDAO;
import org.runehub.api.util.IDManager;
import org.runehub.api.util.SkillDictionary;

public class GemInteractionEditor {

    public static void main(String[] args) {
        create(1755, 1625, 1, 122, 1609, 1, 1633, 15, 256,5);//opal
        create(1755, 1627, 13, 145, 1611, 1, 1633, 20, 256,5);//jade
        create(1755, 1629, 16, 150, 1613, 1, 1633, 25, 256,5);//topaz
        create(1755, 1623, 20, 256, 1607, 1, 1633, 50, 256,5);//sapphire
        create(1755, 1621, 27, 256, 1605, 1, 1633, 68, 256,5);//emerald
        create(1755, 1619, 34, 256, 1603, 1, 1633, 85, 256,5);//ruby
        create(1755, 1617, 43, 256, 1601, 1, 1633, 108, 256,5);//diamond
        create(1755, 1631, 55, 256, 1615, 1, 1633, 138, 256,5);//dragonstone
        create(1755, 6571, 67, 256, 6573, 1, 1633, 168, 256,5);//onyx
        create(1755, 19496, 89, 256, 19493, 1, 1633, 200, 256,5);//zenyte

        create(1625, 1755, 1, 122, 1609, 1, 1633, 15, 256,5);//opal
        create(1627, 1755, 13, 145, 1611, 1, 1633, 20, 256,5);//jade
        create(1629, 1755, 16, 150, 1613, 1, 1633, 25, 256,5);//topaz
        create(1623, 1755, 20, 256, 1607, 1, 1633, 50, 256,5);//sapphire
        create(1621, 1755, 27, 256, 1605, 1, 1633, 68, 256,5);//emerald
        create(1619, 1755, 34, 256, 1603, 1, 1633, 85, 256,5);//ruby
        create(1617, 1755, 43, 256, 1601, 1, 1633, 108, 256,5);//diamond
        create(1631, 1755, 55, 256, 1615, 1, 1633, 138, 256,5);//dragonstone
        create(6571, 1755, 67, 256, 6573, 1, 1633, 168, 256,5);//onyx
        create(19496, 1755, 89, 256, 19493, 1, 1633, 200, 256,5);//zenyte


        LeatherDAO.getInstance().create(new Leather(1059,1,14,false,new GameItem[]{new GameItem(1741,1),new GameItem(1734,1)}));//glove
        LeatherDAO.getInstance().create(new Leather(1061,7,16,false,new GameItem[]{new GameItem(1741,1),new GameItem(1734,1)}));//boot
        LeatherDAO.getInstance().create(new Leather(1167,9,19,false,new GameItem[]{new GameItem(1741,1),new GameItem(1734,1)}));//cowl
        LeatherDAO.getInstance().create(new Leather(1063,11,22,false,new GameItem[]{new GameItem(1741,1),new GameItem(1734,1)}));//vamb
        LeatherDAO.getInstance().create(new Leather(1129,14,25,false,new GameItem[]{new GameItem(1741,1),new GameItem(1734,1)}));//body
        LeatherDAO.getInstance().create(new Leather(1095,18,27,false,new GameItem[]{new GameItem(1741,1),new GameItem(1734,1)}));//chaps
        LeatherDAO.getInstance().create(new Leather(10077,32,6,true,new GameItem[]{new GameItem(1741,1),new GameItem(10113,1)}));//spiky
        LeatherDAO.getInstance().create(new Leather(1169,38,37,true,new GameItem[]{new GameItem(1741,1),new GameItem(1734,1)}));//coif

        LeatherDAO.getInstance().create(new Leather(1131,28,35,false,new GameItem[]{new GameItem(1743,1),new GameItem(1734,1)}));//hard leather body

        LeatherDAO.getInstance().create(new Leather(1065,57,62,true,new GameItem[]{new GameItem(1745,1),new GameItem(1734,1)}));//green vamb
        LeatherDAO.getInstance().create(new Leather(1099,60,124,true,new GameItem[]{new GameItem(1745,2),new GameItem(1734,1)}));//green chaps
        LeatherDAO.getInstance().create(new Leather(1135,63,186,true,new GameItem[]{new GameItem(1745,3),new GameItem(1734,1)}));//green body

        LeatherDAO.getInstance().create(new Leather(2487,66,70,true,new GameItem[]{new GameItem(2505,1),new GameItem(1734,1)}));//blue vamb
        LeatherDAO.getInstance().create(new Leather(2493,68,140,true,new GameItem[]{new GameItem(2505,2),new GameItem(1734,1)}));//blue chaps
        LeatherDAO.getInstance().create(new Leather(2499,71,210,true,new GameItem[]{new GameItem(2505,3),new GameItem(1734,1)}));//blue body

        LeatherDAO.getInstance().create(new Leather(2489,73,78,true,new GameItem[]{new GameItem(2507,1),new GameItem(1734,1)}));//red vamb
        LeatherDAO.getInstance().create(new Leather(2495,75,156,true,new GameItem[]{new GameItem(2507,2),new GameItem(1734,1)}));//red chaps
        LeatherDAO.getInstance().create(new Leather(2501,77,234,true,new GameItem[]{new GameItem(2507,3),new GameItem(1734,1)}));//red body

        LeatherDAO.getInstance().create(new Leather(2491,79,86,true,new GameItem[]{new GameItem(2509,1),new GameItem(1734,1)}));//black vamb
        LeatherDAO.getInstance().create(new Leather(2497,82,172,true,new GameItem[]{new GameItem(2509,2),new GameItem(1734,1)}));//black chaps
        LeatherDAO.getInstance().create(new Leather(2503,84,258,true,new GameItem[]{new GameItem(2509,3),new GameItem(1734,1)}));//black body

        LeatherDAO.getInstance().create(new Leather(6328,45,30,true,new GameItem[]{new GameItem(6289,6),new GameItem(1734,1)}));//snakeskin boots
        LeatherDAO.getInstance().create(new Leather(6330,47,35,true,new GameItem[]{new GameItem(6289,8),new GameItem(1734,1)}));//snakeskin vamb
        LeatherDAO.getInstance().create(new Leather(6326,48,45,true,new GameItem[]{new GameItem(6289,5),new GameItem(1734,1)}));//snakeskin bandana
        LeatherDAO.getInstance().create(new Leather(6324,51,50,true,new GameItem[]{new GameItem(6289,12),new GameItem(1734,1)}));//snakeskin chaps
        LeatherDAO.getInstance().create(new Leather(6322,53,55,true,new GameItem[]{new GameItem(6289,15),new GameItem(1734,1)}));//snakeskin body

        LeatherDAO.getInstance().create(new Leather(10824,43,32,true,new GameItem[]{new GameItem(10820,1),new GameItem(1734,1)}));//yak chaps
        LeatherDAO.getInstance().create(new Leather(10822,46,32,true,new GameItem[]{new GameItem(10820,2),new GameItem(1734,1)}));//yak body

        create(1733, 1741, 1, 256, -1, 1, -1, 0, 256,6);//leather
        create(1733, 1743, 1, 256, -1, 1, -1, 0, 256,7);//hardleather
        create(1733, 1745, 1, 256, -1, 1, -1, 0, 256,8);//green
        create(1733, 2505, 1, 256, -1, 1, -1, 0, 256,9);//blue
        create(1733, 2507, 1, 256, -1, 1, -1, 0, 256,10);//red
        create(1733, 2509, 1, 256, -1, 1, -1, 0, 256,11);//black
        create(1733, 6289, 1, 256, -1, 1, -1, 0, 256,12);//snakeskin
        create(1733, 10820, 1, 256, -1, 1, -1, 0, 256,13);//yakhide
    }

    private static void create(int usedId, int usedWithId,
                               int levelRequired, int minBurnRoll, int productItemId, int productItemBaseAmount,
                               int failedItemId, int reactionXp, int high, int  aId) {
        final long uuid = IDManager.getUUID();
        final long reactionUuid = IDManager.getUUID();
        createCookOnFireInteraction(uuid, usedId, usedWithId, reactionUuid);
        createCookOnFireReaction(reactionUuid, levelRequired, minBurnRoll, productItemId, productItemBaseAmount, failedItemId, reactionXp, high,aId);
    }

    private static void createCookOnFireInteraction(long uuid, int usedId, int usedWithId, long reactionUuid) {
        ItemInteraction interaction = new ItemInteraction(
                uuid,
                usedId,
                usedWithId,
                SkillDictionary.Skill.CRAFTING.getId(),
                reactionUuid,
                ItemInteraction.ITEM_INTERACTION
        );

        ItemInteractionLoader.getInstance().createAndPush(uuid, interaction);
    }

    private static void createCookOnFireReaction(long uuid, int levelRequired, int minBurnRoll, int productItemId, int productItemBaseAmount,
                                                 int failedItemId, int reactionXp, int high, int aId) {
        CraftingItemReaction reaction = new CraftingItemReaction(
                uuid,
                aId,
                levelRequired,
                minBurnRoll,
                productItemId,
                productItemBaseAmount,
                failedItemId,
                reactionXp,
                true,
                false,
                high
        );

        CraftingItemReactionLoader.getInstance().createAndPush(uuid, reaction);
    }
}
