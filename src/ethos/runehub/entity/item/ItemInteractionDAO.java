package ethos.runehub.entity.item;

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
        super(APIFileSystem.getInstance().buildFileRequest()
                .inDirectory(APIFileSystem.APP_DIRECTORY)
                .inDirectory(APIFileSystem.SERVER_DIRECTORY)
                .withFileName("item-interactions")
                .withExtension(".db")
                .build()
                .getAbsolutePath(), ItemInteraction.class);
    }
}
