package agh.controlrules.utils;

import agh.db.elements.Rule;

public class ParserHelper {
	public static Rule getParsedRule(String ruleStr) {
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
		return r;
	}

}
