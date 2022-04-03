package ethos.runehub.skill.gathering.tool;

import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class GatheringToolDAO  extends BetaAbstractDataAcessObject<GatheringTool> {

    private static GatheringToolDAO instance = null;

    public static GatheringToolDAO getInstance() {
        if (instance == null)
            instance = new GatheringToolDAO();
        return instance;
    }

    private GatheringToolDAO() {
        super(APIFileSystem.getInstance().buildFileRequest()
                .inDirectory(APIFileSystem.APP_DIRECTORY)
                .inDirectory(APIFileSystem.SERVER_DIRECTORY)
                .withFileName("tools")
                .withExtension(".db")
                .build()
                .getAbsolutePath(), GatheringTool.class);
    }
}
