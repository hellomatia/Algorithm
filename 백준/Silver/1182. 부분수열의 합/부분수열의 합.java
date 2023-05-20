import java.io.*;
import java.util.*;


public class Main {
    int[] sequence;
    int n, s;
    int count = 0;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        sequence = new int[n];

        st = new StringTokenizer(bf.readLine());

        for(int i=0; i<n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }


        sumSequence(0, 0);

        bw.write(String.valueOf(count));


        bw.flush();
        bw.close();
    }

    public void sumSequence(int start, int sum){

        if(start == n) return;


        for(int i=start; i<n; i++) {
            sum += sequence[i];
            if(sum==s) {
                count++;
            }
            sumSequence(i+1, sum);
            sum -= sequence[i];
        }


    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}