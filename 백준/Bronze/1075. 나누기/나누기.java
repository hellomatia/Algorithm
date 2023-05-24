import java.io.*;
import java.util.*;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bf.readLine());
        int F = Integer.parseInt(bf.readLine());

        N /= 100;
        N *= 100;

        for(int i=0; i<100; i++) {
            N += i;
            if(N%F == 0) {
                if(i<10) {
                    bw.write("0"+i+"\n");
                    break;
                }
                bw.write(i + "\n");
                break;
            }
            N -= i;
        }


        bw.flush();
        bw.close();

    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}