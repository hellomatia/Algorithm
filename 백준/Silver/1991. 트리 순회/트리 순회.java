import java.io.*;
import java.util.*;

class Tree {

    String value;
    Tree left;
    Tree right;

    Tree(String value) {
        this.value = value;
    }

    public void addLeft(String root, String value) {
        if (this.value.equals(root)) {
            this.left = new Tree(value);
            return;
        }
        if (left != null) {
            left.addLeft(root, value);
        }
        if (right != null) {
            right.addLeft(root, value);
        }
    }

    public void addRight(String root, String value) {
        if (this.value.equals(root)) {
            this.right = new Tree(value);
            return;
        }
        if (left != null) {
            left.addRight(root, value);
        }
        if (right != null) {
            right.addRight(root, value);
        }
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static Tree tree = new Tree("A");
    private static StringBuilder prefix = new StringBuilder();
    private static StringBuilder infix = new StringBuilder();
    private static StringBuilder postfix = new StringBuilder();

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < N; i++) {
            initTree();
        }
        treePrefix(tree);
        treeInfix(tree);
        treePostfix(tree);
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initN();
    }

    private void initN() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private void initTree() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        String root = st.nextToken();
        String left = st.nextToken();
        String right = st.nextToken();
        if (!left.equals(".")) {
            tree.addLeft(root, left);
        }
        if (!right.equals(".")) {
            tree.addRight(root, right);
        }
    }

    private void treePrefix(Tree tree) {
        prefix.append(tree.value);
        if (tree.left != null) {
            treePrefix(tree.left);
        }
        if (tree.right != null) {
            treePrefix(tree.right);
        }
    }

    private void treeInfix(Tree tree) {
        if (tree.left != null) {
            treeInfix(tree.left);
        }
        infix.append(tree.value);
        if (tree.right != null) {
            treeInfix(tree.right);
        }
    }

    private void treePostfix(Tree tree) {
        if (tree.left != null) {
            treePostfix(tree.left);
        }
        if (tree.right != null) {
            treePostfix(tree.right);
        }
        postfix.append(tree.value);
    }

    private void printResult() throws IOException {
        bw.write(prefix.toString() + "\n");
        bw.write(infix.toString() + "\n");
        bw.write(postfix.toString() + "\n");
    }
}
