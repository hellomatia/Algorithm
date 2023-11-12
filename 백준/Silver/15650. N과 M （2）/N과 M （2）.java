import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static List<StringBuilder> sbs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        findSequence(1, new StringBuilder());
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initNAndM();
    }

    private void initNAndM() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    private void findSequence(int start, StringBuilder sb) {
        if (sb.length() == M * 2) {
            sbs.add(sb);
        }
        for (int i = start; i <= N; i++) {
            findSequence(i + 1, new StringBuilder(sb).append(i).append(" "));
        }
    }

    private void printResult() throws IOException {
        for (StringBuilder sb : sbs) {
            bw.write(sb.toString() + "\n");
        }
    }
}
