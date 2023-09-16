import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        int K = Integer.parseInt(bf.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            ArrayList<Integer>[] nodes = new ArrayList[V + 1];
            for (int j = 1; j <= V; j++) {
                nodes[j] = new ArrayList<>();
            }

            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(bf.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                nodes[u].add(v);
                nodes[v].add(u);
            }

            int[] nodeColor = new int[V + 1];
            Queue<Integer> queue = new LinkedList<>();

            boolean checkBipartite = true;

            for (int j = 1; j <= V; j++) {
                if (nodeColor[j] != 0) continue;

                nodeColor[j] = 1;
                queue.offer(j);

                while(!queue.isEmpty() && checkBipartite) {

                    int now = queue.poll();

                    for (int k = 0; k < nodes[now].size(); k++) {

                        int next = nodes[now].get(k);

                        if (nodeColor[next] == nodeColor[now]) {
                            checkBipartite = false;
                            break;
                        }

                        if (nodeColor[next] != 0) continue;

                        if (nodeColor[now] == 1) {
                            nodeColor[next] = -1;
                        } else {
                            nodeColor[next] = 1;
                        }

                        queue.offer(next);
                    }
                }
            }


            if (checkBipartite) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }

        }

        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}