import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] visited;
    static int count;
    static ArrayList<Integer>[] nodes;
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            nodes[u].add(v);
            nodes[v].add(u);
        }

        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            count++;
            dfs(i);
        }

        bw.write(count + "\n");

        bw.flush();
        bw.close();
    }

    public void dfs(int startNode) {

        for (int i = 0; i < nodes[startNode].size(); i++) {
            int node = nodes[startNode].get(i);

            if (visited[node]) continue;
            visited[node] = true;
            dfs(node);
        }

    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}