import java.io.*;
import java.util.*;

class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    int N, L, R; // 세로 가로크기, 최소, 최대 인원
    int[][] map;
    int[] dirX = {0, 0, -1, 1}; // 동 서 북 남
    int[] dirY = {1, -1, 0, 0};
    boolean[][] isVisited;
    int day;

    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        isVisited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        simulation();

        bw.write(day + "\n");




        bw.flush();
        bw.close();
    }

    public void simulation() {

        int[][] copyMap = copyMap();

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!isVisited[i][j]) {
                    movePeople(i, j, copyMap);
                }
            }
        }

        for(int i=0; i<N; i++) {
            Arrays.fill(isVisited[i], false);
        }

        /*
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
         */

        if(checkChange(copyMap)) {

            for(int i=0; i<N; i++) {
                map[i] = copyMap[i].clone();
            }

            day++;
            simulation();
        }

    }

    public void movePeople(int x, int y, int[][] copyMap) {
        Queue<Point> unitedCountry = new LinkedList<>();
        Queue<Point> queue = new LinkedList<>();

        unitedCountry.add(new Point(x, y));
        queue.add(new Point(x, y));

        isVisited[x][y] = true;

        int count = 1;
        int total = copyMap[x][y];


        while(!unitedCountry.isEmpty()){
            Point now = unitedCountry.poll();
            for(int i=0; i<4; i++) {
                int r = now.x + dirX[i];
                int c = now.y + dirY[i];


                if(r<0 || c<0 || N<=r || N<=c || isVisited[r][c]) continue;
                int gap = Math.abs(copyMap[now.x][now.y]-copyMap[r][c]);
                if(gap<L || R<gap) continue;

                isVisited[r][c] = true;
                unitedCountry.add(new Point(r, c));
                queue.add(new Point(r, c));
                count++;
                total += copyMap[r][c];
            }
        }

        if(count==1) return;

        int num = total/count;

        while(!queue.isEmpty()) {
            Point now = queue.poll();
            copyMap[now.x][now.y] = num;
        }

    }

    public int[][] copyMap() {
        int[][] copyMap = new int[N][N];

        for(int i=0; i<N; i++) {
            copyMap[i] = map[i].clone();
        }

        return copyMap;
    }

    public boolean checkChange(int[][] copyMap) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(copyMap[i][j]!=map[i][j]) return true;
            }
        }
        return false;
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}