package ethos.runehub.skill.artisan.herblore;

import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class HerbloreItemReactionDAO extends BetaAbstractDataAcessObject<HerbloreItemReaction> {

    private static HerbloreItemReactionDAO instance = null;

    public static HerbloreItemReactionDAO getInstance() {
        if (instance == null)
            instance = new HerbloreItemReactionDAO();
        return instance;
    }

    private HerbloreItemReactionDAO() {
        super(APIFileSystem.getInstance().buildFileRequest()
                .inDirectory(APIFileSystem.APP_DIRECTORY)
                .inDirectory(APIFileSystem.SERVER_DIRECTORY)
                .withFileName("item-interactions")
                .withExtension(".db")
                .build()
                .getAbsolutePath(), HerbloreItemReaction.class);
    }
}
