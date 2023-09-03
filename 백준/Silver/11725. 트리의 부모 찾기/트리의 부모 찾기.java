import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        int N = Integer.parseInt(bf.readLine());

        ArrayList<Integer>[] nodes = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {

            nodes[i] = new ArrayList<>();

        }

        for (int i = 0; i < N - 1; i++) {

            StringTokenizer st = new StringTokenizer(bf.readLine());

            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            nodes[node1].add(node2);
            nodes[node2].add(node1);

        }


        int[] parentNode = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < nodes[1].size(); i++) {

            int node = nodes[1].get(i);
            visited[node] = true;
            parentNode[node] = 1;
            queue.offer(node);

        }


        while (!queue.isEmpty()) {

            int now = queue.poll();

            for (int i = 0; i < nodes[now].size(); i++) {

                int next = nodes[now].get(i);

                if (visited[next]) continue;

                visited[next] = true;
                parentNode[next] = now;
                queue.offer(next);

            }
        }


        for (int i = 2; i <= N; i++) {

            bw.write(parentNode[i] + "\n");

        }




        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}