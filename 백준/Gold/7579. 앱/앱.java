import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

       StringTokenizer st = new StringTokenizer(br.readLine());
       int numOfApps = Integer.parseInt(st.nextToken());
       int bytesNeeded = Integer.parseInt(st.nextToken());

       int[] appMemory = new int[numOfApps];
       st = new StringTokenizer(br.readLine());
       for (int i = 0; i < numOfApps; i++) {
           appMemory[i] = Integer.parseInt(st.nextToken());
       }

       int[] appCost = new int[numOfApps];
       st = new StringTokenizer(br.readLine());
       for (int i = 0; i < numOfApps; i++) {
           appCost[i] = Integer.parseInt(st.nextToken());
       }

       int[] dp = new int[10_001];
        Arrays.fill(dp, -1);

       for (int i = 0; i < numOfApps; i++) {
           int cost = appCost[i];
           for (int j = 10_000; j >= cost; j--) {
                if (dp[j-cost] != -1) {
                    int memory = dp[j - cost] + appMemory[i];
                    dp[j] = Math.max(dp[j], memory);
                }
           }
           dp[cost] = Math.max(dp[cost], appMemory[i]);
       }

        for (int i = 0; i <= 10_000; i++) {
            if (dp[i] >= bytesNeeded) {
                bw.write(i + "\n");
                break;
            }
        }

        bw.flush();
        bw.close();
   }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
