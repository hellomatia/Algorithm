import java.io.*;
import java.util.*;

class Node {
    String value;
    Node parents;
    List<Node> children = new ArrayList<>();

    Node() {
    }

    Node(String value, Node parents) {
        this.value = value;
    }

    public Node addChildren(String value) {
        Node child = new Node(value, this);
        children.add(child);
        sort();
        return child;
    }

    public Optional<Node> getChildren(String value) {
        Optional<Node> findNode = Optional.empty();
        for (Node node : children) {
            if (node.value.equals(value)) {
                findNode = Optional.of(node);
                break;
            }
        }
        return findNode;
    }

    public boolean isChildrenEmpty() {
        return children.isEmpty();
    }

    public void sort() {
        if (children.size() < 2) {
            return;
        }
        Collections.sort(children, (o1, o2) -> o1.value.compareTo(o2.value));
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static Node root;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (Node node : root.children) {
            printResult(node, 0);
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        root = new Node();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int count = Integer.parseInt(st.nextToken());
            String[] values = new String[count];
            for (int j = 0; j < count; j++) {
                values[j] = st.nextToken();
            }
            initNodes(values);
        }
    }

    private void initNodes(String[] values) {
        Node now = root;
        for (String value : values) {
            if (now.getChildren(value).isPresent()) {
                now = now.getChildren(value).get();
            } else {
                now = now.addChildren(value);
            }
        }
    }

    private void printResult(Node node, int depth) throws IOException {
        for (int i = 0; i < depth; i++) {
            bw.write("--");
        }
        bw.write(node.value + "\n");
        if (!node.isChildrenEmpty()) {
            for (Node child : node.children) {
                printResult(child, depth + 1);
            }
        }
    }
}