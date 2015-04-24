/*
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	Category.java
*/

package gswilkenHW6;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Validate login session */
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

		String address = "";
		if(request.getParameter("id") != null) { // Set the appropriate file name
			address = ((String) request.getParameter("id") + ".jsp");
		} else {
			address = "Catalog";
		}

		/* Load the jsp view */
	    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}