import java.io.*;
import java.util.*;

public class Main {
    int n; // n개로 이루어져있는 정수
    int[] arr;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(bf.readLine());
        arr = new int[n];

        int max = Integer.MIN_VALUE;
        int total = 0;

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
            max = Math.max(total, max);
            if (total<0) total = 0;
        }


        bw.write(max+"\n");

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}