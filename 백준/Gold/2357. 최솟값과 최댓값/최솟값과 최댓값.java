import java.io.*;
import java.util.*;
class SegmentMaxTree {
    int[] arr;
    int treeSize;
    int[] tree;

    public SegmentMaxTree(int[] arr) {
        this.arr = arr;
        this.treeSize = (int) Math.pow(2, (int)Math.ceil((Math.log(arr.length) / Math.log(2))) + 1);
        tree = new int[treeSize];
    }

    public void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        init(node * 2, start, (start + end) / 2);
        init(node * 2 + 1, (start + end) / 2 + 1, end);
        tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
    }

    public int getMax(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int leftMax = getMax(node * 2, start, (start + end) / 2, left, right);
        int rightMax = getMax(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return Math.max(leftMax, rightMax);
    }
}

class SegmentMinTree {
    int[] arr;
    int treeSize;
    int[] tree;

    public SegmentMinTree(int[] arr) {
        this.arr = arr;
        this.treeSize = (int) Math.pow(2, (int)Math.ceil((Math.log(arr.length) / Math.log(2))) + 1);
        tree = new int[treeSize];
    }

    public void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        init(node * 2, start, (start + end) / 2);
        init(node * 2 + 1, (start + end) / 2 + 1, end);
        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }

    public int getMin(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return Integer.MAX_VALUE;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int leftMax = getMin(node * 2, start, (start + end) / 2, left, right);
        int rightMax = getMin(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return Math.min(leftMax, rightMax);
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, M;
    private static int[] arr;
    private static SegmentMaxTree segmentMaxTree;
    private static SegmentMinTree segmentMinTree;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < M; i++) {
            readCommand();
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }
        segmentMaxTree = new SegmentMaxTree(arr);
        segmentMaxTree.init(1, 1, N);
        segmentMinTree = new SegmentMinTree(arr);
        segmentMinTree.init(1, 1, N);
    }

    private void readCommand() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        calcResult(left, right);
    }

    private void calcResult(int left, int right) throws IOException {
        printResult(segmentMinTree.getMin(1, 1, N, left, right),
                segmentMaxTree.getMax(1, 1, N, left, right));
    }

    private void printResult(int min, int max) throws IOException {
        bw.write(min + " " + max + "\n");
    }
}
