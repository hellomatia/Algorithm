import java.io.*;
import java.util.*;

public class Main {
    int[] dirR = {-1, 0, 1, 0};
    int[] dirC = {0, 1, 0, -1};
    int N, M; // 세로, 가로
    int r, c, d; // 로봇 청소기의 위치, 바라보는 방향
    int[][] map;
    int count;

    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        simulationCleaning(r, c);
        bw.write(count + "\n");

        bw.flush();
        bw.close();
    }

    public void simulationCleaning(int x, int y) {
        if(map[x][y]==0) {
            map[x][y] = -1;
            count++;
        }

        boolean allClean = true;

        for(int i=0; i<4; i++) {
            int x1 = x + dirR[i];
            int y1 = y + dirC[i];
            if(x1<0 || y1<0 || x1>=N || y1>=M) continue;
            if(map[x1][y1]==0) allClean = false;
        }

        if(allClean) {
            int x1, y1;
            switch (d) {
                case 0:
                    x1 = x + dirR[2];
                    y1 = y + dirC[2];
                    if(map[x1][y1]==1) return;
                    simulationCleaning(x1, y1);
                    return;


                case 1:
                    x1 = x + dirR[3];
                    y1 = y + dirC[3];
                    if(map[x1][y1]==1) return;
                    simulationCleaning(x1, y1);
                    return;


                case 2:
                    x1 = x + dirR[0];
                    y1 = y + dirC[0];
                    if(map[x1][y1]==1) return;
                    simulationCleaning(x1, y1);
                    return;

                case 3:
                    x1 = x + dirR[1];
                    y1 = y + dirC[1];
                    if(map[x1][y1]==1) return;
                    simulationCleaning(x1, y1);
            }
        } else {
            int x1, y1;
            while(true) {
                d = (d+3)%4;
                x1 = x + dirR[d];
                y1 = y + dirC[d];
                if(map[x1][y1]==0) {
                    simulationCleaning(x1, y1);
                    break;
                }
            }
        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
