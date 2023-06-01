import java.io.*;
import java.util.*;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    int[] dirR = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    int[] dirC = {0, 1, 0, -1};
    int N; // 가로, 세로 크기
    int K; // 사과의 개수
    int L; // 뱀의 방향 변환 횟수
    int[][] map;
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Point> snake = new ArrayDeque<>();

        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];

        map[0][0] = 2; // 2일경우 snake;
        snake.addLast(new Point(0, 0));

        K = Integer.parseInt(bf.readLine());

        for(int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x-1][y-1] = 1; // 1일경우 사과
        }

        L = Integer.parseInt(bf.readLine());

        int dir = 1;

        boolean isEnd = false;
        int time = 0;
        int lastTime = 0;
        int endTime = 0;

        int r = 0;
        int c = 0;

        for(int i=0; i<L; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int X = Integer.parseInt(st.nextToken()); // X초 후 실행
            String C = st.nextToken(); // 방향

            for(int j=lastTime; j<X; j++) {
                time++;
                r += dirR[dir];
                c += dirC[dir];
                if(r<0 || c<0 || N<=r || N<=c || map[r][c]==2) {
                    if(!isEnd) {
                        isEnd = true;
                        endTime = time;
                    }
                    continue;
                }

                if(map[r][c] == 0) {
                    map[r][c] = 2;
                    snake.addLast(new Point(r, c));

                    int sR = snake.peekFirst().x;
                    int sC = snake.peekFirst().y;

                    map[sR][sC] = 0;

                    snake.pollFirst();


                } else if(map[r][c]==1){
                    map[r][c] = 2;
                    snake.addLast(new Point(r, c));
                }


            }

            if(C.equals("L")) {
                dir = (dir+3) % 4;

            } else {
                dir = (dir+1) % 4;
            }

            lastTime = time;

        }

        while(!isEnd) {
            time++;
            r += dirR[dir];
            c += dirC[dir];

            if(r<0 || c<0 || N<=r || N<=c || map[r][c]==2) {
                isEnd = true;
                endTime = time;
                continue;
            }

            if(map[r][c] == 0) {
                map[r][c] = 2;
                snake.addLast(new Point(r, c));

                int sR = snake.peekFirst().x;
                int sC = snake.peekFirst().y;
                map[sR][sC] = 0;

                snake.pollFirst();
            } else {
                map[r][c] = 2;
                snake.addLast(new Point(r, c));
            }
        }

        bw.write(endTime + "\n");


        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
