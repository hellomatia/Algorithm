import java.util.*;

class Solution {
    private static int[] dirX = {0, -1, 0, 1};
    private static int[] dirY = {-1, 0, 1, 0};
    private static int OIL = 1;
    private int[][] map;
    private int m, n;
    private int id;
    private int[] oilSizes;
    
    public int solution(int[][] land) {
        m = land.length;
        n = land[0].length;
        id = 1;
        map = land;
        oilSizes = new int[n];
        
        return calcAns();
    }
    
    private int calcAns() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == OIL) {
                    calcAreaSize(i, j);
                }
            }
        }
        
        int ans = 0;
        for (int size : oilSizes) {
            ans = Math.max(ans, size);
        }
        return ans;
    }
    
    private void calcAreaSize(int x, int y) {
        Queue<Point> queue = new ArrayDeque<>();
        Set<Integer> oilPoint = new HashSet<>(); 
        ++id;
        
        map[x][y] = id;
        int areaSize = 1;
        queue.offer(new Point(x, y));
        oilPoint.add(y);
        
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = now.getX() + dirX[i];
                int nextY = now.getY() + dirY[i];
                if (isIn(nextX, nextY) && map[nextX][nextY] == OIL) {
                    map[nextX][nextY] = id;
                    areaSize++;
                    queue.offer(new Point(nextX, nextY));
                    oilPoint.add(nextY);
                }
            }
        }
        
        for (int point : oilPoint) {
            oilSizes[point] += areaSize;
        }
    }
    
    private boolean isIn(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }
    
    private static class Point {
        private final int x;
        private final int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int getX() {
            return x;
        }
        
        public int getY() {
            return y;
        }
    }
}