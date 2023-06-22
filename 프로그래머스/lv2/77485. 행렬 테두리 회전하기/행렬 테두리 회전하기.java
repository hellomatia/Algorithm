import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        
        //answer배열 크기 선언
        int[] answer = new int[queries.length];
        
        //map생성후 값 채워넣기
        int[][] map = new int[rows][columns];
        
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                map[i][j] = i*columns + (1+j);
            }
        }
        
        //시계방향으로 회전 시작
        for(int i=0; i<queries.length; i++) {
            //회전 범위는 0부터 시작하기에 1을 빼준 값을 넘겨줌
            //rotation 함수는 map 과 회전범위를 넘겨주면 그 회전값 하는 값들 중에 가장 작은 값 반환
            answer[i] = rotation(map, queries[i][0]-1, queries[i][1]-1, queries[i][2]-1, queries[i][3]-1);
        }
        
        return answer;
    }
    
    public int rotation(int[][] map, int x1, int y1, int x2, int y2) {
        // 값들 중에 최소 값을 구하기 위해 minNum 변수에 최대값 선언
        int minNum = Integer.MAX_VALUE;
        
        //큐에 담아두고 순서대로 순서대로 넘겨줌
        Queue<Integer> queue = new LinkedList<>();
        
        //회전반경에 있는 값들 큐에 담아두기
        //가로 첫번째
        for(int i=y1; i<=y2; i++) {
            queue.add(map[x1][i]);
        }
        //세로 첫번째
        for(int i=x1+1; i<=x2; i++) {
            queue.add(map[i][y2]);
        }
        //가로 두번째
        for(int i=y2-1; y1<=i; i--) {
            queue.add(map[x2][i]);
        }
        //세로 두번째
        for(int i=x2-1; x1+1<=i; i--) {
            queue.add(map[i][y1]);
        }
        
        //한칸씩 밀린 상태에서 큐에 들어 있는 값들 다시 넘겨주기
        //넘겨주면서 최소값인지 비교
        //가로 첫번째
        for(int i=y1+1; i<=y2; i++) {
            int num = queue.poll();
            minNum = Math.min(minNum, num);
            map[x1][i] = num;
        }
        //세로 첫번째
        for(int i=x1+1; i<=x2; i++) {
            int num = queue.poll();
            minNum = Math.min(minNum, num);
            map[i][y2] = num;
        }
        //가로 두번째
        for(int i=y2-1; y1<=i; i--) {
            int num = queue.poll();
            minNum = Math.min(minNum, num);
            map[x2][i] = num;
        }
        //세로 두번째
        for(int i=x2-1; x1<=i; i--) {
            int num = queue.poll();
            minNum = Math.min(minNum, num);
            map[i][y1] = num;
        }
        
        return minNum;
    }
}