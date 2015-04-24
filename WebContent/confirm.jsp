<!--
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	confirm.jsp
-->

<jsp:useBean id = "html" type = "gswilkenHW6.HtmlBean" scope = "session" />
<jsp:useBean id = "portfolio" type = "gswilkenHW6.PortfolioBean" scope = "session" />
<jsp:useBean id = "cart" type = "gswilkenHW6.CartBean" scope = "session" />

<!DOCTYPE html>
<html>
	<head>
		<title>E-Trade Application</title>
		<link rel = "stylesheet" type = "text/css" href = "styles.css" />
		<script src="bankJavaScript.js"></script>
	</head>
	<body>
		<table>
			<tr>
				<th colspan = "5">Transaction confirmation for: <jsp:getProperty name = "portfolio" property = "username" /></th>
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
				<td><jsp:getProperty name = "cart" property = "qty" /></td>
				<td class = "right"><jsp:getProperty name = "cart" property = "costCurrency" /></td>
				<td class = "right"><jsp:getProperty name = "cart" property = "subtotalCurrency" /></td>
			</tr>

			<form name = "f" >
				<input type = "hidden" name = "user" value = "<jsp:getProperty name = "portfolio" property = "username" />">
				<input type = "hidden" name = "cost" value = "<jsp:getProperty name = "cart" property = "subtotal" />">
				<input type = "hidden" name = "action" value = "<jsp:getProperty name = "cart" property = "action" />">
				<tr>
					<th colspan = "5"><input type = "button" name = "charge" value = "Charge Account" onclick = "message();"></th>
				</tr>
			</form>

		</table>
		<br>
		<div id = "results"></div>

<jsp:getProperty name = "html" property = "pageEnd" />