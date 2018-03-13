import java.util.*;
import java.util.stream.Stream;

public class A {
	public static void main(String[] args) {
		A a = new A();
		// System.out.println(a.permutation(args[0], args[1]));
		// System.out.println(a.sumNumber(3));
		// System.out.println(a.fibonacciList(6));

		// int[] l = new int[] { 1, 2, 3, 4, 1, 3, 5, 6, 6, 6, 6, 6 };
		// int[] l = new int[] { 2, 7, 11, 15 };
		// Arrays.stream(a.sum(l, 9)).forEach(element -> System.out.println(element));
		// a.sum(l, 9).stream()
			// .forEach(element -> System.out.println("[" + element[0] + ", " + element[1] + "]"));
		// System.out.println(a.getPopularNumber(l));
		// System.out.println(a.getPopularElement(l));

		// /**replace spaces to %20 -- begin -- */
		// char[] ch = { 'a', 'a', ' ', 'a', ' ', 'a', 'a', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
		// int length = 7;
		// a.replaceSpaces(ch, length);
		// /**replace spaces to %20 -- end -- */
		// System.out.println(a.printBinary(Double.parseDouble(args[0])));

        // String sentence = "I like Akemi so much.";
        // System.out.println(a.reverseSentence(sentence));
        // int[] input = { 1, 2, 3, 4, 1, 3, 5, 6 };// {1,2,3,4,5};
        // System.out.println(a.biggestNumber(input));
        // System.out.println(a.findNBiggest(input, 3));
        // System.out.println(a.findSumNumber(input, 3));

        // System.out.println(Integer.parseInt(args[0])^Integer.parseInt(args[1]));

        System.out.println(a.longestSubString("cbqabcdab"));
	}

    String longestSubString(String s) {
        String result = "";
        String temp = "";
        Set<Character> set = new HashSet<Character>();
        for(int i = 0; i < s.length(); i++) {
            // System.out.println("i: " + i);
            for(int j = i; j < s.length(); j++) {
                // System.out.println("j: " + j);
                // set.forEach(element -> System.out.println("set: " + element));
                if(set.contains(s.charAt(j))) {
                    if (temp.length() > result.length()) {
                        // System.out.println("re: " + result + " te: " + temp);
                        // set.forEach(element -> System.out.println(element));
                        result = temp;
                    }
                    break;
                } else {
                    temp += Character.toString(s.charAt(j));
                    set.add(s.charAt(j));
                }
            }
            temp = "";
            set.clear();
        }
        return result;
    }

    int findSumNumber(int[] input, int sum) {
        Set<Integer> result = new HashSet<Integer>();
        for(int i : input) {
            if (result.contains(sum-i)) return i;
            else result.add(sum-i);
        }
        return -1;
    }

    // int findSumNumber(int[] input, int sum) {
    //     HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    //     for(int i : input) {
    //         map.put(i, null);
    //     }
    //     map.forEach((k,v) -> System.out.println(k + " " + v));
    //     return 1;
    // }

    int biggestNumber(int[] input) {
        int result = Integer.MIN_VALUE;
        for(int element: input) {
            result = element > result ? element : result;
        }
        return result;
    }

    int findNBiggest (int[] input, int n) {
        for (int i = 0; i < n; i++) {
            int temp = 0, index = i;
            for (int j = i; j < input.length; j++) {
                index = input[index] > input[j] ? index : j;
            }
            temp = input[i];
            input[i] = input[index];
            input[index] = temp;
        }
        return input[n-1];
    }

    String reverseSentence(String sentence) {
        String result = "";
        String temp[] = sentence.split(" |\\.");
        for(int i = temp.length; i > 0; i--) {
            result += temp[i-1] + " ";
        }
        return result.substring(0, result.length()-1) + ".";
    }

	List<int[]> sum(int[] array, int sum) {
		List<int[]> list = new ArrayList<int[]>();
		int[] result = new int[2];
		Set<Integer> set = new HashSet<Integer>();
		for (int element : array) {
			if (set.contains(sum - element)) {
				result[1] = element;
				result[0] = sum - element;
				list.add(result);
			}
			set.add(element);
		}
		return list;
	}

	int getPopularNumber(int[] l) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		Arrays.stream(l).forEach(i -> map.put(i, map.containsKey(i) ? map.get(i) + 1 : 1));

		return map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
	}

	int getPopularElement(int[] l) {

		if (l == null || l.length == 0)
			return 0;

		Arrays.sort(l);

		int previous = l[0];
		int popular = l[0];
		int count = 1;
		int maxCount = 1;

		for (int element : l) {
			if (element == previous) {
				count++;
			} else {
				if (count > maxCount) {
					popular = previous;
					maxCount = count;
				}
				previous = element;
				count = 1;
			}
		}

		return count > maxCount ? previous : popular;

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
    //
    // TreeNode reverseTree(TreeNode root) {
    //  if (root == null) return root;
    //  TreeNode temp = root.left;
    //  root.left = reverseTree(root.right);
    //  root.right = reverseTree(root.left);
    //  return root;
    // }
    //
    // TreeNode insertTree(TreeNode root, TreeNode node) {
    //  if(root == null) return node;
    //  if(node == null) return root;
    //  return node.val > root.val ? insertTree(root.right, node) : insertTree(root,left, node);
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
