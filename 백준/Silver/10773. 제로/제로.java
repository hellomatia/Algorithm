import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int K;
    private static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        K = Integer.parseInt(bf.readLine());
        stack = new Stack<>();
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(bf.readLine());
            if (num == 0) {
                stack.pop();
                continue;
            }
            stack.push(num);
        }
    }

    private int calcResult() {
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
