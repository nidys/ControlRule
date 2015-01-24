package agh.controlrules.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import agh.controlrules.db.DatabaseManager;
import agh.controlrules.db.queries.InsertControlArguments;
import agh.controlrules.db.queries.InsertControlConditions;
import agh.controlrules.db.queries.InsertControlRules;
import agh.controlrules.db.queries.SelectControlArguments;
import agh.controlrules.db.queries.SelectControlConditions;
import agh.controlrules.db.queries.SelectControlRules;
import agh.controlrules.db.queries.tables.ControlConditions;
import agh.controlrules.db.queries.tables.ControlRules;
import agh.controlrules.db.queries.tables.QueryCreator;
import agh.controlrules.utils.Logger;
import agh.db.ControlRuleDaoImpl;

public class SomeTest {

	public static void main(String[] args) {
		System.out.println("1 - insert\n2 - select\n3-getall\n4-delete\n5 - quit");
		// new SomeTest().insertTest1();
		new SomeTest().selectTest1();
		// new ControlRuleDaoImpl().deleteControlRule("1a1b");
		// f();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String userName = null;

		// read the username from the command-line; need to use try/catch with
		// the
		// readLine() method
		try {
			while ((userName = br.readLine()) != null) {
				if (userName.equals("1")) {
					new SomeTest().insertTest1();
				} else if (userName.equals("2")) {
					new SomeTest().selectTest1();
				} else if (userName.equals("3")) {
					f();
				} else if (userName.equals("4")) {
					new ControlRuleDaoImpl().deleteControlRule("1a1b");
				} else
					break;
				Logger.info("-------------------------------");
			}
		} catch (IOException ioe) {
			System.out.println("IO error trying to read your name!");
			System.exit(1);
		}

	}

	private static void f() {
		List<String> names = new ControlRuleDaoImpl().getAllControlRules();
		for (String str : names) {
			Logger.info(str);
		}
	}

	// Logger.info();
	public void selectTest1() {
		Db db = new Db();
		List<QueryCreator> list = db.select(new SelectControlRules("1a1b"));
		for (QueryCreator qc : list) {
			Logger.info(qc.toString());
			ControlRules cr = (ControlRules) qc;
			List<QueryCreator> conditions = db.select(new SelectControlConditions(cr.rule_id));
			for (QueryCreator qcConditions : conditions) {
				Logger.info("\t" + qcConditions.toString());
				ControlConditions cc = (ControlConditions) qcConditions;
				List<QueryCreator> arguments = db.select(new SelectControlArguments(cc.condition_id));
				for (QueryCreator qcArguments : arguments) {
					Logger.info("\t\t" + qcArguments.toString());
				}
			}
		}
	}

	public void selectTest1_differentFormat() {
		Db db = new Db();
		Logger.level = 1;
		List<QueryCreator> list = db.select(new SelectControlRules("1a1b"));
		for (QueryCreator qc : list) {
			Logger.info(qc.asString());
			ControlRules cr = (ControlRules) qc;
			List<QueryCreator> conditions = db.select(new SelectControlConditions(cr.rule_id));
			for (QueryCreator qcConditions : conditions) {
				Logger.info("\t" + qcConditions.asString());
				ControlConditions cc = (ControlConditions) qcConditions;
				List<QueryCreator> arguments = db.select(new SelectControlArguments(cc.condition_id));
				for (QueryCreator qcArguments : arguments) {
					Logger.info("\t\t" + qcArguments.asString());
				}
			}
		}
	}

	public void insertTest1() {
		Logger.info("---test1---");
		Db db = new Db();
		int ruleId = db.insert(new InsertControlRules("1a1b", "r"));
		insertFirstCondition(db, ruleId);
		insertSecondCondition(db, ruleId);
		insertThirdCondition(db, ruleId);
		insertFourthCondition(db, ruleId); // forall
		insertFifthCondition(db, ruleId);
	}

	private void insertFirstCondition(Db db, int ruleId) {
		int controlCondition1 = db.insert(new InsertControlConditions(ruleId, "v"));
		db.insert(new InsertControlArguments(controlCondition1, "d=dark"));
		db.insert(new InsertControlArguments(controlCondition1, "L"));
		db.insert(new InsertControlArguments(controlCondition1, "detected=false"));
	}

	private void insertSecondCondition(Db db, int ruleId) {
		int controlCondition1 = db.insert(new InsertControlConditions(ruleId, "e"));
		db.insert(new InsertControlArguments(controlCondition1, "d=dark"));
		db.insert(new InsertControlArguments(controlCondition1, "L"));
		db.insert(new InsertControlArguments(controlCondition1, "s"));
		db.insert(new InsertControlArguments(controlCondition1, "J"));
	}

	private void insertThirdCondition(Db db, int ruleId) {
		InsertControlConditions query = new InsertControlConditions(ruleId, "e");
		query.negation = true;
		int controlCondition1 = db.insert(query);
		db.insert(new InsertControlArguments(controlCondition1, "d=dark"));
		db.insert(new InsertControlArguments(controlCondition1, "L"));
		db.insert(new InsertControlArguments(controlCondition1, "s"));
		db.insert(new InsertControlArguments(controlCondition1, "J"));
	}

	private void insertFourthCondition(Db db, int ruleId) {
		InsertControlConditions forallQuery = new InsertControlConditions(ruleId, "forall");
		forallQuery.forall = true;
		int controlCondition1 = db.insert(forallQuery);

		InsertControlConditions queryCondition = new InsertControlConditions(ruleId, "v");
		queryCondition.reference_condition_action_id = controlCondition1;
		int controlCondition2 = db.insert(queryCondition);
		db.insert(new InsertControlArguments(controlCondition2, "d=present"));
		db.insert(new InsertControlArguments(controlCondition2, "K"));

		InsertControlConditions actionCondition1 = new InsertControlConditions(ruleId, "vla");
		actionCondition1.reference_condition_action_id = controlCondition1;
		actionCondition1.action = true;
		int controlCondition3 = db.insert(actionCondition1);
		db.insert(new InsertControlArguments(controlCondition3, "d=present"));
		db.insert(new InsertControlArguments(controlCondition3, "K"));
		db.insert(new InsertControlArguments(controlCondition3, "detected=false"));

		InsertControlConditions actionCondition2 = new InsertControlConditions(ruleId, "vlc");
		actionCondition2.reference_condition_action_id = controlCondition1;
		actionCondition2.action = true;
		int controlCondition4 = db.insert(actionCondition2);
		db.insert(new InsertControlArguments(controlCondition4, "d=present"));
		db.insert(new InsertControlArguments(controlCondition4, "J"));
		db.insert(new InsertControlArguments(controlCondition4, "detected=true"));
	}

	private void insertFifthCondition(Db db, int ruleId) {
		InsertControlConditions query = new InsertControlConditions(ruleId, "vla");
		query.action = true;
		int controlCondition1 = db.insert(query);
		db.insert(new InsertControlArguments(controlCondition1, "s"));
		db.insert(new InsertControlArguments(controlCondition1, "J"));
		db.insert(new InsertControlArguments(controlCondition1, "off"));
	}

	class Db {
		public int insert(QueryCreator query) {
			return DatabaseManager.insert(query);
		}

		public List<QueryCreator> select(QueryCreator query) {
			return DatabaseManager.select(query);
		}
	}

}
