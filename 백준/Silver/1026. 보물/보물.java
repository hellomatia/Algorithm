import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int numCount = Integer.parseInt(bf.readLine());

        int[]A = new int[numCount];
        int[]B = new int[numCount];

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        for(int i=0; i<numCount; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine(), " ");

        for(int i=0; i<numCount; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);

        int minSum = 0;

        for(int i=0; i<numCount; i++){
            minSum += A[i] * B[numCount-i-1];
        }





        bw.write(String.valueOf(minSum));

        bw.flush();
        bw.close();

    }
}

