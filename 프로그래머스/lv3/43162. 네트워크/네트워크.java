import java.util.*;

class Solution {
    boolean[] isVisited;
    int answer;
    Queue<Integer> queue = new LinkedList<>();
    
    public int solution(int n, int[][] computers) {
        answer = 0;
        isVisited = new boolean[n];
        
        for(int i=0; i<n; i++) {
            if(isVisited[i]) continue;
            isVisited[i] = true;
            for(int j=0; j<n; j++) {
                if(!isVisited[j]&&computers[i][j]==1){
                    queue.add(j);
                    isVisited[j] = true;
                }
            }
            BFS(computers, n);
        }
        
        return answer;
    }
    
    public void BFS(int[][] computers, int n){
        
        while(!queue.isEmpty()) {
            int computer = queue.poll();
            for(int i=0; i<n; i++) {
                if(!isVisited[i]&&computers[computer][i]==1) {
                    queue.add(i);
                    isVisited[i] = true;
                }
            }
        }
        
        answer++;
        return;
    }
}