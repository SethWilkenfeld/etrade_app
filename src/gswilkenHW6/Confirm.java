/*
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	Confirm.java
*/

package gswilkenHW6;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Confirm extends HttpServlet {
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
		/* Verify current login session */
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(username == null) {
			String address = "Login";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			String errStr = "Unauthorized access!";
			request.setAttribute("error", errStr);
			dispatcher.forward(request, response);
			return;
		}

		/* Get current cart or redirect to Catalog if doesn't exist */
		CartBean cart = (CartBean) session.getAttribute("cart");
		if(cart == null) {
			response.sendRedirect("Catalog");
		} else {
			PrintWriter out = response.getWriter();

			try {
				String stockID = cart.getStockID(); // Get the stockID
				int oldQty = 0, newQty; // Variables to store both qty's

				Class.forName(driver); // Load database driver if it's not already loaded.
				Connection connection = DriverManager.getConnection(url, dbUser, dbPass); // Establish network connection to database.
				Statement statement = connection.createStatement(); // Create a statement for executing queries.
				String query = "SELECT " + stockID + "Qty FROM portfolios WHERE userID = '" + username + "'"; // Query to submit
				ResultSet resultSet = statement.executeQuery(query); // Execute query

				if(resultSet.next()) {
					oldQty = Integer.parseInt(resultSet.getString(stockID + "Qty")); // Get the old qty
					resultSet.close();
				}

				if(cart.getAction().equals("buy")) // For action = buy
					newQty = (oldQty + Integer.parseInt(cart.getQty())); // Add the qty
				else // Sell
					newQty = (oldQty - Integer.parseInt(cart.getQty())); // Subtract the qty

				query = "UPDATE portfolios SET " + cart.getStockID() + "Qty = " + newQty + " WHERE userID = '" + username + "'"; // Query to submit
				resultSet = statement.executeQuery(query); // Execute query
				resultSet.next();

				connection.close();
			} catch(ClassNotFoundException cnfe) {
				out.println("Error loading driver: " + cnfe);
			} catch(SQLException sqle) {
				out.println("Error with connection: " + sqle);
			}
		}

		/* Load the jsp view */
		String address = "confirm.jsp";
	    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
	    dispatcher.forward(request, response);

	    session.removeAttribute("cart"); // Clear the cart
	}
}