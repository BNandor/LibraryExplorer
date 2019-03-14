package libgenexplorer.frontend.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OpenController extends Thread {
    private String path;

    public OpenController(String path) {
        this.path = path;
    }

    class viewer extends JFrame {

        class canvas extends JPanel {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(drawCurrentPage(), 0, 0, null);
            }
        }

        private canvas myCanvas;
        private int currentPage = 0;
        private JButton next;
        private JButton prev;
        private File file;
        private PDDocument document;
        PDFRenderer renderer;

        public viewer() {

            file = new File(path);
            try {
                document = PDDocument.load(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            renderer = new PDFRenderer(document);

            next = new JButton(">");

            next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    if (currentPage == document.getNumberOfPages() - 1) {
                        next.setEnabled(false);
                    }
                    ++currentPage;
                    prev.setEnabled(true);
                    myCanvas.revalidate();
                    myCanvas.repaint();
                    viewer.super.revalidate();
                    viewer.super.repaint();

                }
            });

            prev = new JButton("<");
            prev.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (currentPage == 1) {
                        prev.setEnabled(false);
                    }
                    --currentPage;
                    next.setEnabled(true);
                    myCanvas.revalidate();
                    myCanvas.repaint();
                    viewer.super.revalidate();
                    viewer.super.repaint();
                }
            });
            prev.setEnabled(false);
            if(document.getNumberOfPages() == 1){
                next.setEnabled(false);
            }

            myCanvas = new canvas();
            myCanvas.setLayout(new BorderLayout());
            myCanvas.add(next, BorderLayout.EAST);
            myCanvas.add(prev, BorderLayout.WEST);
            myCanvas.setVisible(true);

            add(myCanvas);
            Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
            setBounds(0, 0, screenDim.width, screenDim.height);
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }



        public  BufferedImage resize(BufferedImage img, int newW, int newH) {
            int w = img.getWidth();
            int h = img.getHeight();
            BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
            Graphics2D g = dimg.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
            g.dispose();
            return dimg;
        }

        public BufferedImage drawCurrentPage() {
            try {
                BufferedImage result=(renderer.renderImage(currentPage));

                return resize(result,result.getWidth()*myCanvas.getHeight()/result.getHeight(),myCanvas.getHeight());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


    }

    @Override
    public synchronized void run() {
        super.run();
        viewer view = new viewer();

    }
}
