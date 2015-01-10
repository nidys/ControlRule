package agh.controlrules.db.queries.tables;

public abstract class ControlArguments extends QueryCreator {
	public long arg_id;
	public long fk_condition;
	public String value;

	public ControlArguments(long fk_condition, String value) {
		super();
		this.fk_condition = fk_condition;
		this.value = value;
	}

	public ControlArguments(long fk_condition) {
		super();
		this.fk_condition = fk_condition;
	}
	

}
