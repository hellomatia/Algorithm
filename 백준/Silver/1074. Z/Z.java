import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, n, r, c;
    boolean find = false;
    int result = 0;
    private static int count = -1;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        exploreMap(0, 0, n);
        printResult();
    }

    private void init() throws IOException {
        initNrc();
        initn();
    }

    private void initNrc() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
    }

    private void initn() {
        n = (int) Math.pow(2, N);
    }

    private void exploreMap(int x, int y, int n) {
        if (find) {
            return;
        }

        if (n == 1) {
             count++;
             if (x == r && y == c) {
                 result = count;
                 find = true;
             }
            return;
        }

        int newN = n / 2;

        if (r < x + newN && c < y + newN) {
            exploreMap(x, y, newN);
        } else {
            count += newN * newN;
        }

        if (r < x + newN && c < y + n) {
            exploreMap(x, y + newN, newN);
        } else {
            count += newN * newN;
        }

        if (r < x + n && c < y + newN) {
            exploreMap(x + newN, y, newN);
        } else {
            count += newN * newN;
        }

        if (r < x + n && c < y + n) {
            exploreMap(x + newN, y + newN, newN);
        } else {
            count += newN * newN;
        }
    }

    private void printResult() throws IOException {
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
