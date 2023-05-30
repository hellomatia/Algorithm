import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    int[] arr;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        arr = new int[6];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for(int i=0; i<6; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int x = calculationX();
        int y = calculationY(x);

        bw.write(x + " " + y);

        bw.flush();
        bw.close();
    }

    public int calculationX() {
        return ((arr[2]*arr[4])-(arr[1]*arr[5])) / ((arr[0]*arr[4]) - (arr[1]*arr[3]));
    }

    public int calculationY(int x) {
        if(arr[1]!=0) {
            return (arr[2]-(arr[0]*x)) / arr[1];
        } else {
            return (arr[5]-(arr[3]*x)) / arr[4];
        }
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
