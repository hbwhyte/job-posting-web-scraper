package job_crawler.model;

public class JobPost {

    private int id;
    private String url;
    private String title;
    private String company;
    private String location;
    private boolean javaCheck;

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

    public boolean isJavaCheck() {
        return javaCheck;
    }

    public void setJavaCheck(boolean javaCheck) {
        this.javaCheck = javaCheck;
    }
}
