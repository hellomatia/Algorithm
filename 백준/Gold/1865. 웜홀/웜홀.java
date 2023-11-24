import java.io.*;
import java.util.*;
class Node {
    int num;
    int time;

    Node(int num, int time) {
        this.num = num;
        this.time = time;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int TC, N, M, W;
    private static List<Node>[] roads;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        initTC();
        for (int testCase = 0; testCase < TC; testCase++) {
            initNMW();
            initRoads();
            printResult(canSaveTime());
        }
        bw.flush();
        bw.close();
    }

    private void initTC() throws IOException {
        TC = Integer.parseInt(bf.readLine());
    }

    private void initNMW() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
    }

    private void initRoads() throws IOException {
        roads = new List[N + 1];
        for (int node = 1; node <= N; node++) {
            roads[node] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            roads[start].add(new Node(end, time));
            roads[end].add(new Node(start, time));
        }

        for (int i = 0; i < W; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            roads[start].add(new Node(end, - time));
        }
    }

    private boolean canSaveTime() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 987654321);
        dist[1] = 0;
        boolean isUpdate = false;

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= N; j++) {
                for (Node node : roads[j]) {
                    int next = node.num;
                    int time = node.time;
                    if (dist[next] > dist[j] + time) {
                        dist[next] = dist[j] + time;
                        isUpdate = true;
                    }
                }
            }
        }

        if (isUpdate) {
            for (int i = 1; i <= N; i++) {
                for (Node node : roads[i]) {
                    if (dist[node.num] > dist[i] + node.time) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void printResult(boolean canSaveTime) throws IOException {
        if (canSaveTime) {
            bw.write("YES\n");
            return;
        }
        bw.write("NO\n");
    }
}
