import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static int[][] matrixA, matrixB;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrixA = new int[N][M];
        matrixB = new int[N][M];

        for(int i=0; i<N; i++) {
            String str = bf.readLine();
            for(int j=0; j<M; j++) {
                matrixA[i][j] = str.charAt(j) - '0';
            }
        }

        for(int i=0; i<N; i++) {
            String str = bf.readLine();
            for(int j=0; j<M; j++) {
                matrixB[i][j] = str.charAt(j) - '0';
            }
        }

        int flipCount = 0;

        for(int i=0; i<N-2; i++) {
            for(int j=0; j<M-2; j++) {
                if(matrixA[i][j]!=matrixB[i][j]) {
                    flipMatrix(i, j);
                    flipCount++;
                }
            }
        }

        if(!isMatrixSame()) {
            bw.write(String.valueOf(-1));
        }
        else {
            bw.write(String.valueOf(flipCount));
        }

        bw.flush();
        bw.close();
    }

    public static void flipMatrix(int row, int col) {
        for(int i=row; i<row+3; i++){
            for(int j=col; j<col+3; j++){
                matrixA[i][j] = matrixA[i][j]^1;
            }
        }
    }

    public boolean isMatrixSame() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(matrixA[i][j]!=matrixB[i][j]) return false;
            }
        }
        return true;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}

