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

    public JobPost scrapePost(String URL) throws IOException {
        JobPost job = new JobPost();
        try {
            Document doc = Jsoup.connect(URL).get();
            if (REDIRECT_URL.equals(doc.location())) {
                System.out.println(URL + " is not a live posting\n");
                return job;
            }

            Element jobTitle = doc.select("h1").first();
            Element location = doc.select(".meta").first();
            Element company = doc.select("title").first();
            Element javaCheck = doc.select("p:contains(java):not(:contains(javascript)),li:contains(java):not(:contains(javascript))").first();

            job.setUrl(URL);
            job.setTitle(jobTitle.text());
            System.out.println("Title: " + job.getTitle());
            job.setCompany(company.text().split(	" \u002D")[0]);
            System.out.println("Company: " + job.getCompany());
            job.setLocation(location.text().split(	" \u00B7")[0]);
            System.out.println("Location: " + job.getLocation());
            job.setJavaCheck(javaCheck.text());
            System.out.println("JavaCheck: " + job.getJavaCheck());

            jobMapper.addJob(job);
            System.out.println("Job successfully added!");
            return job;

        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new IOException("Error finding job");
        }
    }


//    public static void main(String[] args) {
//        //1. Pick a URL from the frontier
//        new WorkableSearch().getPageLinks("https://www.workable.com/j/24942CEFE3");
//        new WorkableSearch().getPageLinks("https://www.workable.com/j/AE24DA328D");
//        new WorkableSearch().getPageLinks("https://www.workable.com/j/404A8AD946");
//        new WorkableSearch().getPageLinks("https://www.workable.com/j/24942CEFE4");


        //Generate loop of possible job titles
//        String base = "https://www.workable.com/j/";
//        System.out.println(Long.parseLong("AE24DA328D", 16));
//        System.out.println(Long.parseLong("404A8AD946", 16));
//        System.out.println(Long.parseLong("24942CEFE4", 16));
//        System.out.println(Long.toHexString(747942589069L).toUpperCase());
//        System.out.println(Long.toHexString(276128520518L).toUpperCase());
//        System.out.println(Long.toHexString(157104795620L).toUpperCase());
//    }



}