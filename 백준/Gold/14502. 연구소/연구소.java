import java.io.*;
import java.util.*;

public class Main {

    int[][] labMap;
    int row;
    int col;
    int beforeChangeArea = -1;
    int minChangeArea = Integer.MAX_VALUE;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        labMap = new int[row][col];

        int safeArea=0;

        for(int i=0; i<row; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<col; j++) {
                labMap[i][j] = Integer.parseInt(st.nextToken());
                if(labMap[i][j]==0) {
                    safeArea++;
                }
            }
        }
        //bw.write(String.valueOf(safeArea));

        makeWall(0);

        bw.write(String.valueOf(safeArea-3-minChangeArea));


        bw.flush();
        bw.close();
    }

    public void makeWall(int wallCount) {
        if(wallCount==3) {
            int[][] copyMap = new int[row][col];
            for(int i=0; i<row; i++) {
                copyMap[i] = labMap[i].clone();
            }
            bfs(copyMap,0);
            return;
        }
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(labMap[i][j]==0) {
                    labMap[i][j]=1;
                    makeWall(wallCount+1);
                    labMap[i][j]=0;
                }
            }
        }
    }

    public void bfs(int[][] map, int count) {

        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(map[i][j]==2) {
                    if(0<=(i-1)&&map[i-1][j]==0){
                        map[i-1][j]=2;
                        count++;
                    }
                    if(row>(i+1)&&map[i+1][j]==0){
                        map[i+1][j]=2;
                        count++;
                    }
                    if(0<=(j-1)&&map[i][j-1]==0){
                        map[i][j-1]=2;
                        count++;
                    }
                    if(col>(j+1)&&map[i][j+1]==0){
                        map[i][j+1]=2;
                        count++;
                    }
                }
            }
        }

        if(count==beforeChangeArea){
            if(count<minChangeArea){
                minChangeArea = count;
            }
            return;
        }

        beforeChangeArea = count;

        bfs(map, count);



    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}