import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solution() throws IOException {


        int T = Integer.parseInt(bf.readLine());

        for (int i = 0; i < T; i++) {
            buildBuilding();
        }

        bw.flush();
        bw.close();

    }

    public void buildBuilding() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());   //건물의 개수
        int K = Integer.parseInt(st.nextToken());   //규칙의 개수

        int[] buildTime = new int[N+1];             //각 건물을 짓는대 걸리는 시간
        int[] buildCost = new int[N+1];             //각 건물까지 짓는데 걸리는 최대 시간

        int[] buildingCount = new int[N+1];         //각 건물까지 지을 수 있을 때까지 지어야할 건물 개수

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            buildTime[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer>[] rules = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            rules[i] = new ArrayList<>();
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            rules[X].add(Y);
            buildingCount[Y]++;
        }

        int W = Integer.parseInt(bf.readLine());    //승리하기 위해 건설해야 할 건물의 번호

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (buildingCount[i] == 0) {
                buildCost[i] = buildTime[i];
                queue.offer(i);
            }

        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0; i < rules[now].size(); i++) {
                int next = rules[now].get(i);
                buildCost[next] = Math.max(buildCost[now] + buildTime[next], buildCost[next]);
                buildingCount[next]--;
                if(buildingCount[next] == 0) queue.offer(next);
            }

        }

        bw.write(buildCost[W]+"\n");

    }




    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}