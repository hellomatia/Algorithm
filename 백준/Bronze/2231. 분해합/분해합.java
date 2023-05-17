import java.io.*;
import java.util.*;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int inputNum = Integer.parseInt(bf.readLine());
        int ans = d(inputNum);

        if(ans == inputNum+1) ans = 0;


        bw.write(String.valueOf(ans));


        bw.flush();
        bw.close();
    }

    public int d(int number){
        int num = 1;
        for(int i=0; i<number; i++){
            int sum = num;
            StringBuilder sb = new StringBuilder(String.valueOf(num));
            for(int j=0; j<sb.length(); j++){
                sum += (int)sb.charAt(j) - 48;
            }
            if(sum == number) break;
            num++;
         }
        return num;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}