import java.io.*;
import java.util.StringTokenizer;


class Node {
    int value;
    Node next;

    Node() {

    }

    Node(int value) {
        this.value = value;
    }


}

class LinkedList {

    Node head;
    Node tail;
    int size;

    LinkedList() {
        head = new Node();
        tail = head;
    }

    void add(int value) {
        Node addNode = new Node(value);
        tail.next = addNode;
        tail = addNode;
        size++;
    }

    Node get(int n) {
        Node now = head;
        for (int i = 0; i < n; i++) {
            now = now.next;
        }
        return now;
    }

    void insert(int x, int y, LinkedList s) {
        Node node = get(x);
        s.tail.next = node.next;
        node.next = s.head.next;
        if (x == size) {
            tail = s.tail;
        }
        size += y;
    }

    void delete(int x, int y) {
        Node preNode = get(x);
        Node sufNode = get(x + y + 1);
        preNode.next = sufNode;
        size -= y;
    }

}



class Solution {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private LinkedList pw;

    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private void solution() throws IOException {
        for (int i = 1; i <= 10; i++) {
            init();
            calcResult();
            printResult(i);
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        pw = new LinkedList();
        int count = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < count; i++) {
            pw.add(Integer.parseInt(st.nextToken()));
        }
    }

    private void calcResult() throws IOException {
        int commandCount = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < commandCount; i++) {
            String command = st.nextToken();
            if (command.equals("I")) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                LinkedList s = new LinkedList();
                for (int j = 0; j < y; j++) {
                    s.add(Integer.parseInt(st.nextToken()));
                }
                pw.insert(x, y, s);
            } else if (command.equals("D")) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                pw.delete(x, y);
            } else if (command.equals("A")) {
                int y = Integer.parseInt(st.nextToken());
                for (int j = 0; j < y; j++) {
                    pw.add(Integer.parseInt(st.nextToken()));
                }
            }
        }
    }

    private void printResult(int testCase) throws IOException {
        bw.write("#" + testCase + " ");
        Node now = pw.head.next;
        for (int i = 0; i < 10; i++) {
            bw.write(now.value + " ");
            now = now.next;
        }
        bw.write("\n");
    }
}