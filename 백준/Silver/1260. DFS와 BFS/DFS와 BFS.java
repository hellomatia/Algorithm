import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, V;
    static ArrayList<Integer>[] nodes;
    static boolean[] visited;
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());   //정점의 개수
        M = Integer.parseInt(st.nextToken());   //간선의 개수
        V = Integer.parseInt(st.nextToken());   //탐색을 시작할 정점의 번호

        nodes = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            nodes[node1].add(node2);
            nodes[node2].add(node1);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(nodes[i]);
        }

        visited = new boolean[N+1];
        visited[V] = true;
        dfs(V);

        bw.write("\n");

        Arrays.fill(visited, false);
        visited[V] = true;
        bfs(V);


        bw.flush();
        bw.close();
    }

    public void dfs(int startNode) throws IOException {

        bw.write(startNode + " ");

        if (nodes[startNode].isEmpty()) return;

        for (int i = 0; i < nodes[startNode].size(); i++) {
            int node = nodes[startNode].get(i);
            if (!visited[node]) {
                visited[node] = true;
                dfs(node);
            }
        }
    }

    public void bfs(int startNode) throws IOException{
        bw.write(startNode + " ");

        if (nodes[startNode].isEmpty()) return;

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < nodes[startNode].size(); i++) {
            queue.offer(nodes[startNode].get(i));
        }

        while(!queue.isEmpty()) {
            int node = queue.poll();

            if(visited[node]) continue;

            visited[node] = true;
            bw.write(node + " ");

            if(nodes[node].isEmpty()) continue;

            for (int i = 0; i < nodes[node].size(); i++) {
                queue.offer(nodes[node].get(i));
            }
        }
    }



    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}