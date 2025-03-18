package Project.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import Project.model.CourseDetails;
import Project.service.StudentService;

import java.util.List;
import java.util.Map;

/**
 * Created by aliabbasjaffri on 25/11/2015.
 */
@Action(value = "courses-show", results = {
    @Result(name = "success", location = "/WEB-INF/content/showcourses.jsp")
})
public class ShowCoursesAction extends ActionSupport implements SessionAware
{
    private Map<String, Object> session;
    private List<CourseDetails> registeredCourses;
    private String message;

    public String execute() throws Exception
    {
        String username = (String) session.get("username");
        
        if (username == null || username.isEmpty()) {
            message = "ログインしてください。";
            return SUCCESS;
        }
        
        StudentService studentService = new StudentService();
        registeredCourses = studentService.showRegisteredCourses(username);
        
        if (registeredCourses == null) {
            message = "コースの取得に失敗しました。";
        } else if (registeredCourses.isEmpty()) {
            message = "登録済みのコースはありません。";
        } else {
            message = registeredCourses.size() + "件のコースが登録されています。";
        }
        
        return SUCCESS;
    }
    
    public List<CourseDetails> getRegisteredCourses() {
        return registeredCourses;
    }
    
    public void setRegisteredCourses(List<CourseDetails> registeredCourses) {
        this.registeredCourses = registeredCourses;
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
