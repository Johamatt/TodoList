package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAO {
	protected static Connection getDBConnection() {
		Connection connection = null;	
		
		DBAccount account = new DBAccount();
		String dbUsername = account.getDbUsername();
		String dbPassword = account.getDbPassword();
		String url = account.getUrl();

		try {
			Class.forName("org.mariadb.jdbc.Driver").newInstance();					//ladataan jdbc ajuri
			connection = DriverManager.getConnection(url, dbUsername, dbPassword); 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return connection;
	}
	protected static void closeDBConnection(Statement stmt, Connection connection) {		
		closeDBConnection(null, stmt, connection);
	}

	protected static void closeDBConnection(ResultSet rs, Statement stmt, Connection conn) {

		try {
			if (rs != null) {													// suljetaan seuraavat, jos niitä ei ole suoritettu loppuun.
				rs.close();
			}
			if (stmt != null) {					
				stmt.close();
			}
			if (conn != null) { 
				conn.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}