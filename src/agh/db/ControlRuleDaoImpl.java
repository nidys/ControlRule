package agh.db;

import java.util.List;

import agh.controlrules.utils.DbHelper;
import agh.controlrules.utils.ParserHelper;
import agh.db.elements.Rule;

public class ControlRuleDaoImpl implements ControlRuleDao {

	@Override
	public List<String> getAllControlRules() {
		return DbHelper.getAll();
	}

	@Override
	public String getControlRule(String name) {
		return DbHelper.select(name).toString();
	}

	@Override
	public String addControlRule(String ruleStr) {
		Rule r = ParserHelper.getParsedRule(ruleStr);
		DbHelper.insert(r);
		return "OK";
	}

	@Override
	public String deleteControlRule(String name) {
		return DbHelper.delete(name);
	}

}
