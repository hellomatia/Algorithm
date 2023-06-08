import java.io.*;
import java.util.*;

public class Main {
    int M, N; // 세로 가로크기, 최소, 최대 인원
    int[][] map;
    int[][] dp;
    int[] dirX = {0, 0, -1, 1}; // 동 서 북 남
    int[] dirY = {1, -1, 0, 0};
    //boolean[][] isVisited;
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];
        //isVisited = new boolean[M][N];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        //isVisited[0][0] = true;

        //moveToEnd(0, 0);

        bw.write(DFS(0, 0) + "\n");

        bw.flush();
        bw.close();
    }

    /*
    public void moveToEnd(int x, int y) {
        if(x==M-1&&y==N-1) {
            wayCount++;
            return;
        }

        for(int i=0; i<4; i++) {
            int nowX = x + dirX[i];
            int nowY = y + dirY[i];

            if(nowX<0 || nowY<0 || M<=nowX || N<=nowY || isVisited[nowX][nowY]) continue;

            if(map[x][y]>map[nowX][nowY]) {
                isVisited[nowX][nowY] = true;
                moveToEnd(nowX, nowY);
                isVisited[nowX][nowY] = false;
            }
        }
    }

     */

    public int DFS(int x, int y) {
        if(x==M-1&&y==N-1) {
            return 1;
        }

        if(dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;
        for(int i=0; i<4; i++) {
            int dx = x + dirX[i];
            int dy = y + dirY[i];

            if(dx<0 || dy<0 || M<=dx || N<=dy) {
                continue;
            }
            if(map[x][y]>map[dx][dy]) {
                    dp[x][y] += DFS(dx,dy);
            }
        }

        return dp[x][y];
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}