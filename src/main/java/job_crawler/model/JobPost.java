package job_crawler.model;

public class JobPost {

    private int id;
    private String url;
    private String title;
    private String company;
    private String location;
    private String javaCheck;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJavaCheck() {
        return javaCheck;
    }

    public void setJavaCheck(String javaCheck) {
        this.javaCheck = javaCheck;
    }
}
