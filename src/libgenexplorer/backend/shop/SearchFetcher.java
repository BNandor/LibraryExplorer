package libgenexplorer.backend.shop;

import java.io.*;

public class SearchFetcher {


    private String searchString;
    private static final String fetchScript="scripts/genlib.sh";
    Process pGetTraits;
    private BufferedReader reader;
    public SearchFetcher(String searchString){
        this.searchString=searchString;
    }

    public BufferedReader fetchTraits()
    {
        if(reader == null) {
            try {

                String sanitized=searchString.replaceAll(" ","+");
                System.out.println("executing" + fetchScript + " " +searchString);
                pGetTraits= Runtime.getRuntime().exec(fetchScript + " " + sanitized);
                reader=new BufferedReader(new InputStreamReader(pGetTraits.getInputStream()));
                return reader;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return reader;
    }
}
