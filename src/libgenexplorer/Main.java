package libgenexplorer;

import libgenexplorer.frontend.view.local.LibraryPanel;
import libgenexplorer.frontend.view.shop.SearchPanel;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private static final int width=600;
    private static final int height=800;



    public JTabbedPane tabs;

    public Main(){

        tabs = new JTabbedPane();
        tabs.add(new LibraryPanel(),"Local Library");
        tabs.add(new SearchPanel(),"Search library genesis");

        add(tabs);

        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screenDim.width,screenDim.height);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Main mainframe = new Main();


    }
}
