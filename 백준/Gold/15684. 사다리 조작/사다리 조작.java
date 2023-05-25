import java.io.*;
import java.util.StringTokenizer;

public class Main {
    int N; //세로선 개수
    int M; //가로선 개수
    int H; //세로선 마다 가로선을 놓을 수 있는 위치의 개수
    boolean[][] hasLine;
    int minPlusLineCount = Integer.MAX_VALUE;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        if(M==0) {
            bw.write("0");
            bw.flush();
            bw.close();
            return;
        }

        hasLine = new boolean[H][N-1];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(bf.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;

            hasLine[row][col] = true;
        }

        randomPlaceLine(0, 0);

        if(minPlusLineCount==Integer.MAX_VALUE) {
            minPlusLineCount = -1;
        }

        bw.write(minPlusLineCount+"\n");

        bw.flush();
        bw.close();
    }

    public void randomPlaceLine(int start, int count) {
        if(count>3) return;

        if(winGame()) {
            minPlusLineCount = Math.min(count, minPlusLineCount);
            return;
        }

        for(int i=start; i<H; i++) {
            for(int j=0; j<N-1; j++) {
                if(!hasLine[i][j]&&canPlaceLine(i,j)) {
                    hasLine[i][j] = true;
                    randomPlaceLine(i,count+1);
                    hasLine[i][j] = false;
                }
            }
        }
    }

    public boolean canPlaceLine(int row, int col) {
        if(col-1>=0 && hasLine[row][col-1]) return false;
        if(col+1<N-1 && hasLine[row][col+1]) return false;

        return true;
    }

    public boolean winGame() {

        for(int i=0; i<N; i++) {
            int col = i;
            for(int j=0; j<H; j++) {
                if(col-1>=0 && hasLine[j][col-1]) {
                    col--;
                }
                else if(col<N-1 && hasLine[j][col]){
                    col++;
                }
            }

            if(i!=col) return false;
        }
        return true;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}