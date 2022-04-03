package ethos.runehub.skill.gathering.farming.patch;

import org.runehub.api.io.data.impl.AbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class PatchPropertiesDatabase extends AbstractDataAcessObject<PatchProperties> {

    private static PatchPropertiesDatabase instance = null;

    public static PatchPropertiesDatabase getInstance() {
        if (instance == null)
            instance = new PatchPropertiesDatabase();
        return instance;
    }

    private PatchPropertiesDatabase() {
        super(APIFileSystem.getInstance().buildFileRequest()
                .inDirectory(APIFileSystem.APP_DIRECTORY)
                .inDirectory(APIFileSystem.SERVER_DIRECTORY)
                .withFileName("farming-data")
                .withExtension(".db")
                .build()
                .getAbsolutePath(), PatchProperties.class);
        this.getDatabaseServiceProvider().createTable();
    }
}
