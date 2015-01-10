package agh.controlrules.db.queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import agh.controlrules.db.queries.tables.ControlArguments;
import agh.controlrules.db.queries.tables.QueryCreator;

public class SelectControlArguments extends ControlArguments {

	public SelectControlArguments(long fk_condition, String value) {
		super(fk_condition, value);

	}

	public SelectControlArguments(long arg_id, long fk_condition, String value) {
		super(fk_condition, value);
		this.arg_id = arg_id;
	}

	public SelectControlArguments(long fk_condition) {
		super(fk_condition);
		selectSql = String.format("select * from control_arguments where fk_condition = %d", fk_condition);
	}

	@Override
	protected PreparedStatement fillStmt(PreparedStatement stmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	protected QueryCreator getInstrance(ResultSet rs) throws SQLException {
		arg_id = rs.getLong("arg_id");
		fk_condition = rs.getLong("fk_condition");
		value = rs.getString("value");
		return new SelectControlArguments(arg_id, fk_condition, value);
	}

	@Override
	public String toString() {
		return String.format("control_arguments={arg_id=%d, fk_condition=%d, value=%s}", arg_id, fk_condition, value);
	}
}
