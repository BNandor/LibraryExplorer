package libgenexplorer.frontend.view.shop;

import libgenexplorer.backend.AbstractBookBuilder;
import libgenexplorer.backend.shop.BookBuilder;

import javax.swing.*;
import java.awt.*;

public class BookShop extends JPanel {
    public static final int bookWidth=100;
    public static final int bookHeight=150;
    public static final int columnNum=3;
    private AbstractBookBuilder builder;
    private GridBagConstraints c;
    private GridBagLayout grid;

    public BookShop(String searchedbookName){
        this.builder=new BookBuilder(searchedbookName);
        grid = new GridBagLayout();
        setLayout(grid);
        setVisible(true);
    }



    public AbstractBookBuilder getBuilder() {
        return builder;
    }

    public GridBagConstraints getC() {
        return c;
    }

    public void setC(GridBagConstraints c) {
        this.c = c;
    }

    public GridBagLayout getGrid() {
        return grid;
    }

    public void setGrid(GridBagLayout grid) {
        this.grid = grid;
    }




}
