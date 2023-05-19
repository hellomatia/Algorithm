import java.io.*;
import java.util.*;

public class Main {

    int[][] map;
    boolean[][] safeMap;
    int n;

    int rainfall = 0;
    int maxHeight;

    int maxSafeArea = 0;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(bf.readLine());

        map = new int[n][n];
        safeMap = new boolean[n][n];


        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]>maxHeight){
                    maxHeight = map[i][j];
                }
            }
        }

        safeAreaCheck();


        bw.write(String.valueOf(maxSafeArea));


        bw.flush();
        bw.close();
    }

    public void safeAreaCheck() {
        while(rainfall<maxHeight){
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(rainfall<map[i][j]){
                        safeMap[i][j] = true;
                    }
                }
            }
            safeAreaCount();


            rainfall++;
        }
    }

    public void safeAreaCount(){

        int count = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(safeMap[i][j]==true) {
                    safeMap[i][j]=false;
                    safeAreaChange(i, j);
                    count++;
                }
            }
        }

        maxSafeArea = Math.max(count, maxSafeArea);
    }

    public void safeAreaChange(int x, int y){
        if(x+1<n&&safeMap[x+1][y]==true){
            safeMap[x+1][y]=false;
            safeAreaChange(x+1,y);
        }
        if(0<=x-1&&safeMap[x-1][y]==true){
            safeMap[x-1][y]=false;
            safeAreaChange(x-1,y);
        }
        if(y+1<n&&safeMap[x][y+1]==true){
            safeMap[x][y+1]=false;
            safeAreaChange(x,y+1);
        }
        if(0<=y-1&&safeMap[x][y-1]==true){
            safeMap[x][y-1]=false;
            safeAreaChange(x,y-1);
        }
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}