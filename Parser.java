import java.util.*;
import java.io.*;

public class Parser {

	@SuppressWarnings("serial")
	private static class ParseError extends Exception {

		ParseError(String err) {
			super(err);
		}
	}

	// read the string/file/input
	public static void main(String[] args) throws IOException {

		// initial variables
		int c;
		int n;
		char con;
		char last;
		double val;

		while (true) {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\otter\\Downloads\\hw1.txt"));
			// read file as string(Change location for test)
			String input = br.readLine();

			/*
			 * if you want to user input the reserved words Scanner keyboard = new
			 * Scanner(System.in); String input = keyboard.next();
			 */

			c = 0;

			n = input.length();

			// remove all the spaces/comments/gaps
			input = input.replaceAll("\n", "");
			last = input.charAt(n - 1);

			if (last == '\n') {
				break;
			}

			try {
				// get value of index or string
				val = value(input, n, c);

				// remove all the spaces/comments/gaps
				input = input.replaceAll("\n", "");

				if (last != '\n') {
					throw new ParseError("Experession error(no_end)");
				}

				con = input.charAt(c + 1);
				System.out.println("\nValue is " + val);
			}

			catch (ParseError e) {
				System.out.println("Input error");

			}
		}
	}

	// calculation part
	public static double value(String input, int n, int c) throws ParseError {

		// initial variables
		char last = input.charAt(n - 1);
		char op;
		double d;
		double left;
		double right;

		// remove all the spaces/comments/gaps
		input = input.replaceAll("\n", "");

		if (Character.isDigit(last)) {
			d = Integer.parseInt(String.valueOf(last));
			return d;
		} else if (last == '(') {
			char next;

			// get value of left and right
			left = value(input, n, c);
			right = value(input, n, c);

			// check operation
			op = checkOp(input, n);

			input = input.replaceAll("\n", "");

			if (last != ')') {
				throw new ParseError("No right parenthesis.\nPlease add ')'");
			}

			// check next index of string
			next = input.charAt(c + 1);

			// check operation
			if (op == '%') {
				return left % right;
			} else if (op == '*') {
				return left * right;
			} else if (op == '/') {
				return left / right;
			} else if (op == '+') {
				return left + right;
			} else if (op == '-') {
				return left - right;
			} else if (op == '%') {
				return left % right;
			} else if (op == '-') {
				return left * (-1);
			} else if (op == '++') {
				return left + 1;
			} else if (op == '--') {
				return left + 1;
			} else if (op == '<=') {
				return left <= right;
			} else if (op == ">=") {
				return left >= right;
			} else if (op == "||") {
				return left || right;
			} else if (op == "=") {
				return left = right;
			} else if (op == "&&") {
				return left && right;
			} else if (op == "&") {
				return left & right;
			} else if(op == "<") {
				return left < right;
			} else if(op == ">") {
				return left > right;
			} else if(op == "~|") {
				return left ~| right;
			}
			else {
				return 0;
			}

			/*
			 * switch (op) { case '+': return left + right; case '-': return left - right;
			 * case '*': return left * right; case '/': return left / right; case '%':
			 * return left % right; default: return 0; }
			 */
		} else {
			throw new ParseError("Unexpected character in input: " + last);
		}

	}

	// function that check operation is exist inside of the string or not
	public static char checkOp(String input, int n) throws ParseError {

		// initial variable
		char op = input.charAt(n - 1);

		// check operation is exist inside of the string or not
		if (op == '%' || op == '*' || op == '/' || op == '+' || op == '-') {
			return op;
		} else if (op == '\n') {
			throw new ParseError("No operator");
		} else {
			throw new ParseError(
					"Found unexpected operation: " + op + "\nPlease choose one of '%' || '*' || '/' || '+' || '-'");
		}
	}

}