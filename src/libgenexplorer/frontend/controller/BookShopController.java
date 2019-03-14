package libgenexplorer.frontend.controller;

import libgenexplorer.backend.shop.CoverFetcher;
import libgenexplorer.frontend.model.Book;
import libgenexplorer.frontend.view.shop.BookProduct;
import libgenexplorer.frontend.view.shop.BookShop;
import libgenexplorer.frontend.view.shop.SearchPanel;

import javax.swing.*;
import java.awt.*;

import static libgenexplorer.frontend.view.shop.BookShop.columnNum;

public class BookShopController extends Thread {

    SearchPanel frame;
    String searchtext;
    public BookShopController(SearchPanel frame, String searchtext){
        this.frame = frame;
        this.searchtext=searchtext;
    }

    public  void populate(BookShop shop){
        int counter=0;
        Book toAdd=shop.getBuilder().getBook();

        while(toAdd!=null) {
            shop.setC( new GridBagConstraints());
            shop.getC().fill = GridBagConstraints.BOTH;
            shop.getC().weightx = 1.0;
            shop.getC().weighty = 1.0;

            ++counter;
            if(counter%columnNum == 0){
                shop.getC().gridwidth = GridBagConstraints.REMAINDER;
            }else{
            }

            System.out.println("loading book in shop");
            DownloadController downloadController = new DownloadController(toAdd.getDownloadLink(),toAdd.getTitle());
            BookProduct product = new BookProduct(toAdd,new CoverFetcher());
            downloadController.setParent(product);


            shop.getGrid().setConstraints(product, shop.getC());
            shop.add(product);
            shop.getRootPane().revalidate();
            toAdd=shop.getBuilder().getBook();

            shop.revalidate();
            shop.repaint();
        }
    }
    @Override
    public void run() {

        frame.getSearchButton().setEnabled(false);
        super.run();
            if(frame.getScrollPane() != null){
                frame.remove(frame.getScrollPane());
            }

            frame.revalidate();
            frame.repaint();

            BookShop shop=new BookShop(searchtext);

            frame.setScrollPane(new JScrollPane(shop));
            frame.add(frame.getScrollPane(), BorderLayout.CENTER);
            populate(shop);
        frame.getSearchButton().setEnabled(true);





    }
}
