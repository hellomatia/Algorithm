import java.io.*;
import java.util.*;
 
class Point {
    int x;
    int y;
    int time;
     
    Point(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
     
}
 
public class Solution {
     
    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int T;
    private static int[][] map;
    private static boolean[][] visited;
    private static int minTime;
    private static int[] dirX = {0 ,0 , 1, -1};
    private static int[] dirY = {1, -1, 0, 0};
     
    public static void main(String args[]) throws Exception {
        solution();
    }
     
    private static void solution() throws IOException {
        initT();
        for (int testCase = 1; testCase <= T; testCase++) {
            initMap();
            printResult(testCase, minTime());
        }
        bw.flush();
        bw.close();
    }
     
    private static void initT() throws IOException {
        T = Integer.parseInt(bf.readLine());
    }
 
    private static void initMap() throws IOException {
        int length = Integer.parseInt(bf.readLine());
        map = new int[length][length];
        visited = new boolean[length][length];
         
        for (int row = 0; row < length; row++) {
            String string = bf.readLine();
            for (int col = 0; col < length; col++) {
                map[row][col] = string.charAt(col) - '0';
            }
        }
    }
     
    private static int minTime() {
        PriorityQueue<Point> queue = new PriorityQueue<>((o1, o2) -> {
            return o1.time - o2.time;
        });
        queue.offer(new Point(0, 0, map[0][0]));
        minTime = Integer.MAX_VALUE;
        visited[0][0] = true;
         
        while (!queue.isEmpty() ) {
            Point now = queue.poll();
                         
            if (now.x == map.length - 1 && now.y == map.length - 1) {
                minTime = Math.min(minTime, now.time);
                continue;
            }
             
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                 
                if (nextX < 0 || nextY < 0 || map.length <= nextX || map.length <= nextY 
                        || visited[nextX][nextY]) {
                    continue;
                }
                visited[nextX][nextY] = true;
                queue.offer(new Point(nextX, nextY, now.time + map[nextX][nextY]));
            }
        }
         
         
        return minTime;
    }
     
    private static void printResult(int testCase, int result) throws IOException {
        bw.write("#"+testCase+" "+result + "\n");
    }
}