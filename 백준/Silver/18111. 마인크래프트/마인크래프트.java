import java.io.*;
import java.util.*;

public class Main {

    int N; // 세로
    int M; // 가로
    int B; // 인벤토리 블록 개수

    int minHeight = Integer.MAX_VALUE;
    int maxHeight = Integer.MIN_VALUE;
    int curHeight;
    int minTime = Integer.MAX_VALUE;
    int bag;

    int[][] map;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                minHeight = Math.min(map[i][j], minHeight);
                maxHeight = Math.max(map[i][j], maxHeight);
            }
        }

        for(int i=minHeight; i<=maxHeight; i++) {
            makeLandAtHeight(i);
        }


        bw.write(minTime + " " + curHeight);
        bw.flush();
        bw.close();
    }

    public void makeLandAtHeight(int height) {

        int totalTime = 0;
        int inventory = B;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                int heightDifference = height - map[i][j];

                if(heightDifference == 0) {
                    continue;
                } else if(heightDifference<0) {
                    heightDifference = Math.abs(heightDifference);
                    totalTime += heightDifference*2;
                    inventory += heightDifference;
                } else {
                    inventory -= heightDifference;
                    totalTime += heightDifference;
                }
            }
        }

        if(inventory>=0 && totalTime<=minTime) {
            minTime = totalTime;
            curHeight = height;
        }
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}