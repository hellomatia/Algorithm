import java.io.*;
import java.util.*;
class Point{
    int x;
    int y;
    int count = 0;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[][] map;
    static int continentCount = 1;
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};
    public void solution() throws IOException {

        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<Point> continents = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 1) {
                    continue;
                }
                Point startPoint = new Point(i, j);
                checkContinent(startPoint);
                continents.add(startPoint);
            }
        }

        int minLength = Integer.MAX_VALUE;

        for (Point continent : continents) {
            minLength = Math.min(minLengthBridge(continent), minLength);
        }


        //printMap();

        bw.write(minLength+"\n");

        bw.flush();
        bw.close();
    }

    public void checkContinent(Point startPoint) {

        continentCount++;

        Queue<Point> queue = new LinkedList<>();

        map[startPoint.x][startPoint.y] = continentCount;
        queue.offer(startPoint);

        while(!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i <4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || N <= nextX || N <= nextY
                    || map[nextX][nextY] != 1) {
                    continue;
                }

                map[nextX][nextY] = continentCount;
                queue.offer(new Point(nextX, nextY));
            }
        }
    }

    public int minLengthBridge(Point startPoint){

        boolean[][] visited = new boolean[N][N];

        Queue<Point> queue = new LinkedList<>();
        Queue<Point> continentQ = new LinkedList<>();

        int continentNum = map[startPoint.x][startPoint.y];
        visited[startPoint.x][startPoint.y] = true;
        queue.offer(startPoint);

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i <4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || N <= nextX || N <= nextY
                        || map[nextX][nextY] != continentNum
                        || visited[nextX][nextY]) {
                    continue;
                }

                visited[nextX][nextY] = true;
                queue.offer(new Point(nextX, nextY));
            }
            continentQ.offer(now);
        }

        while (!continentQ.isEmpty()) {

            Point now = continentQ.poll();

            if (1 < map[now.x][now.y] && map[now.x][now.y] != continentNum) {
                return now.count - 1;
            }

            for (int i = 0; i <4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || N <= nextX || N <= nextY
                        || visited[nextX][nextY]) {
                    continue;
                }

                visited[nextX][nextY] = true;
                continentQ.offer(new Point(nextX, nextY, now.count+1));
            }
        }

        return Integer.MAX_VALUE;
    }

    public void printMap(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}