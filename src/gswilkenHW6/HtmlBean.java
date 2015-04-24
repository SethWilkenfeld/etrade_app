/*
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	HtmlBean.java
*/

package gswilkenHW6;

public class HtmlBean {
	public String getPageStart() {
		return pageStart;
	}

	public String getPageEnd() {
		return pageEnd;
	}

	/* Concept & name from HW3-portner */
	private String pageStart = (
		"<!DOCTYPE html>\n" + // Set DOCTYPE to HTML5
		"<html>" + "\n" +
		"	<head>" + "\n" +
		"		<title>E-Trade Application</title>" + "\n" +
		"		<link rel = \"stylesheet\" type = \"text/css\" href = \"styles.css\" />" + "\n" + // External CSS
		"	</head>" + "\n" +
		"	<body>" + "\n"
	);

	/* Concept & name from HW3-portner */
	private String pageEnd = (
		"	</body>" + "\n" +
		"</html>"
	);
}