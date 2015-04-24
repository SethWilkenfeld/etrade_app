/*
	Gardner Wilkenfeld
	ITIS - 4166 - 001
	11/06/2012
	OracleConfig.java
*/

package gswilkenHW6;

/* Stores the DB config data */
public class OracleConfig {
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@cci-ora02.uncc.edu:1521:class";
	private final String DB_USER = "gswilken"; // Use your ninerID here.
	private final String DB_PASS = "qwe123";

	public String getDriver() {
		return DRIVER;
	}

	public String getUrl() {
		return URL;
	}

	public String getDbUser() {
		return DB_USER;
	}

	public String getDbPass() {
		return DB_PASS;
	}
}