import java.io.*;
import java.util.*;

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numWeight = Integer.parseInt(bf.readLine());
        Integer[] weightArr = new Integer[numWeight];

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for(int i=0; i<numWeight; i++) {
            weightArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weightArr);

        int sum = 0;

        for(int i=0; i<numWeight; i++) {
            if((sum+1)<weightArr[i]) {
                break;
            }
            sum += weightArr[i];
        }

        bw.write(String.valueOf(sum+1));


        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}

