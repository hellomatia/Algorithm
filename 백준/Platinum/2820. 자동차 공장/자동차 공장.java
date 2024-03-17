import java.io.*;
import java.util.*;

public class Main {

    class Segtree {
        long[] tree;
        long[] lazy;

        Segtree(int[] arr) {
            int treeSize = 1 << (int) Math.ceil(Math.log(arr.length) / Math.log(2) + 1);
            tree = new long[treeSize];
            lazy = new long[treeSize];
            init(1, 1, arr.length - 1, arr);
        }

        void init(int node, int start, int end, int[] arr) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }

            int mid = start + end >>> 1;
            init(node * 2, start, mid, arr);
            init(node * 2 + 1, mid + 1, end, arr);
        }

        void lazyUpdate(int node, int start, int end) {
            if (lazy[node] != 0) {
                tree[node] += (end - start  + 1) * lazy[node];
                if (start != end) {
                    lazy[node * 2] += lazy[node];
                    lazy[node * 2 + 1] += lazy[node];
                }
                lazy[node] = 0;
            }
        }

        void update(int node, int start, int end, int left, int right, int diff) {
            lazyUpdate(node, start, end);
            if (right < start || end < left)
                return;
            if (left <= start && end <= right) {
                lazy[node] += diff;
                lazyUpdate(node, start, end);
                return;
            }

            int mid = start + end >>> 1;
            update(node * 2, start, mid, left, right, diff);
            update(node * 2 + 1, mid + 1, end, left, right, diff);
        }

        long query(int node, int start, int end, int index) {
            lazyUpdate(node, start, end);
            int mid = start + end >>> 1;
            if (start == end) {
                return tree[node];
            } else if (index <= mid) {
                return query(node * 2, start, mid, index);
            } else {
                return query(node * 2 + 1, mid + 1, end, index);
            }
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private List<Integer>[] nearNode;
    private int[] arr;
    private int[] salary;
    private int[][] range;
    private Segtree segtree;
    private int id;

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
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        salary = new int[N + 1];
        nearNode = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nearNode[i] = new ArrayList<>();
        }

        salary[1] = Integer.parseInt(bf.readLine());
        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            salary[i] = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            nearNode[from].add(i);
        }

        arr = new int[N + 1];
        range = new int[N + 1][2];
        dfs(1);

        segtree = new Segtree(arr);
    }

    private void dfs(int from) {
        range[from][0] = ++id;
        arr[id] = salary[from];
        for (int to : nearNode[from]) {
            dfs(to);
        }
        range[from][1] = id;
    }

    private void calcResult() throws IOException {
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String command = st.nextToken();
            if (command.equals("u")) {
                int a = Integer.parseInt(st.nextToken());
                printResult(segtree.query(1, 1, N, range[a][0]));
            } else {
                int a = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                segtree.update(1, 1, N, range[a][0] + 1, range[a][1], x);
            }
        }
    }

    public void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}