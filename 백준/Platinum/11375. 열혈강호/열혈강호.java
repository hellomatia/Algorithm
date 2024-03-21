import java.io.*;
import java.util.*;

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private int[] A;
    private int[] B;
    private List<Integer>[] adj;
    private boolean[] visited;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        A = new int[N + 1];
        B = new int[M + 1];
        visited = new boolean[N + 1];
    }

    private int calcResult() throws IOException {
        int match = 0;
        for (int i = 1; i <= N; i++) {
            if (A[i] == 0) {
                Arrays.fill(visited, false);
                if (dfs(i)) match++;
            }
        }
        return match;
    }

    private boolean dfs(int from) {
        visited[from] = true;
        for (int to : adj[from]) {
            if (B[to] == 0 || !visited[B[to]] && dfs(B[to])) {
                A[from] = to;
                B[to] = from;
                return true;
            }
        }
        return false;
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}