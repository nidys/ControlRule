package agh.controlrules.test;

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

public class SomeTest {

	public static void main(String[] args) {
//		 new SomeTest().insertTest1();
		new SomeTest().selectTest1();
	}

	public void selectTest1() {
		Db db = new Db();
		List<QueryCreator> list = db.select(new SelectControlRules("1a1b"));
		for (QueryCreator qc : list) {
			System.out.println(qc.toString());
			ControlRules cr = (ControlRules) qc;
			List<QueryCreator> conditions = db.select(new SelectControlConditions(cr.rule_id));
			for (QueryCreator qcConditions : conditions) {
				System.out.println("\t" + qcConditions.toString());
				ControlConditions cc = (ControlConditions) qcConditions;
				List<QueryCreator> arguments = db.select(new SelectControlArguments(cc.condition_id));
				for (QueryCreator qcArguments : arguments) {
					System.out.println("\t\t" + qcArguments.toString());
				}
			}
		}
	}

	public void selectTest1_differentFormat() {
		Db db = new Db();
		Logger.level = 1;
		List<QueryCreator> list = db.select(new SelectControlRules("1a1b"));
		for (QueryCreator qc : list) {
			System.out.println(qc.asString());
			ControlRules cr = (ControlRules) qc;
			List<QueryCreator> conditions = db.select(new SelectControlConditions(cr.rule_id));
			for (QueryCreator qcConditions : conditions) {
				System.out.println("\t" + qcConditions.asString());
				ControlConditions cc = (ControlConditions) qcConditions;
				List<QueryCreator> arguments = db.select(new SelectControlArguments(cc.condition_id));
				for (QueryCreator qcArguments : arguments) {
					System.out.println("\t\t" + qcArguments.asString());
				}
			}
		}
	}

	public void insertTest1() {
		System.out.println("---test1---");
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
