package Project.actions;

import Project.model.CourseDetails;
import Project.service.StudentService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by aliabbasjaffri on 25/11/2015.
 */
public class AddCourseAction extends ActionSupport implements SessionAware
{
    private Map<String, Object> session;
    private List<CourseDetails> allCourses;
    private List<String> selectedCourseIds;
    private String message;

    @Action(value = "courses-add", results = {
        @Result(name = "AddCourses", location = "/WEB-INF/content/AddCourses.jsp")
    })
    public String addcourses() throws Exception
    {
        StudentService studentService = new StudentService();
        allCourses = studentService.showAllAvailableCourses();
        return "AddCourses";
    }
    
    @Action(value = "courses-register", results = {
        @Result(name = "registerSuccess", location = "/WEB-INF/content/register-success.jsp"),
        @Result(name = "registerFailure", location = "/WEB-INF/content/register-failure.jsp")
    })
    public String registerCourses() throws Exception
    {
        if (selectedCourseIds != null && !selectedCourseIds.isEmpty()) {
            StudentService studentService = new StudentService();
            String username = (String) session.get("username");
            
            boolean success = studentService.registerCourses(username, selectedCourseIds);
            
            if (success) {
                message = "コースの登録が完了しました。";
                return "registerSuccess";
            } else {
                message = "コースの登録に失敗しました。";
                return "registerFailure";
            }
        } else {
            message = "コースが選択されていません。";
            return "registerFailure";
        }
    }

    public List<CourseDetails> getAllCourses() {
        return allCourses;
    }

    public void setAllCourses(List<CourseDetails> allCourses) {
        this.allCourses = allCourses;
    }

    public List<String> getSelectedCourseIds() {
        return selectedCourseIds;
    }

    public void setSelectedCourseIds(List<String> selectedCourseIds) {
        this.selectedCourseIds = selectedCourseIds;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
