import java.io.*;
import java.util.*;

public class Main {
    int N, M, dX, dY, K; // 세로, 가로, 주사위 좌표, 명령어 개수
    int[][] map;
    int[] dirX = {0, 0, -1, 1}; // 동 서 북 남
    int[] dirY = {1, -1, 0, 0};
    int[] diceV = {0, 0, 0, 0};
    int[] diceH = {0, 0, 0};
    int curV, curH;
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dX = Integer.parseInt(st.nextToken());
        dY = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        st = new StringTokenizer(bf.readLine());

        for(int i=0; i<K; i++) {
            int dir = Integer.parseInt(st.nextToken())-1;
            int topNum = diceMove(dir);
            if(topNum==-1) continue;
            bw.write(topNum+"\n");
            /*
            for(int k=0; k<N; k++){
                for(int j=0; j<M; j++){
                    System.out.print(map[k][j]+" ");
                }
                System.out.println();
            }
            System.out.println();

             */
        }

        bw.flush();
        bw.close();
    }

    public int diceMove(int dir){

        int curVEnd, temp, x, y;

        switch(dir) {
            case 0: //동

                x = dX + dirX[dir];
                y = dY + dirY[dir];

                if(x<0 || y<0 || N<=x || M<=y) return -1;

                curVEnd = (curV+3)%4;
                curH = (curH+2)%3;

                temp = diceH[curH];
                diceH[curH] = diceV[curVEnd];
                diceV[curVEnd] = temp;

                diceV[(curV+1)%4]=diceH[(curH+1)%3];

                dX = x;
                dY = y;
                
                if(map[x][y]!=0) {
                    diceV[curVEnd] = map[x][y];
                    map[x][y] = 0;
                } else if (map[x][y]==0) {
                    map[x][y] = diceV[curVEnd];
                }

                return diceH[(curH+1)%3];

            case 1: //서

                x = dX + dirX[dir];
                y = dY + dirY[dir];

                if(x<0 || y<0 || N<=x || M<=y) return -1;

                curVEnd = (curV+3)%4;
                curH = (curH+1)%3;

                temp = diceH[(curH+2)%3];
                diceH[(curH+2)%3] = diceV[curVEnd];
                diceV[curVEnd] = temp;

                diceV[(curV+1)%4]=diceH[(curH+1)%3];


                dX = x;
                dY = y;

                if(map[x][y]!=0) {
                    diceV[curVEnd] = map[x][y];
                    map[x][y] = 0;
                } else if (map[x][y]==0) {
                    map[x][y] = diceV[curVEnd];
                }

                return diceH[(curH+1)%3];

            case 2: //북

                x = dX + dirX[dir];
                y = dY + dirY[dir];

                if(x<0 || y<0 || N<=x || M<=y) return -1;

                curV = (curV+1)%4;
                curVEnd = (curV+3)%4;

                diceH[(curH+1)%3]=diceV[(curV+1)%4];

                dX = x;
                dY = y;

                if(map[x][y]!=0) {
                    diceV[curVEnd] = map[x][y];
                    map[x][y] = 0;
                } else if (map[x][y]==0) {
                    map[x][y] = diceV[curVEnd];
                }

                return diceV[(curV+1)%4];

            default: //남

                x = dX + dirX[dir];
                y = dY + dirY[dir];

                if(x<0 || y<0 || N<=x || M<=y) return -1;

                curV = (curV+3)%4;
                curVEnd = (curV+3)%4;

                diceH[(curH+1)%3]=diceV[(curV+1)%4];

                dX = x;
                dY = y;

                if(map[x][y]!=0) {
                    diceV[curVEnd] = map[x][y];
                    map[x][y] = 0;
                } else if (map[x][y]==0) {
                    map[x][y] = diceV[curVEnd];
                }

                return diceV[(curV+1)%4];

        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}