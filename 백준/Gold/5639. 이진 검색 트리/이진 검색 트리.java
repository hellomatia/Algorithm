import java.io.*;
import java.util.*;
class Node {
    int num;
    Node left, right;
    Node(int num) {
        this.num = num;
    }
    void insert(int n) {
        if (n < this.num) {
            if (this.left == null)
                this.left = new Node(n);
            else this.left.insert(n);
        } else {
            if (this.right == null)
                this.right = new Node(n);
            else this.right.insert(n);
        }
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {
        Node root = new Node(Integer.parseInt(bf.readLine()));

        String input;
        while (true) {
            input = bf.readLine();
            if (input == null || input.equals("")) {
                break;
            }
            root.insert(Integer.parseInt(input));
        }

        postOrder(root);

        bw.flush();
        bw.close();
    }

    public void postOrder(Node node) throws IOException {

        if (node == null) {
            return;
        }

        if (node.left != null) {
            postOrder(node.left);
        }
        if (node.right != null) {
            postOrder(node.right);
        }
        bw.write(node.num + "\n");
    }

    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}