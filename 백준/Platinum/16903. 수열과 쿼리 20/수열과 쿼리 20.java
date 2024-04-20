import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    class Node {
        int count;
        Node left;
        Node right;

        public Node(int count) {
            this.count = count;
        }
    }

    class Trie {

        Node head;

        public Trie() {
            head = new Node(0);
            insert(0);
        }

        public void insert(int value) {

            Node now = head;

            for (int i = 30; i >= 0; i--) {
                if ((value & (1 << i)) > 0) {
                    if (now.left != null) {
                        now.left.count++;
                    } else {
                        now.left = new Node(0);
                    }
                    now = now.left;
                } else {
                    if (now.right != null) {
                        now.right.count++;
                    } else {
                        now.right = new Node(0);
                    }
                    now = now.right;
                }
            }
        }

        public void delete(int value) {

            Node now = head;

            for (int i = 30; i >= 0; i--) {
                if ((value & (1 << i)) > 0) {
                    if (now.left.count > 0) {
                        now.left.count--;
                    } else {
                        now.left = null;
                        break;
                    }
                    now = now.left;
                } else {
                    if (now.right.count > 0) {
                        now.right.count--;
                    } else {
                        now.right = null;
                        break;
                    }
                    now = now.right;
                }
            }
        }

        public int query(int value) {
            Node now = head;
            int result = 0;

            for (int i = 30; i >= 0; i--) {
                if ((value & (1 << i)) > 0) {
                    if (now.right != null) {
                        result = result << 1;
                        result += 1;
                        now = now.right;
                    } else {
                        result = result << 1;
                        result += 0;
                        now = now.left;
                    }
                } else {
                    if (now.left != null) {
                        result = result << 1;
                        result += 1;
                        now = now.left;
                    } else {
                        result = result << 1;
                        result += 0;
                        now = now.right;
                    }
                }
            }
            return result;
        }
     }

    private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private Trie trie;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcResult();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        trie = new Trie();
    }

    private void calcResult() throws IOException {
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int command = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            if (command == 1) {
                trie.insert(x);
            } else if (command == 2) {
                trie.delete(x);
            } else {
                printResult(trie.query(x));
            }
        }
    }

    private void printResult(int result) {
        System.out.println(result);
    }
}