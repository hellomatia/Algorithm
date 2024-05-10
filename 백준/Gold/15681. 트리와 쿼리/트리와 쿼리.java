import java.util.*;
import java.io.*;

public class Main {
    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, R, Q;
    private List<Integer>[] adj;
    private int[][] range;
    private int id;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < Q; i++) {
            int root = Integer.parseInt(bf.readLine());
            printAns(calcAns(root));
        }
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        range = new int[N + 1][2];
        dfs(R, R);
    }

    private int calcAns(int root) {
        return range[root][1] - range[root][0] + 1;
    }

    private void dfs(int now, int from) {
        range[now][0] = ++id;
        for (int to : adj[now]) {
            if (to == from) { continue; }
            dfs(to, now);
        }
        range[now][1] = id;
    }

    private void printAns(int ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}