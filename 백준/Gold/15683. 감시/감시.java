import java.io.*;
import java.util.*;

class Point {
    int row;
    int col;
    Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {
    int N, M;
    int[][] ogMap;
    int minCount = Integer.MAX_VALUE;
    ArrayList<Point> cctv1 = new ArrayList<>();
    ArrayList<Point> cctv2 = new ArrayList<>();
    ArrayList<Point> cctv3 = new ArrayList<>();
    ArrayList<Point> cctv4 = new ArrayList<>();
    ArrayList<Point> cctv5 = new ArrayList<>();


    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로 크기
        M = Integer.parseInt(st.nextToken()); // 가로 크기

        ogMap = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<M; j++) {
                ogMap[i][j] = Integer.parseInt(st.nextToken());
                if(ogMap[i][j]==1) {
                    cctv1.add(new Point(i, j));
                } else if(ogMap[i][j]==2) {
                    cctv2.add(new Point(i, j));
                } else if(ogMap[i][j]==3) {
                    cctv3.add(new Point(i, j));
                } else if(ogMap[i][j]==4) {
                    cctv4.add(new Point(i, j));
                } else if(ogMap[i][j]==5) {
                    cctv5.add(new Point(i, j));
                }
            }
        }

        runCCTV5(0);



        bw.write(minCount + "\n");
        bw.flush();
        bw.close();

    }

    public void countMinBlindSpot(int[][] map ) {
        int sum = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j]==0) {
                    sum++;
                }
            }
        }

        minCount = Math.min(sum, minCount);

    }

    public void runCCTV1(int[][] map, int count) {
        if(cctv1.size()==count) {
            runCCTV2(map, 0);
            return;
        }
        int row = cctv1.get(count).row;
        int col = cctv1.get(count).col;

        int[][] tempMap1 = copyMap(map);
        CCTVDirUp(tempMap1, row, col);
        runCCTV1(tempMap1, count+1);

        int[][] tempMap2 = copyMap(map);
        CCTVDirDown(tempMap2, row, col);
        runCCTV1(tempMap2, count+1);

        int[][] tempMap3 = copyMap(map);
        CCTVDirRight(tempMap3, row, col);
        runCCTV1(tempMap3, count+1);

        int[][] tempMap4 = copyMap(map);
        CCTVDirLeft(tempMap4, row, col);
        runCCTV1(tempMap4, count+1);
    }
    public void runCCTV2(int[][] map, int count) {
        if(cctv2.size()==count) {
            runCCTV3(map, 0);
            return;
        }
        int row = cctv2.get(count).row;
        int col = cctv2.get(count).col;

        int[][] tempMap1 = copyMap(map);
        CCTVDirUp(tempMap1, row, col);
        CCTVDirDown(tempMap1, row, col);
        runCCTV2(tempMap1, count+1);

        int[][] tempMap2 = copyMap(map);
        CCTVDirRight(tempMap2, row, col);
        CCTVDirLeft(tempMap2, row, col);
        runCCTV2(tempMap2, count+1);

    }
    public void runCCTV3(int[][] map, int count) {
        if(cctv3.size()==count) {
            runCCTV4(map, 0);
            return;
        }
        int row = cctv3.get(count).row;
        int col = cctv3.get(count).col;

        int[][] tempMap1 = copyMap(map);
        CCTVDirUp(tempMap1, row, col);
        CCTVDirLeft(tempMap1, row, col);
        runCCTV3(tempMap1, count+1);

        int[][] tempMap2 = copyMap(map);
        CCTVDirUp(tempMap2, row, col);
        CCTVDirRight(tempMap2, row, col);
        runCCTV3(tempMap2, count+1);

        int[][] tempMap3 = copyMap(map);
        CCTVDirDown(tempMap3, row, col);
        CCTVDirLeft(tempMap3, row, col);
        runCCTV3(tempMap3, count+1);

        int[][] tempMap4 = copyMap(map);
        CCTVDirDown(tempMap4, row, col);
        CCTVDirRight(tempMap4, row, col);
        runCCTV3(tempMap4, count+1);
    }
    public void runCCTV4(int[][] map, int count) {
        if(cctv4.size()==count) {
            countMinBlindSpot(map);
            return;
        }
        int row = cctv4.get(count).row;
        int col = cctv4.get(count).col;

        int[][] tempMap1 = copyMap(map);
        CCTVDirUp(tempMap1, row, col);
        CCTVDirLeft(tempMap1, row, col);
        CCTVDirRight(tempMap1, row, col);
        runCCTV4(tempMap1, count+1);

        int[][] tempMap2 = copyMap(map);
        CCTVDirUp(tempMap2, row, col);
        CCTVDirDown(tempMap2, row, col);
        CCTVDirRight(tempMap2, row, col);
        runCCTV4(tempMap2, count+1);

        int[][] tempMap3 = copyMap(map);
        CCTVDirUp(tempMap3, row, col);
        CCTVDirDown(tempMap3, row, col);
        CCTVDirLeft(tempMap3, row, col);
        runCCTV4(tempMap3, count+1);

        int[][] tempMap4 = copyMap(map);
        CCTVDirDown(tempMap4, row, col);
        CCTVDirRight(tempMap4, row, col);
        CCTVDirLeft(tempMap4, row, col);
        runCCTV4(tempMap4, count+1);
    }
    public void runCCTV5(int count) {

        if(cctv5.size()==count){
            runCCTV1(ogMap, 0);
            return;
        }

        int row = cctv5.get(count).row;
        int col = cctv5.get(count).col;

        CCTVDirUp(ogMap, row, col);
        CCTVDirDown(ogMap, row, col);
        CCTVDirRight(ogMap, row, col);
        CCTVDirLeft(ogMap, row, col);

        runCCTV5(count+1);
    }

    public void CCTVDirUp(int[][] tempMap, int row, int col){
        for(int i=row-1; i>=0; i--) {
            if(tempMap[i][col]==6) {
                break;
            } else if(tempMap[i][col]==0) {
                tempMap[i][col] = -1;
            }
        }
    }
    public void CCTVDirDown(int[][] tempMap, int row, int col){
        for(int i=row+1; i<N; i++) {
            if(tempMap[i][col]==6) {
                break;
            } else if(tempMap[i][col]==0) {
                tempMap[i][col] = -1;
            }
        }
    }
    public void CCTVDirLeft(int[][] tempMap, int row, int col){
        for(int i=col-1; i>=0; i--) {
            if(tempMap[row][i]==6) {
                break;
            } else if(tempMap[row][i]==0) {
                tempMap[row][i] = -1;
            }
        }
    }
    public void CCTVDirRight(int[][] tempMap, int row, int col){
        for(int i=col+1; i<M; i++) {
            if(tempMap[row][i]==6) {
                break;
            } else if(tempMap[row][i]==0) {
                tempMap[row][i] = -1;
            }
        }
    }

    public int[][] copyMap (int[][] map) {
        int[][] copyMap = new int[N][M];
        for(int i=0; i<N; i++) {
            copyMap[i] = map[i].clone();
        }
        return copyMap;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}