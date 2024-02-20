import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] inDegrees;
    static boolean[] solved;

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {

        int N = read();
        int M = read();

        ArrayList<Integer>[] questions = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            questions[i] = new ArrayList<>();
        }

        inDegrees = new int[N + 1];

        for (int i = 1; i <= M; i++) {
            int q1 = read();
            int q2 = read();

            inDegrees[q2]++;
            questions[q1].add(q2);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (inDegrees[i] != 0) continue;
            pq.offer(i);
        }

        solved = new boolean[N + 1];

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now).append(" ");
            for (int i = 0; i < questions[now].size(); i++) {
                int nextNum = questions[now].get(i);
                if (--inDegrees[nextNum] == 0) {
                    pq.offer(nextNum);
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}