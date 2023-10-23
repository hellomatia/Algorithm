import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final long INF = Long.MAX_VALUE;
    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static List<Long> solutions;
    private static long minZeroSolution = INF;
    private static int[] resultIndex = new int[3];

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int mid = 1; mid < N - 1; mid++) {
            findSumZeroSolutions(mid);
        }
        printResult();
    }

    private void init() throws IOException {
        initN();
        initSolutions();
    }

    private void initN() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private void initSolutions() throws IOException {
        solutions = inputNumbers();
        sortSolutions();
    }

    private List<Long> inputNumbers() throws IOException {
        return Arrays.stream(bf.readLine().split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    private void sortSolutions() {
        Collections.sort(solutions);
        //printSolutions();
    }

    private void findSumZeroSolutions(int mid) {
        int forward = 0;
        int backward = N - 1;
        long midValue = solutions.get(mid);

        while (forward < mid && mid < backward) {

            long sum = midValue + solutions.get(forward) + solutions.get(backward);

            if (minZeroSolution > Math.abs(sum)) {
                minZeroSolution = Math.abs(sum);
                resultIndex[0] = forward;
                resultIndex[1] = mid;
                resultIndex[2] = backward;
            }

            //System.out.println(sum + " " + forward + " " + mid + " " + backward);

            if (sum < 0) {
                forward++;
            }
            else if (sum > 0) {
                backward--;
            }
            else {
                return;
            }
        }
    }

    private void printSolutions() {
        for (long solution : solutions) {
            System.out.print(solution + " ");
        }
        System.out.println();
    }

    private void printResult() throws IOException {
        for (int index : resultIndex) {
            bw.write(solutions.get(index) + " ");
        }
        bw.flush();
        bw.close();
    }
}