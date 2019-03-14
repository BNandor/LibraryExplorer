package libgenexplorer.frontend.view.shop;

import libgenexplorer.frontend.controller.BookShopController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel implements ActionListener {


    private JTextField searchBar;
    private JButton searchButton;
    private JPanel searchPanel;




    private JScrollPane scrollPane;

    public SearchPanel() {
        searchPanel = new JPanel();
        searchBar = new JTextField(25);
        searchButton = new JButton("search");
        searchButton.addActionListener(this);

        setLayout(new BorderLayout());
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(searchBar);
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);
    }

    public  JScrollPane getScrollPane(){return scrollPane;}
    public  void setScrollPane(JScrollPane pane){scrollPane=pane;}

    public JButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        BookShopController updater = new BookShopController(this,searchBar.getText());
        updater.start();
    }
}
