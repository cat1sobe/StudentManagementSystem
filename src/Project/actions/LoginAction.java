package Project.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import Project.service.StudentService;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements SessionAware {

	private String pageName = "login";
	private String userName;
	private String password;
	private UserBean userBean;
	private Map<String, Object> session;

	@Action("login-input")
	public String input() throws Exception {
		return "login";
	}

	@Action("login")
	public String execute() throws Exception {
		String result = "";
		StudentService studentService = new StudentService();

		if (userBean != null) {
			if (userBean.getUserName() != null) {
				userName = userBean.getUserName();
			}
			if (userBean.getPassword() != null) {
				password = userBean.getPassword();
			}
		}

		if (pageName != null && studentService != null) {
			if (pageName.equals("login")) {
				result = studentService.findByLogin(userName, password);
				if (result.equals("LoginFailure")) {
					return "failure";
				} else {
					session.put("username", userName);
					return "success";
				}
			}
		}
		return SUCCESS;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getUserName() {
		return userName;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "UserName is a required field")
	@StringLengthFieldValidator(type = ValidatorType.FIELD, maxLength = "12", minLength = "6", message = "UserName must be of length between 6 and 12")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Password is a required field")
	@StringLengthFieldValidator(type = ValidatorType.FIELD, maxLength = "12", minLength = "6", message = "Password must be of length between 6 and 12")
	public void setPassword(String password) {
		this.password = password;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public static class UserBean {
		private String userName;
		private String password;

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
