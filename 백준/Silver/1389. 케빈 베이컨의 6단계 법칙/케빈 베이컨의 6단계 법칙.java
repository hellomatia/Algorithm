import java.io.*;
import java.util.*;
class User {
    int num;
    int kevinBacon;
    User (int num, int kevinBacon) {
        this.num = num;
        this.kevinBacon = kevinBacon;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());

            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;

            dist[A][B] = 1;
            dist[B][A] = 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {

                    if (j == k || dist[j][i] == Integer.MAX_VALUE || dist[i][k] == Integer.MAX_VALUE) continue;

                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);

                }
            }
        }

        PriorityQueue<User> pq = new PriorityQueue<>((o1, o2) -> {

            if (o1.kevinBacon == o2.kevinBacon) {
                return o1.num - o2.num;
            }

            return o1.kevinBacon - o2.kevinBacon;
        });

        for(int i = 0; i < N; i ++) {
            User user = new User(i+1, 0);
            for (int j = 0; j < N; j ++) {

                if (i == j) continue;

                user.kevinBacon += dist[i][j];
            }
            pq.offer(user);
        }

        bw.write(pq.poll().num + "\n");
        
        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}