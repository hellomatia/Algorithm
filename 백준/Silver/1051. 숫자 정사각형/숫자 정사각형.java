import java.io.*;
import java.util.StringTokenizer;

public class Main {

    int N; // 세로
    int M; // 가로
    int[][] map;

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
                map[i][j] = str.charAt(j);
            }
        }

        int maxSquareSize = Math.min(M,N);
        int maxSize = 0;

        for(int i=maxSquareSize; i>=1; i--) {
            if(checkForSquare(i)) {
                maxSize = i+1;
                break;
            }
        }

        if(maxSize==0) maxSize = 1;

        bw.write((int)Math.pow(maxSize, 2)+"\n");

        bw.flush();
        bw.close();
    }

    public boolean checkForSquare(int size) {
        for(int i=0; i<N-size; i++) {
            for(int j=0; j<M-size; j++) {
                if(map[i][j]==map[i+size][j]&&map[i+size][j]==map[i+size][j+size]&&map[i+size][j+size]==map[i][j+size]) return true;
            }
        }
        return false;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}