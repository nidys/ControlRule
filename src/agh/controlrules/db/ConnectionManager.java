package agh.controlrules.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import agh.controlrules.utils.Logger;

public class ConnectionManager {
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://127.0.0.1:5432/control_rules_db";
	private static final String DB_USER = "controlrules";
	private static final String DB_PASSWORD = "controlrules";

	public static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println();
			Logger.error(e.getMessage());
		}
		try {
			Logger.info("user=" +DB_USER);
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			Logger.error(e.getMessage());
		}
		return dbConnection;
	}
}
