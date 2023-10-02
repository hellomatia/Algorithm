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
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] inDegrees;
    static boolean[] solved;
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] questions = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            questions[i] = new ArrayList<>();
        }

        inDegrees = new int[N + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(bf.readLine());
            int q1 = Integer.parseInt(st.nextToken());
            int q2 = Integer.parseInt(st.nextToken());

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


    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}