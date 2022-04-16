package ethos.runehub.skill.artisan.herblore;

import ethos.runehub.entity.item.ItemInteraction;
import ethos.runehub.entity.item.ItemInteractionLoader;
import ethos.runehub.skill.artisan.herblore.potion.effect.SkillAdjustmentEffect;
import ethos.runehub.skill.artisan.herblore.potion.SkillAdjustmentEffectDAO;
import org.runehub.api.util.IDManager;
import org.runehub.api.util.SkillDictionary;

import java.util.Scanner;

public class HerbloreTest {

    private static void requestInput() {
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Item ID");
        int usedId = 0;
        int usedWithId = 0;
        int level = 0;
        int productId = 0;
        int xp = 0;
        int low = 0;
        int high = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                usedId = Integer.parseInt(in.nextLine());

                System.out.println("Enter Secondary Item ID");
                usedWithId = Integer.parseInt(in.nextLine());

                System.out.println("Enter Level Requirement");
                level = Integer.parseInt(in.nextLine());

                System.out.println("Enter Product Item ID");
                productId = Integer.parseInt(in.nextLine());

                System.out.println("Enter Action XP");
                xp = Integer.parseInt(in.nextLine());

                System.out.println("Enter Low Roll");
                low = Integer.parseInt(in.nextLine());

                System.out.println("Enter High Roll");
                high = Integer.parseInt(in.nextLine());

                createPotionAction(usedId, usedWithId, level, productId, xp, low, high);
            } catch (NumberFormatException e) {
                System.out.println("Please input an integer.");
            }
        }
    }

    public static void main(String[] args) {
        createUnfinishedPotionAction(227,249,3,91); //guam
        createUnfinishedPotionAction(227,251,5,93); //marrentill
        createUnfinishedPotionAction(227,253,12,95); //tarromin
        createUnfinishedPotionAction(227,255,22,97); //harralander
        createUnfinishedPotionAction(227,257,30,99); //ranarr
        createUnfinishedPotionAction(227,2998,34,3002); //toadflax
        createUnfinishedPotionAction(227,259,45,101); //irit
        createUnfinishedPotionAction(227,261,50,103); //avantoe
        createUnfinishedPotionAction(227,263,55,105); //kwuarm
        createUnfinishedPotionAction(227,3000,63,3004); //snapdragon
        createUnfinishedPotionAction(227,265,66,107); //cadantine
        createUnfinishedPotionAction(227,2481,69,2483); //lantadyme
        createUnfinishedPotionAction(227,267,72,109); //dwarf weed
        createUnfinishedPotionAction(227,269,78,111); //torstol

        createUnfinishedPotionAction(249,227,3,91); //guam
        createUnfinishedPotionAction(251,227,5,93); //marrentill
        createUnfinishedPotionAction(253,227,12,95); //tarromin
        createUnfinishedPotionAction(255,227,22,97); //harralander
        createUnfinishedPotionAction(257,227,30,99); //ranarr
        createUnfinishedPotionAction(2998,227,34,3002); //toadflax
        createUnfinishedPotionAction(259,227,45,101); //irit
        createUnfinishedPotionAction(261,227,50,103); //avantoe
        createUnfinishedPotionAction(263,227,55,105); //kwuarm
        createUnfinishedPotionAction(3000,227,63,3004); //snapdragon
        createUnfinishedPotionAction(265,227,66,107); //cadantine
        createUnfinishedPotionAction(2481,227,69,2483); //lantadyme
        createUnfinishedPotionAction(267,227,72,109); //dwarf weed
        createUnfinishedPotionAction(269,227,78,111); //torstol

        createPotionAction(91,221,3,121,25); //attack potion
        createPotionAction(93,235,5,175,37); //antipoison
        createPotionAction(95,225,12,115,50); //strength potion
        createPotionAction(97,223,22,127,62); //restore potion
        createPotionAction(97,1975,26,3010,67); //energy potion
        createPotionAction(99,239,30,133,75); //defence potion
        createPotionAction(95,9736,36,9741,84); //combat potion
        createPotionAction(99,231,38,139,87); //prayer potion
        createPotionAction(101,221,45,145,100); //super attack potion
        createPotionAction(101,235,48,181,106); //super antipoison
        createPotionAction(103,2970,52,3018,117); //super energy
        createPotionAction(105,225,55,157,125); //super strength
        createPotionAction(3004,223,63,3026,142); //super restore
        createPotionAction(107,239,66,163,150); //super defence
        createPotionAction(2483,241,69,2454,157); //antifire
        createPotionAction(109,245,72,169,162); //ranging potion
        createPotionAction(109,3138,76,3042,172); //magic potion
        createPotionAction(3002,6693,81,6687,180); //saradomin brew

//        createSkillAdjustmentEffect(0,0,3,1.1f); //attack potion effect
//        createSkillAdjustmentEffect(1,1,3,1.1f); //defence potion effect
//        createSkillAdjustmentEffect(2,2,3,1.1f); //strength potion effect
//        createSkillAdjustmentEffect(3,0,5,1.15f); //super attack potion effect
//        createSkillAdjustmentEffect(4,1,5,1.15f); //super defence potion effect
//        createSkillAdjustmentEffect(5,2,5,1.15f); //super strength potion effect

//        Attack Potion doses
//        createPotion(2428,1,121);
//        createPotion(121,1,123);
//        createPotion(123,1,125);
//        createPotion(125,1,229);

    }

