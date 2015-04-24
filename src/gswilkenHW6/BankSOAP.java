package gswilkenHW6;

import java.io.*;
import java.net.*;

class BankSOAP {
    
	public String getResponseFromWebService(String studentID, String methodName, String userID) {
		return post(studentID, methodName, userID, 0.00);
	}

	public String setBalanceInWebService(String studentID, String methodName, String userID, double balance) {
		return post(studentID, methodName, userID, balance);
	}

	private byte[] marshall(String methodName, String userID, double balance) { // Adding the SOAP Headers
		StringBuffer payload = new StringBuffer("<?xml version=\'1.0\' encoding=\'UTF-8\'?>");
		payload.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
		payload.append("<soap:Body>");
		payload.append("<"+methodName+" xmlns=\"http://tempuri.org/\">"); // The Web Service that is being called
		payload.append("<userID>"+userID+"</userID>");
		payload.append("<balance>"+Double.toString(balance)+"</balance>");
		payload.append("</"+methodName+">");
		payload.append("</soap:Body>");
		payload.append("</soap:Envelope>\r\n");
		return payload.toString().getBytes();
    }

	private String post(String studentID, String methodName, String userID, double balance) {
		try {
			byte[] request = marshall(methodName, userID, balance);
			String returning="";
			URL endpoint = new URL("http://coit-ts2003.uncc.edu/" + studentID + "/Service.asmx"); //URL of the Web Service
			URLConnection con = endpoint.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setAllowUserInteraction(false);
			con.setRequestProperty("Content-Length", Integer.toString(request.length));
			con.setRequestProperty("Content-Type", "text/xml");
			con.setRequestProperty("SOAPAction","\"http://tempuri.org/"+methodName+"\"");//the webmethod To be accessed.
			OutputStream out = con.getOutputStream();
			out.write(request);
			out.flush();
			out.close();
			InputStream in = con.getInputStream();
			int i= -1;
			while((i = in.read()) > -1) {
				returning=returning+(char)i;
			}
			return(returning); // returning the data from webservice
		} catch(MalformedURLException e1) {
			return(e1.getMessage());
		} catch(IOException e2) {
			return(e2.getMessage());
		}
	}

	// use this method to split out the result field from an xml string that includes a method name
	public String getValue(String xmlSting, String methodName) {
		String[] splitter = xmlSting.split("<"+methodName+"Result>");
		if (splitter.length < 2) {
			return null;
		}
		String[] splitter2 = splitter[1].split("</"+methodName+"Result>");
		if (splitter.length < 2) {
			return null;
		}
		return new String(splitter2[0]);
	}
}