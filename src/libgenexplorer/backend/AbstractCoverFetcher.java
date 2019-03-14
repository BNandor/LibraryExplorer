package libgenexplorer.backend;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface AbstractCoverFetcher {
    BufferedImage fetchCover(String link)throws IOException;
}
