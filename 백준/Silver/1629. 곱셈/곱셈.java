import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int A, B, C;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calculateABC(A, B, C));
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initABC();
    }

    private void initABC() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    }

    static long calculateABC(int a, int b, int c) {
        if (b == 0)
            return 1;

        long n = calculateABC(a, b / 2, c);
        if (b % 2 == 0)
            return n * n % c;
        else
            return (n * n % c) * a % c;
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}
