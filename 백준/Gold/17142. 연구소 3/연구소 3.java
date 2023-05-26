import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;
    int dir;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class Main {

    int N; //연구실의 크기
    int M; //활성화 시킬 수 있는 바이러스 개수
    int minTime = Integer.MAX_VALUE; //최소시간
    int[] dirR = {0, 0, 1, -1};
    int[] dirC = {1, -1, 0, 0};
    int[][] labMap;
    boolean[][] isVisit;
    ArrayList<Point> virus = new ArrayList<>();
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        labMap = new int[N][N];
        isVisit = new boolean[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<N; j++){
                labMap[i][j]=Integer.parseInt(st.nextToken());
                if(labMap[i][j]==2) {
                    virus.add(new Point(i,j));
                }
            }
        }

        selectVirusToSpread(0,0);


        if(minTime==Integer.MAX_VALUE) {
            minTime = -1;
        }
        bw.write(minTime+"\n");


        bw.flush();
        bw.close();
    }

    public void selectVirusToSpread(int idx, int count) {
        if(count==M){
            minTime = Math.min(spreadVirusInLab(), minTime);
        }

        for(int i=idx; i<virus.size(); i++) {
            labMap[virus.get(i).x][virus.get(i).y] = 3; //3은 활성화된 바이러스
            selectVirusToSpread(i+1, count+1);
            labMap[virus.get(i).x][virus.get(i).y] = 2;
        }
    }

    public int spreadVirusInLab() {

        int[][] copyMap = copyMap(labMap);

        Queue<Point> spreadQ = new LinkedList<>();

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(copyMap[i][j]==3) {
                    for(int k=0; k<4; k++) {
                        spreadQ.add(new Point(i, j));
                    }
                }
            }
        }

        int qSize = spreadQ.size();
        int count = 0;

        while(!isAllSpread(copyMap) && !spreadQ.isEmpty()) {
            for(int i=0; i<qSize; i++) {
                Point now = spreadQ.poll();
                for(int j=0; j<4; j++) {
                    int x = now.x + dirR[j];
                    int y = now.y + dirC[j];
                    if(x<0 || x>=N || y<0 || y>=N || copyMap[x][y]==1) continue;
                    if(copyMap[x][y]==0 || copyMap[x][y]==2) {
                        copyMap[x][y]=3;
                        spreadQ.add(new Point(x,y));
                    }
                }
            }
            qSize = spreadQ.size();
            count++;
        }

        if(isAllSpread(copyMap)) return count;
        return Integer.MAX_VALUE;
    }

    public int[][] copyMap(int[][] map) {
        int[][] copyMap = new int[N][N];
        for(int i=0; i<N; i++){
            copyMap[i] = map[i].clone();
        }
        return copyMap;
    }

    public boolean isAllSpread(int[][] map) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j]==0) return false;
            }
        }
        return true;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}