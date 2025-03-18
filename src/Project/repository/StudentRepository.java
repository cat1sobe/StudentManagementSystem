package Project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

import Project.model.CourseDetails;
import Project.model.RegisteredCourses;
import Project.model.User;
import Project.util.DbUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class StudentRepository
{
	//Simple JDBC
	private Connection dbConnection;

	//Hibernate Libraries
	private Configuration cfg;
	private SessionFactory factory;
	private Session session;
	private Transaction transaction;

	private static int RollNumber = 114000;

	public StudentRepository()
	{
		//JDBC
		dbConnection = DbUtil.getConnection();

		//Hibernate
		cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}

	public void save(String firstName, String lastName, String dateOfBirth, String Address , String PhoneNumber , String Username, String Password, String emailAddress)
	{
		if (dbConnection != null)
		{
			try
			{
				PreparedStatement prepStatement = dbConnection
						.prepareStatement("INSERT INTO User( id , Name , DOB , Address , Phone_Number , Roll_Number , Username , Password , EmailAddress  )  VALUES (DEFAULT , ? , ? , ? , ? , ? , ? , ? , ? )");
				prepStatement.setString(1, firstName + lastName);
				prepStatement.setDate(2, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dateOfBirth.substring(0, 10)).getTime()));
                prepStatement.setString(3, Address);
                prepStatement.setString(4, PhoneNumber);
				prepStatement.setString(5, String.valueOf(RollNumber++));
				prepStatement.setString(6, Username);
				prepStatement.setString(7, Password);
				prepStatement.setString(8, emailAddress);

				prepStatement.executeUpdate();

			}
            catch (SQLException | ParseException e)
            {
				e.printStackTrace();
			}
		}
	}

	public boolean findByUserName(String userName) {
		if (dbConnection != null) {
			try {
				PreparedStatement prepStatement = dbConnection
						.prepareStatement("select count(*) from User where UserName = ?");
				prepStatement.setString(1, userName);

				ResultSet result = prepStatement.executeQuery();
				if (result != null) {
					while (result.next()) {
						if (result.getInt(1) == 1) {
							return true;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean findByLogin(String userName, String password) {
		try {
			// デバッグ用のファイル出力
			java.io.FileWriter fw = new java.io.FileWriter("/tmp/debug.log", true);
			fw.write("findByLogin called with userName: " + userName + ", password: " + password + "\n");
			
			if (dbConnection != null) {
				fw.write("Database connection is not null\n");
				PreparedStatement prepStatement = dbConnection
						.prepareStatement("select Password from User where Username = ?");
				prepStatement.setString(1, userName);
				fw.write("Executing query: select Password from User where Username = '" + userName + "'\n");

				ResultSet result = prepStatement.executeQuery();
				fw.write("Query executed\n");
				if (result != null) {
					fw.write("ResultSet is not null\n");
					boolean hasRows = false;
					while (result.next()) {
						hasRows = true;
						String dbPassword = result.getString(1);
						fw.write("Found password in DB: " + dbPassword + "\n");
						if (dbPassword.equals(password)) {
							fw.write("Password matches!\n");
							fw.close();
							return true;
						} else {
							fw.write("Password does not match!\n");
						}
					}
					if (!hasRows) {
						fw.write("No rows found for username: " + userName + "\n");
					}
				} else {
					fw.write("ResultSet is null\n");
				}
			} else {
				fw.write("Database connection is null\n");
			}
			fw.close();
		} catch (Exception e) {
			try {
				java.io.FileWriter fw = new java.io.FileWriter("/tmp/debug.log", true);
				fw.write("Exception in findByLogin: " + e.getMessage() + "\n");
				e.printStackTrace(new java.io.PrintWriter(fw));
				fw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}

	public List<CourseDetails> showAllAvailableCourses()
	{
		try {
			System.out.println("showAllAvailableCourses called");
			transaction = session.beginTransaction();
			Query query = session.createQuery("from CourseDetails");
			List<CourseDetails> courses = query.list();
			transaction.commit();
			
			// デバッグ出力
			if (courses != null) {
				System.out.println("Found " + courses.size() + " courses");
				for (CourseDetails course : courses) {
					System.out.println("Course ID: " + course.getCourseId() + ", Name: " + course.getName());
				}
			} else {
				System.out.println("No courses found");
			}
			
			return courses;
		} catch (Exception e) {
			System.out.println("Exception in showAllAvailableCourses: " + e.getMessage());
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			return null;
		}
	}

	public List<CourseDetails> showAllCourses( String username )
	{
		Query query1 = session.createQuery("from User where username = :Username");
		query1.setParameter( "Username" , username);

		List <User> users = query1.list();

		String rollNumber = users.get(0).getRollNumber();

		Query query2 = session.createQuery("from RegisteredCourses where rollNumber = :RollNumber");
		query2.setParameter( "RollNumber" , rollNumber);
		List <RegisteredCourses> courses = query2.list();

		String values = "courseId LIKE '%";

		for( int i = 0; i < courses.size(); i++ )
		{
			values += courses.get(i).getCourseId();
			if(i != courses.size() - 1)
				values += "%' OR courseId LIKE '%";
		}
		values += "%'";

		Query query3 = session.createQuery("from CourseDetails where :CourseID");
		query3.setParameter( "CourseID" , values);
		List <CourseDetails> courseDetails = query3.list();

		return courseDetails;
	}

	public List<CourseDetails> showRegisteredCourses( String username )
	{
		try {
			System.out.println("showRegisteredCourses called with username: " + username);
			transaction = session.beginTransaction();
			
			// ユーザー情報を取得
			Query query1 = session.createQuery("from User where username = :Username");
			query1.setParameter("Username", username);
			List<User> users = query1.list();
			
			if (users.isEmpty()) {
				System.out.println("User not found with username: " + username);
				return null;
			}
			
			String rollNumber = users.get(0).getRollNumber();
			System.out.println("User roll number: " + rollNumber);
			
			// 登録済みコースのIDを取得
			Query query2 = session.createQuery("from RegisteredCourses where rollNumber = :RollNumber");
			query2.setParameter("RollNumber", rollNumber);
			List<RegisteredCourses> registeredCourses = query2.list();
			
			if (registeredCourses.isEmpty()) {
				System.out.println("No registered courses found for user: " + username);
				transaction.commit();
				return new ArrayList<>();
			}
			
			// 登録済みコースのIDのリストを作成
			List<String> courseIds = new ArrayList<>();
			for (RegisteredCourses course : registeredCourses) {
				courseIds.add(course.getCourseId());
			}
			
			// コースの詳細情報を取得
			Query query3 = session.createQuery("from CourseDetails where courseId in (:courseIds)");
			query3.setParameterList("courseIds", courseIds);
			List<CourseDetails> courseDetails = query3.list();
			
			transaction.commit();
			return courseDetails;
		} catch (Exception e) {
			System.out.println("Exception in showRegisteredCourses: " + e.getMessage());
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			return null;
		}
	}

	public boolean registerCourses(String username, List<String> courseIds)
	{
		try {
			// デバッグログ出力
			System.out.println("registerCourses called with username: " + username);
			System.out.println("Selected course IDs: " + courseIds);
			
			transaction = session.beginTransaction();
			
			// ユーザー情報を取得
			Query query1 = session.createQuery("from User where username = :Username");
			query1.setParameter("Username", username);
			List<User> users = query1.list();
			
			System.out.println("User query result size: " + users.size());
			
			if (users.isEmpty()) {
				System.out.println("User not found with username: " + username);
				return false;
			}
			
			String rollNumber = users.get(0).getRollNumber();
			System.out.println("User roll number: " + rollNumber);
			
			// 選択されたコースを登録
			for (String courseId : courseIds) {
				System.out.println("Processing course ID: " + courseId);
				
				// コースIDを使用してコース情報を取得
				Query courseQuery = session.createQuery("from CourseDetails where courseId = :courseId");
				courseQuery.setParameter("courseId", courseId);
				List<CourseDetails> courses = courseQuery.list();
				
				if (courses.isEmpty()) {
					System.out.println("Course not found with ID: " + courseId);
					continue;
				}
				
				RegisteredCourses registeredCourse = new RegisteredCourses();
				registeredCourse.setRollNumber(rollNumber);
				registeredCourse.setCourseId(courseId);
				System.out.println("Registering course with ID: " + courseId);
				
				session.save(registeredCourse);
			}
			
			transaction.commit();
			System.out.println("Transaction committed successfully");
			return true;
		} catch (Exception e) {
			System.out.println("Exception in registerCourses: " + e.getMessage());
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
				System.out.println("Transaction rolled back");
			}
			return false;
		}
	}

}


