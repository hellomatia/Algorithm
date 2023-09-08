import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solution() throws IOException {

        int N = Integer.parseInt(bf.readLine());

        int[][] dist = new int[N][N];

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < N; j++) {

                dist[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {

                    if (dist[j][i] == 1 && dist[i][k] == 1) {

                        dist[j][k] = 1;

                    }

                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
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