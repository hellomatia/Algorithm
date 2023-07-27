import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solution() throws IOException {

        int N = Integer.parseInt(bf.readLine());
        int[][] map = new int[N][3];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] dpMax = new int[N][3];
        int[][] dpMin = new int[N][3];

        dpMax[0][0] = map[0][0];
        dpMax[0][1] = map[0][1];
        dpMax[0][2] = map[0][2];

        dpMin[0][0] = map[0][0];
        dpMin[0][1] = map[0][1];
        dpMin[0][2] = map[0][2];

        for (int i = 1; i < N; i++) {
            dpMax[i][0] = Math.max(dpMax[i-1][0], dpMax[i-1][1]) + map[i][0];
            dpMax[i][2] = Math.max(dpMax[i-1][2], dpMax[i-1][1]) + map[i][2];
            dpMax[i][1] = Math.max(dpMax[i-1][0], Math.max(dpMax[i-1][1], dpMax[i-1][2])) + map[i][1];

            dpMin[i][0] = Math.min(dpMin[i-1][0], dpMin[i-1][1]) + map[i][0];
            dpMin[i][2] = Math.min(dpMin[i-1][2], dpMin[i-1][1]) + map[i][2];
            dpMin[i][1] = Math.min(dpMin[i-1][0], Math.min(dpMin[i-1][1], dpMin[i-1][2])) + map[i][1];
        }

        int max = Math.max(dpMax[N-1][0], Math.max(dpMax[N-1][1], dpMax[N-1][2]));
        int min = Math.min(dpMin[N-1][0], Math.min(dpMin[N-1][1], dpMin[N-1][2]));


        bw.write(max + " " + min + "\n");
        bw.flush();
        bw.close();

    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}