package libgenexplorer.frontend.controller;

import libgenexplorer.frontend.model.Book;

public class OpenControllerFactory implements RunnableFactory {
    private Book toOpen;
    public OpenControllerFactory(Book toOpen){
        this.toOpen=toOpen;
    }
    public OpenController getNext(){return  new OpenController(toOpen.getDownloadLink());}
}
