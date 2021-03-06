package agh.controlrules.db.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import agh.controlrules.db.queries.tables.ControlRules;
import agh.controlrules.db.queries.tables.QueryCreator;

public class SelectAllControlRules extends ControlRules {

	public SelectAllControlRules(String ruleName, String type) {
		super(ruleName, type);
		selectSql = "select rule_id, rule_name, type from control_rules";
	}

	public SelectAllControlRules(long rule_id, String ruleName, String type) {
		super(ruleName, type);
		this.rule_id = rule_id;
		selectSql = "select rule_id, rule_name, type from control_rules";
	}

	public SelectAllControlRules(String ruleName) {
		this(ruleName, null);
	}

	public SelectAllControlRules() {
		this(null, null);
	}

	protected QueryCreator getInstrance(ResultSet rs) throws SQLException {
		rule_id = rs.getInt("rule_id");
		rule_name = rs.getString("rule_name");
		type = rs.getString("type");
		return new SelectControlRules(rule_id, rule_name, type);
	}

	@Override
	public PreparedStatement getInsertQuery(Connection prepStmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement fillStmt(PreparedStatement stmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return String.format("control_rule={rule_id=%d, rule_name=%s, type=%s}", rule_id, rule_name, type);
	}

	public String asString() {
		return String.format("%d, %s, %s", rule_id, rule_name, type);
	}
}
