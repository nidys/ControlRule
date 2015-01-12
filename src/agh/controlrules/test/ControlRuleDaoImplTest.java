package agh.controlrules.test;

import agh.db.ControlRuleDao;
import agh.db.ControlRuleDaoImpl;

public class ControlRuleDaoImplTest {

	public static void main(String[] args) {
		ControlRuleDaoImplTest obj = new ControlRuleDaoImplTest();
		obj.test1();
	}

	private void test1() {
		ControlRuleDao dao = new ControlRuleDaoImpl();
		dao.addControlRule(rule1);
	}
	String rule1 ="r(2a2b) :- \n    v(d=dark,L,detected=false),\n    "
			+ "e(d=dark,L,s,J),\n    \\+ v(s,J,off),\n    e(s,J,c,_,_),\n    then,\n    "
			+ "forall(e(s,I,c,J,_),vlr(c,J,_),vla(c,J,off)),\n    vla(s,J,off).";
	
	
	
//	String rule1 = "r(1a1b\n" + // <br>
//			"v(d=dark,L,detected=false),\n" + // <br>
//			"v(d=dark,L,detected=false),\n" + // <br>
//			"\\+ v(s,J,off),\n" + // <br>
//			"forall(v(d=present,K),vla(d=present,K,detected=false), vlc(d=present,J,detected=true)),\n" + // <br>
//			" then,\n" + // <br>
//			"vla(s,J,off).\n";
}
