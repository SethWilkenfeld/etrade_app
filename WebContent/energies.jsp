<!--
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	energies.jsp
-->

<jsp:useBean id = "html" type = "gswilkenHW6.HtmlBean" scope = "session" />
<jsp:useBean id = "portfolio" type = "gswilkenHW6.PortfolioBean" scope = "session" />

<jsp:getProperty name="html" property="pageStart" />

			<table>
				<tr><th colspan = "7">Energies</th></tr>
				<tr>
					<th>Name</th>
					<th>Currently Owned Shares</th>
					<th>Price per share</th>
					<th>Total value</th>
					<th>Action</th>
					<th>Quantity</th>
					<th></th>
				</tr>
				<form name = "dukeForm" action = "Transaction" method = "POST">
					<input type = "hidden" name = "stockID" value = "duke">
					<tr>
						<td>Duke Energy</td>
						<td><jsp:getProperty name = "portfolio" property = "dukeQty" /></td>
						<td class = "right"><jsp:getProperty name = "portfolio" property = "dukeCostCurrency" /></td>
						<td class = "right"><jsp:getProperty name = "portfolio" property = "dukeValueCurrency" /></td>
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
				<form name = "pgeForm" action = "Transaction" method = "POST">
					<input type = "hidden" name = "stockID" value = "pge">
					<tr>
						<td>PG&E</td>
						<td><jsp:getProperty name = "portfolio" property = "pgeQty" /></td>
						<td class = "right"><jsp:getProperty name = "portfolio" property = "pgeCostCurrency" /></td>
						<td class = "right"><jsp:getProperty name = "portfolio" property = "pgeValueCurrency" /></td>
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