import java.io.*;
import java.util.*;

class Pair {
    int height;
    int count;

    Pair(int height, int count) {
        this.height = height;
        this.count = count;
    }
}

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;

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
        N = Integer.parseInt(bf.readLine());
    }

    private long calcResult() throws IOException {
        long result = 0;
        Stack<Pair> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(bf.readLine());
            Pair pair = new Pair(height, 1);

            while (!stack.empty() && stack.peek().height <= height) {
                Pair pop = stack.pop();
                result += pop.count;
                if (pop.height == height) {
                    pair.count += pop.count;
                }
            }
            
            if (!stack.empty()) {
                result++;
            }

            stack.push(pair);
        }

        return result;
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}
