package Project.actions;

import Project.model.CourseDetails;
import Project.service.StudentService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import java.util.List;

/**
 * Created by aliabbasjaffri on 26/11/2015.
 */

public class ShowAllCoursesAction extends ActionSupport
{

    List<CourseDetails> allCourses;

    @Action(value = "courses-show-all", results = {
        @Result(name = "showallcourses", location = "/WEB-INF/content/showallcourses.jsp")
    })
    public String addcourses() throws Exception
    {
        StudentService studentService = new StudentService();
        allCourses = studentService.showAllAvailableCourses();
        return "showallcourses";
    }

    public List<CourseDetails> getAllCourses() {
        return allCourses;
    }

    public void setAllCourses(List<CourseDetails> allCourses) {
        this.allCourses = allCourses;
    }
}
