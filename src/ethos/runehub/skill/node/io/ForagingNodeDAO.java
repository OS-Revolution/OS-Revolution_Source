package ethos.runehub.skill.node.io;

import ethos.runehub.skill.node.impl.gatherable.impl.ForagingNode;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class ForagingNodeDAO extends BetaAbstractDataAcessObject<ForagingNode> {

    private static ForagingNodeDAO instance = null;

    public static ForagingNodeDAO getInstance() {
        if (instance == null)
            instance = new ForagingNodeDAO();
        return instance;
    }

    private ForagingNodeDAO() {
        super(APIFileSystem.getInstance().buildFileRequest()
                .inDirectory(APIFileSystem.APP_DIRECTORY)
                .inDirectory(APIFileSystem.SERVER_DIRECTORY)
                .withFileName("nodes")
                .withExtension(".db")
                .build()
                .getAbsolutePath(), ForagingNode.class);
    }
}
