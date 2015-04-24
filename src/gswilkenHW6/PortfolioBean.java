/*
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	PortfolioBean.java
*/

package gswilkenHW6;

import java.text.NumberFormat;

public class PortfolioBean {
	NumberFormat formatter = NumberFormat.getCurrencyInstance(); // Formatter for currency

	private String username;

	/* Qty owned */
	private String goldQty;
	private String oilQty;
	private String pgeQty;
	private String dukeQty;

	/* Cost of a single share */
	private String goldCost;
	private String oilCost;
	private String pgeCost;
	private String dukeCost;

	/* Invested subtotals */
	private String totalInvested;
	private String totalCommodities;
	private String totalEnergies;

	/* Value of currently owned shares (sum) */
	private String goldValue;
	private String oilValue;
	private String pgeValue;
	private String dukeValue;

	public PortfolioBean() {
		username = "Error!";
	}

/* User */
	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		username = name;
	}

/* User totals */
	/* Getters */
	public String getTotalInvestedCurrency() {
		return formatter.format(Double.parseDouble(totalInvested));
	}

	public String getTotalCommoditiesCurrency() {
		return formatter.format(Double.parseDouble(totalCommodities));
	}

	public String getTotalEnergiesCurrency() {
		return formatter.format(Double.parseDouble(totalEnergies));
	}

	public String getTotalInvested() {
		return totalInvested;
	}

	public String getTotalCommodities() {
		return totalCommodities;
	}

	public String getTotalEnergies() {
		return totalEnergies;
	}

	/* Setters */
	public void setTotalInvested(String total) {
		totalInvested = total;
	}

	public void setTotalCommodities(String total) {
		totalCommodities = total;
	}

	public void setTotalEnergies(String total) {
		totalEnergies = total;
	}

/* Get the cost of a single share of */
	public String getGoldValueCurrency() {
		return formatter.format(Double.parseDouble(goldValue));
	}

	public String getOilValueCurrency() {
		return formatter.format(Double.parseDouble(oilValue));
	}

	public String getPgeValueCurrency() {
		return formatter.format(Double.parseDouble(pgeValue));
	}

	public String getDukeValueCurrency() {
		return formatter.format(Double.parseDouble(dukeValue));
	}

	public String getGoldValue() {
		return goldValue;
	}

	public String getOilValue() {
		return oilValue;
	}

	public String getPgeValue() {
		return pgeValue;
	}

	public String getDukeValue() {
		return dukeValue;
	}

	/* Get the cost of a single share of */
	public void setGoldValue(String value) {
		goldValue = value;
	}

	public void setOilValue(String value) {
		oilValue = value;
	}

	public void setPgeValue(String value) {
		pgeValue = value;
	}

	public void setDukeValue(String value) {
		dukeValue = value;
	}

/* Get the QTY of each investment owned by this user */
	public String getGoldQty() {
		return goldQty;
	}

	public String getOilQty() {
		return oilQty;
	}

	public String getPgeQty() {
		return pgeQty;
	}

	public String getDukeQty() {
		return dukeQty;
	}

/* Set the QTY owned by this user */
	public void setGoldQty(String qty) {
		goldQty = qty;
	}

	public void setOilQty(String qty) {
		oilQty = qty;
	}

	public void setPgeQty(String qty) {
		pgeQty = qty;
	}

	public void setDukeQty(String qty) {
		dukeQty = qty;
	}

/* Get the cost of a single share of */
	public String getGoldCostCurrency() {
		return formatter.format(Double.parseDouble(goldCost));
	}

	public String getOilCostCurrency() {
		return formatter.format(Double.parseDouble(oilCost));
	}

	public String getPgeCostCurrency() {
		return formatter.format(Double.parseDouble(pgeCost));
	}

	public String getDukeCostCurrency() {
		return formatter.format(Double.parseDouble(dukeCost));
	}

	public String getGoldCost() {
		return goldCost;
	}

	public String getOilCost() {
		return oilCost;
	}

	public String getPgeCost() {
		return pgeCost;
	}

	public String getDukeCost() {
		return dukeCost;
	}

/* Set the cost of a single share for each stock */
	public void setGoldCost(String cost) {
		goldCost = cost;
	}

	public void setOilCost(String cost) {
		oilCost = cost;
	}

	public void setPgeCost(String cost) {
		pgeCost = cost;
	}

	public void setDukeCost(String cost) {
		dukeCost = cost;
	}
}