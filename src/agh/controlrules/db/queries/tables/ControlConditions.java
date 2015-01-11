package agh.controlrules.db.queries.tables;

public abstract class ControlConditions extends QueryCreator {
	public long condition_id;
	public long fk_control_rule;
	public String condition_name;
	public boolean negation;
	public boolean action;
	public boolean forall;
	public int reference_condition_action_id;

	public ControlConditions(long fk_control_rule, String condition_name, boolean negation, boolean actio, boolean forall,
			int reference_condition_action_id) {
		super();
		this.fk_control_rule = fk_control_rule;
		this.condition_name = condition_name;
		this.negation = negation;
		this.action = actio;
		this.forall = forall;
		this.reference_condition_action_id = reference_condition_action_id;
	}

	public ControlConditions(long fk_control_rule) {
		super();
		this.fk_control_rule = fk_control_rule;
	}
}
