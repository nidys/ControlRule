package agh.controlrules.db.queries.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import agh.controlrules.utils.Logger;

public abstract class QueryCreator {
	protected String insertSql;
	protected String selectSql;

	public PreparedStatement getInsertQuery(Connection connection) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
		return fillStmt(stmt);
	}
	public List<QueryCreator> getSelectResult(Statement statement) throws SQLException {
		Logger.info("Executing = %s", selectSql);
		ResultSet rs = statement.executeQuery(selectSql);
		List<QueryCreator> result = new ArrayList<QueryCreator>();
		while(rs.next()) {
			result.add(getInstrance(rs));
		}
		return result;
	}

	protected abstract PreparedStatement fillStmt(PreparedStatement stmt) throws SQLException;
	
	protected QueryCreator getInstrance(ResultSet rs) throws SQLException {
		return null;
	}
	public String asString() {
		return null;
	}
}
