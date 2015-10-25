package viewui;

import java.util.Scanner;

/**
 * Prompts for and then validates console based input. Based in part on code
 * from Murach's Java SE 6 by Joel Murach, et. al.
 *
 * @author John Phillips
 * @version 20151009
 */
public class Validator {

	/**
	 * Prompts the user with a message and then retrieves what the user types as
	 * a String.
	 *
	 * @param sc
	 * @param prompt
	 * @return this method returns a String
	 */
	public static String getLine(Scanner sc, String prompt) {
		System.out.print(prompt);
		return sc.nextLine();
	}

	/**
	 * Prompts the user with a message and then retrieves what the user types as
	 * a String. The regex is a String that contains a regular expression. The
	 * message repeats until the user enters a correct value. For example, a
	 * regular expression of "^[qadl]$" would require a "q" or an "a" or a "d"
	 * or an "l" to be entered. A regular expression of "^quit|add|delete|list$"
	 * would require one of the listed words to be entered. A regular expression
	 * of "^\d\d-\d\d-\d\d\d\d$" would require a date in the form of
	 * "11-01-2014" to be entered.
	 *
	 * @param sc
	 * @param prompt
	 * @param regex
	 * @return this method returns a String
	 */
	public static String getLine(Scanner sc, String prompt, String regex) {
		boolean isValid = false;
		String s = "";
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextLine()) {
				s = sc.nextLine();
				if (s.matches(regex)) {
					isValid = true;
				} else {
					System.out.println("\nError! Invalid value. Try again.\n");
				}
			}
		}
		return s;
	}

	/**
	 * Prompts the user with a message and then retrieves what the user types as
	 * an integer.
	 *
	 * @param sc
	 * @param prompt
	 * @return this method returns an Integer
	 */
	public static int getInt(Scanner sc, String prompt) {
		boolean isValid = false;
		int i = 0;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				isValid = true;
			} else {
				System.out.println("\nError! Invalid integer value. Try again.");
			}
			sc.nextLine();
		}
		return i;
	}

	public static int getInt(Scanner sc, String prompt, int min, int max) {
		boolean isValid = false;
		int i = 0;
		while (isValid == false) {
			i = getInt(sc, prompt);
			if (i < min) {
				System.out.println("\nError! Must be greater than or equal to " + min);
			} else if (i > max) {
				System.out.println("\nError! Must be less than " + max);
			} else {
				isValid = true;
			}
		}
		return i;
	}

	/**
	 * Prompts the user with a message and then retrieves what the user types as
	 * an integer.
	 *
	 * @param sc
	 * @param prompt
	 * @return this method returns a Long
	 */
	public static long getLong(Scanner sc, String prompt) {
		boolean isValid = false;
		long i = 0;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextLong()) {
				i = sc.nextLong();
				isValid = true;
			} else {
				System.out.println("\nError! Invalid long value. Try again.");
			}
			sc.nextLine();
		}
		return i;
	}

	public static long getLong(Scanner sc, String prompt, long min, long max) {
		boolean isValid = false;
		long i = 0;
		while (isValid == false) {
			i = getLong(sc, prompt);
			if (i < min) {
				System.out.println("\nError! Must be greater than or equal to " + min);
			} else if (i > max) {
				System.out.println("\nError! Must be less than " + max);
			} else {
				isValid = true;
			}
		}
		return i;
	}

	public static double getDouble(Scanner sc, String prompt) {
		boolean isValid = false;
		double d = 0;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextDouble()) {
				d = sc.nextDouble();
				isValid = true;
			} else {
				System.out.println("\nError! Invalid double value. Try again.");
			}
			sc.nextLine();
		}
		return d;
	}

	public static double getDouble(Scanner sc, String prompt, double min, double max) {
		boolean isValid = false;
		double d = 0;
		while (isValid == false) {
			d = getDouble(sc, prompt);
			if (d < min) {
				System.out.println("\nError! Must be greater than or equal to " + min);
			} else if (d > max) {
				System.out.println("\nError! Must be less than " + max);
			} else {
				isValid = true;
			}
		}
		return d;
	}
}
