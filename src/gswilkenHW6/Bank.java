/*
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	Bank.java
*/

package gswilkenHW6;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Bank extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the username from the request
		String user = (String) request.getParameter("user");
		String costStr = (String) request.getParameter("cost");
		String action = (String) request.getParameter("action");

		double cost = Double.parseDouble(costStr); // Convert to doublee for use

		// Query the BankCustomer class (provided) to check for sufficient funds
		BankSOAP bs = new gswilkenHW6.BankSOAP();

		// Check for available funds
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();

		boolean success = false; // Control flag

		String firstName = bs.getValue(bs.getResponseFromWebService("gswilken", "getFirstName", user), "getFirstName");
		String lastName = bs.getValue(bs.getResponseFromWebService("gswilken", "getLastName", user), "getLastName");
		double oldBalance = Double.parseDouble(bs.getValue(bs.getResponseFromWebService("gswilken", "getBalance", user), "getBalance"));

		// Take appropriate action (buy/sell)
		if(action.equals("buy")) {
			if(oldBalance > cost) { //  If there's anough money to buy
				bs.setBalanceInWebService("gswilken", "setBalance", user, (oldBalance - cost)); // New balance
				success = true; // Trip flag
			}
		} else {
			bs.setBalanceInWebService("gswilken", "setBalance", user, (oldBalance - cost)); // New balance
			success = true; // Trip flag
		}

		double newBalance = Double.parseDouble(bs.getValue(bs.getResponseFromWebService("gswilken", "getBalance", user), "getBalance"));

		// Output appropriate message
		if(success) {
			// Output SUCCESS message
			out.println(
				"<bank_response>" + "\n" +
				"	<FirstName>" + firstName + "</FirstName>" + "\n" +
				"	<LastName>" + lastName + "</LastName>" + "\n" +
				"	<Id>" + user + "</Id>" + "\n" +
				"	<Balance>" + oldBalance + "</Balance>" + "\n" +
				"	<Status>Stable</Status>" + "\n" +
				"	<NewBalance>" + newBalance + "</NewBalance>" + "\n" +
				"</bank_response>" + "\n"
			);
		} else {
			// Output FAIL message
			out.println(
				"<bank_response>" + "\n" +
				"	<FirstName>" + firstName + "</FirstName>" + "\n" +
				"	<LastName>" + lastName + "</LastName>" + "\n" +
				"	<Id>" + user + "</Id>" + "\n" +
				"	<Balance>" + oldBalance + "</Balance>" + "\n" +
				"	<Status>Unstable</Status>" + "\n" +
				"</bank_response>" + "\n"
			);
		}
	}
}