package job_crawler.mapper;


import job_crawler.model.JobPost;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface JobMapper {

    String GET_BY_ID = ("SELECT * FROM workable-job-posts WHERE id = #{id}");

    String ADD_JOB = ("INSERT INTO `workable-job-posts` " +
            "(url, title, company, location, javaCheck) VALUES" +
            "(#{url}, #{title}, #{company}, #{location}, #{javaCheck})");

    @Select(GET_BY_ID)
    public JobPost getById(int id);

    @Insert(ADD_JOB)
    public int addJob(JobPost job);

}
