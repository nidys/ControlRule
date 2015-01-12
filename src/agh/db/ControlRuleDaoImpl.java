package agh.db;

import java.util.List;

import agh.controlrules.db.queries.InsertControlArguments;
import agh.controlrules.db.queries.InsertControlConditions;
import agh.controlrules.db.queries.InsertControlRules;
import agh.controlrules.utils.DbHelper;
import agh.db.elements.Condition;
import agh.db.elements.ForAll;
import agh.db.elements.Rule;

public class ControlRuleDaoImpl implements ControlRuleDao {

	@Override
	public List<String> getAllControlRules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getControlRule(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addControlRule(String ruleStr) {
		String[] lines = ruleStr.split("\n");

		// ustawienie name i type
		String[] rule = lines[0].split("\\(");
		Rule r = new Rule(rule[1].split("\\)")[0], rule[0]);

		int i = 1;
		while (!lines[i].equalsIgnoreCase("    then,")) {
			r.addCondition(lines[i]);
			i++;
		}
		i++;
		for (; i < lines.length; i++) {
			r.addActions(lines[i]);
		}
		insert(r);
		return "OK";
	}

	private void insert(Rule rule) {
		DbHelper db = new DbHelper();
		int ruleId = db.insert(new InsertControlRules(rule));
		for (Condition cond : rule.getConditions()) {
			if (cond instanceof ForAll) {
				ForAll forallCond = (ForAll) cond;
				InsertControlConditions forallQuery = new InsertControlConditions(ruleId, "forall");
				forallQuery.forall = true;
				int forallId = db.insert(forallQuery);
				//
				for (Condition condOfForall : forallCond.args) {
					InsertControlConditions queryCondition = new InsertControlConditions(ruleId, condOfForall.getName());
					queryCondition.reference_condition_action_id = forallId;
					int controlCondition2 = db.insert(queryCondition);
					for (String condOfForAllArg : condOfForall.getArgs())
					db.insert(new InsertControlArguments(controlCondition2, condOfForAllArg));
				}
				continue;
			}
			int condId = db.insert(new InsertControlConditions(ruleId, cond.getName()));
			for (String arg : cond.getArgs())
				db.insert(new InsertControlArguments(condId, arg));
		}
	}

	@Override
	public String deleteControlRule(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
