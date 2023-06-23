import java.util.*;

class Solution {
    static char[][] map;
    
    public int solution(int m, int n, String[] board) {
        
        //1차원 배열을 2차원 배열로 변환
        map = new char[m][n];
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        int cnt=0;
        int answer = 0;
        
        while(true){
            cnt = detectionAndDrop(m, n);
            if(cnt==0) break;
            reorderMap(m, n);
            answer += cnt;
        }
        
        
        return answer;
    }
    
    static int detectionAndDrop(int m, int n) {
        // 삭제할 블록을 저장할 2차원 배열 선언
        boolean[][] drop = new boolean[m][n];
        
        // 각 블록에서 시계방향으로 탐색하면서 같은지 확인
        // map의 범위안에서 돌기 위해 가로 세로에 -1
        for(int i=0; i<m-1; i++) {
            for(int j=0; j<n-1; j++) {
                //시작할 블록을 block변수에 값을 저장
                char block = map[i][j];
                //map이 비어있으면 continue
                if(block=='X') continue;
                int x = i; // row
                int y = j; // col
                //시계방향으로 도는 범위를 확인하면서 하나라도 다르면 continue
                //시계방향 범위 확인
                if(map[x+1][y]!=block || map[x][y+1]!=block || map[x+1][y+1]!=block) {
                    continue;
                }
                
                //모두 같으면 drop = true
                drop[i][j]=true;
                drop[i+1][j]=true;
                drop[i][j+1]=true;
                drop[i+1][j+1]=true;
            }
        }
        
        // drop시킬 block의 개수를 세는 변수 선언
        int countDrop = 0;
        
        // drop을 전부 탐색하면서 drop시킬 블록은 drop시키고 count
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(drop[i][j]) {
                    map[i][j] = 'X';
                    countDrop++;
                }
            }
        }
        return countDrop;
    }
    
    static void reorderMap(int m, int n){
        //'X'를 제외하고 다 큐에 넣어준후 다시 map에 차례대로 넣어준다.
        Queue<Character> queue = new LinkedList<>();
        
        for(int i=0; i<n; i++){
            //queue에 넣어준다.
            for(int j=m-1; j>=0; j--) {
                if(map[j][i]!='X') {
                    queue.add(map[j][i]);
                    map[j][i] = 'X';
                }
            }
            //다시 map에 넣어준다
            int idx=m-1;
            while(!queue.isEmpty()){
                map[idx--][i] = queue.poll();
            }
        }
        
    }
    
}