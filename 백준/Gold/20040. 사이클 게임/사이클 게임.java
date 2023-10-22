import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static final int NOT_FIND_CIRCLE = 0;

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int n, m;
    private static int[] parent;
    private static boolean isCircle = false;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        int result = INF;
        for (int count = 1; count <= m; count++) {
            inputLines();
            if (isCircle && result == INF) {
                result = count;
            }
        }
        if (isCircle) {
            printResult(result);
            return;
        }
        printResult(NOT_FIND_CIRCLE);
    }

    private void init() throws IOException {
        initNAndM();
        initPartent();
    }

    private void initNAndM() throws IOException {
        List<Integer> numbers = inputNumbers();
        n = numbers.get(0);
        m = numbers.get(1);
    }

    private List<Integer> inputNumbers() throws IOException {
        return Arrays.stream(bf.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void initPartent() {
        parent = new int[n];
        IntStream.range(0, n)
                .forEach(it -> parent[it] = it);
    }

    private void inputLines() throws IOException {
        List<Integer> nodes = inputNumbers();
        union(nodes.get(0), nodes.get(1));
    }

    private void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            isCircle = true;
            return;
        }
        if (x < y) {
            parent[y] = x;
            return;
        }
        parent[x] = y;
    }

    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return find(parent[x]);
    }

    private void printResult(int count) throws IOException {
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}