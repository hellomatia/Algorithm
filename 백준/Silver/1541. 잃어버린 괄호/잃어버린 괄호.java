import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer subtraction = new StringTokenizer(bf.readLine(), "-");
        int sum = Integer.MAX_VALUE;

        while(subtraction.hasMoreTokens()){
            int temp = 0;
            StringTokenizer addition = new StringTokenizer(subtraction.nextToken(), "+");

            while(addition.hasMoreTokens()){
                temp += Integer.parseInt(addition.nextToken());
            }

            if(sum == Integer.MAX_VALUE){
                sum = temp;
            }
            else {
                sum -= temp;
            }
        }


        bw.write(String.valueOf(sum));

        bw.flush();
        bw.close();

    }
}

