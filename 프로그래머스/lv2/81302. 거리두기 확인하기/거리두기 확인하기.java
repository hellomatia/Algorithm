import java.util.*;

class Point{
    int x;
    int y;
    int d;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    Point(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

class Solution {
    int[] dirX = {0, 0, 1, -1};
    int[] dirY = {1, -1, 0, 0};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i=0; i<5; i++) {
            answer[i] = check(places[i]);
        }
        
        
        return answer;
    }
    
    //거리두기 확인하기
    public int check(String[] place) {
        
        int[][] map = new int[5][5];
        
        Queue<Point> person = new LinkedList<>();
        
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                char ch = place[i].charAt(j);
                
                //1==사람, 0==테이블, -1==파티션
                if(ch=='P') {
                    map[i][j] = 1;
                    person.add(new Point(i, j));
                } else if(ch=='X') {
                    map[i][j] = -1;
                }
            }
        }
        
        if(person.isEmpty()) return 1;
        
        Queue<Point> queue = new LinkedList<>();
        
        
        while(!person.isEmpty()) {
            
            Point checkP = person.poll();
            queue.add(new Point(checkP.x, checkP.y, 0));
            boolean[][] visited = new boolean[5][5];
            
            while(!queue.isEmpty()) {
                
                Point now = queue.poll();
                visited[now.x][now.y] = true;
                
                for(int i=0; i<4; i++) {
                    int x = now.x + dirX[i];
                    int y = now.y + dirY[i];
                    
                    if(x<0 || y<0 || 5<=x || 5<=y || map[x][y]==-1 || visited[x][y]) continue;
                    
                    visited[x][y] = true;
                    if(map[x][y]==1&&now.d+1<=2) {
                        return 0;
                    } else {
                        queue.add(new Point(x, y, now.d+1));
                    }
                }
            }
        }
        
        
        
        return 1;
    }
}