import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int T;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        initT();
        for (int i = 0; i < T; i++) {
            printResult(isVPS(readLine()));
        }
        bw.flush();
        bw.close();
    }

    private void initT() throws IOException {
        T = Integer.parseInt(bf.readLine());
    }

    private String readLine() throws IOException {
        return bf.readLine();
    }

    private boolean isVPS(String string) {
        char[] chars = string.toCharArray();
        int count = 0;
        for (char ch : chars) {
            if (ch == '(') {
                count++;
            }
            if (ch == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }

        return count == 0;
    }

    private void printResult(boolean isVPS) throws IOException {
        if (isVPS) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }
        bw.write("\n");
    }
}
