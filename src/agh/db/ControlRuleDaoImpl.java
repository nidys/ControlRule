package agh.db;

import java.util.List;

import agh.db.elements.Rule;

public class ControlRuleDaoImpl implements ControlRuleDao{

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
		//TODO save rule in DB
		return "OK";
	}

	@Override
	public String deleteControlRule(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
