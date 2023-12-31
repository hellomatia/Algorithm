import java.io.*;
import java.util.*;
class Node {

    int num;
    Node parents;
    List<Node> child = new ArrayList<>();

    Node(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setParents(Node node) {
        this.parents = node;
    }

    public void setChild(Node node) {
        this.child.add(node);
    }

    public boolean isRoot() {
        return num == 1;
    }
}

public class Solution {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int V, E, node1, node2;
    private static int start;
    private static Node[] tree;

    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private void solution() throws IOException {
        int testCase = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= testCase; t++) {
            init();
            int result = calcResult();
            printResult(t, start, result);
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        node1 = Integer.parseInt(st.nextToken());
        node2 = Integer.parseInt(st.nextToken());

        tree = new Node[V + 1];
        for (int i = 1; i <= V; i++) {
            tree[i] = new Node(i);
        }


        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < E; i++) {
            int parents = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            tree[parents].setChild(tree[child]);
            tree[child].setParents(tree[parents]);
        }
        
        start = 1;
    }

    private int calcResult() {
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(node1);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            set.add(now);
            if (now == 1) {
                continue;
            }
            queue.offer(tree[now].parents
                    .getNum());
        }

        queue.offer(node2);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (set.contains(now)) {
                start = now;
                continue;
            }
            queue.offer(tree[now].parents
                    .getNum());
        }

        return calcTreeSize(start);
    }

    private int calcTreeSize(int start) {
        int size = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            size++;
            for (int i = 0; i < tree[now].child.size(); i++) {
                queue.offer(tree[now].child
                        .get(i)
                        .num);
            }
        }
        return size;
    }

    private void printResult(int testCase, int start, int result) throws IOException {
        bw.write("#"+testCase + " " + start + " " + result + "\n");
    }
}
