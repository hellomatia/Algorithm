import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static int[] requestedAmountSum;
    private static List<Integer> requestedAmounts;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(findBudgetLimit());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initN();
        initRequestedAmounts();
        initRequestedAmountSum();
        initM();
    }

    private void initN() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private void initRequestedAmounts() throws IOException {
        requestedAmounts = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int count = 0; count < N; count++) {
            int requestedAmount = Integer.parseInt(st.nextToken());
            requestedAmounts.add(requestedAmount);
        }

        Collections.sort(requestedAmounts);
    }

    private void initRequestedAmountSum() {
        requestedAmountSum = new int[N + 1];
        for (int index = 1; index <= N; index++) {
            requestedAmountSum[index] = requestedAmountSum[index - 1] + requestedAmounts.get(index - 1);
        }
    }

    private void initM() throws IOException {
        M = Integer.parseInt(bf.readLine());
    }

    private int findBudgetLimit() {
        int budgetLimit = 0;
        for (int index = 0; index < N; index++) {
            if (requestedAmountSum[index] + requestedAmounts.get(index) * (N - index) >= M) {
                budgetLimit = requestedAmounts.get(index);
                int count = N - index;
               while (requestedAmountSum[index] + budgetLimit * count > M) {
                   budgetLimit--;
               }
               break;
            }
        }

        if (budgetLimit == 0) {
            budgetLimit = requestedAmounts.get(N - 1);
        }

        return budgetLimit;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
