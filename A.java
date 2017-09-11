import java.util.*;

public class A {
	public static void main(String[] args) {
		A a = new A();
		// System.out.println(a.permutation(args[0], args[1]));
		// System.out.println(a.sumNumber(3));
		// System.out.println(a.fibonacciList(0));

		// /**replace spaces to %20 -- begin -- */
		// char[] ch = { 'a', 'a', ' ', 'a', ' ', 'a', 'a', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
		// int length = 7;
		// a.replaceSpaces(ch, length);
		// /**replace spaces to %20 -- end -- */
		// System.out.println(a.printBinary(Double.parseDouble(args[0])));
	}

	boolean permutation(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		int[] letters = new int[256];
		s.chars().forEach(e -> letters[e]++);
		return t.chars().filter(c -> (--letters[c] < 0)).findFirst().orElse(-1) == -1;

	}

	int sumNumber(int element) {
		return element == 0 ? 0 : element + sumNumber(element - 1);
	}

	int fibonacciList(int entrance) {
		if (entrance == 0)
			return 0;
		return entrance == 1 || entrance == 2 ? 1 : fibonacciList(entrance - 1) + fibonacciList(entrance - 2);
	}

	//  void reverseTree(final TreeNode root) {
	// 	if (root == null) {
	// 		return;
	// 	}

	// 	final TreeNode temp = root.right;
	// 	root.right = root.left;
	// 	root.left = temp;

	// 	reverseTree(root.left);

	// 	reverseTree(root.right);
	// }

	void replaceSpaces(char[] str, int length) {
		int spaceCount = 0, newLength;
		for (int i = 0; i < length; i++) {
			if (str[i] == ' ') {
				spaceCount++;
			}
		}

		newLength = length + spaceCount * 2;
		str[newLength] = '\0';
		for (int i = length - 1; i >= 0; i--) {
			if (str[i] == ' ') {
				str[newLength - 1] = '0';
				str[newLength - 2] = '2';
				str[newLength - 3] = '%';
				newLength = newLength - 3;
			} else {
				str[newLength - 1] = str[i];
				newLength = newLength - 1;
			}
		}
	}

	String printBinary(double num) {
		if (num >= 1 || num <= 0) {
			return "ERROR";
		}

		StringBuilder binary = new StringBuilder();
		binary.append(".");
		while (num > 0) {
			/* setting a limit on length: 32 characters */
			if (binary.length() >= 32) {
				return "ERROR";
			}

			double r = num * 2;
			if (r >= 1) {
				binary.append(1);
				num = r - 1;
			} else {
				binary.append(0);
				num = r;
			}
		}
		return binary.toString();
	}
}