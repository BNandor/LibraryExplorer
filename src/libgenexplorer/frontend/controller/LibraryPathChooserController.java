package libgenexplorer.frontend.controller;

import libgenexplorer.backend.shop.BookDownloader;
import libgenexplorer.frontend.view.local.LibraryPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryPathChooserController extends Thread implements ActionListener {


    LibraryPanel frame;
    Component parent;
    public LibraryPathChooserController(LibraryPanel frame,Component parent){
        this.frame=frame;
        this.parent=parent;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        RunnableFilechooser runnableFilechooser  = new RunnableFilechooser();
        runnableFilechooser.start();
    }

    class RunnableFilechooser extends Thread{

        @Override
        public synchronized void run() {
            super.run();
            frame.getPathButton().setEnabled(false);
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int returnVal = chooser.showOpenDialog(parent);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                frame.setLibraryPath( chooser.getSelectedFile()+"");
            }

            LibraryController libraryController = new LibraryController(frame,frame.getLibraryPath());
            libraryController.start();
            frame.getPathButton().setEnabled(true);
        }
    }

}
