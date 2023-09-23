import java.io.*;
import java.util.*;
class Floor {
    int num;
    int count;
    public Floor(int num, int count) {
        this.num = num;
        this.count = count;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        Queue<Floor> queue = new LinkedList<>();

        boolean[] visited = new boolean[F + 1];

        visited[S] = true;
        queue.offer(new Floor(S, 0));

        while(!queue.isEmpty()) {
            Floor now = queue.poll();

            if (now.num == G) {
                bw.write(now.count + "\n");
                break;
            }

            int nextUp = now.num + U;
            int nextDown = now.num - D;

            if (nextUp <= F && !visited[nextUp]) {
                visited[nextUp] = true;
                queue.offer(new Floor(nextUp, now.count + 1));
            }
            if (0 < nextDown && !visited[nextDown]) {
                visited[nextDown] = true;
                queue.offer(new Floor(nextDown, now.count + 1));
            }
        }

        if (!visited[G]) {
            bw.write("use the stairs\n");
        }

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}