import java.util.*;
import java.io.*;

public class Main {
    class Segtree {
        int[] tree;
        int[] lazy;

        Segtree(int n) {
            int treeSize = (int) 1 << ((int) Math.ceil(Math.log(n) / Math.log(2)) + 1);
            tree = new int[treeSize];
            lazy = new int[treeSize];
        }

        void lazyUpdate(int node, int start, int end) {
            if (lazy[node] != 0) {
                tree[node] += (end - start + 1) * lazy[node];
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
                lazy[node] = diff;
                lazyUpdate(node, start, end);
                return;
            }

            int mid = (start + end) / 2;
            update(node * 2, start, mid, left, right, diff);
            update(node * 2 + 1, mid + 1, end, left, right, diff);
        }

        int query(int node, int start, int end, int index) {
            lazyUpdate(node, start, end);
            if (start == end) {
                return tree[node];
            } else {
                int mid = start + end >>> 1;
                if (index <= mid) {
                    return query(node * 2, start, mid, index);
                } else {
                    return query(node * 2 + 1, mid + 1, end, index);
                }
            }
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int n, m;
    private List<Integer>[] adj;
    private int[][] range;
    private int id;
    private Segtree segtree;
    private String ans;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        st = new StringTokenizer(bf.readLine());
        st.nextToken();
        for (int i = 2; i <= n; i++) {
            int node = Integer.parseInt(st.nextToken());
            adj[node].add(i);
        }

        range = new int[n + 1][2];
        dfs(1);
        segtree = new Segtree(n);
    }

    private String calcAns() throws IOException {
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            segtree.update(1, 1, n, range[a][0], range[a][1], b);
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            ans.append(segtree.query(1, 1, n, range[i][0])).append(" ");
        }

        return ans.toString();
    }

    private void dfs(int now) {
        range[now][0] = ++id;
        for (int next : adj[now]) {
            dfs(next);
        }
        range[now][1] = id;
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}