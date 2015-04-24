/*
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	Transaction.java
*/

package gswilkenHW6;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Check for valid login */
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

		CartBean cart = (CartBean) session.getAttribute("cart"); // Get current cart
		if(cart == null) { // If no cart
			String stockID = (String) request.getParameter("stockID"); // Get stockID
			PortfolioBean portfolio = (PortfolioBean) session.getAttribute("portfolio"); // Get the user's portfolio

			/* Setup cart with purchase data */
			cart = new CartBean();
			cart.setStockID(stockID);
			cart.setAction((String) request.getParameter("actionSelect"));
			cart.setQty((String) request.getParameter("qty"));

			// Check which stock is in cart & set the cost & current qty
			if(stockID.equals("gold")) {
				cart.setCost((String) portfolio.getGoldCost());
				cart.setCurrentQty((String) portfolio.getGoldQty());
			}
			else if (stockID.equals("oil")) {
				cart.setCost((String) portfolio.getOilCost());
				cart.setCurrentQty((String) portfolio.getOilQty());
			}
			else if(stockID.equals("duke")) {
				cart.setCost((String) portfolio.getDukeCost());
				cart.setCurrentQty((String) portfolio.getDukeQty());
			}
			else if(stockID.equals("pge")) {
				cart.setCost((String) portfolio.getPgeCost());
				cart.setCurrentQty((String) portfolio.getPgeQty());
			}
		} else if(request.getParameter("newQty") != null) { // Current cart & change qty
			cart.setQty(request.getParameter("newQty"));
		}

		// Validate "sell" qty
		if(cart.getAction().equals("sell") && (Integer.parseInt(cart.getCurrentQty()) < Integer.parseInt(cart.getQty()))) {
			cart.setQty(cart.getCurrentQty());
		}

		// Load the jsp view
		session.setAttribute("cart", cart);
		String address = "transaction.jsp";
	    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
	    dispatcher.forward(request, response);
	}
}