package ethos.runehub.skill.node.io;

import ethos.runehub.skill.gathering.foraging.ForagingSeed;
import ethos.runehub.skill.node.impl.gatherable.impl.ForagingNode;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class ForagingSeedDAO extends BetaAbstractDataAcessObject<ForagingSeed> {

    private static ForagingSeedDAO instance = null;

    public static ForagingSeedDAO getInstance() {
        if (instance == null)
            instance = new ForagingSeedDAO();
        return instance;
    }

    private ForagingSeedDAO() {
        super(APIFileSystem.getInstance().buildFileRequest()
                .inDirectory(APIFileSystem.APP_DIRECTORY)
                .inDirectory(APIFileSystem.SERVER_DIRECTORY)
                .withFileName("nodes")
                .withExtension(".db")
                .build()
                .getAbsolutePath(), ForagingSeed.class);
    }
}
