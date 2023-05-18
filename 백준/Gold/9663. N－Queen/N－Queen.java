import java.io.*;
import java.util.*;

public class Main {

    int N;
    int[] queen;
    int count;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine());
        queen = new int[N];

        nQueen(0);

        bw.write(String.valueOf(count));


        bw.flush();
        bw.close();
    }

    public void nQueen(int depth) {
        if(depth==N){
            count++;
            return;
        }
        for(int i=0; i<N; i++) {
            queen[depth] = i;

            if(possible(depth)) {
                nQueen(depth+1);
            }
        }
    }

    public boolean possible(int col) {
        for(int i=0; i<col; i++) {
            if(queen[col]==queen[i]) return false;
            else if (Math.abs(col-i)==Math.abs(queen[col]-queen[i])) return  false;
        }
        return true;
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}