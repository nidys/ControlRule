package agh.db;

import java.util.List;

import agh.controlrules.db.queries.InsertControlArguments;
import agh.controlrules.db.queries.InsertControlConditions;
import agh.controlrules.db.queries.InsertControlRules;
import agh.controlrules.db.queries.SelectControlArguments;
import agh.controlrules.db.queries.SelectControlConditions;
import agh.controlrules.db.queries.SelectControlRules;
import agh.controlrules.db.queries.tables.ControlArguments;
import agh.controlrules.db.queries.tables.ControlConditions;
import agh.controlrules.db.queries.tables.ControlRules;
import agh.controlrules.db.queries.tables.QueryCreator;
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
		
		return null;
	}
	
	public Rule select(String name) {
		Rule r = null;
		DbHelper db = new DbHelper();
		List<QueryCreator> list = db.select(new SelectControlRules(name));
		for (QueryCreator qc : list) {
			ControlRules cr = (ControlRules) qc;
			r = new Rule(cr); // bo jest tylko jeden
			List<QueryCreator> conditions = db.select(new SelectControlConditions(cr.rule_id));
			//for (QueryCreator qcConditions : conditions) {
			for (int i = 0; i < conditions.size(); i++) {
				QueryCreator qcConditions = conditions.get(i);
				ControlConditions cc = (ControlConditions) qcConditions;
				if (cc.forall) {
					ForAll fa = new ForAll();
					ControlConditions facc = cc;
					i++;
					qcConditions = conditions.get(i);
					cc = (ControlConditions) qcConditions;
					while (cc.forall && (facc.condition_id == cc.reference_condition_action_id)) {
						Condition con = new Condition(cc);
						List<QueryCreator> arguments = db.select(new SelectControlArguments(cc.condition_id));
						for (QueryCreator qcArguments : arguments) {
							ControlArguments ca = (ControlArguments) qcArguments;
							con.addArg(ca.value);
						}
						fa.addArg(con);
						i++;
						qcConditions = conditions.get(i);
						cc = (ControlConditions) qcConditions;
					}
					i -= 1;
					r.addActions(fa);
				} else {			
					Condition con = new Condition(cc);
					List<QueryCreator> arguments = db.select(new SelectControlArguments(cc.condition_id));
					for (QueryCreator qcArguments : arguments) {
						ControlArguments ca = (ControlArguments) qcArguments;
						con.addArg(ca.value);
					}
					if (!cc.action) {
						r.addCondition(con);
					} else {
						System.out.println(con.getName());
						r.addActions(con);
					}	
				}
			}
		}
		
		return r;
	}

	@Override
	public String addControlRule(String ruleStr) {
		String[] lines = ruleStr.split("\n");

		// ustawienie name i type
		String[] rule = lines[0].split("\\(");
		Rule r = new Rule(rule[1].split("\\)")[0], rule[0]);

		int i = 1;
		while (!lines[i].contains("then")) {
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
			int condId = db.insert(new InsertControlConditions(ruleId, cond.getName(), cond.getNegation(), false, false, 0));
			for (String arg : cond.getArgs())
				db.insert(new InsertControlArguments(condId, arg));
		}
		
		for (Condition act : rule.getActions()) {
			if (act instanceof ForAll) {
				ForAll forallCond = (ForAll) act;
				InsertControlConditions forallQuery = new InsertControlConditions(ruleId, "forall", act.getNegation(), true, true, 0);
				forallQuery.forall = true;
				int forallId = db.insert(forallQuery);
				//
				for (Condition condOfForall : forallCond.args) {
					InsertControlConditions queryCondition = new InsertControlConditions(ruleId, condOfForall.getName(), condOfForall.getNegation(), false, true, 0);
					queryCondition.reference_condition_action_id = forallId;
					int controlCondition2 = db.insert(queryCondition);
					for (String condOfForAllArg : condOfForall.getArgs())
					db.insert(new InsertControlArguments(controlCondition2, condOfForAllArg));
				}
				continue;
			}
			int condId = db.insert(new InsertControlConditions(ruleId, act.getName(), act.getNegation(), true, false, 0));
			for (String arg : act.getArgs())
				db.insert(new InsertControlArguments(condId, arg));
		}
	}

	@Override
	public String deleteControlRule(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
