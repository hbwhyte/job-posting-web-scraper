package job_crawler.services;

import job_crawler.mapper.JobMapper;
import job_crawler.model.JobPost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;

@Service
public class WorkableSearch {

    @Autowired
    JobMapper jobMapper;

    public static String REDIRECT_URL = "https://www.workable.com/";

    public int scrapePost() throws IOException {
        int counter = 0;
        String cssJavaQuery = "p:contains(java):not(:contains(javascript)),li:contains(java):not(:contains(javascript))";
        JobPost job = new JobPost();
        try {
            for (long l = 747942589000L; l < 747942589004L; l++) {
                StringBuilder sb = new StringBuilder();
                sb.append("https://www.workable.com/j/");
                String hex = Long.toHexString(l).toUpperCase();
                String URL = sb.append(hex).toString();
                System.out.println(URL);
                Document doc = Jsoup.connect(URL).get();
                if (!REDIRECT_URL.equals(doc.location()) && (doc.select(cssJavaQuery).first() != null)) {
                    Element jobTitle = doc.select("h1").first();
                    Element location = doc.select(".meta").first();
                    Element company = doc.select("title").first();
                    Element javaCheck = doc.select(cssJavaQuery).first();

                    job.setUrl(URL);
                    job.setTitle(jobTitle.text());
                    System.out.println("Title: " + job.getTitle());
                    job.setCompany(company.text().split(" \u002D")[0]);
                    System.out.println("Company: " + job.getCompany());
                    job.setLocation(location.text().split(" \u00B7")[0]);
                    System.out.println("Location: " + job.getLocation());
                    job.setJavaCheck(javaCheck.text());
                    System.out.println("JavaCheck: " + job.getJavaCheck());

                    jobMapper.addJob(job);
                    System.out.println("Job successfully added!");
                    counter++;
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new IOException("Error finding job");
        }
        return counter;
    }

    public static void loop() {
        //Generate loop of possible job titles
        for (long l = 68719476736L; l < 747942589169L; l++) {
            StringBuilder sb = new StringBuilder();
            sb.append("https://www.workable.com/j/");
            String hex = Long.toHexString(l).toUpperCase();
            sb.append(hex);
            System.out.println(sb);

        }

//        for (long l = 0_000_000_000; l < 1099511627775L; l++) {
//
//        System.out.println(Long.parseLong("AE24DA328D", 16));
//        System.out.println(Long.parseLong("404A8AD946", 16));
//        System.out.println(Long.parseLong("24942CEFE4", 16));
//        System.out.println(Long.toHexString(747942589069L).toUpperCase());
//        System.out.println(Long.toHexString(276128520518L).toUpperCase());
//        System.out.println(Long.toHexString(157104795620L).toUpperCase());
    }

    public static void main(String[] args) {
        System.out.println(Long.parseLong("AE24DA328D", 16));
        System.out.println(Long.toHexString(747942589069L).toUpperCase());
        loop();
    }


}