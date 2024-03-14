

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

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
            lazyUpdate(node, start, end); // 지연 업데이트를 적용합니다.
            if (right < start || end < left) // 범위 밖의 경우 업데이트하지 않고 반환합니다.
                return;
            if (left <= start && end <= right) { // 완전히 범위 안에 있는 경우
                lazy[node] = diff;
                lazyUpdate(node, start, end);
                return;
            }

            int mid = (start + end) / 2; // 범위를 반으로 나누어 재귀적으로 업데이트를 진행합니다.
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

    private int N, M;
    private int id;
    private int[][] range;
    private List<Integer>[] child;
    private Segtree segtree;
    private StringBuilder sb;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcResult();
        printResult();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        child = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            child[i] = new ArrayList<>();
        }

        st = new StringTokenizer(bf.readLine());
        st.nextToken();
        for (int i = 2; i <= N; i++) {
            int p = Integer.parseInt(st.nextToken());
            child[p].add(i);
        }
        range = new int[N + 1][2];
        dfs(1);
        sb = new StringBuilder();
        segtree = new Segtree(N);
    }

    private void dfs(int now) {
        range[now][0] = ++id;
        for (int next : child[now]) {
            dfs(next);
        }
        range[now][1] = id;
    }

    private void calcResult() throws IOException {
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                segtree.update(1, 1, N, range[a][0], range[a][1], b);
            } else {
                int a = Integer.parseInt(st.nextToken());
                sb.append(segtree.query(1, 1, N, range[a][0])).append("\n");
            }
        }
    }

    private void printResult() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }
}
