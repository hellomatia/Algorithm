import java.io.*;
import java.util.*;

class SegmentTree {

    int[] arr;
    int treeSize;
    int[] tree;

    SegmentTree(int[] arr) {
        this.arr = arr;
        this.treeSize = (int) Math.pow(2, (int) Math.ceil(Math.log(arr.length) / Math.log(2)) + 1);
        this.tree = new int[treeSize];
    }

    void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = start;
            return;
        }
        init(node * 2, start, (start + end) / 2);
        init(node * 2 + 1, (start + end) / 2 + 1, end);
        if (arr[tree[node * 2]] < arr[tree[node * 2 + 1]]) {
            tree[node] = tree[node * 2];
        } else if (arr[tree[node * 2]] > arr[tree[node * 2 + 1]]) {
            tree[node] = tree[node * 2 + 1];
        } else {
            tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    void update(int node, int start, int end, int index, int value) {
        if (index < start || end < index) {
            return;
        }
        if (start == end) {
            arr[index] = value;
            tree[node] = index;
            return;
        }
        update(node * 2, start, (start + end) / 2, index, value);
        update(node * 2 + 1, (start + end) / 2 + 1, end, index, value);
        if (arr[tree[node * 2]] < arr[tree[node * 2 + 1]]) {
            tree[node] = tree[node * 2];
        } else if (arr[tree[node * 2]] > arr[tree[node * 2 + 1]]) {
            tree[node] = tree[node * 2 + 1];
        } else {
            tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    int getMinValueIndex(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return - 1;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }

        int leftIndex = getMinValueIndex(node * 2, start, (start + end) / 2, left, right);
        int rightIndex = getMinValueIndex(node * 2 + 1, (start + end) / 2 + 1, end, left, right);

        if (leftIndex == -1) {
            return rightIndex;
        } else if (rightIndex == -1) {
            return leftIndex;
        }

        if (arr[leftIndex] <= arr[rightIndex]) {
            return leftIndex;
        } else {
            return rightIndex;
        }
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    private static int N;
    private static int[] arr;
    private static SegmentTree segmentTree;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        int numOfCommands = Integer.parseInt(bf.readLine());
        for (int i = 0; i < numOfCommands; i++) {
            readCommand();
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        segmentTree = new SegmentTree(arr);
        segmentTree.init(1, 1, N);
    }

    private void readCommand() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int command = Integer.parseInt(st.nextToken());
        if (command == 1) {
            int index = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            segmentTree.update(1, 1, N, index, value);
            return;
        }
        if (command == 2) {
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            printResult(segmentTree.getMinValueIndex(1, 1, N, left, right));
        }
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
