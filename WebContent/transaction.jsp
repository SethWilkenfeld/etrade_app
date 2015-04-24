<!--
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	transaction.jsp
-->

<jsp:useBean id = "html" type = "gswilkenHW6.HtmlBean" scope = "session" />
<jsp:useBean id = "portfolio" type = "gswilkenHW6.PortfolioBean" scope = "session" />
<jsp:useBean id = "cart" type = "gswilkenHW6.CartBean" scope = "session" />

<jsp:getProperty name="html" property="pageStart" />

			<table id = "confirmation">
				<tr>
					<th colspan = "5">Transaction details for: <jsp:getProperty name = "portfolio" property = "username" /></th>
				</tr>
				<tr>
					<th>Stock name</th>
					<th>Action</th>
					<th>Quantity</th>
					<th>Price per share</th>
					<th>Total value</th>
				</tr>
				<tr>
					<td><jsp:getProperty name = "cart" property = "stockID" /></td>
					<td><jsp:getProperty name = "cart" property = "action" /></td>
					<td>
						<form name = "qtyForm" action = "Transaction" method = "POST">
							<input type = "number" name = "newQty" min = "0" value = "<jsp:getProperty name = "cart" property = "qty" />">
							<input type = "submit" value = "Change" name = "submit">
						</form>
					</td>
					<td class = "right"><jsp:getProperty name = "cart" property = "costCurrency" /></td>
					<td class = "right"><jsp:getProperty name = "cart" property = "subtotalCurrency" /></td>
				</tr>
				<form name = "banking" action = "Confirm" method = "Post">
					<tr>
						<th colspan = "5">Bank account information</th>
					</tr>
					<tr>
						<td colspan = "3">Account holder name:</td>
						<td colspan = "2"><input type = "text" name = "name" value = "<jsp:getProperty name = "portfolio" property = "username" />"></td>
					</tr>
					<tr>
						<td colspan = "3">Routing number:</td>
						<td colspan = "2"><input type = "text" name = "routing" value = ""></td>
					</tr>
					<tr>
						<td colspan = "3">Account number:</td>
						<td colspan = "2"><input type = "text" name = "account" value = ""></td>
					</tr>
					<tr>
						<th colspan = "3">
							<input type = "Submit" value = "Submit" name = "Submit">
							<input type = "reset" value = "Reset">
						</th>
						<th colspan = "2">
							<a href = "Catalog">[Catalog]</a>
							<a href = "LogoutHandler">[Log Out]</a>
						</th>
					</tr>
				</form>
			</table>

<jsp:getProperty name = "html" property = "pageEnd" />