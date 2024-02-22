import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Node {

	char value;
	List<Node> child;
	boolean end;
	String word;

	Node(char value) {
		this.value = value;
		child = new ArrayList<>();
	}

	void insert(int index, char[] value) {
		if (index == value.length) {
			end = true;
			word = String.valueOf(value);
			return;
		}
		for (Node node : child) {
			if (node.value == value[index]) {
				node.insert(index + 1, value);
				return;
			}
		}
		Node node = new Node(value[index]);
		child.add(node);
		node.insert(index + 1, value);
	}
}

public class Main {

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int w;
	private Node trie;
	private char[][] board;

	private Set<String> findWords;
	private String maxLengthWord;
	private int maxScore;

	private int visited;

	private final int[] dirX = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private final int[] dirY = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		initWord();
		int b = Integer.parseInt(bf.readLine());
		for (int i = 0; i < b; i++) {
			initBoard();
			calcResult();
			if (i != b - 1) bf.readLine();
			printResult();
		}
		bw.flush();
		bw.close();
	}

	private void initWord() throws IOException {
		w = Integer.parseInt(bf.readLine());
		trie = new Node('0');
		for (int i = 0; i < w; i++) {
			trie.insert(0, bf.readLine().toCharArray());
		}
		bf.readLine();
	}

	private void initBoard() throws IOException {
		board = new char[4][4];
		for (int i = 0; i < 4; i++) {
			String rowValue = bf.readLine();
			for (int j = 0; j < 4; j++) {
				board[i][j] = rowValue.charAt(j);
			}
		}
	}

	private void calcResult() {
		findWords = new HashSet<>();
		maxLengthWord = "";
		maxScore = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				visited = 0;
				for (Node start : trie.child) {
					if (start.value == board[i][j]) {
						checkVisited(i, j);
						dfs(i, j, start);
						uncheckVisited(i, j);
					}
				}
			}
		}
		calcScore();
	}

	private void dfs(int x, int y, Node now) {
		if (now.end) {
			findWords.add(now.word);
		}

		for (int i = 0; i < 8; i++) {
			int nX = x + dirX[i];
			int nY = y + dirY[i];

			if (isIn(nX, nY) && !isVisited(nX, nY)) {
				for (Node next : now.child) {
					if (next.value == board[nX][nY]) {
						checkVisited(nX, nY);
						dfs(nX, nY, next);
						uncheckVisited(nX, nY);
					}
				}
			}
		}
	}

	private boolean isIn(int x, int y) {
		return 0 <= x && x < 4 && 0 <= y && y < 4;
	}

	private boolean isVisited(int x, int y) {
		return ((1 << ((x * 4) + y)) & visited) > 0;
	}

	private void checkVisited(int x, int y) {
		visited |= (1 << ((x * 4) + y));
	}

	private void uncheckVisited(int x, int y) {
		visited &= ~(1 << ((x * 4) + y));
	}

	private void calcScore() {
		for (String word : findWords) {
			int length = word.length();

			if (3 <= length && length <= 4)
				maxScore += 1;
			else if (length == 5)
				maxScore += 2;
			else if (length == 6)
				maxScore += 3;
			else if (length == 7)
				maxScore += 5;
			else if (length == 8)
				maxScore += 11;

			if (maxLengthWord.length() < length) {
				maxLengthWord = word;
				continue;
			} else if (maxLengthWord.length() == length) {
				if (maxLengthWord.compareTo(word) > 0) {
					maxLengthWord = word;
				}
			}
		}
	}

	private void printResult() throws IOException {
		if (findWords.size() == 0) bw.write(0 + " " + 0 + "\n");
		else bw.write(maxScore + " " + maxLengthWord + " " + findWords.size() + "\n");
	}
}
