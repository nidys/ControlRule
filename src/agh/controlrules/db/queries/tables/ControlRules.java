package agh.controlrules.db.queries.tables;

public abstract class ControlRules extends QueryCreator {
	public long rule_id;
	public String rule_name;
	public String type;

	public ControlRules() {
		super();
	}

	public ControlRules(String ruleName, String type) {
		super();
		this.rule_name = ruleName;
		this.type = type;
	}
	
}
