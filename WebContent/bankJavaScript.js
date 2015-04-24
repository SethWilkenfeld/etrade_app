/*
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	bankJavaScript.js
*/

var xmlHttp;

function message() {
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) {
		alert ("Browser does not support HTTP Request");
		return;
	} 

	var u = document.f.user.value; 
	var c = document.f.cost.value;
	// Added this variable to control buy/sell
	var a = document.f.action.value;
	// Edited URL and added action variable
	var hurl = "http://coit-servlet02.uncc.edu:8080/gswilken_Eservlet5/Bank?user=" + u + "&cost=" + c + "&action=" + a;

	xmlHttp.open("GET",hurl,true);
	xmlHttp.onreadystatechange=stateChanged;
	xmlHttp.send(null);
} 


function stateChanged() {
	if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
		var firstName = xmlHttp.responseXML.getElementsByTagName("FirstName")[0].childNodes[0].nodeValue;
		var lastName = xmlHttp.responseXML.getElementsByTagName("LastName")[0].childNodes[0].nodeValue;
		var Id = xmlHttp.responseXML.getElementsByTagName("Id")[0].childNodes[0].nodeValue;
		var Balance = xmlHttp.responseXML.getElementsByTagName("Balance")[0].childNodes[0].nodeValue;
		var Status = xmlHttp.responseXML.getElementsByTagName("Status")[0].childNodes[0].nodeValue;

		Balance = parseFloat(Balance);
		Balance = Balance.toFixed(2);

		// Edited for formatting
		var message = "<table><tr><td>Name: " + firstName + " " + lastName + "<br>Account ID: " + Id + "<br>Account Balance: $" + Balance + "<br>";
		var buttons = "<tr><th class = 'right'><a href = 'Catalog'>[Catalog]</a><a href = 'LogoutHandler'>[Log Out]</a></th></tr></table>";

		if(Status == "Unstable") {
			// Edited for formatting
			var message = message + "<h1>You Do Not Have Sufficient Funds! </h1><br></td></tr>";
		} else {
			var NewBalance = xmlHttp.responseXML.getElementsByTagName("NewBalance")[0].childNodes[0].nodeValue;
			NewBalance = parseFloat(NewBalance);
			NewBalance = NewBalance.toFixed(2);
			// Edited for formatting
			var message = message + "<h1>Thank you for your purchase! Your new balance is shown below.</h1></br>New Balance: $" + NewBalance + "<br></td></tr>";
		}

		// Edited for formatting
		document.getElementById("results").innerHTML = (message + buttons);
	} else {
    }
} 

function GetXmlHttpObject() { 
	var objXMLHttp = null;
	if(window.XMLHttpRequest) {
		objXMLHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		objXMLHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return objXMLHttp;
}