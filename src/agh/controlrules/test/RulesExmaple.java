package agh.controlrules.test;

public interface RulesExmaple {
	String TEST_test = "r(test):- \n    v(d=dark,L,detected=false),\n    "
			+ "e(d=dark,L,s,J),\n    \\+ v(s,J,off),\n    e(s,J,c,_,_),\n    then,\n    "
			+ "forall(e(s,I,c,J,_),vlr(c,J,_),vla(c,J,off)),\n    "
			+ "e(d=dark,L,s,J),\n    "
			+ "forall(e(s,I,c,J,_),vlr(c,J,_),vla(c,J,off)),\n    "
			+ "vla(s,J,off).";
	
	String TEST_6 = "r(6) :-\n" +
			"v(d=hour,H,detected=day),\n" +
			"v(d=done,I,detected=true),\n" +
			"e(d=done,I,s,J,interior),\n" +
			"e(d=hour,H,s,J),\n" +
			"\\+ v(s,J,high),\n" +
			"e(s,J,c,_,high),\n" +
			"then,\n" +
			"vla(s,J,high).";
	
	String TEST_off = "p(off) :-\n" +
			"v(s,I,off),\n" +
			"\\+ v(s,I,high),\n" +
			"\\+ v(s,I,low),\n" +
			"then,\n" +
			"vlc(s,I,_),\n" +
			"forall(e(s,I,c,J,_),(vlr(c,J,_),vla(c,J,off))).";
	
	String TEST_7 = "r(7) :-\n" +
			"v(d=hour,H,detected=day),\n" +
			"e(d=done,I,s,J,interior),\n" +
			"\\+ v(s,J,off),\n" +
			"v(d=done,I,detected=false),\n" +
			"e(s,J,c,_,high),\n" +
			"e(d=hour,H,s,J),\n" +
			"then,\n" +
			"vla(s,J,off).";
	
}
