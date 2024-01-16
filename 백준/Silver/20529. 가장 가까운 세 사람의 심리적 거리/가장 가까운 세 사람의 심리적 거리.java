import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int T;
    private static int N;
    private static char[][] mbti;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int i = 0; i < T; i++) {
            init();
            printResult(calcResult());
        }

        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        mbti = new char[N][4];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            String value = st.nextToken();
            for (int j = 0; j < 4; j++) {
                mbti[i][j] = value.charAt(j);
            }
        }
    }

    private int calcResult() {
        int result = Integer.MAX_VALUE;
        if (N > 33) {
            return 0;
        }
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    result = Math.min(calcScore(i, j, k), result);
                }
            }
        }
        return result;
    }

    private int calcScore(int index1, int index2, int index3) {
        return calcScore(index1, index2) + calcScore(index1, index3) + calcScore(index2, index3);
    }

    private int calcScore(int index1, int index2) {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (mbti[index1][i] != mbti[index2][i]) {
                score++;
            }
        }
        return score;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}