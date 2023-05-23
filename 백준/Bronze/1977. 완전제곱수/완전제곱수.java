import java.io.*;
import java.util.*;

public class Main {
    int N, M;
    int squareNumSum = 0;
    int minSquareNum = 0;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine()); //최소값
        M = Integer.parseInt(bf.readLine()); //최대값

        int num = (int) Math.sqrt(N);
        int squareNum = 0;

        while(squareNum<=M) {
            squareNum = (int) Math.pow(num++, 2);

            if(squareNum>=N && squareNum <= M) {
                squareNumSum += squareNum;
                if(minSquareNum==0) {
                    minSquareNum = squareNum;
                }
            }
        }

        if(minSquareNum==0) {
            bw.write("-1");
        } else {
            bw.write(squareNumSum + "\n");
            bw.write(minSquareNum + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}