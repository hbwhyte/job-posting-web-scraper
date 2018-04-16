package job_crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class WorkableSearch {
    private static final int MAX_DEPTH = 2;
    private HashSet<String> links;
    public static String REDIRECT_URL = "https://www.workable.com/";
    public WorkableSearch() {
        links = new HashSet<>();
    }

    public void getPageLinks(String URL) {
        try {
            Document doc = Jsoup.connect(URL).get();
            if (REDIRECT_URL.equals(doc.location())) {
                System.out.println(URL + " is not a live posting\n");
                return;
            }

            Elements h1 = doc.select("h1");
            Elements meta = doc.select(".meta");
            Elements title = doc.select("title");
            Element javaCheck = doc.select("p:contains(java):not(:contains(javascript)),li:contains(java):not(:contains(javascript))").first();

            System.out.println(URL);
            for (Element jobTitles : h1) {
                    System.out.println(jobTitles.text());
                }
            for (Element company : title) {
                String c = company.text();
                c = c.split(	" \u002D")[0];
                System.out.println(c);
            }
            for (Element locations : meta) {
                String loc = locations.text();
                loc = loc.split(	" \u00B7")[0];
                System.out.println(loc);
            }
                System.out.println(javaCheck.text());

            System.out.println();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        //1. Pick a URL from the frontier
        new WorkableSearch().getPageLinks("https://www.workable.com/j/24942CEFE3");
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