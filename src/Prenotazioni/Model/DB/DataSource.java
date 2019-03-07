package Prenotazioni.Model.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	
	private static final String jdbcURL = "jdbc:mysql://localhost/adminbus?user=root";
	private static Connection conn;

	public static Connection getConnection() {
		try {

			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(jdbcURL);
			}

		} catch (SQLException e) {
			System.err.println("Errore connessione al DB");
			throw new RuntimeException(e);
		}
		return conn;
	}
}
