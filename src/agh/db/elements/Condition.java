package agh.db.elements;
import java.util.ArrayList;
import java.util.List;

import agh.controlrules.db.queries.tables.ControlConditions;


public class Condition {
	String name;
	Boolean negation;
	List<String> args;
	
	public Condition(){
		args = new ArrayList<String>();
		negation = false;
	}
	
	public Condition(ControlConditions cc) {
		args = new ArrayList<String>();
		negation = cc.negation;
		name = cc.condition_name;
	}

	public Condition(String name) {
		args = new ArrayList<String>();
		negation = false;
		this.name = name;
	}
	
	public void addArg(String str) {
		args.add(str);
	}
	
	public String toString() {
		String str = "";
		if (negation) {
			str += "\\+ ";
		}
		str += name + "(";
		for (int i = 0; i < args.size() - 1; i++) {
			str += args.get(i) + ",";
		}
		str += args.get(args.size() - 1) + ")";
		return str;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getArgs() {
		return args;
	}
	public void setArgs(List<String> args) {
		this.args = args;
	}
	
	public Boolean getNegation() {
		return negation;
	}

	public void setNegation(Boolean negation) {
		this.negation = negation;
	}
}
