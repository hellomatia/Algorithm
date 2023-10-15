import java.io.*;
import java.util.*;

class SolutionIDX {
    int forward;
    int backward;
    public SolutionIDX(int forward, int backward) {
        this.forward = forward;
        this.backward = backward;
    }
}

public class Main {

    static final int INF = Integer.MAX_VALUE;

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int[] solutions;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        init();
        printResult(findMinSolutionValueIDX());

        bw.flush();
        bw.close();
    }

    public void init() throws IOException {
        initialN();
        initialSolutions();
    }

    public void initialN() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    public void initialSolutions() throws IOException {
        solutions = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }
    }

    public SolutionIDX findMinSolutionValueIDX() {
        int minValue = INF;
        SolutionIDX idx = new SolutionIDX(0, N - 1);
        SolutionIDX result = idx;

        while(idx.forward != idx.backward) {
            int value = solutions[idx.backward] + solutions[idx.forward];

            if (Math.abs(value) < minValue) {
                minValue = Math.abs(value);
                result = new SolutionIDX(idx.forward, idx.backward);
            }

            if (value < 0) {
                idx.forward++;
            } else {
                idx.backward--;
            }
        }

        return result;
    }

    public void printResult(SolutionIDX idx) throws IOException {
        bw.write(solutions[idx.forward] + " " + solutions[idx.backward]);
    }

}
