package libgenexplorer.backend.shop;

import libgenexplorer.backend.AbstractBookBuilder;
import libgenexplorer.frontend.model.Book;

import java.io.BufferedReader;
import java.io.IOException;

public class BookBuilder implements AbstractBookBuilder {

    BufferedReader reader;

    public BookBuilder(String search){
        this.reader= (new SearchFetcher(search)).fetchTraits();
    }

    public Book getBook(){

        Book result = new Book();
        try {
            result.setDownloadLink(reader.readLine());
            result.setCover(reader.readLine());
            result.setTitle(reader.readLine());
            result.setAuthor(reader.readLine());
        }catch (IOException e){
            e.printStackTrace();
        }
        if(result.getDownloadLink()==null && result.getTitle()==null)
            return null;

        return result;
    }

}
