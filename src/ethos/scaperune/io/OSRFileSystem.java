package ethos.scaperune.io;

import org.scaperune.api.io.fs.DirectorySearchVisitor;
import org.scaperune.api.io.fs.FileSearchVisitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OSRFileSystem {

    private static OSRFileSystem instance = null;

    public static OSRFileSystem getInstance() {
        if (instance ==null)
            instance = new OSRFileSystem();
        return instance;
    }

    public boolean fileExists(String fileName) {
        return this.fileExists(fileName, base);
    }

    public File getFile(String fileName) {
        return this.getFile(fileName, base);
    }

    public File getDirectory(String fileName) {
        return this.getDirectory(fileName, base);
    }

    private boolean fileExists(String fileName, Path path) {
        try {
            FileSearchVisitor visitor = new FileSearchVisitor(fileName);
            Files.walkFileTree(path, visitor);
            return visitor.isFileFound();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private File getFile(String fileName, Path path) {
        try {
            FileSearchVisitor visitor = new FileSearchVisitor(fileName);
            Files.walkFileTree(path, visitor);
            if (visitor.isFileFound()) {
                return visitor.getFoundFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File getDirectory(String directoryName, Path path) {
        try {
            DirectorySearchVisitor visitor = new DirectorySearchVisitor(directoryName);
            Files.walkFileTree(path, visitor);
            if (visitor.getFoundDirectory() != null) {
                return visitor.getFoundDirectory();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private OSRFileSystem() {
        this.base = Paths.get(
                new File(
                        "./Data/runehub"
                ).toURI()
        );
    }

    private final Path base;
}
