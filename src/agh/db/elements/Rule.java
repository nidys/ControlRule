package agh.db.elements;
import java.util.ArrayList;
import java.util.List;

import agh.controlrules.db.queries.tables.ControlRules;


public class Rule {
	String name;
	String type;
	List<Condition> conditions;
	List<Condition> actions;
	
	public Rule() {
		conditions = new ArrayList<Condition>();
		actions = new ArrayList<Condition>();
	}
	
	public Rule(ControlRules cr) {
		conditions = new ArrayList<Condition>();
		actions = new ArrayList<Condition>();
		name = cr.rule_name;
		type = cr.type;
	}
	
	public Rule(String name, String type) {
		conditions = new ArrayList<Condition>();
		actions = new ArrayList<Condition>();
		this.name = name.replace("'", "");
		this.type = type;
	}
	
	public void addCondition(Condition con) {
		conditions.add(con);
	}
	
	public void addCondition(String conStr) {
		conditions.add(parseConditionString(conStr));
	}
	
	public void addActions(Condition act) {
		actions.add(act);
	}
	
	public void addActions(String actStr) {
		if (actStr.split("\\(")[0].equalsIgnoreCase("    forall")) {
			ForAll fa = new ForAll();
			String[] forAllArgs = actStr.split("forall\\(")[1].split("\\),");
			for (String str : forAllArgs) {
				fa.addArg(parseConditionString(str));
			}	
			actions.add(fa);
		} else {
			actions.add(parseConditionString(actStr));
		}
		
	}
	
	private Condition parseConditionString(String conStr) {
		Condition con;
		String[] cons = conStr.split("\\(");		
		if (cons[0].contains("\\+")) {
			con = new Condition(cons[0].split("\\+")[1]);
			con.setNegation(true);
		} else {
			con = new Condition(cons[0]);
		}
		String[] args = cons[1].split(",");
		for (int i = 0; i < args.length - 1; i++) {
			con.addArg(args[i]);
		}
		con.addArg(args[args.length - 1].split("\\)")[0]);
		return con;
	}
	
	public String toString() {
		String str = "";
		if (needsApostrophe(name)) {
			str = type + "('" + name + "') :-\n";
		} else {
			str = type + "(" + name + ") :-\n";
		}
		 
		for (int i = 0; i < conditions.size(); i++) {
			str += conditions.get(i) + ",\n";
		}
		str += "then,\n";
		for (int i = 0; i < actions.size() - 1; i++) {
			str += actions.get(i) + ",\n";
		}
		str += actions.get(actions.size() - 1) + ".";
		return str;
	}
	
	private static boolean needsApostrophe(String name) {
		if (name.matches(".*\\d.*")) { //contains number
			try {
					Integer.valueOf(name);
			} catch (NumberFormatException e) {
				return true;
			}
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Condition> getConditions() {
		return conditions;
	}
	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}
	public List<Condition> getActions() {
		return actions;
	}
	public void setActions(List<Condition> actions) {
		this.actions = actions;
	}
}
