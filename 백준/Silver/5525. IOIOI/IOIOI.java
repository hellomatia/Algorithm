import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static char[] S;
    private static char[] P;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        initN();
        initM();
        initS();
        initP();
        printResult(calcResult());
        bw.flush();
        bw.close();
    }

    private void initN() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private void initM() throws IOException {
        M = Integer.parseInt(bf.readLine());
    }

    private void initS() throws IOException {
        String string = bf.readLine();
        S = string.toCharArray();
    }

    private void initP() {
        P = new char[3 + (N - 1) * 2];
        P[0] = 'I';
        P[1] = 'O';
        P[2] = 'I';
        int index = 3;
        while (index < P.length) {
            P[index++] = 'O';
            P[index++] = 'I';
        }
    }

    private int calcResult() {
        int count = 0;
        for (int i = 0; i < M; i++) {
            if (isP(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isP(int start) {
        if ((start + P.length) > M || S[start] == 'O') {
            return false;
        }

        int index = 0;
        while (index < P.length) {
            if (P[index] != S[start + index]) {
                return false;
            }
            index++;
        }
        return true;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
