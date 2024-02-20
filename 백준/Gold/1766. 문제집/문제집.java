import java.io.*;
import java.util.*;
class Question {
    int num;
    int inDegree;
    public Question(int num, int inDegree) {
        this.num = num;
        this.inDegree = inDegree;
    }
}

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

        PriorityQueue<Question> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.inDegree == o2.inDegree) {
                return o1.num - o2.num;
            }
            return o1.inDegree - o2.inDegree;
        });

        for (int i = 1; i <= N; i++) {
            pq.offer(new Question(i, inDegrees[i]));
        }

        solved = new boolean[N + 1];

        while (!pq.isEmpty()) {
            Question now = pq.poll();

            if (solved[now.num]) {
                continue;
            }
            bw.write(now.num + " ");

            solved[now.num] = true;

            for (int i = 0; i < questions[now.num].size(); i++) {
                int nextNum = questions[now.num].get(i);

                inDegrees[nextNum]--;
                pq.offer(new Question(nextNum, inDegrees[nextNum]));
            }
        }

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