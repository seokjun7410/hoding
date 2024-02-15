import java.util.HashMap;
import java.util.Map;

public class 프로그래머스_단어변환 {

	static String begin = "hit";
	static String target = "cog";
	static String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

	static int min = Integer.MAX_VALUE;
	static Map<String, Boolean> visited = new HashMap<>();

	public static void main(String[] args) {
		visited.put(begin, false);
		visited.put(target, false);
		for (String word : words) {
			visited.put(word, false);
		}

		DFS(begin, 0);

		if (min == Integer.MAX_VALUE) {
			System.out.println(0);
		}
		System.out.println(min);
	}

	private static void DFS(String begin, int depth) {

		if (begin.equals(target)) {
			min = Math.min(min, depth);
			return;
		}
		visited.put(begin, true);

		char[] temp = begin.toCharArray();
		for (int i = 0; i < words.length; i++) {
			char[] wordsChar = words[i].toCharArray();
			if (visited.get(words[i])) {
				continue;
			}
			for (int excludeIndex = 0; excludeIndex < temp.length; excludeIndex++) {
				if (canChange(temp, wordsChar, excludeIndex)) {
					DFS(String.valueOf(wordsChar), depth + 1);
				}
			}
		}

	}

	private static boolean canChange(char[] temp, char[] wordsChar, int excludeIndex) {
		for (int j = 0; j < temp.length; j++) {
			if (j == excludeIndex) {
				continue;
			}
			if (temp[j] != wordsChar[j]) {
				return false;
			}
		}
		return true;
	}

}
