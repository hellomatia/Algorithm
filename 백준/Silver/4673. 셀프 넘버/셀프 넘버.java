import java.io.*;
import java.util.*;

public class Main {

    public void solution() throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[] isNotSelfNum = new boolean[10001];

        for(int i=1; i<10000; i++){
            int temp = d(i);
            if(temp>10000) continue;
            isNotSelfNum[temp] = true;
        }

        for(int i=1; i<10000; i++){
            if(!isNotSelfNum[i]) {
                bw.write(String.valueOf(i));
                bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
    }

    public int d(int number) {
        int sum = number;
        while(!(number==0)) {
            sum += number%10;
            number /= 10;
        }

        return sum;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}