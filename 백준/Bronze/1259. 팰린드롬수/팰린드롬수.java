import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        while (true) {
            String string = bf.readLine();
            if (string.equals("0")) {
                break;
            }
            printResult(isBoolean(string));
        }
        bw.flush();
        bw.close();
    }

    private boolean isBoolean(String string) {
        char[] chars = string.toCharArray();
        int start = 0;
        int end = chars.length - 1;

        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    private void printResult(boolean result) throws IOException {
        if (result) {
            bw.write("yes\n");
            return;
        }
        bw.write("no\n");
    }
}
