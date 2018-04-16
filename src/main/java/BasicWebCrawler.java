package com.mkyong.basicwebcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class BasicWebCrawler {
    private static final int MAX_DEPTH = 2;
    private HashSet<String> links;

    public BasicWebCrawler() {
        links = new HashSet<>();
    }

    public void getPageLinks(String URL, int depth ) {
        //4. Check if you have already crawled the URLs
        //(we are intentionally not checking for duplicate content in this example)
        if ((!links.contains(URL) && depth < MAX_DEPTH)) {
            System.out.println(">> Depth: " + depth + " [" + URL + "]");
            try {
                //4. (i) If not add it to the index
                if (links.add(URL)) {
                    System.out.println(URL);
                }

                //2. Fetch the HTML code
                Document document = Jsoup.connect(URL).get();
                //3. Parse the HTML to extract links to other URLs
                Elements linksOnPage = document.select("a[href]");

                depth++;
                //5. For each extracted URL... go back to Step 4.
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"), depth);
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        //1. Pick a URL from the frontier
        new BasicWebCrawler().getPageLinks("http://www.codingnomads.co", 0);
    }

}