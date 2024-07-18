import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, S;
    private List<Integer>[] adj;
    private int id;
    private int[][] range;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int parent = Integer.parseInt(st.nextToken());
            while(true) {
                int child = Integer.parseInt(st.nextToken());
                if (child == -1) {
                    break;
                }
                adj[parent].add(child);
            }
        }
        S = Integer.parseInt(bf.readLine());
    }

    private String calcAns() {
        for (int i = 1; i <= N; i++) {
            Collections.sort(adj[i]);
        }
        range = new int[N + 1][2];
        dfs(S, S);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(i).append(" ")
                    .append(range[i][0]).append(" ")
                    .append(range[i][1]).append("\n");
        }
        return sb.toString();
    }

    private void dfs(int now, int from) {
        range[now][0] = ++id;
        for (int to : adj[now]) {
            if (to == from) { continue; }
            dfs(to, now);
        }
        range[now][1] = ++id;
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}
