import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[] As;
    private int[] opCount;
    private int min;
    private int max;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
        close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());

        // A 초기화
        As = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            As[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 개수 초기화
        opCount = new int[4];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 4; i++) {
            opCount[i] = Integer.parseInt(st.nextToken());
        }
    }

    private String calcAns() {
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        int[] opNow = new int[4];
        dfs(1, As[0], opNow);
        return max + "\n" + min;
    }

    private void dfs(int count, int now, int[] opNow) {
        if (count == N) {
            min = Math.min(now, min);
            max = Math.max(now, max);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (opCount[i] < opNow[i] + 1) { continue; }
            opNow[i] = opNow[i] + 1;
            dfs(count + 1, op(now, As[count], i), opNow);
            opNow[i] = opNow[i] - 1;
        }
    }

    private int op(int num1, int num2, int op) {
        if (op == 0) {
            return num1 + num2;
        } else if (op == 1) {
            return num1 - num2;
        } else if (op == 2) {
            return num1 * num2;
        }
        return num1 / num2;
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
    }

    private void close() throws IOException {
        bw.close();
        bf.close();
    }
}