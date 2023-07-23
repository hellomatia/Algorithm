import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class ElectricWire {
    int a;
    int b;
    ElectricWire (int a, int b) {
        this.a = a;
        this.b = b;
    }
}

public class Main {
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<ElectricWire> electricWires = new ArrayList<>();

        int N = Integer.parseInt(bf.readLine());
        int[][] dp = new int[N][2];


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            electricWires.add(new ElectricWire(a, b));
        }

        electricWires.sort((o1, o2) -> {
            return o1.a - o2.a;
        });

        int maxCount = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            dp[i][0] = electricWires.get(i).b;
            dp[i][1] = 1;
            for (int j = i-1; j >= 0; j--) {
                if (dp[i][0] > dp[j][0] && dp[i][1] <= dp[j][1]) {
                    dp[i][1] = dp[j][1] + 1;
                }
            }
            maxCount = Math.max(maxCount, dp[i][1]);
        }

        bw.write((N - maxCount) + "\n");
        bw.flush();
        bw.close();

    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}