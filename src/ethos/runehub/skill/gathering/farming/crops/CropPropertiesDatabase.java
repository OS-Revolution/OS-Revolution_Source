package ethos.runehub.skill.gathering.farming.crops;

import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.impl.AbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

@StoredObject(tableName = "crops")
public class CropPropertiesDatabase extends AbstractDataAcessObject<CropProperties> {

    private static CropPropertiesDatabase instance = null;

    public static CropPropertiesDatabase getInstance() {
        if (instance == null)
            instance = new CropPropertiesDatabase();
        return instance;
    }

    private CropPropertiesDatabase() {
        super(APIFileSystem.getInstance().buildFileRequest()
                .inDirectory(APIFileSystem.APP_DIRECTORY)
                .inDirectory(APIFileSystem.COMMON_RESOURCE_DIRECTORY)
                .inDirectory(APIFileSystem.COMMON_DB_RESOURCE)
                .withFileName("farming-data")
                .withExtension(".db")
                .build()
                .getAbsolutePath(), CropProperties.class);
        this.getDatabaseServiceProvider().createTable();
    }
}
