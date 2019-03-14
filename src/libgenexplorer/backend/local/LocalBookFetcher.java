package libgenexplorer.backend.local;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;

public class LocalBookFetcher {
    private String path;
    private static DirectoryStream<Path> stream;
    public LocalBookFetcher(String libraryPath) {
        path = libraryPath;
    }

    public DirectoryStream fetchTraits() {
        //Path dir = FileSystems.getDefault().getPath(path);
        Path dir= Paths.get(path+"/.");
        try {
            stream = Files.newDirectoryStream(dir, "*.pdf");
            return stream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
