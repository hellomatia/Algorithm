import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        int numOfCoins = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(st.nextToken());

        int numCoinsUsed = 0;
        int[] coinUnit = new int[numOfCoins];

        for(int i=0; i<numOfCoins; i++){
            coinUnit[i] = Integer.parseInt(bf.readLine());
        }

        for(int i=numOfCoins-1; i>-1; i--){
            int coinsUsed = money / coinUnit[i];
            money %= coinUnit[i];
            numCoinsUsed += coinsUsed;
        }

        bw.write(String.valueOf(numCoinsUsed));

        bw.flush();
        bw.close();

    }
}




