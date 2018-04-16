package job_crawler.controller;

import job_crawler.model.GeneralResponse;
import job_crawler.model.JobURL;
import job_crawler.services.WorkableSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/search")
public class JobController {

    @Autowired
    WorkableSearch workableSearch;

    /**
     * POST request that runs an INSERT query (Read)
     *
     * @return the GeneralResponse object with that job post
     */
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public GeneralResponse searchOne() throws IOException {
            GeneralResponse gr = new GeneralResponse();
            gr.setData(workableSearch.scrapePost() + " rows updated");
            return gr;
    }
}
