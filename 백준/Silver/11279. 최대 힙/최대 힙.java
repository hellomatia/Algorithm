import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private void calcResult() throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            int command = Integer.parseInt(bf.readLine());
            if (command == 0) {
                if (pq.isEmpty()) {
                    printResult(0);
                } else {
                    printResult(pq.poll());
                }
            } else {
                pq.offer(command);
            }
        }
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
