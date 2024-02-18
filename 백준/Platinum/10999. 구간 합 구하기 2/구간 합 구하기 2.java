import java.io.*;
import java.util.*;

public class Main {

    class SegTree{

        int treeSize;
        long[] tree;
        long[] lazy;

        SegTree(long[] arr) {
            treeSize = 1 << (int) (Math.ceil(Math.log(arr.length) / Math.log(2)) + 1);
            tree = new long[treeSize];
            lazy = new long[treeSize];
            init(arr, 1, 1, arr.length - 1);
        }

        void init(long[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }

            int mid = (start + end) / 2;

            init(arr, node * 2, start, mid);
            init(arr, node * 2 + 1, mid + 1, end);

            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        void lazyUpdate(int node, int start, int end) {
            if (lazy[node] != 0) {
                tree[node] += (lazy[node] * (end - start + 1));
                if (start != end) {
                    lazy[node * 2] += lazy[node];
                    lazy[node * 2 + 1] += lazy[node];
                }
                lazy[node] = 0;
            }
        }

        void updateRange(int node, int start, int end, int left, int right, long diff) {
            lazyUpdate(node, start, end);

            if (right < start || end < left) return;
            if (left <= start && end <= right) {
                tree[node] += (diff * (end - start + 1));

                if (start != end) {
                    lazy[node * 2] += diff;
                    lazy[node * 2 + 1] += diff;
                }
                return;
            }

            int mid = (start + end) / 2;
            updateRange(node * 2, start, mid, left, right, diff);
            updateRange(node * 2 + 1, mid + 1, end, left, right, diff);

            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        long sum(int node, int start, int end, int left, int right) {
            lazyUpdate(node, start, end);

            if (right < start || end < left) return 0;
            if (left <= start && end <= right) return tree[node];

            int mid = (start + end) / 2;

            return sum(node * 2, start, mid, left, right)
                    + sum(node * 2 + 1, mid + 1, end, left, right);
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M, K;
    private long[] arr;
    private SegTree segTree;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < M + K; i++) {
            calcResult();
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(bf.readLine());
        }

        segTree = new SegTree(arr);
    }

    private void calcResult() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int command = Integer.parseInt(st.nextToken());

        if (command == 1) {
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            long diff = Long.parseLong(st.nextToken());
            segTree.updateRange(1, 1, N, left, right, diff);
        } else if (command == 2) {
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            printResult(segTree.sum(1, 1, N, left, right));
        }
    }

    private void printResult(long result) throws IOException {
        bw.write(result+ "\n");
    }
}
