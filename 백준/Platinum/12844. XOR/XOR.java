import java.io.*;
import java.util.*;

public class Main {

    class SegmentTree{

        int[] tree;
        int[] lazy;

        SegmentTree(int[] arr) {
            int treeSize = 1 << (int) Math.ceil(Math.log(arr.length) / Math.log(2) + 1);
            tree = new int[treeSize];
            lazy = new int[treeSize];
            init(1, 1, arr.length - 1, arr);
        }

        void init(int node, int start, int end, int[] arr) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }

            int mid = (start + end) >>> 1;
            init(node * 2, start, mid, arr);
            init(node * 2 + 1, mid + 1, end, arr);

            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        void updateLazy(int node, int start, int end) {
            if (lazy[node] != 0) {
                int count = (end - start + 1);
                if (count % 2 == 1) {
                    tree[node] ^= lazy[node];
                }
                if (start != end) {
                    lazy[node * 2] ^= lazy[node];
                    lazy[node * 2 + 1] ^= lazy[node];
                }
                lazy[node] = 0;
            }
        }

        void update(int node, int start, int end, int left, int right, int k) {
            updateLazy(node, start, end);
            if (right < start || end < left) {
                return;
            }
            if (left <= start && end <= right) {
                lazy[node] = k;
                updateLazy(node, start, end);
                return;
            }

            int mid = (start + end) >>> 1;
            update(node * 2, start, mid, left, right, k);
            update(node * 2 + 1, mid + 1, end, left, right, k);

            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        int query(int node, int start, int end, int left, int right) {
            updateLazy(node, start, end);
            if (right < start || end < left) {
                return 0;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) >>> 1;
            return query(node * 2, start, mid, left, right)
                    ^ query(node * 2 + 1, mid + 1, end, left, right);
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private SegmentTree segmentTree;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        segmentTree = new SegmentTree(arr);
    }

    private void calcResult() throws IOException {
        M = Integer.parseInt(bf.readLine());
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                segmentTree.update(1, 1, N, left  + 1, right + 1, k);
            } else {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                printResult(segmentTree.query(1, 1, N, left + 1, right  + 1));
            }
        }
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}