import java.io.*;
import java.util.*;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(bf.readLine());

        for (int i = 0; i < testCase; i++) {
            int logCount = Integer.parseInt(bf.readLine());
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int[] logHeight = new int[logCount];

            for(int j=0; j<logCount; j++) {
                logHeight[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(logHeight);
            int[] minLevelLogArr = new int[logCount];
            int forwardIndex = 0;
            int backwardIndex = logCount-1;

            for(int j=0; j<logCount; ) {
                minLevelLogArr[forwardIndex++] = logHeight[j];
                if(j+1>logCount-1) break;
                minLevelLogArr[backwardIndex--] = logHeight[j+1];
                j += 2;
            }

            int maxLevel = Math.abs(minLevelLogArr[0] - minLevelLogArr[logCount-1]);

            for(int j=0; j<logCount-1; j++) {
                int level = Math.abs(minLevelLogArr[j] - minLevelLogArr[j+1]);
                if(maxLevel<level) {
                    maxLevel = level;
                }
            }

            bw.write(String.valueOf(maxLevel));
            bw.write("\n");

        }
        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}