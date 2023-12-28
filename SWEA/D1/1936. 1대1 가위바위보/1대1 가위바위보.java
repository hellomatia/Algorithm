import java.io.*;
import java.util.*;

public class Solution {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int ROCK = 1;
    private static final int PAPER = 2;
    private static final int SCISSOR = 3;

    private static int A;
    private static int B;

    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(isAWin());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
    }

    private boolean isAWin() {
        if (A == ROCK) {
            if (B == PAPER) {
                return false;
            } else if (B == SCISSOR) {
                return true;
            }
        } else if (A == PAPER) {
            if (B == ROCK) {
                return true;
            } else if (B == SCISSOR) {
                return false;
            }
        } else if (A == SCISSOR) {
            if (B == PAPER) {
                return true;
            }
        }
        return false;
    }

    private void printResult(boolean isAWin) throws IOException {
        if (isAWin) {
            bw.write("A");
            return;
        }
        bw.write("B");
    }
}
