package agh.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import agh.db.utils.DatabaseManager;

public class ControlRule {
	public static void main(String[] argv) {

		System.out.println("-------- PostgreSQL " + "JDBC Connection Testing ------------");
		Connection dbConnection = null;
		Statement statement = null;

		// ;

		String insertTableSQL = "insert into control_rules (rule_id, rule_name, type) values (2, '1a1b', 'r')";

		try {
			dbConnection = DatabaseManager.getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(insertTableSQL);
			statement.executeUpdate(insertTableSQL);
			System.out.println("Record is inserted into DBUSER table!");
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		System.out.println("Just when I thought I was out...they pull me back in");
	}
}
