import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int[][] map;
    static int[][] dp;
    static int maxArea = 0;
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = bf.readLine();
            int cnt = 0;
            for (int j = m - 1; j >= 0; j--) {
                 map[i][j] = str.charAt(j) - '0';
                 if (map[i][j] == 1) {
                     cnt++;
                     dp[i][j] = cnt;
                 } else {
                     cnt = 0;
                 }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[j][i] == 1) {
                    maxArea = Math.max(maxArea, findSquareArea(j, i));
                }
            }
        }

        /*

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();

         */


        bw.write(maxArea + "\n");

        bw.flush();
        bw.close();
    }

    public int findSquareArea(int x, int y) {
        int side = 0;
        int min = Integer.MAX_VALUE;
        for (int i = x; i < n; i++) {
            min = Math.min(min, dp[i][y]);
            if (side >= min) {
                break;
            } else {
                side++;
            }
        }
        return side * side;
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}