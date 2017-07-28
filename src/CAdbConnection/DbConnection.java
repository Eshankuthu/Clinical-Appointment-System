package CAdbConnection;

import java.sql.*;

public class DbConnection {
	private static DbConnection dbCon = new DbConnection();
	Connection con;

	private DbConnection() {
		connect();
	}

	public static DbConnection getInstance() {
		return dbCon;
	}

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cas?autoReconnect=true&useSSL=false", "root",
					"admin");
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	public void close() {
		try {
			con.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	public void executeQuery(String query) {
		//connect();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			//con.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	public ResultSet fetchResult(String query) {
		//connect();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// con.close();
			return rs;

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return null;
	}
}
