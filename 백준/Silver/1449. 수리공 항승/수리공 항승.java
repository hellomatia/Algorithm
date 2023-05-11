import java.io.*;
import java.util.*;

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        int numLeaks = Integer.parseInt(st.nextToken());
        int tapeLength = Integer.parseInt(st.nextToken());
        int[] leakLocations = new int[numLeaks];

        st = new StringTokenizer(bf.readLine(), " ");

        for(int i=0; i<numLeaks; i++){
            leakLocations[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(leakLocations);
        int tapeCount = 0;
        double tapeEndLocation = 0;

        for(int i=0; i<numLeaks; i++){
            if (tapeEndLocation>=(leakLocations[i]+0.5)) {
                continue;
            }
            else if (tapeEndLocation>=(leakLocations[i]-0.5)) {
                tapeEndLocation += tapeLength;
                tapeCount++;
            }
            else {
                tapeEndLocation = (leakLocations[i])-0.5 + tapeLength;
                tapeCount++;
            }
        }



        bw.write(String.valueOf(tapeCount));

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}

