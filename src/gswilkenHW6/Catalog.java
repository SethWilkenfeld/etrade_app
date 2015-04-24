/*
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	Catalog.java
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

public class Catalog extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String driver;
	private String url;
	private String dbUser;
	private String dbPass;

	public void init(ServletConfig config) throws ServletException {
		/* Get the DB config data */
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
		/* Verify login session */
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

		PrintWriter out = response.getWriter();

		/* Create a portfolio object & load data */
		PortfolioBean portfolio = new PortfolioBean();
		portfolio.setUsername(username);
		try {
			Class.forName(driver); // Load database driver if it's not already loaded.
			Connection connection = DriverManager.getConnection(url, dbUser, dbPass); // Establish network connection to database.
			Statement statement = connection.createStatement(); // Create a statement for executing queries.

			String query = "SELECT * FROM portfolios WHERE userID = '" + username + "'"; // Query to submit
			ResultSet resultSet = statement.executeQuery(query); // Execute query

			/* Get the portfolio data */
			if(resultSet.next()) {
				portfolio.setGoldQty(resultSet.getString("goldQty"));
				portfolio.setOilQty(resultSet.getString("oilQty"));
				portfolio.setDukeQty(resultSet.getString("dukeQty"));
				portfolio.setPgeQty(resultSet.getString("pgeQty"));
			}
			else {
				// Create a new portfolio?
			}

			resultSet.close();
			query = "SELECT * FROM catalog"; // Query to submit
			resultSet = statement.executeQuery(query); // Execute query

			double gv = 0, ov = 0, dv = 0, pv = 0;

			/* Get the invested value data */
			while(resultSet.next()) {
				String stock = resultSet.getString("stock");
				if(stock.equals("gold")) {
					portfolio.setGoldCost(resultSet.getString("value"));
					gv = Double.parseDouble(portfolio.getGoldQty()) * Double.parseDouble(resultSet.getString("value"));
					portfolio.setGoldValue(Double.toString(gv));
				} else if(stock.equals("oil")) {
					portfolio.setOilCost(resultSet.getString("value"));
					ov = Double.parseDouble(portfolio.getOilQty()) * Double.parseDouble(resultSet.getString("value"));
					portfolio.setOilValue(Double.toString(ov));
				} else if(stock.equals("duke")) {
					portfolio.setDukeCost(resultSet.getString("value"));
					dv = Double.parseDouble(portfolio.getDukeQty()) * Double.parseDouble(resultSet.getString("value"));
					portfolio.setDukeValue(Double.toString(dv));
				} else if(stock.equals("pge")) {
					portfolio.setPgeCost(resultSet.getString("value"));
					pv = Double.parseDouble(portfolio.getPgeQty()) * Double.parseDouble(resultSet.getString("value"));
					portfolio.setPgeValue(Double.toString(pv));
				}
			}

			/* Set the category totals */
			double tc = gv + ov;
			portfolio.setTotalCommodities(Double.toString(tc));
			double te = dv + pv;
			portfolio.setTotalEnergies(Double.toString(te));
			double ti = tc + te;
			portfolio.setTotalInvested(Double.toString(ti));

			connection.close();
		} catch(ClassNotFoundException cnfe) {
			out.println("Error loading driver: " + cnfe);
		} catch(SQLException sqle) {
			out.println("Error with connection: " + sqle);
		}

		session.setAttribute("portfolio", portfolio);

		String address = "catalog.jsp";
	    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
	    dispatcher.forward(request, response);
	}
}