//    private static void createPotion(int itemId, int effectId, int nextItemId) {
//        Potion potion = new Potion(itemId,effectId,nextItemId);
//
//        PotionDAO.getInstance().create(potion);
//    }

    private static void createSkillAdjustmentEffect(int id, int skillId, int baseChange, float modifier) {
        SkillAdjustmentEffect effect = new SkillAdjustmentEffect(id,skillId,baseChange,modifier);
        SkillAdjustmentEffectDAO.getInstance().create(effect);
    }

    private static void createPotionAction(int usedId, int usedWithId, int levelRequired, int productItemId, int xp, int low, int high) {
        final long uuid = IDManager.getUUID();
        final long reactionUuid = IDManager.getUUID();
        System.out.println("Potion Created");
        createPotionInteraction(uuid, usedId, usedWithId, reactionUuid);
        createPotionReaction(reactionUuid, levelRequired, productItemId, xp,low,high);
    }

    private static void createPotionAction(int usedId, int usedWithId, int levelRequired, int productItemId, int xp) {
        createPotionAction(usedId, usedWithId, levelRequired, productItemId, xp, 1000, 1000);
    }

    private static void createPotionInteraction(long uuid, int usedId, int usedWithId, long reactionUuid) {
        ItemInteraction interaction = new ItemInteraction(
                uuid,
                usedId,
                usedWithId,
                SkillDictionary.Skill.HERBLORE.getId(),
                reactionUuid,
                ItemInteraction.ITEM_INTERACTION
        );

        ItemInteractionLoader.getInstance().createAndPush(uuid, interaction);
    }

    private static void createPotionReaction(long uuid, int levelRequired, int productItemId, int reactionXp) {
        createPotionReaction(uuid, levelRequired, productItemId, reactionXp, 1000, 1000);
    }

    private static void createPotionReaction(long uuid, int levelRequired, int productItemId, int reactionXp, int low, int high) {
        HerbloreItemReaction reaction = new HerbloreItemReaction(
                uuid,
                2,
                levelRequired,
                low,
                productItemId,
                1,
                -1,
                reactionXp,
                true,
                true,
                high
        );

        HerbloreItemReactionLoader.getInstance().createAndPush(uuid, reaction);
    }

    private static void createUnfinishedPotionAction(int usedId, int usedWithId, int levelRequired, int productItemId) {
        final long uuid = IDManager.getUUID();
        final long reactionUuid = IDManager.getUUID();

        createUnfinishedPotionInteraction(uuid, usedId, usedWithId, reactionUuid);
        createUnfinishedPotionReaction(reactionUuid, levelRequired, productItemId, 0);
    }

    private static void createUnfinishedPotionInteraction(long uuid, int usedId, int usedWithId, long reactionUuid) {
        ItemInteraction interaction = new ItemInteraction(
                uuid,
                usedId,
                usedWithId,
                SkillDictionary.Skill.HERBLORE.getId(),
                reactionUuid,
                ItemInteraction.ITEM_INTERACTION
        );

        ItemInteractionLoader.getInstance().createAndPush(uuid, interaction);
    }

    private static void createUnfinishedPotionReaction(long uuid, int levelRequired, int productItemId, int reactionXp) {
        HerbloreItemReaction reaction = new HerbloreItemReaction(
                uuid,
                2,
                levelRequired,
                1000,
                productItemId,
                1,
                -1,
                reactionXp,
                true,
                true,
                1000
        );

        HerbloreItemReactionLoader.getInstance().createAndPush(uuid, reaction);
    }
}
