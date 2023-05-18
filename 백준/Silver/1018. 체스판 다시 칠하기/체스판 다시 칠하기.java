import java.io.*;
import java.util.*;

public class Main {

    int row;
    int col;
    String[] board;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        board = new String[row];

        for(int i=0; i<row; i++) {
            board[i] = bf.readLine();
        }

        int ans = Integer.MAX_VALUE;

        for(int i=0; i<row-7; i++){
            for(int j=0; j<col-7; j++){
                int count = makeBoardCount(i,j);

                if(ans>count) {
                    ans = count;
                }
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
    }

    public int makeBoardCount(int x, int y) {

        int count = 0;
        for(int i=x; i<x+8; i++) {
            for(int j=y; j<y+8; j++) {
                if((i+j)%2==0) {
                    if(board[i].charAt(j)=='W') continue;
                    else count++;
                }
                else {
                    if(board[i].charAt(j)=='B') continue;
                    else count++;
                }
            }
        }

        int otherCount = 64-count;

        return Math.min(count,otherCount);
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}