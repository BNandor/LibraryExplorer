package libgenexplorer.backend.local;

import libgenexplorer.backend.AbstractCoverFetcher;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LocalCoverFetcher implements AbstractCoverFetcher {

    @Override
    public BufferedImage fetchCover(String link) throws IOException {
        //Loading an existing PDF document
        File file = new File(link);
        PDDocument document = PDDocument.load(file);

        //Instantiating the PDFRenderer class
        PDFRenderer renderer = new PDFRenderer(document);

        //Rendering an image from the PDF document
        BufferedImage image = renderer.renderImage(0);
        document.close();
        return image;
    }
}
