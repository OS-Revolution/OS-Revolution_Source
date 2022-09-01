package ethos.runehub.entity.item;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class ItemInteractionDAO extends BetaAbstractDataAcessObject<ItemInteraction> {

    private static ItemInteractionDAO instance = null;

    public static ItemInteractionDAO getInstance() {
        if (instance == null)
            instance = new ItemInteractionDAO();
        return instance;
    }

    private ItemInteractionDAO() {
        super(RunehubConstants.ITEM_INTERACTION_DB, ItemInteraction.class);
    }
}
