package agh.controlrules.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import agh.controlrules.db.queries.tables.QueryCreator;
import agh.controlrules.utils.Logger;

public class DatabaseManager {

	/**
	 * 
	 * @param query
	 * @return generated primary key id, on error returns -1
	 */
	public static int insert(QueryCreator query) {
		Connection dbConnection = null;
		PreparedStatement statement = null;
		try {
			dbConnection = ConnectionManager.getDBConnection();
			statement = query.getInsertQuery(dbConnection);
			statement.executeUpdate();
			ResultSet generatedKey = statement.getGeneratedKeys();
			if (generatedKey.next())
				return (int) generatedKey.getLong(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeResources(dbConnection, statement);
		}
		Logger.error("Insert returned -1 !!!");
		return -1;
	}

	public static List<QueryCreator> select(QueryCreator query) {
		Connection dbConnection = null;
		Statement statement = null;
		try {
			dbConnection = ConnectionManager.getDBConnection();
			statement = dbConnection.createStatement();
			return query.getSelectResult(statement);
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			closeResources(dbConnection, statement);
		}
		return null;
	}

	private static void closeResources(Connection dbConnection, Statement statement) {
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
}