import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        initN();
        initM();
        bw.flush();
        bw.close();
    }

    private void initN() throws IOException {
        N = Integer.parseInt(bf.readLine());
        set = new HashSet<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
    }

    private void initM() throws IOException {
        M = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            printResult(set.contains(num));
        }
    }

    private void printResult(boolean isContain) throws IOException {
        if (isContain) {
            bw.write("1");
        } else {
            bw.write("0");
        }
        bw.write("\n");
    }
}
