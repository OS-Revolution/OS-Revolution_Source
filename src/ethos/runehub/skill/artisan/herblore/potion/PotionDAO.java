package ethos.runehub.skill.artisan.herblore.potion;

import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class PotionDAO extends BetaAbstractDataAcessObject<Potion> {

    private static PotionDAO instance = null;

    public static PotionDAO getInstance() {
        if (instance == null)
            instance = new PotionDAO();
        return instance;
    }

    private PotionDAO() {
        super(APIFileSystem.getInstance().buildFileRequest()
                .inDirectory(APIFileSystem.APP_DIRECTORY)
                .inDirectory(APIFileSystem.SERVER_DIRECTORY)
                .withFileName("item-actions")
                .withExtension(".db")
                .build()
                .getAbsolutePath(), Potion.class);
    }
}
