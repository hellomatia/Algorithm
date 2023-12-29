import java.io.*;
import java.util.*;

public class Solution {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] parents;
    private static int N;
    private static int M;
    private static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private void solution() throws IOException {
        int testCase = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= testCase; t++) {
            init();
            printResult(t, calcResult());
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            union(num1, num2);
        }
    }

    private void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return;
        }
        if (x < y) {
            parents[y] = x;
        } else {
            parents[x] = y;
        }
    }

    private int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return find(parents[x]);
    }

    private int calcResult() {
        set = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            set.add(find(i));
        }
        return set.size();
    }

    private void printResult(int testCase, int result) throws IOException {
        bw.write("#"+testCase + " " + result + "\n");
    }
}
