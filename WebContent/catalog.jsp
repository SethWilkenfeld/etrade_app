<!--
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	catalog.jsp
-->

<jsp:useBean id = "html" type = "gswilkenHW6.HtmlBean" scope = "session" />
<jsp:useBean id = "portfolio" type = "gswilkenHW6.PortfolioBean" scope = "session" />

<jsp:getProperty name="html" property="pageStart" />

		<table id = "welcome">
			<tr><th colspan="2">Welcome <jsp:getProperty name = "portfolio" property = "username" /></th></tr>
			<tr class = "light"><td>You currently have a total of <jsp:getProperty name = "portfolio" property = "totalInvestedCurrency" /> invested</td></tr>
			<tr class = "dark"><td>Please select from the list of stock categories below.</td></tr>
		</table>
		<br>
		<table id = "categories">
			<tr><th class = "left">Type of investment</th><th class = "right">Invested in category</th></tr>
			<tr class = "light">
				<td>
					<a href = "Category?id=commodities">Commodities</a>
				</td>
				<td class = "right">
					<jsp:getProperty name = "portfolio" property = "totalCommoditiesCurrency" />
				</td>
			</tr>
			<tr class = "dark">
				<td>
					<a href = "Category?id=energies">Energy</a>
				</td>
				<td class = "right">
					<jsp:getProperty name = "portfolio" property = "totalEnergiesCurrency" />
				</td>
			</tr>
		</table>
		<p class = "center">
			<a href = "LogoutHandler">[Log Out]</a>
		</p>

<jsp:getProperty name = "html" property = "pageEnd" />