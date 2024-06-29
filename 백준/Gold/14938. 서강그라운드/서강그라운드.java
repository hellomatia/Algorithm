import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int INF = 987654321;

    private int n, m, r;
    private int[] numOfItems;
    private int[][] dist;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        numOfItems = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            numOfItems[i] = Integer.parseInt(st.nextToken());
        }

        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());
            dist[a][b] = Math.min(dist[a][b], l);
            dist[b][a] = dist[a][b];
        }
    }

    private int calcAns() {
        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int totalItems = 0;
            for (int j = 0; j < n; j++) {
                if (dist[i][j] <= m) {
                    totalItems += numOfItems[j];
                }
            }
            ans = Math.max(ans, totalItems);
        }
        return ans;
    }

    private void printAns(int ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}
