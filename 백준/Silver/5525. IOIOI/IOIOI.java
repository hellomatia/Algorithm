import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static char[] S;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        initN();
        initM();
        initS();
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

    private int calcResult() {
        int count = 0;
        int result = 0;
        for (int i = 1; i < M - 1; i++) {
            if (S[i - 1] == 'I' && S[i] == 'O' && S[i + 1] == 'I') {
                count++;
                if (count == N) {
                    count--;
                    result++;
                }
                i++;
            } else {
                count = 0;
            }
        }
        return result;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
