package agh.controlrules.db;

public class DeleteQuery {
	
	public static String createControlArguments(long fk) {
		return String.format("delete from control_arguments where fk_condition=%d", fk);
	}
	public static String createControlConditions(long fk) {
		return String.format("delete from control_conditions where condition_id=%d", fk);
	}
	public static String createControlRules(String name) {
		return String.format("delete from control_rules where rule_name='%s'", name);
	}
}
