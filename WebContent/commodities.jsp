<!--
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	commodities.jsp
-->

<jsp:useBean id = "html" type = "gswilkenHW6.HtmlBean" scope = "session" />
<jsp:useBean id = "portfolio" type = "gswilkenHW6.PortfolioBean" scope = "session" />

<jsp:getProperty name="html" property="pageStart" />

			<table>
				<tr><th colspan = "7">Commodities</th></tr>
				<tr>
					<th>Name</th>
					<th>Currently Owned Shares</th>
					<th>Price per share</th>
					<th>Total value</th>
					<th>Action</th>
					<th>Quantity</th>
					<th></th>
				</tr>
				<form name = "goldForm" action = "Transaction" method = "POST">
					<input type = "hidden" name = "stockID" value = "gold">
					<tr>
						<td>Gold</td>
						<td><jsp:getProperty name = "portfolio" property = "goldQty" /></td>
						<td class = "right"><jsp:getProperty name = "portfolio" property = "goldCostCurrency" /></td>
						<td class = "right"><jsp:getProperty name = "portfolio" property = "goldValueCurrency" /></td>
						<td>
							<select name = "actionSelect">
								<option value = "buy">Buy</option>
								<option value = "sell">Sell</option>
							</select>
						</td>
						<td>
							<input type = "number" name = "qty" min = "0" value = "0">
						</td>
						<td>
							<input type = "Submit" value = "Make Transaction" name = "Submit">
						</td>
					</tr>
				</form>
				<form name = "oilForm" action = "Transaction" method = "POST">
					<input type = "hidden" name = "stockID" value = "oil">
					<tr>
						<td>Crude Oil</td>
						<td><jsp:getProperty name = "portfolio" property = "oilQty" /></td>
						<td class = "right"><jsp:getProperty name = "portfolio" property = "oilCostCurrency" /></td>
						<td class = "right"><jsp:getProperty name = "portfolio" property = "oilValueCurrency" /></td>
						<td>
							<select name = "actionSelect">
								<option value = "buy">Buy</option>
								<option value = "sell">Sell</option>
							</select>
						</td>
						<td>
							<input type = "number" name = "qty" min = "0" value = "0">
						</td>
						<td>
							<input type = "Submit" value = "Make Transaction" name = "Submit">
						</td>
					</tr>
				</form>
				<tr>
					<th colspan = "6">
						User: <jsp:getProperty name = "portfolio" property = "username" />
					</th>
					<th>
						<a href = "Catalog">[Catalog]</a>
						<a href = "LogoutHandler">[Log Out]</a>
					</th>
				</tr>
			</table>

<jsp:getProperty name = "html" property = "pageEnd" />