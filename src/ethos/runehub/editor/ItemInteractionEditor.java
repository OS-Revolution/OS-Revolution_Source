package ethos.runehub.editor;

import ethos.runehub.ConsoleEditor;
import ethos.runehub.entity.item.ItemInteraction;
import ethos.runehub.entity.item.ItemInteractionLoader;
import org.runehub.api.util.IDManager;
import org.runehub.api.util.SkillDictionary;

public class ItemInteractionEditor extends Editor{

    private static ItemInteractionEditor instance = null;


    public static ItemInteractionEditor getInstance() {
        if (instance == null)
            instance = new ItemInteractionEditor();
        return instance;
    }

    @Override
    public void run(ConsoleEditor console) {
        int usedId = console.getInputAsInteger(console.getPrompt("Enter Used Item ID"));
        int usedWithId = console.getInputAsInteger(console.getPrompt("Enter Used With Item ID"));

        createInteraction(usedId,usedWithId);
    }

    private void createInteraction(int usedId, int usedWithId) {
        final long uuid = IDManager.getUUID();
        final long reactionUuid = IDManager.getUUID();
        ItemInteraction interaction = new ItemInteraction(
                uuid,
                usedId,
                usedWithId,
                69,
                reactionUuid,
                ItemInteraction.ITEM_INTERACTION
        );

        ItemInteractionLoader.getInstance().createAndPush(uuid, interaction);
    }

    private ItemInteractionEditor() {

    }
}
