package agh.db.elements;
import java.util.ArrayList;
import java.util.List;


public class ForAll extends Condition{
	public List<Condition> args;
	
	public ForAll() {
		name = "    forall";
		args = new ArrayList<Condition>();
	}
	
	public void addArg(Condition con) {
		args.add(con);
	}

	public String toString() {
		String str = name + "(";
		for (int i = 0; i < args.size() - 1; i++) {
			str += args.get(i) + ",";
		}
		str += args.get(args.size() - 1) + ")";
		return str;
	}
}
