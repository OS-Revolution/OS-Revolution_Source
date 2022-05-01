package ethos.runehub.editor;

import ethos.runehub.ConsoleEditor;
import ethos.runehub.entity.item.ItemInteraction;
import ethos.runehub.entity.item.ItemInteractionLoader;
import ethos.runehub.skill.artisan.herblore.HerbloreItemReaction;
import ethos.runehub.skill.artisan.herblore.HerbloreItemReactionLoader;
import org.runehub.api.util.IDManager;
import org.runehub.api.util.SkillDictionary;

public class HerbloreItemActionEditor extends Editor {

    private static HerbloreItemActionEditor instance = null;

    public static HerbloreItemActionEditor getInstance() {
        if (instance == null)
            instance = new HerbloreItemActionEditor();
        return instance;
    }

    @Override
    public void run(ConsoleEditor console) {
        int actionId = console.getInputAsInteger(console.getPrompt("Enter Action ID"));
        int primaryItemId = console.getInputAsInteger(console.getPrompt("Enter Item ID"));
        int secondaryItemId = console.getInputAsInteger(console.getPrompt("Enter Secondary Item ID"));
        int levelRequirement = console.getInputAsInteger(console.getPrompt("Enter Level Requirement"));
        int productItemId = console.getInputAsInteger(console.getPrompt("Enter Product Item ID"));
        int xp = console.getInputAsInteger(console.getPrompt("Enter XP for Completing Action"));
        int lowSuccessRoll = console.getInputAsInteger(console.getPrompt("Enter Low Success Roll"));
        int highSuccessRoll = console.getInputAsInteger(console.getPrompt("Enter High Success Roll"));
        int failedItemId = console.getInputAsInteger(console.getPrompt("Enter Item ID Produced on Failure (-1 if none)"));
        int amountProduced = console.getInputAsInteger(console.getPrompt("Enter Amount Produced"));
        boolean primaryConsumed= console.getInputAsBoolean(console.getPrompt("Is Primary Item ID Consumed? (true/false)"));
        boolean secondaryConsumed = console.getInputAsBoolean(console.getPrompt("Is Secondary Item ID Consumed? (true/false)"));
        createHerbloreAction(actionId,primaryItemId,secondaryItemId,levelRequirement,productItemId,xp,lowSuccessRoll,highSuccessRoll,failedItemId,amountProduced,
                primaryConsumed,secondaryConsumed);
        createHerbloreAction(actionId,secondaryItemId,primaryItemId,levelRequirement,productItemId,xp,lowSuccessRoll,highSuccessRoll,failedItemId,amountProduced,
                secondaryConsumed,primaryConsumed);
        System.out.println("[Runehub] Successfully Created!");
        if (console.getInputAsBoolean(console.getPrompt("Continue? (true/false)"))) {
            this.run(console);
        }
    }


    private void createHerbloreAction(int actionId, int usedId, int usedWithId, int levelRequired, int productItemId, int xp, int low, int high, int failedId,
                                      int producedAmount, boolean primaryConsumed, boolean secondaryConsumed) {
        final long uuid = IDManager.getUUID();
        final long reactionUuid = IDManager.getUUID();
        createInteraction(uuid, usedId, usedWithId, reactionUuid);
        createHerbloreReaction(reactionUuid, actionId, levelRequired, productItemId, xp, low, high, failedId, producedAmount, primaryConsumed, secondaryConsumed);
    }

    private void createHerbloreReaction(long uuid, int actionId, int levelRequired, int productItemId, int reactionXp, int low, int high, int failedId, int producedAmount,
                                        boolean primaryConsumed, boolean secondaryConsumed) {
        HerbloreItemReaction reaction = new HerbloreItemReaction(
                uuid,
                actionId,
                levelRequired,
                low,
                productItemId,
                producedAmount,
                failedId,
                reactionXp,
                primaryConsumed,
                secondaryConsumed,
                high
        );

        HerbloreItemReactionLoader.getInstance().createAndPush(uuid, reaction);
    }

    private void createInteraction(long uuid, int usedId, int usedWithId, long reactionUuid) {
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

    private HerbloreItemActionEditor() {}
}
