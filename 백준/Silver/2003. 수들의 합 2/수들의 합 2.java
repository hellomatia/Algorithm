import java.io.*;
import java.util.*;

public class Main {
    int N, M;
    int[] arr;
    int count;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken()); // 수열의 개수
        M = Integer.parseInt(st.nextToken()); // 만들고자 하는 숫자

        arr = new int[N];

        st = new StringTokenizer(bf.readLine());

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            sumArr(i);
        }


        bw.write(count + "\n");

        bw.flush();
        bw.close();
    }

    public void sumArr(int start) {
        int sum = 0;
        for(int i=start; i<N; i++) {
            sum += arr[i];
            if(sum == M) count++;
        }
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}