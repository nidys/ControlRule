package agh.controlrules.utils;

public class Logger {

	private static int level = 5;

	public static void info(String in, Object... args) {
		String s = String.format(in, args);
		if (level >= 5) {
			System.out.println(s);
		}
	}

	public static void error(String in, Object... args) {
		String s = String.format(in, args);
		if (level >= 1) {
			System.out.println("\n###\n\t" + s + "\n###");
		}
	}
}
