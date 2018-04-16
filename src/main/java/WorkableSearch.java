
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class WorkableSearch {
    private static final int MAX_DEPTH = 2;
    private HashSet<String> links;

    public WorkableSearch() {
        links = new HashSet<>();
    }

    public void getPageLinks(String URL) {
        try {
            Document doc = Jsoup.connect(URL).get();
            Elements h1 = doc.select("h1");
            Elements meta = doc.select(".meta");
            Elements java = doc.select("p:contains(java),li:contains(java)");

            for (Element jobTitles : h1) {
                    System.out.println(jobTitles);
                }
            for (Element locations : h1) {
                System.out.println(meta);
            }
            for (Element jobDescription : h1) {
                System.out.println(java);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        //1. Pick a URL from the frontier
        new WorkableSearch().getPageLinks("https://www.workable.com/j/AE24DA328D");
        new WorkableSearch().getPageLinks("https://www.workable.com/j/404A8AD946");
        new WorkableSearch().getPageLinks("https://www.workable.com/j/24942CEFE4");

        //Generate loop of possible job titles
//        String base = "https://www.workable.com/j/";
//        System.out.println(Long.parseLong("AE24DA328D", 16));
//        System.out.println(Long.parseLong("404A8AD946", 16));
//        System.out.println(Long.parseLong("24942CEFE4", 16));
//        System.out.println(Long.toHexString(747942589069L).toUpperCase());
//        System.out.println(Long.toHexString(276128520518L).toUpperCase());
//        System.out.println(Long.toHexString(157104795620L).toUpperCase());
    }

}