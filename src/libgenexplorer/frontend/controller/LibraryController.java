package libgenexplorer.frontend.controller;

import libgenexplorer.backend.local.LocalCoverFetcher;
import libgenexplorer.frontend.model.Book;
import libgenexplorer.frontend.view.local.*;
import libgenexplorer.frontend.view.local.BookLocalLibrary;
import libgenexplorer.frontend.view.local.BookProduct;

import javax.swing.*;
import java.awt.*;

import static libgenexplorer.frontend.view.shop.BookShop.columnNum;

public class LibraryController extends Thread {

    private LibraryPanel frame;
    private String path;
    private String keyword;

    public LibraryController(LibraryPanel frame, String path) {
        this.frame = frame;
        this.path = path;
    }

    public LibraryController(LibraryPanel frame, String path, String keyword) {
        this.frame = frame;
        this.path = path;
        this.keyword=keyword;
    }

    public synchronized void populate(BookLocalLibrary library) {
        int counter = 0;
        Book toAdd = library.getBuilder().getBook();

        while (toAdd != null) {

            if(keyword!=null && !toAdd.getTitle().contains(keyword)){toAdd = library.getBuilder().getBook();continue;}
            library.setC(new GridBagConstraints());
            library.getC().fill = GridBagConstraints.BOTH;
            library.getC().weightx = 1.0;
            library.getC().weighty = 1.0;

            ++counter;
            if (counter % columnNum == 0) {
                library.getC().gridwidth = GridBagConstraints.REMAINDER;
            } else {
            }

            System.out.println("loading book in library");

            BookProduct product = new BookProduct(toAdd, new LocalCoverFetcher(),new OpenControllerFactory(toAdd));
            library.getGrid().setConstraints(product, library.getC());
            library.add(product);
            //library.getRootPane().revalidate();
            frame.revalidate();
            toAdd = library.getBuilder().getBook();

            library.revalidate();
            library.repaint();
        }
    }

    @Override
    public void run() {
        super.run();
        frame.getSearchButton().setEnabled(false);

        if (frame.getScrollPane() != null) {
            frame.remove(frame.getScrollPane());
        }

        frame.revalidate();
        frame.repaint();


        BookLocalLibrary library = new BookLocalLibrary(path);
        frame.setScrollPane(new JScrollPane(library));

        frame.add(frame.getScrollPane(), BorderLayout.CENTER);
        populate(library);
        frame.getSearchButton().setEnabled(true);


    }
}
