package libgenexplorer.frontend.view.local;

import libgenexplorer.frontend.controller.LibraryController;
import libgenexplorer.frontend.controller.LibraryPathChooserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryPanel extends JPanel implements ActionListener {

    private static String libraryPath="library";
    private JTextField searchBar;
    private JButton searchButton;
    private JButton pathButton;
    private JPanel libraryPanel;
    private JLabel currentDir;



    private JScrollPane scrollPane;

    public LibraryPanel() {
        currentDir = new JLabel(libraryPath);
        pathButton = new JButton("...");
        pathButton.addActionListener(new LibraryPathChooserController(this,pathButton));

        libraryPanel = new JPanel();
        searchBar = new JTextField(25);
        searchButton = new JButton("search");
        searchButton.addActionListener(this);

        setLayout(new BorderLayout());
        libraryPanel.setLayout(new FlowLayout());
        libraryPanel.add(searchBar);
        libraryPanel.add(searchButton);
        libraryPanel.add(pathButton);
        libraryPanel.add(currentDir);


        add(libraryPanel, BorderLayout.NORTH);
        LibraryController libraryController = new LibraryController(this,libraryPath);
        libraryController.start();


    }

    public  JScrollPane getScrollPane(){return scrollPane;}
    public  void setScrollPane(JScrollPane pane){scrollPane=pane;}

    public JButton getSearchButton() {
        return searchButton;
    }
    public JButton getPathButton(){return pathButton;}

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }
    public void setLibraryPath(String path){
        libraryPath=path;
        currentDir.setText(libraryPath);
    }
    public String getLibraryPath(){return libraryPath;}

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        LibraryController libraryController = new LibraryController(this,libraryPath,searchBar.getText());
        libraryController.start();
    }
}
