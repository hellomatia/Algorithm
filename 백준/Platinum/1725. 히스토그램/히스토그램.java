import java.io.*;
import java.util.*;

class SegmentTree {

    int[] arr;
    int treeSize;
    int[] tree;

    public SegmentTree(int[] arr) {
        this.arr = arr;
        this.treeSize = (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)) + 1);
        this.tree = new int[treeSize];
    }

    public void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = start;
            return;
        }
        int mid = (start + end) / 2;
        init(node * 2, start, mid);
        init(node * 2 + 1, mid + 1, end);
        tree[node] = arr[tree[node * 2]] < arr[tree[node * 2 + 1]] ? tree[node * 2] : tree[node * 2 + 1];
    }

    public int getMinValueIndex(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        int leftValue = getMinValueIndex(node * 2, start, mid, left, right);
        int rightValue = getMinValueIndex(node * 2 + 1, mid + 1, end, left, right);
        return arr[leftValue] < arr[rightValue] ? leftValue : rightValue;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static int[] arr;
    private static SegmentTree segmentTree;
    private static int result;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcResult(1, N);
        printResult(result);
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        arr = new int[N + 1];
        arr[0] = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }
        segmentTree = new SegmentTree(arr);
        segmentTree.init(1, 1, N);
    }

    private void calcResult(int left, int right) {
        if (left > right) {
            return;
        }

        int index = segmentTree.getMinValueIndex(1, 1, N, left, right);
        result = Math.max(result, arr[index] * (right - left  + 1));

        calcResult(left, index - 1);
        calcResult(index + 1, right);
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
