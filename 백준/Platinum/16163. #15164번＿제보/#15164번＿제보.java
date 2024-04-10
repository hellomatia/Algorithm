import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private String str;
    private long result;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcResult();
        printResult();
    }

    private void init() throws IOException {
        str = bf.readLine();
    }

    private void calcResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append('#').append(str.charAt(i));
        }
        sb.append('#');
        manachers(sb.toString().toCharArray(), sb.length());
    }

    private void manachers(char[] S, int N) {
        int[] A = new int[N];
        int r = -1;
        int p = -1;
        for (int i = 0; i < N; i++) {
            if (i <= r) {
                A[i] = Math.min(A[2 * p - i], r - i);
            }

            while (0 <= i - A[i] - 1 && i + A[i] + 1 < N
                    && S[i - A[i] - 1] == S[i + A[i] + 1]) {
                if (i + ++A[i] > r) {
                    r = i + A[i];
                    p = i;
                }
            }

            result += (A[i] + 1) / 2;
        }
    }

    private void printResult() {
        System.out.println(result);
    }
}