import java.io.*;
import java.util.*;

public class Main {

    class Intersection {
        long cash;
        Set<Integer> nearIntersections;

        public Intersection(long cash) {
            this.cash = cash;
            nearIntersections = new HashSet<>();
        }

        void addIntersection(int intersection) {
            nearIntersections.add(intersection);
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M, S, P;
    private int[] cash;
    private boolean[] hasRestaurant;
    private List<Integer>[] nearNodes;
    private List<Intersection> intersectionList;
    private int id, size;
    private int[] d;
    private boolean[] isFinished;
    private Deque<Integer> stack;
    private int[] intersectionNum;
    private long result;
    private long[] dp;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcResult();
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nearNodes = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nearNodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nearNodes[from].add(to);
        }

        cash = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            cash[i] = Integer.parseInt(bf.readLine());
        }

        st = new StringTokenizer(bf.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        hasRestaurant = new boolean[N + 1];
        for (int i = 0; i < P; i++) {
            int num = Integer.parseInt(st.nextToken());
            hasRestaurant[num] = true;
        }

        d = new int[N + 1];
        isFinished = new boolean[N + 1];
        stack = new ArrayDeque<>();
        intersectionList = new ArrayList<>();
        intersectionNum = new int[N + 1];
    }

    private int scc(int from) {
        d[from] = ++id;
        int parent = d[from];
        stack.offerLast(from);

        for (int to : nearNodes[from]) {
            if (d[to] == 0) parent = Math.min(parent, scc(to));
            else if (!isFinished[to]) parent = Math.min(parent, d[to]);
        }

        if (parent == d[from]) {
            long totalCash = 0;
            while (!stack.isEmpty()) {
                int num = stack.pollLast();
                isFinished[num] = true;
                intersectionNum[num] = size;
                totalCash += cash[num];
                if (num == from) break;
            }
            Intersection intersection = new Intersection(totalCash);
            intersectionList.add(intersection);
            size++;
        }

        return parent;
    }

    private void calcResult() {
        for (int i = 1; i <= N; i++) {
            if (!isFinished[i]) scc(i);
        }

        for (int from = 1; from <= N; from++) {
            for (int to : nearNodes[from]) {
                if (intersectionNum[to] == intersectionNum[from]) continue;
                intersectionList.get(intersectionNum[from])
                        .addIntersection(intersectionNum[to]);
            }
        }

        bfs();

        for (int i = 1; i <= N; i++) {
            if (hasRestaurant[i]) result = Math.max(result, dp[intersectionNum[i]]);
        }
    }

    private void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        dp = new long[size];
        q.offer(intersectionNum[S]);
        dp[intersectionNum[S]] = intersectionList.get(intersectionNum[S]).cash;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : intersectionList.get(now).nearIntersections) {
                if (dp[next] < dp[now] + intersectionList.get(next).cash) {
                    dp[next] = dp[now] + intersectionList.get(next).cash;
                    q.offer(next);
                }
            }
        }
    }

    private void printResult() throws IOException {
        bw.write(result+ "\n");
    }
}
