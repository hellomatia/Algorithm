import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numOfSheets = Integer.parseInt(bf.readLine());
        int[][] canvas = new int[100][100];

        for (int i = 0; i < numOfSheets; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            int horizontalDistance = Integer.parseInt(st.nextToken());
            int verticalDistance = Integer.parseInt(st.nextToken());
            canvas = changeBlackArea(canvas, horizontalDistance, verticalDistance);
        }

        bf.close();

        bw.write(String.valueOf(getBlackArea(canvas)));

        bw.flush();
        bw.close();


    }
    static int[][] changeBlackArea (int[][] canvas, int x, int y){
        for(int i=x; i<(x+10); i++){
            for(int j=y; j<(y+10); j++){
                canvas[j][i] = 1;
            }
        }
        return canvas;
    }

    static int getBlackArea (int[][] canvas){
        int blackArea = 0;
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                blackArea += canvas[i][j];
            }
        }
        return blackArea;
    }
}