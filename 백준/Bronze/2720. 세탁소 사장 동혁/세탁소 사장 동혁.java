import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public void solution() throws IOException{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(bf.readLine());
        for(int i=0; i<testCaseCount; i++){
            int changeAmount = Integer.parseInt(bf.readLine());
            int quarterCount = changeAmount/25;
            changeAmount %= 25;
            int dimeCount = changeAmount/10;
            changeAmount %= 10;
            int nickelCount = changeAmount/5;
            changeAmount %= 5;
            int pennyCount = changeAmount;

            bw.write(String.valueOf(quarterCount));
            bw.write(" ");
            bw.write(String.valueOf(dimeCount));
            bw.write(" ");
            bw.write(String.valueOf(nickelCount));
            bw.write(" ");
            bw.write(String.valueOf(pennyCount));
            bw.write("\n");

        }
        
        bw.flush();
        bw.close();

    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

