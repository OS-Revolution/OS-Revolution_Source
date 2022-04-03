package ethos.runehub.skill.gathering.farming.patch;

import org.runehub.api.io.data.impl.AbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class PatchDatabase extends AbstractDataAcessObject<Patch> {

    private static PatchDatabase instance = null;

    public static PatchDatabase getInstance() {
        if (instance == null)
            instance = new PatchDatabase();
        return instance;
    }

    private PatchDatabase() {
        super(APIFileSystem.getInstance().buildFileRequest()
                .inDirectory(APIFileSystem.APP_DIRECTORY)
                .inDirectory(APIFileSystem.COMMON_RESOURCE_DIRECTORY)
                .inDirectory(APIFileSystem.SERVER_DIRECTORY)
                .withFileName("farming-data")
                .withExtension(".db")
                .build()
                .getAbsolutePath(), Patch.class);
        this.getDatabaseServiceProvider().createTable();
    }
}
