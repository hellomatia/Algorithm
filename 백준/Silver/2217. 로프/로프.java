import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int ropeCount = Integer.parseInt(bf.readLine());
        int[] ropeWeight = new int[ropeCount];

        for(int i=0; i<ropeCount; i++){
            ropeWeight[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(ropeWeight);
        int maxWeight = 0;


        for(int i=0; i<ropeCount; i++){
            if(maxWeight<(ropeWeight[i]*(ropeCount-i))){
                maxWeight = ropeWeight[i]*(ropeCount-i);
            }
        }


        bw.write(String.valueOf(maxWeight));

        bw.flush();
        bw.close();

    }
}