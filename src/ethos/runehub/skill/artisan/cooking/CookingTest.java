package ethos.runehub.skill.artisan.cooking;

import ethos.runehub.entity.item.ItemInteraction;
import ethos.runehub.entity.item.ItemInteractionDAO;
import ethos.runehub.entity.item.ItemInteractionLoader;
import org.runehub.api.util.IDManager;
import org.runehub.api.util.SkillDictionary;

public class CookingTest {

    public static void main(String[] args) {
        createDataForCookingOnFire(2132, 5249, 1, 118, 2142, 1, 2146, 30,492);//beef
        createDataForCookingOnFire(2136, 5249, 1, 118, 2142, 1, 2146, 30,492);//bear meat
        createDataForCookingOnFire(2134, 5249, 1, 118, 2142, 1, 2146, 30,492);//rat meat
        createDataForCookingOnFire(10816, 5249, 1, 118, 2142, 1, 2146, 40,492);//yak meat
        createDataForCookingOnFire(317, 5249, 1, 118, 315, 1, 7954, 30,492);//shrimp
        createDataForCookingOnFire(2138, 5249, 1, 118, 2140, 1, 2144, 30,492);//chicken
        createDataForCookingOnFire(3226, 5249, 1, 118, 3228, 1, 7229, 30,492);//rabbit
        createDataForCookingOnFire(321, 5249, 1, 118, 323, 1, 7954, 30,492);//anchovies
        createDataForCookingOnFire(327, 5249, 1, 118, 325, 1, 369, 40,492);//sardine
        createDataForCookingOnFire(345, 5249, 5, 108, 347, 1, 357, 50,472);//herring
        createDataForCookingOnFire(353, 5249, 10, 98, 355, 1, 357, 60,452);//mackerel
        createDataForCookingOnFire(335, 5249, 15, 88, 333, 1, 343, 70,432);//trout
        createDataForCookingOnFire(339, 5249, 18, 83, 341, 1, 343, 75,422);//cod <---
        createDataForCookingOnFire(349, 5249, 20, 78, 351, 1, 343, 80,412);//pike
        createDataForCookingOnFire(329, 5249, 25, 68, 331, 1, 343, 90,392);//salmon
        createDataForCookingOnFire(359, 5249, 30, 58, 361, 1, 367, 100,372);//tuna
        createDataForCookingOnFire(377, 5249, 40, 38, 379, 1, 381, 120,332);//lobster
        createDataForCookingOnFire(363, 5249, 43, 33, 365, 1, 367, 130,312);//bass
        createDataForCookingOnFire(371, 5249, 45, 18, 373, 1, 375, 140,292);//swordfish
        createDataForCookingOnFire(383, 5249, 80, 1, 385, 1, 387, 210,202);//shark


        copyFireToRange();





    }

    private static void createDataForCookingOnFire(int usedId, int usedWithId,
                                                   int levelRequired, int minBurnRoll, int productItemId, int productItemBaseAmount,
                                                   int failedItemId, int reactionXp, int high) {
        final long uuid = IDManager.getUUID();
        final long reactionUuid = IDManager.getUUID();
        createCookOnFireInteraction(uuid, usedId, usedWithId, reactionUuid);
        createCookOnFireReaction(reactionUuid, levelRequired, minBurnRoll, productItemId, productItemBaseAmount, failedItemId, reactionXp, high);
    }

    private static void createCookOnFireInteraction(long uuid, int usedId, int usedWithId, long reactionUuid) {
        ItemInteraction interaction = new ItemInteraction(
                uuid,
                usedId,
                usedWithId,
                SkillDictionary.Skill.COOKING.getId(),
                reactionUuid,
                ItemInteraction.OBJECT_INTERACTION
        );

        ItemInteractionLoader.getInstance().createAndPush(uuid, interaction);
    }

    private static void copyFireToRange() {
        ItemInteractionDAO.getInstance().getAllEntries().forEach(interaction -> {
            long uuid = IDManager.getUUID();
            ItemInteractionDAO.getInstance().create(new ItemInteraction(
                    uuid,
                    interaction.getUsedId(),
                    114,
                    interaction.getReactionKey(),
                    interaction.getReactionUuid(),
                    interaction.getInteractionTypeId()
            ));
        });
    }

    private static void createCookOnRangeInteraction(long uuid, int usedId, int usedWithId, long reactionUuid) {
        ItemInteraction interaction = new ItemInteraction(
                uuid,
                usedId,
                usedWithId,
                SkillDictionary.Skill.COOKING.getId(),
                reactionUuid,
                ItemInteraction.OBJECT_INTERACTION
        );

        ItemInteractionLoader.getInstance().createAndPush(uuid, interaction);
    }

    private static void createCookOnFireReaction(long uuid, int levelRequired, int minBurnRoll, int productItemId, int productItemBaseAmount,
                                                 int failedItemId, int reactionXp, int high) {
        CookingItemReaction reaction = new CookingItemReaction(
                uuid,
                1,
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

        CookingItemReactionLoader.getInstance().createAndPush(uuid, reaction);
    }
}