import java.io.*;
import java.util.*;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(bf.readLine());

        for(int i=0; i<testCase; i++) {
            int day = Integer.parseInt(bf.readLine());
            long[] stockPrice = new long[day];
            long maxPrice = 0;

            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

            for(int j=0; j<day; j++) {
                stockPrice[j] = Integer.parseInt(st.nextToken());
            }
            long ans = 0;
            for(int j=day-1; j>=0; j--) {
                if(stockPrice[j]>maxPrice) {
                    maxPrice = stockPrice[j];
                } else {
                    ans += (maxPrice - stockPrice[j]);
                }
            }
            
            bw.write(String.valueOf(ans));
            bw.write("\n");
        }

        bw.flush();
        bw.close();


    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}