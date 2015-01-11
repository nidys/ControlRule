package agh.db.elements;
import java.util.ArrayList;
import java.util.List;


public class Condition {
	String name;
	List<String> args;
	
	public Condition(){
		args = new ArrayList<String>();
	}
	
	public Condition(String name) {
		args = new ArrayList<String>();
		this.name = name;
	}
	
	public void addArg(String str) {
		args.add(str);
	}
	
	public String toString() {
		String str = name + "(";
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
}
