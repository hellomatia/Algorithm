import java.io.*;
import java.util.*;

class Node {
    int n;
    int count;
    Node(int n, int count) {
        this.n = n;
        this.count = count;
    }
}


public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static ArrayList<Integer>[] nodes;
    static boolean[] visited;
    public void solution() throws IOException {

        n = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(bf.readLine());

        nodes = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());

            int parentsNode = Integer.parseInt(st.nextToken());
            int childNode = Integer.parseInt(st.nextToken());

            nodes[parentsNode].add(childNode);
            nodes[childNode].add(parentsNode);

        }

        visited = new boolean[n + 1];
        bw.write(calculateChonesu(startNode, endNode) + "\n");

        bw.flush();
        bw.close();
    }

    public int calculateChonesu(int startNode, int endNode) {

        Queue<Node> queue = new LinkedList<>();

        int chonesu = -1;
        visited[startNode] = true;
        queue.offer(new Node(startNode, 0));

        while (!queue.isEmpty()) {

            Node now = queue.poll();

            if (now.n == endNode) {
                chonesu = now.count;
            }

            for (int i = 0; i < nodes[now.n].size(); i++) {
                int next = nodes[now.n].get(i);

                if (visited[next]) continue;

                visited[next] = true;
                queue.offer(new Node(next, now.count + 1));
            }
        }

        return chonesu;
    }



    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}