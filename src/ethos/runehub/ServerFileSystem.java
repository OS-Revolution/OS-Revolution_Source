package ethos.runehub;

import org.rhd.api.io.fs.ApplicationFileSystem;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServerFileSystem extends ApplicationFileSystem {

    public static final String APP_HOME = "osr";
    public static final String CONFIG_HOME = "config";
    public static final String RESOURCE_HOME = "resources";
    public static final String DATABASE_HOME = "data";

    private static ServerFileSystem instance = null;

    private final Map<String, File> cachedFileMap;
    private final Properties settings;

    public static ServerFileSystem getInstance() {
        if (instance == null)
            instance = new ServerFileSystem();
        return instance;
    }

    private ServerFileSystem() {
        this.cachedFileMap = new HashMap<>();
        this.settings = new Properties();
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.requireFile(Path.of(
                this.buildFileRequest()
                        .inDirectory(ApplicationFileSystem.APP_DIRECTORY)
                        .withFileName(APP_HOME)
                        .build()
                        .getAbsolutePath()
        ));
        this.requireFile(Path.of(
                this.buildFileRequest()
                        .inDirectory(ApplicationFileSystem.APP_DIRECTORY)
                        .inDirectory(APP_HOME)
                        .withFileName(CONFIG_HOME)
                        .build()
                        .getAbsolutePath()
        ));
        this.requireFile(Path.of(
                this.buildFileRequest()
                        .inDirectory(ApplicationFileSystem.APP_DIRECTORY)
                        .inDirectory(APP_HOME)
                        .withFileName(RESOURCE_HOME)
                        .build()
                        .getAbsolutePath()
        ));
        this.requireFile(Path.of(
                this.buildFileRequest()
                        .inDirectory(ApplicationFileSystem.APP_DIRECTORY)
                        .inDirectory(APP_HOME)
                        .inDirectory(RESOURCE_HOME)
                        .withFileName(DATABASE_HOME)
                        .build()
                        .getAbsolutePath()
        ));
    }

    public File getFile(Path path) {
        File file = cachedFileMap.get(path.toAbsolutePath().toString());

        if (file == null) {
            File diskFile = path.toFile();
            if (!diskFile.exists())
                throw new NullPointerException("File Not Found: " + path.toAbsolutePath().toString());
            file = diskFile;
        }
        return file;
    }

    public File getAndCacheFile(Path path) {
        File file = getFile(path);
        cachedFileMap.put(path.toAbsolutePath().toString(), file);
        return file;
    }

    public Properties getSettings() {
        return settings;
    }

    public Map<String, File> getCachedFileMap() {
        return cachedFileMap;
    }
}
