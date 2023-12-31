import java.io.*;
import java.util.*;
class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static List<Point> points;
    private static boolean[] visited;
    private static int[][] cost;
    private static int result;

    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private void solution() throws IOException {
        int testCase = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= testCase; t++) {
            init();
            calcResult(0, 0, 0);
            printResult(t, result);
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());

        points = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N + 2; i++) {
            points.add(
                    new Point(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));
        }

        cost = new int[N + 2][N + 2];
        for (int i = 0; i < N + 2; i++) {
            for (int j = i + 1; j < N + 2; j++) {
                int tempCost = calcCost(points.get(i), points.get(j));
                cost[i][j] = tempCost;
                cost[j][i] = tempCost;
            }
        }

        visited = new boolean[N + 2];
        result = Integer.MAX_VALUE;
    }

    private int calcCost(Point point1, Point point2) {
        return Math.abs(point1.x - point2.x) + Math.abs(point1.y - point2.y);
    }

    private void calcResult(int now, int visitCount,int totalCost) {
        if (visitCount == N) {
            result = Math.min(totalCost + cost[now][1], result);
            return;
        }

        for (int next = 2; next < N + 2; next++) {
            if (visited[next]) {
                continue;
            }
            visited[next] = true;
            calcResult(next, visitCount + 1, totalCost + cost[now][next]);
            visited[next] = false;
        }
    }

    private void printResult(int testCase, int result) throws IOException {
        bw.write("#"+testCase + " " + result + "\n");
    }
}
