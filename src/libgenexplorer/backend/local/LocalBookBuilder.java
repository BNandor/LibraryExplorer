package libgenexplorer.backend.local;

import libgenexplorer.backend.AbstractBookBuilder;
import libgenexplorer.frontend.model.Book;

import java.nio.file.DirectoryStream;
import java.util.Iterator;

public class LocalBookBuilder implements AbstractBookBuilder {

    private String path;
    private DirectoryStream stream;
    private Iterator iterator;
    public LocalBookBuilder(String path){
        this.path=path;
        stream=(new LocalBookFetcher(path)).fetchTraits();
        iterator =stream.iterator();
    }

    @Override
    public Book getBook() {
        if(!iterator.hasNext())return null;

        Book result=new Book();
        result.setAuthor("unknown");
        String bookPath=iterator.next().toString();
        result.setTitle(bookPath);
        result.setDownloadLink(bookPath);
        result.setCover(bookPath);

        System.out.println("rendering book from"+bookPath);

        return result;
    }
}
