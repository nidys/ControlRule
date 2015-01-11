package agh.controlrules.db.queries;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import agh.controlrules.db.queries.tables.ControlRules;
import agh.controlrules.utils.Logger;

public class InsertControlRules extends ControlRules {

	public InsertControlRules(String ruleName, String type) {
		super(ruleName, type);
		this.insertSql = "INSERT INTO control_rules (rule_name, type) VALUES (?, ?)";
	}

	@Override
	protected PreparedStatement fillStmt(PreparedStatement stmt) throws SQLException {
		Logger.info("statement=%s, \n\trule_name=%s\n\ttype=%s", insertSql, rule_name, type);
		stmt.setString(1, rule_name);
		stmt.setString(2, type);
		return stmt;
	}
}