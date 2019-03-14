package libgenexplorer.frontend.view.local;

import libgenexplorer.backend.AbstractBookBuilder;
import libgenexplorer.backend.local.LocalBookBuilder;


import javax.swing.*;
import java.awt.*;

public class BookLocalLibrary extends JPanel {
    public static final int bookWidth=100;
    public static final int bookHeight=150;
    public static final int columnNum=3;
    private AbstractBookBuilder builder;
    private GridBagConstraints c;
    private GridBagLayout grid;

    public BookLocalLibrary(String path){
        this.builder=new LocalBookBuilder(path);
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
