import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] buildingTimes;
    static Queue<Integer>[] prepareBuilding;
    static int[] dp;

    public void solution() throws IOException {

        N = Integer.parseInt(bf.readLine());
        buildingTimes = new int[N];
        prepareBuilding = new Queue[N];


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            buildingTimes[i] = Integer.parseInt(st.nextToken());

            prepareBuilding[i] = new LinkedList<>();

            int buildingNum = Integer.parseInt(st.nextToken());
            while(buildingNum != -1) {
                prepareBuilding[i].offer(buildingNum-1);
                buildingNum = Integer.parseInt(st.nextToken());
            }
        }


        dp = new int[N];

        for (int i = 0; i < N; i++) {

            if (dp[i] != 0) continue;

            int maxTime = 0;

            while (!prepareBuilding[i].isEmpty()) {
                int nowBuilding = prepareBuilding[i].poll();
                maxTime = Math.max(minBuildingTime(nowBuilding), maxTime);
            }

            dp[i] = maxTime + buildingTimes[i];
        }

        for (int i = 0; i < N; i++) {
            bw.write(dp[i] + "\n");
        }


        bw.flush();
        bw.close();
    }

    public int minBuildingTime(int buildingNum) {

        if(dp[buildingNum] != 0) {
            return dp[buildingNum];
        }

        int maxTime = 0;

        while (!prepareBuilding[buildingNum].isEmpty()) {
            int nowBuilding = prepareBuilding[buildingNum].poll();
            maxTime = Math.max(minBuildingTime(nowBuilding), maxTime);
        }

        return dp[buildingNum] = maxTime + buildingTimes[buildingNum];

    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}