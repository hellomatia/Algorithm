import java.util.*;

class Point {
    int x;
    int y;
    int d;
    Point(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

class Solution {
    int[] dirX = {0, 0, 1, -1};
    int[] dirY = {1, -1, 0, 0};
    int n, m; //세로, 가로
    boolean[][] isVisited;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] maps) {
        
        n = maps.length;
        m = maps[0].length;
        
        isVisited = new boolean[n][m];
        isVisited[0][0] = true;
        
        //DFS(maps, 0, 0, 1);
        BFS(maps);
        
        
        if(answer==Integer.MAX_VALUE) answer = -1;
        
        return answer;
    }
    
    /*
    public void DFS(int[][] maps, int x, int y, int count) {
        if(x==n-1&&y==m-1) {
            answer = Math.min(count, answer);
            return;
        }
        
        
        for(int i=0; i<4; i++) {
            int nowX = x + dirX[i];
            int nowY = y + dirY[i];
            
            if(nowX<0 || nowY<0 || n<=nowX || m<=nowY || maps[nowX][nowY]==0 || isVisited[nowX][nowY]) {
                continue;
            }
            isVisited[nowX][nowY] = true;
            DFS(maps, nowX, nowY, count+1);
            isVisited[nowX][nowY] = false;
        }
        
    }
    */
    
    public void BFS(int[][] maps) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 1));
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            if(now.x==n-1&&now.y==m-1) {
                answer = now.d;
                return;
            }
            
            for(int i=0; i<4; i++) {
                int x = now.x + dirX[i];
                int y = now.y + dirY[i];
                if(x<0 || y<0 || n<=x || m<=y || maps[x][y]==0 || isVisited[x][y]) {
                    continue;
                }
                queue.add(new Point(x, y, now.d+1));
                isVisited[x][y] = true;
            }
        }
        
    }
}