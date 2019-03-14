package libgenexplorer.backend.shop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class BookDownloader extends Thread {
    private  String link;
    private  String path;
    public BookDownloader(String link,String filepath){
        this.link=link;
        this.path=filepath;
    }

    @Override
    public void run() {
        super.run();
        try {

            InputStream in = new URL(link).openStream();
            Files.copy(in, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

