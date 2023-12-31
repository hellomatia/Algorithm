import java.io.*;
import java.util.*;
class Docs {
    int num;
    int priority;

    Docs(int num, int priority) {
        this.num = num;
        this.priority = priority;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static int M;
    private static PriorityQueue<Integer> pq;
    private static Queue<Docs> queue;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        int tc = Integer.parseInt(bf.readLine());
        for (int i = 0; i < tc; i++) {
            init();
            printResult(calcResult());
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        queue = new LinkedList<>();
        pq = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            int priority = Integer.parseInt(st.nextToken());
            queue.offer(new Docs(
                    i,
                    priority
            ));
            pq.offer(priority);
        }
    }

    private int calcResult() {
        int count = 1;
        while (!queue.isEmpty()) {
            int priority = pq.poll();
            while (queue.peek().priority != priority) {
                queue.offer(queue.poll());
            }
            Docs now = queue.poll();
            if (now.num == M) {
                return count;
            }
            count++;
        }
        return count;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
