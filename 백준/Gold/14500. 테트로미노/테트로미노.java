import java.io.*;
import java.util.*;

public class Main {

    int N, M;
    int[][] paper;
    int maxSum = Integer.MIN_VALUE;
    int[] dirR = {1, -1, 0};
    int[] dirC = {0, 0, 1};
    boolean[][] visit;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로 크기
        M = Integer.parseInt(st.nextToken()); // 가로 크기

        paper = new int[N][M];
        visit = new boolean[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++){
                visit[i][j]=true;
                polynomino1(i, j, 1, paper[i][j]);
                polynomino2(i, j, paper[i][j]);
                polynomino3(i, j, paper[i][j]);
                visit[i][j]=false;
            }
        }

        bw.write(maxSum + "\n");


        bw.flush();
        bw.close();
    }

    public void polynomino1(int row, int col, int cnt, int sum){

        if(cnt==4) {
            maxSum = Math.max(sum, maxSum);
            return;
        }

        for(int i=0; i<3; i++){
            int x = row + dirR[i];
            int y = col + dirC[i];
            if(0<=x && x<N && 0<=y && y<M && !visit[x][y]) {
                sum += paper[x][y];
                visit[x][y]=true;
                polynomino1(x, y, cnt+1, sum);
                visit[x][y]=false;
                sum -= paper[x][y];
            }
        }
    }

    public void polynomino2(int row, int col, int sum){

        int x = row;
        int y = col;

        for(int i=0; i<2; i++){
            x++;
            if(x>=N) return;
            sum += paper[x][y];
        }

        if(y+1<M){
            sum += paper[x-1][y+1];
            maxSum = Math.max(sum, maxSum);
            sum -= paper[x-1][y+1];
        }

        if(0<=y-1){
            sum += paper[x-1][y-1];
            maxSum = Math.max(sum, maxSum);
        }

    }
    public void polynomino3(int row, int col, int sum){

        int x = row;
        int y = col;

        for(int i=0; i<2; i++){
            y++;
            if(y>=M) return;
            sum += paper[x][y];
        }

        if(x+1<N){
            sum += paper[x+1][y-1];
            maxSum = Math.max(sum, maxSum);
            sum -= paper[x+1][y-1];
        }

        if(0<=x-1){
            sum += paper[x-1][y-1];
            maxSum = Math.max(sum, maxSum);
        }

    }



    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}