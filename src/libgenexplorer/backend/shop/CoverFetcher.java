package libgenexplorer.backend.shop;

import libgenexplorer.backend.AbstractCoverFetcher;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class CoverFetcher implements AbstractCoverFetcher {



    public CoverFetcher() {
    }

    public  BufferedImage fetchCover(String link) throws IOException {
        try {
            return ImageIO.read(new URL(link));
        }catch ( IOException e){

            throw new IOException();
        }
    }
}
