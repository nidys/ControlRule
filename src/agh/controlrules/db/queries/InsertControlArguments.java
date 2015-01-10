package agh.controlrules.db.queries;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import agh.controlrules.db.queries.tables.ControlArguments;
import agh.controlrules.utils.Logger;

public class InsertControlArguments extends ControlArguments {

	public InsertControlArguments(int fk_condition, String value) {
		super(fk_condition, value);
		this.insertSql = "INSERT INTO control_arguments (fk_condition, value) VALUES (?, ?)";
	}

	@Override
	protected PreparedStatement fillStmt(PreparedStatement stmt) throws SQLException {
		Logger.info("statement=%s, \n\tfk_condition=%d\n\tvalue=%s", insertSql, fk_condition, value);
		stmt.setLong(1, fk_condition);
		stmt.setString(2, value);
		return stmt;
	}

}
