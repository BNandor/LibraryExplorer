package libgenexplorer.frontend.view.local;

import libgenexplorer.backend.AbstractCoverFetcher;
import libgenexplorer.frontend.controller.RunnableFactory;
import libgenexplorer.frontend.model.Book;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class BookProduct extends JPanel implements ActionListener {

    private Book mBook;
    private BookCover coverPanel;
    private JPanel titleAuthorPanel;
    JLabel title;
    JLabel author;
    JButton downloadButton;
    private RunnableFactory factory;
    private AbstractCoverFetcher coverFetcher;

    public BookProduct(Book book, AbstractCoverFetcher coverFetcher, RunnableFactory factory) {
        if (book == null) {
            return;
        }
        this.factory =  factory;
        setLayout(new BorderLayout());
        title = new JLabel(book.getTitle());
        author = new JLabel(book.getAuthor());
        titleAuthorPanel = new JPanel();
        titleAuthorPanel.setLayout(new BorderLayout());
        downloadButton = new JButton("OPEN");
        downloadButton.addActionListener(this);
        mBook = book;
        setVisible(true);
        this.coverFetcher = coverFetcher;
        try {

            coverPanel = new BookCover(coverFetcher.fetchCover(mBook.getCover()));
            coverPanel.setPreferredSize(new Dimension(100, 800));
            coverPanel.setAlignmentX(0.5f);
            add(coverPanel, BorderLayout.CENTER);

        } catch (IOException e) {

            try {
                coverPanel = new BookCover(ImageIO.read(new File("img/defaultcover.png")));
                coverPanel.setPreferredSize(new Dimension(100, 800));
                add(coverPanel, BorderLayout.CENTER);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            title.setPreferredSize(new Dimension(100, 40));
            author.setPreferredSize(new Dimension(100, 40));

            titleAuthorPanel.add(title, BorderLayout.NORTH);
            titleAuthorPanel.add(author, BorderLayout.SOUTH);
            add(titleAuthorPanel, BorderLayout.NORTH);
            add(downloadButton, BorderLayout.SOUTH);
        }
        revalidate();
        repaint();
    }

    @Override
    public  void actionPerformed(ActionEvent actionEvent) {

            factory.getNext().start();


    }
}
