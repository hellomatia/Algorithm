import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[][][] dp = new int[21][21][21];

    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(bf.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        while (a != -1 || b != -1 || c != -1) {
            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ");
            sb.append(recursive(a, b, c)).append("\n");

            st = new StringTokenizer(bf.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    public int recursive (int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        }
        if (a > 20 || b > 20 || c > 20 ) {
            return recursive(20, 20, 20);
        }
        if (dp[a][b][c] != 0) {
            return dp[a][b][c];
        }
        if (a < b && b < c) {
            dp[a][b][c] = recursive(a, b, c-1) + recursive(a, b-1, c-1) - recursive(a, b-1, c);
            return dp[a][b][c];
        }

        dp[a][b][c] = recursive(a-1, b, c) + recursive(a-1, b-1, c) + recursive(a-1, b, c-1) - recursive(a-1, b-1, c-1);
        return dp[a][b][c];
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}