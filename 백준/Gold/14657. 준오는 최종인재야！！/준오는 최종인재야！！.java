import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    private int N, T;
    private List<Node>[] graph;
    private boolean[] visited;
    private int maxDepth, minWeight, farthestNode;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        int result = solve();
        printResult(result);
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, t));
            graph[b].add(new Node(a, t));
        }

        visited = new boolean[N + 1];
    }

    private int solve() {
        // First DFS to find one end of the diameter
        dfs(1, 0, 0);
        
        // Reset for second DFS
        Arrays.fill(visited, false);
        maxDepth = 0;
        minWeight = Integer.MAX_VALUE;
        
        // Second DFS to find the other end and the minimum weight
        dfs(farthestNode, 0, 0);
        
        // Calculate the result
        return (minWeight + T - 1) / T;  // Ceiling division
    }

    private void dfs(int node, int depth, int weight) {
        visited[node] = true;

        if (depth > maxDepth || (depth == maxDepth && weight < minWeight)) {
            maxDepth = depth;
            minWeight = weight;
            farthestNode = node;
        }

        for (Node next : graph[node]) {
            if (!visited[next.to]) {
                dfs(next.to, depth + 1, weight + next.weight);
            }
        }
    }

    private void printResult(int result) throws IOException {
        bw.write(String.valueOf(result));
        bw.newLine();
        bw.flush();
    }

    private static class Node {
        int to, weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}