package job_crawler.services;

import javafx.util.Pair;
import job_crawler.mapper.JobMapper;
import job_crawler.model.JobPost;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Future;

@Service
public class WorkableSearch {

    @Autowired
    JobMapper jobMapper;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public static String REDIRECT_URL = "https://www.workable.com/";

    public int scrapePost() throws IOException {

        Logger logger = LoggerFactory.getLogger(WorkableSearch.class);

        int counter = 0;
        String cssJavaQuery = "p:contains(java):not(:contains(javascript))," +
                "li:contains(java):not(:contains(javascript)),h1:contains(java):not(:contains(javascript))";
        JobPost job = new JobPost();
        ArrayList<Future<Pair<String, Document>>> futures = new ArrayList<>();

        try {
            for (long l = 68719476736L; l < 1099511627775L; l++) {

                SearchTask task = new SearchTask(l);
                Future<Pair<String, Document>> future = threadPoolTaskExecutor.submit(task);

                // add task to future list
                futures.add(future);
            }

            for (int i = 0; i < futures.size(); i++) {
                Pair<String, Document> response = futures.get(i).get();
                String url = response.getKey();
                //System.out.println(url);
                Document doc = response.getValue();
                if (!REDIRECT_URL.equals(doc.location())) { // && (doc.select(cssJavaQuery).first() != null)) {
                    Element jobTitle = doc.select("h1").first();
                    Element location = doc.select(".meta").first();
                    Element company = doc.select("title").first();
                    // Checks if the job posting mentions "java"
                    boolean javaCheck = false;
                    if (doc.select(cssJavaQuery).first() != null) {javaCheck = true;}

                    job.setUrl(url);
                    job.setTitle(jobTitle.text());
                    logger.info("Title: " + job.getTitle());
                    job.setCompany(company.text().split(" \u002D")[0]);
                    logger.info("Company: " + job.getCompany());
                    job.setLocation(location.text().split(" \u00B7")[0]);
                    logger.info("Location: " + job.getLocation());
                    job.setJavaCheck(javaCheck);
                    logger.info("JavaCheck: " + job.isJavaCheck());

                    jobMapper.addJob(job);
                    logger.info("Job successfully added!");
                    counter++;
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new IOException("Error finding job");
        }
        return counter;
    }

//    public static void loop() {
//        //Generate loop of possible job titles
//        for (long l = 68719476736L; l < 1099511627775L; l++) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("https://www.workable.com/j/");
//            String hex = Long.toHexString(l).toUpperCase();
//            sb.append(hex);
//            System.out.println(sb);
//
//        }

//        for (long l = 0_000_000_000; l < 1099511627775L; l++) {
//
//        System.out.println(Long.parseLong("AE24DA328D", 16));
//        System.out.println(Long.parseLong("404A8AD946", 16));
//        System.out.println(Long.parseLong("24942CEFE4", 16));
//        System.out.println(Long.toHexString(747942589069L).toUpperCase());
//        System.out.println(Long.toHexString(276128520518L).toUpperCase());
//        System.out.println(Long.toHexString(157104795620L).toUpperCase());
//    }

//    public static void main(String[] args) {
//        System.out.println(Long.parseLong("1000000000", 16)); // 68719476736L
//        System.out.println(Long.parseLong("FFFFFFFFFF", 16)); // 1099511627775L
//        System.out.println(Long.toHexString(747942589169L).toUpperCase());
////        loop();
//        try {
//            Document doc = Jsoup.connect("https://www.workable.com/j/AE24DA329E").get();
//            System.out.println(doc.location());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }


}