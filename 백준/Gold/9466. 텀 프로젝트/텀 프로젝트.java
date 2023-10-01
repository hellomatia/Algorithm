import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int count;
    static int[] hope;
    static boolean[] finish;
    static boolean[] visited;
    static Queue<Integer> queue;
    public void solution() throws IOException {

        int T = Integer.parseInt(bf.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(bf.readLine());

            hope = new int[n + 1];

            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= n; j++) {
                 int num = Integer.parseInt(st.nextToken());
                 hope[j] = num;
            }

            finish = new boolean[n + 1];
            visited = new boolean[n + 1];
            count = 0;

            for (int j = 1; j <= n; j++) {
                dfs(j);
            }

            bw.write((n - count) + "\n");
        }

        bw.flush();
        bw.close();
    }

    public void dfs(int nowNum) {
        
        if (visited[nowNum]) {
            return;
        }

        visited[nowNum] = true;
        int nextNum = hope[nowNum];

        if (!visited[nextNum]) {
            dfs(nextNum);
        } else {
            if (!finish[nextNum]) {
                count++;
                int num = nextNum;
                while (num != nowNum) {
                    count++;
                    num = hope[num];
                }
            }
        }

        finish[nowNum] = true;

    }

    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}