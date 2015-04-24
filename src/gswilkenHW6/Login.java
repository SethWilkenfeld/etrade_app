/*
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	Login.java
*/

package gswilkenHW6;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String driver;
	private String url;
	private String dbUser;
	private String dbPass;

	public void init(ServletConfig config) throws ServletException {
		/* Load the DB config data */
		OracleConfig oracleConfig = new OracleConfig();
		driver = oracleConfig.getDriver();
		url = oracleConfig.getUrl();
		dbUser = oracleConfig.getDbUser();
		dbPass = oracleConfig.getDbPass();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Create a session & store the html bean */
		HttpSession session = request.getSession();
		HtmlBean html = new HtmlBean();
		session.setAttribute("html", html);

		/* Get form data */
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		PrintWriter out = response.getWriter(); // Create the output object

		if(username != null) { // Username submitted
			if(username.equals("")) { // Username blank
				String err = "Username cannot be blank!";
				request.setAttribute("error", err);
			} else { // Form data good
				try {
					Class.forName(driver); // Load database driver if it's not already loaded.
					Connection connection = DriverManager.getConnection(url, dbUser, dbPass); // Establish network connection to database.
					Statement statement = connection.createStatement(); // Create a statement for executing queries.
					String query = "SELECT * FROM USERS WHERE username = '" + username + "'"; // Query to submit
					ResultSet resultSet = statement.executeQuery(query); // Execute query

					/* Username & password match = login user & forward to Catalog */
					if(resultSet.next()) {
						if(resultSet.getString("password").equals(password)) {
							session.setAttribute("username", username);
							response.sendRedirect("Catalog");
						}
						else { // Setup error
							String err = "Invalid credentials, try again";
							request.setAttribute("error", err);
						}
					}
					else { // Setup error
						String err = "Invalid credentials, try again";
						request.setAttribute("error", err);
					}
					connection.close();
				} catch(ClassNotFoundException cnfe) {
					out.println("Error loading driver: " + cnfe);
				} catch(SQLException sqle) {
					out.println("Error with connection: " + sqle);
				}
			}
		}
		printLoginForm(request, out);
	}

	/* Prints the login form w/ variable error data */
	private void printLoginForm(HttpServletRequest request, PrintWriter out) throws IOException {
		String errorMsg = (String) request.getAttribute("error");
		HttpSession session = request.getSession();
		HtmlBean htmlBean = (HtmlBean) session.getAttribute("html");

		out.println(htmlBean.getPageStart());
		out.println(
			"	<form name='loginForm' class='credentials' action='Login' method='POST'>" + "\n" +
			"		<fieldset>" + "\n" +
			"			<legend>User Login</legend>" + "\n" +
			"			<p>" + "\n" +
			"				<label for='username'>Username</label>" + "\n" +
			"				<input type='text' name='username'>" + "\n" +
			"			</p>" + "\n" +
			"			<p>" + "\n" +
			"				<label for='password'>Password</label>" + "\n" +
			"				<input type='password' name='password'>" + "\n" +
			"			</p>" + "\n" +
			"			<div class='errorDiv'>" + "\n"
		);

		if(errorMsg != null)
			out.println("	<div class = 'errorMsg'>" + errorMsg + "</div>" + "\n");

		out.println(
			"			</div>" + "\n" +
			"			<p>" + "\n" +
			"				<input type='Submit' value='Submit' name='Submit'>" + "\n" +
			"				<input type='reset' value='Reset'>" + "\n" +
			"			</p>" + "\n" +
			"		</fieldset>" + "\n" +
			"		<p>Don't have an account? <a href='Register'>Register here!</a></p>" + "\n" + // Link to registration page
			"	</form>" + "\n"
		);
		out.println(htmlBean.getPageEnd());
	}
}