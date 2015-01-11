package agh.controlrules.db.queries;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import agh.controlrules.db.queries.tables.ControlConditions;
import agh.controlrules.utils.Logger;

public class InsertControlConditions extends ControlConditions {

	public InsertControlConditions(int fk_control_rule, String condition_name, boolean negation, boolean actio, boolean forall,
			int reference_condition_action_id) {
		super(fk_control_rule, condition_name, negation, actio, forall, reference_condition_action_id);
		this.insertSql = "INSERT INTO control_conditions (fk_control_rule, condition_name, negation, action, forall, reference_condition_action_id) VALUES (?,?,?,?,?,?)";
	}

	public InsertControlConditions(int fk_control_rule, String condition_name) {
		super(fk_control_rule, condition_name, false, false, false, 0);
		this.insertSql = "INSERT INTO control_conditions (fk_control_rule, condition_name, negation, action, forall, reference_condition_action_id) VALUES (?,?,?,?,?,?)";
	}

	@Override
	protected PreparedStatement fillStmt(PreparedStatement stmt) throws SQLException {
		Logger.info(
				"statement=%s, \n\tfk_control_rule=%d\n\tcondition_name=%s\n\tnegation=%s\n\taction=%s\n\tforall=%s\n\treference_condition_action_id=%s",
				insertSql, fk_control_rule, condition_name, negation, action, forall, reference_condition_action_id);
		stmt.setLong(1, fk_control_rule);
		stmt.setString(2, condition_name);
		stmt.setBoolean(3, negation);
		stmt.setBoolean(4, action);
		stmt.setBoolean(5, forall);
		stmt.setInt(6, reference_condition_action_id);
		return stmt;
	}
}
