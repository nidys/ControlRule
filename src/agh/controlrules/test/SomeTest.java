package agh.controlrules.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import agh.controlrules.utils.Logger;
import agh.db.ControlRuleDaoImpl;

public class SomeTest {

	static String sampleRules[] = new String[] { RulesExmaple.TEST_6, RulesExmaple.TEST_7, RulesExmaple.TEST_off, RulesExmaple.TEST_test };

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String cmd = null;
		printHelp();
		try {
			while ((cmd = br.readLine()) != null) {
				if (isOption(cmd, 0)) {
					o0();
				} else if (isOption(cmd, 1)) {
					o1_insert(br);
				} else if (cmd.equals("2")) {
					o2_select(br);
				} else if (cmd.equals("3")) {
					o3_delete(br);
				} else if (cmd.equals("4")) {
					o4(br);
				} else if (cmd.equals("5")) {
					o5(br);
				} else if (cmd.equals("6")) {
					o6(br);
				} else {
					System.out.println("Good byte!");
					break;
				}
				printHelp();
			}
		} catch (IOException ioe) {
			System.out.println("IO error trying to read your name!");
			System.exit(1);
		}

	}

	private static boolean isOption(String cmd, int num) {
		return cmd.equals(String.valueOf(num));
	}

	private static void printHelp() {
		int i = 0;
		Logger.info("-------------------------------");
		System.out.println(i++ + " - print all rules in db");
		System.out.println(i++ + " - insert rule, after enter number of rule");
		System.out.println(i++ + " - select rule, after enter number of rule");
		System.out.println(i++ + " - delete rule, after enter number of rule");
		System.out.println(i++ + " - print available sample rules");
		System.out.println(i++ + " - set logging level to info");
		System.out.println(i++ + " - set logging level to error");
	}

	private static void o0() {
		List<String> names = new ControlRuleDaoImpl().getAllControlRules();
		System.out.println("Got rules names:");
		for (int i = 0; i < names.size(); i++) {
			System.out.println(i + ")" + names.get(i));
		}
		System.out.println("----------");
	}

	private static void o1_insert(BufferedReader br) throws IOException {
		System.out.print("Enter which rule:");
		String cmd = br.readLine();
		int ruleNum = Integer.valueOf(cmd);
		System.out.println("Inserting:");
		new ControlRuleDaoImpl().addControlRule(sampleRules[ruleNum]);
		System.out.println("----------");
	}

	private static void o2_select(BufferedReader br) throws IOException {
		System.out.print("Enter which rule:");
		String cmd = br.readLine();
		int ruleNum = Integer.valueOf(cmd);
		System.out.println("Getting rule...");
		System.out.println("Got:" + new ControlRuleDaoImpl().getControlRule(sampleRules[ruleNum]));
		System.out.println("----------");
	}

	private static void o3_delete(BufferedReader br) throws IOException {
		System.out.print("Enter which rule:");
		String cmd = br.readLine();
		int ruleNum = Integer.valueOf(cmd);
		System.out.println("Deleting");
		new ControlRuleDaoImpl().deleteControlRule(sampleRules[ruleNum]);
		System.out.println("----------");
	}

	private static void o4(BufferedReader br) throws IOException {
		System.out.println("Rules:");
		for(int i = 0; i < sampleRules.length; i++) {
			System.out.println(i + ") -----------------\n" + sampleRules[i]);
		}
		System.out.println("----------");
	}

	private static void o5(BufferedReader br) throws IOException {
		System.out.println("Setting logging to info");
		Logger.level = 5;
	}

	private static void o6(BufferedReader br) throws IOException {
		System.out.println("Setting logging to error");
		Logger.level = 2;
	}
}
