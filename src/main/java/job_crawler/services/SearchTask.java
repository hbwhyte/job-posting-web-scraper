package job_crawler.services;

import javafx.util.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.concurrent.Callable;

public class SearchTask implements Callable {

    private long suffix;

    public SearchTask(long suffix) {
        this.suffix = suffix;
    }

    @Override
    public Pair<String, Document> call() throws Exception {
        // Build URL
        StringBuilder sb = new StringBuilder();
        sb.append("https://www.workable.com/j/");
        // convert long to HexString, how Workable defines their job postings
        String hex = Long.toHexString(suffix).toUpperCase();
        String url = sb.append(hex).toString();
        // Printing URL to track that progress is being made
        // System.out.println("in call " + url);
        // Call with Jsoup to return contents of URL
        Document doc = Jsoup.connect(url).get();
        Pair<String, Document> pair = new Pair<>(url, doc);
        return pair;
    }
}
