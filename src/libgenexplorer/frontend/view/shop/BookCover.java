package libgenexplorer.frontend.view.shop;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BookCover extends JPanel {
    private  BufferedImage mCover;
    public BookCover(BufferedImage cover){

        mCover=cover;

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(mCover,20,0,null);
    }
}
