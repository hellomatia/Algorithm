import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

class Node {
	
	char value;
	List<Node> child;
	boolean canMakeWord;
	int wordCount;
	
	Node(char value) {
		this.value = value;
		this.child = new ArrayList<>();
	}
	
	void add(String str) {
		if (str.equals("")) {
			canMakeWord = true;
			return;
		}
		char value = str.charAt(0);
		for (Node node : child) {
			if (node.value == value) {
				node.add(str.substring(1));
				return;
			}
		}
		Node node = new Node(str.charAt(0));
		child.add(node);
		node.add(str.substring(1));
	}
	
	boolean isLeaf() {
		return child.isEmpty();
	}
	
	boolean isChildOnly() {
		return child.size() == 1;
	}
}


public class Main {

	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
    private int N;
    private Node trie;
    private String[] words;
    
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	private void solution() throws IOException {
		String str;
		while ((str = bf.readLine()) != null) {
			init(str);
			printResult(calcResult());
		}
	}
	
	private void init(String str) throws IOException {
		N = Integer.parseInt(str);
		trie = new Node('0');
		words = new String[N];
		for (int i = 0; i < N; i++) {
			String word = bf.readLine();
			trie.add(word);
			words[i] = word;
		}
	}
	
	private String calcResult() {
		int touchCount = 0;
		for (int i = 0; i < N; i++) {
			touchCount += calcTouchCount(words[i]);
		}
		return String.format("%.2f", (double) touchCount / N);
	}
	
	private int calcTouchCount(String str) {
		int count = 1;
		Node now = trie;
		
		for (int i = 0; i < str.length() - 1; i++) {
			for (Node node : now.child) {
				if (node.value == str.charAt(i)) {
					now = node;
				}
			}
			if (now.isChildOnly()) {
				if (now.canMakeWord) {
					count++;
				}
			} else {
				count++;
			}
		}
		
		return count;
	}
	
	private void printResult(String result) throws IOException {
		bw.write(result + "\n");
		bw.flush();
	}
	

	
//	private int read() throws IOException {
//	    int c, n = System.in.read() & 15;
//	    boolean isNegative = n == 13;
//	    if (isNegative) n = System.in.read() & 15;
//	    while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
//	    if (c == 13) System.in.read();
//	    return isNegative ? ~n + 1 : n;
//
//	}
}
