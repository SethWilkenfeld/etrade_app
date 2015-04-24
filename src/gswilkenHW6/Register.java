/*
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	Register.java
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

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String driver;
	private String url;
	private String dbUser;
	private String dbPass;

	public void init(ServletConfig config) throws ServletException {
		/* Load the DB setup */
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

		/* Get data */
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirm");

		PrintWriter out = response.getWriter(); // Create the output object

		if(username != null) {
			if(username.equals("")) { // Username blank
				String err = "Username cannot be blank!";
				request.setAttribute("error", err);
			} else if(!password.equals(confirm)) { // Password doesn't match confirm
				String err = "Password & Confirm do not match!";
				request.setAttribute("error", err);
			} else { // Form data good
				try { // Query the DB
					Class.forName(driver); // Load database driver if it's not already loaded.
					Connection connection = DriverManager.getConnection(url, dbUser, dbPass); // Establish network connection to database.
					Statement statement = connection.createStatement(); // Create a statement for executing queries.
					String query = "SELECT * FROM USERS WHERE username = '" + username + "'"; // Query to submit
					ResultSet resultSet = statement.executeQuery(query); // Execute query

					if(resultSet.next()) { // Verify unique user
						String err = "Username already in use";
						request.setAttribute("error", err);
					}
					else { // Create new user
						resultSet.close();
						query = "INSERT INTO USERS (username, password) VALUES ('" + username + "', '" + password + "')"; // Query to submit
						resultSet = statement.executeQuery(query); // Execute query

						resultSet.close();
						query = "INSERT INTO portfolios (userID, goldQty, oilQty, pgeQty, dukeQty) VALUES ('" + username + "', 0, 0, 0, 0)"; // Query to submit
						resultSet = statement.executeQuery(query); // Execute query

						session.setAttribute("username", username);
						response.sendRedirect("Catalog");
					}
					connection.close();
				} catch(ClassNotFoundException cnfe) {
					out.println("Error loading driver: " + cnfe);
				} catch(SQLException sqle) {
					out.println("Error with connection: " + sqle);
				}
			}
		}

		printRegisterForm(request, out);
	}

	/* Just prints the form with variable error data */
	private void printRegisterForm(HttpServletRequest request, PrintWriter out) throws IOException {
		String errorMsg = (String) request.getAttribute("error");
		HttpSession session = request.getSession();
		HtmlBean htmlBean = (HtmlBean) session.getAttribute("html");

		out.println(htmlBean.getPageStart());
		out.println(
			"	<form name='registerForm' class='credentials' action='Register' method='POST'>" + "\n" +
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
			"			<p>" + "\n" +
			"				<label for='confirm'>Confirm </label>" + "\n" +
			"				<input type='password' name='confirm'>" + "\n" +
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
			"	</form>" + "\n"
		);
		out.println(htmlBean.getPageEnd());
	}
}