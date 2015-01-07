package agh.db;

import java.util.List;

public interface ControlRuleDao {
	public List<String> getAllControlRules();
	public String getControlRule(String name);
	public String addControlRule(String rule);
	public String deleteControlRule(String name);
}
