import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static boolean canTravel = true;
    static int[] parent;
    public void solution() throws IOException {

        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1){
                    union(i, j);
                }
            }
        }


        StringTokenizer st = new StringTokenizer(bf.readLine());
        int parentNode = find(Integer.parseInt(st.nextToken()));
        for (int i = 0; i < M - 1; i++) {
            int city = Integer.parseInt(st.nextToken());
            if (parentNode != find(city)) {
                canTravel = false;
                break;
            }
        }

        if (canTravel) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }


        bw.flush();
        bw.close();
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        if (x > y) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }

    }

    public int find(int x) {
        if (parent[x] == x) return x;
        return find(parent[x]);
    }


    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}