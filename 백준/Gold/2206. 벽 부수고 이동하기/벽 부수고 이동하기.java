import java.io.*;
import java.util.*;

class Point {
    int x;
    int y;
    int d;
    int canBroke;
    Point(int x, int y, int d, int canBroke) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.canBroke = canBroke;
    }
}

public class Main {
    int N, M; // 세로 가로크기, 최소, 최대 인원
    int[][] map;
    int[] dirX = {0, 0, -1, 1}; // 동 서 북 남
    int[] dirY = {1, -1, 0, 0};
    boolean[][] isVisited;
    boolean brokenWall;
    int minDistance = Integer.MAX_VALUE;

    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            String str = bf.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bw.write(moveToNM() + "\n");

        bw.flush();
        bw.close();
    }

    /*
    public void moveToNM(int x, int y, int count) {

        if(x==N-1 && y==M-1) {
            minDistance = Math.min(minDistance, count);
            return;
        }

        for(int i=0; i<4; i++) {
            int nowX = x + dirX[i];
            int nowY = y + dirY[i];
            if(nowX<0 || nowY<0 || N<=nowX || M<=nowY || isVisited[nowX][nowY]) continue;

            if(map[nowX][nowY]==0) {
                isVisited[nowX][nowY] = true;
                moveToNM(nowX, nowY, count+1);
                isVisited[nowX][nowY] = false;
            } else if(!brokenWall && map[nowX][nowY]==1) {
                brokenWall = true;
                isVisited[nowX][nowY] = true;
                moveToNM(nowX, nowY, count+1);
                brokenWall = false;
                isVisited[nowX][nowY] = false;
            }
        }

    }
     */

    public int moveToNM() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 1, 1));
        boolean[][][] isVisited = new boolean[N][M][2];

        while(!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.x == N - 1 && now.y == M - 1) {
                return now.d;
            }

            for (int i = 0; i < 4; i++) {
                int x = now.x + dirX[i];
                int y = now.y + dirY[i];
                if (x < 0 || y < 0 || N <= x || M <= y || isVisited[x][y][now.canBroke]) continue;

                if (map[x][y] == 0) {
                    isVisited[x][y][now.canBroke] = true;
                    queue.add(new Point(x, y, now.d + 1, now.canBroke));
                } else if (now.canBroke > 0 && map[x][y] == 1 && !isVisited[x][y][now.canBroke-1]) {
                    isVisited[x][y][now.canBroke-1] = true;
                    queue.add(new Point(x, y, now.d + 1, now.canBroke - 1));
                }
            }
        }
        return -1;

    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}