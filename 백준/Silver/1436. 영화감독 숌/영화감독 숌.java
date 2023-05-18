import java.io.*;
import java.util.*;

public class Main {

    int row;
    int col;
    String[] board;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int movieCount = Integer.parseInt(bf.readLine());

        int count = 0;
        int num = 0;

        while(movieCount!=count) {
            num++;
            String str = new String(String.valueOf(num));
            if(str.contains("666")) count++;
        }


        bw.write(String.valueOf(num));

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}