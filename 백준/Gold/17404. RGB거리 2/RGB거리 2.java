import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Cost {

    int red;
    int green;
    int blue;

    public Cost(List<Integer> cost) {
        this.red = cost.get(0);
        this.green = cost.get(1);
        this.blue = cost.get(2);
    }

    public int getColor(int color) {
        if (color == 0) {
            return red;
        }
        if (color == 1) {
            return green;
        }
        return blue;
    }
}

public class Main {

    private static final int COLOR_COUNT = 3;
    private static final int INF = 999_999;
    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static List<Cost> costs = new ArrayList<>();
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        int result = INF;
        for (int color = RED; color < COLOR_COUNT; color++) {
            result = Math.min(result, calculationMinCost(color));
        }
        printResult(result);
    }

    private void init() throws IOException {
        initN();
        initCosts();
    }

    private void initN() throws IOException {
        N = inputN();
    }

    private int inputN() throws IOException {
        return Integer.parseInt(bf.readLine());
    }

    private void initCosts() throws IOException {
        for (int i = 0; i < N; i++) {
            costs.add(new Cost(inputCost()));
        }
    }

    private List<Integer> inputCost() throws IOException {
        return Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private int calculationMinCost(int startColor) {
        initDp(startColor);
        for (int house = 1; house < N; house++) {
            for (int color = RED; color < COLOR_COUNT; color++) {
                dp[house][color] = getMinColorSum(house, color) + costs.get(house).getColor(color);
            }
        }
        return getMinSumCost(startColor);
    }

    private void initDp(int startColor) {
        dp = new int[N][COLOR_COUNT];
        Arrays.fill(dp[0], INF);
        dp[0][startColor] = costs.get(0).getColor(startColor);
    }

    private int getMinColorSum(int house, int color) {
        if (color == RED) {
            return Math.min(dp[house - 1][GREEN], dp[house - 1][BLUE]);
        }
        if (color == GREEN) {
            return Math.min(dp[house - 1][RED], dp[house - 1][BLUE]);
        }
        return Math.min(dp[house - 1][GREEN], dp[house - 1][RED]);
    }

    private int getMinSumCost(int startColor) {
        if (startColor == RED) {
            return Math.min(dp[N - 1][GREEN], dp[N - 1][BLUE]);
        }
        if (startColor == GREEN) {
            return Math.min(dp[N - 1][RED], dp[N - 1][BLUE]);
        }
        return Math.min(dp[N - 1][RED], dp[N - 1][GREEN]);
    }

    private void printResult(int totalCost) throws IOException {
        bw.write(totalCost + "\n");
        bw.flush();
        bw.close();
    }
}