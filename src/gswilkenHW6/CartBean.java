/*
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	CartBean.java
*/

package gswilkenHW6;

import java.text.NumberFormat;

public class CartBean {
	NumberFormat formatter = NumberFormat.getCurrencyInstance(); // Formatter for currency

	/* Stores data about the user's cart */
	private String stockID;
	private String action;
	private String qty;
	private String cost;
	private String currentQty;

	public String getStockID() {
		return stockID;
	}

	public String getAction() {
		return action;
	}

	public String getQty() {
		return qty;
	}

	public String getCurrentQty() {
		return currentQty;
	}

	public String getCostCurrency() {
		return formatter.format(Double.parseDouble(cost));
	}

	public String getCost() {
		return cost;
	}

	public String getSubtotalCurrency() {
		return formatter.format(Double.parseDouble(cost) * Double.parseDouble(qty));
	}

	public String getSubtotal() {
		return Double.toString(Double.parseDouble(cost) * Double.parseDouble(qty));
	}

	public void setStockID(String id) {
		stockID = id;
	}

	public void setAction(String a) {
		action = a;
	}

	public void setQty(String q) {
		qty = q;
	}

	public void setCost(String c) {
		cost = c;
	}

	public void setCurrentQty(String qty) {
		currentQty = qty;
	}
}