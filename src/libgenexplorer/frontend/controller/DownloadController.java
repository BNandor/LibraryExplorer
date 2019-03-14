package libgenexplorer.frontend.controller;

import libgenexplorer.backend.shop.BookDownloader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class DownloadController extends Thread  {
    private String link;
    private String title;
    private JFileChooser jFileChooser;
    private Component parent;
    public  DownloadController(String link,String title){
        this.link=link;
        this.parent=parent;
        this.title=title;
    }

    public void setParent(Component parent){this.parent=parent;}
    @Override
    public void run() {
        super.run();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                link.substring(link.lastIndexOf('.')), link.substring(link.lastIndexOf('.')));
        chooser.setFileFilter(filter);

        int returnVal = chooser.showSaveDialog(parent);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile()+"/"+title+link.substring(link.lastIndexOf('.'));
            System.out.println("Saving to"+filename);
            BookDownloader downloader = new BookDownloader(link,filename);
            downloader.start();
        }
    }


}
