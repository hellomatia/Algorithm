import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solution() throws IOException {

        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());

        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            dist[a][b] = Math.min(dist[a][b], c);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {

                    if (j == k || dist[j][i] == Integer.MAX_VALUE || dist[i][k] == Integer.MAX_VALUE) continue;

                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);

                }
            }
        }


        for(int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    dist[i][j] = 0;
                }

                bw.write(dist[i][j] + " ");
            }
            bw.write("\n");
        }


        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}