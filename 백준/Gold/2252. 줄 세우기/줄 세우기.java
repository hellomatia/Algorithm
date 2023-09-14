import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] studentsOrder = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            studentsOrder[i] = new ArrayList<>();
        }
        int[] inDegree = new int[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());

            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;

            inDegree[B]++;
            studentsOrder[A].add(B);
        }

        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();

        for (int j = 0; j < N; j++) {
            if (inDegree[j] == 0) {
                visited[j] = true;
                queue.offer(j);
            }
        }

        while (!queue.isEmpty()) {

            int now = queue.poll();

            bw.write((now+1) + " ");

            for (int j = 0; j < studentsOrder[now].size(); j++) {
                int next = studentsOrder[now].get(j);

                if (visited[next]) continue;

                inDegree[next]--;
                if (inDegree[next] == 0) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }

        }

        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}