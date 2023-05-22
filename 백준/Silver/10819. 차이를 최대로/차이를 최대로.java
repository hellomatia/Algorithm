import java.io.*;
import java.util.*;

public class Main {
    int N;
    int[] arr;

    ArrayList<Integer> arrIndex = new ArrayList<>();

    int maxSum = Integer.MIN_VALUE;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine()); // 수열의 개수
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        randomArr();


        bw.write(maxSum + "\n");



        bw.flush();
        bw.close();
    }

    public void randomArr() {

        if(arrIndex.size() == N) {
            calculationArr();
            return;
        }

        for(int i=0; i<N; i++) {
            if(!arrIndex.contains(i)) {
                arrIndex.add(i);
                randomArr();
                arrIndex.remove(arrIndex.size()-1);
            }

        }
    }

    public void calculationArr() {
        int sum = 0;
        for(int i=0; i<N-1; i++) {
            sum += Math.abs(arr[arrIndex.get(i)]-arr[arrIndex.get(i+1)]);
        }
        maxSum = Math.max(sum, maxSum);
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}