package agh.controlrules.db.queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import agh.controlrules.db.queries.tables.ControlConditions;
import agh.controlrules.db.queries.tables.QueryCreator;

public class SelectControlConditions extends ControlConditions {

	public SelectControlConditions(long fk_control_rule, String condition_name, boolean negation, boolean actio, boolean forall,
			int reference_condition_action_id) {
		super(fk_control_rule, condition_name, negation, actio, forall, reference_condition_action_id);
	}

	public SelectControlConditions(long condition_id, long fk_control_rule, String condition_name, boolean negation, boolean actio, boolean forall,
			int reference_condition_action_id) {
		super(fk_control_rule, condition_name, negation, actio, forall, reference_condition_action_id);
		this.condition_id = condition_id;
	}

	public SelectControlConditions(long fk_control_rule) {
		super(fk_control_rule);
		selectSql = String.format("select * from control_conditions where fk_control_rule =%d", fk_control_rule);
	}

	@Override
	protected PreparedStatement fillStmt(PreparedStatement stmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	protected QueryCreator getInstrance(ResultSet rs) throws SQLException {
		condition_id = rs.getLong("condition_id");
		fk_control_rule = rs.getInt("fk_control_rule");
		condition_name = rs.getString("condition_name");
		negation = rs.getBoolean("negation");
		action = rs.getBoolean("action");
		forall = rs.getBoolean("forall");
		reference_condition_action_id = rs.getInt("reference_condition_action_id");
		return new SelectControlConditions(condition_id, fk_control_rule, condition_name, negation, action, forall, reference_condition_action_id);
	}

	@Override
	public String toString() {
		return String
				.format("control_conditions={condition_id=%d, fk_control_rule=%s, condition_name=%s, negation=%s, action=%s, forall=%s, reference_condition_action_id=%s}",
						condition_id, fk_control_rule, condition_name, negation, action, forall, reference_condition_action_id);
	}

}
