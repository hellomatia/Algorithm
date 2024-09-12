import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int[] dirX = new int[] {1, 1, 1}; // 아래 방향으로 이동
    private static final int[] dirY = new int[] {-1, 0, 1}; // 왼쪽, 가운데, 오른쪽

    private int N; // 세로
    private int M; // 가로
    private int[][] map;
    private int ans;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
       init();
       printAns(calculationAns());
       close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N + 2][M];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }   
        }
    }

    private String calculationAns() {
        ans = Integer.MAX_VALUE;

        for (int i = 0; i < M; i++) {
            dfs(1, i, map[1][i], 4);
        }

        return ans + "";
    }

    private void dfs(int x, int y, int count, int prevDir) {
        if (isMoon(x)) {
            ans = Math.min(ans, count);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (i == prevDir) {
                continue;
            }
            
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];

            if (isIn(nextX, nextY)) {
                dfs(nextX, nextY, count + map[nextX][nextY], i);
            }
        }
    }

    private boolean isMoon(int x) {
        return x == N + 1;
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x <= N + 1 && 0 <= y && y < M;
    }

    private void printAns(final String ans) throws IOException {
        bw.write(ans);
        bw.flush();
    }

    private void close() throws IOException {
        bw.close();
        bf.close();
    }
}