package ethos.runehub.skill.node.io;

import ethos.runehub.skill.node.impl.gatherable.impl.MiningNode;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class MiningNodeDAO extends BetaAbstractDataAcessObject<MiningNode> {

    private static MiningNodeDAO instance = null;

    public static MiningNodeDAO getInstance() {
        if (instance == null)
            instance = new MiningNodeDAO();
        return instance;
    }

    private MiningNodeDAO() {
        super(APIFileSystem.getInstance().buildFileRequest()
                .inDirectory(APIFileSystem.APP_DIRECTORY)
                .inDirectory(APIFileSystem.SERVER_DIRECTORY)
                .withFileName("nodes")
                .withExtension(".db")
                .build()
                .getAbsolutePath(), MiningNode.class);
    }
}
