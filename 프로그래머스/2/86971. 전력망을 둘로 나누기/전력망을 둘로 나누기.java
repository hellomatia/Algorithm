import java.util.*;

class Solution {
    private int[] size;
    private int[][] range;
    private int id;
    private List<Integer>[] adj;
    
    public int solution(int n, int[][] wires) {
        range = new int[n + 1][2];
        
        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] wire : wires) {
            adj[wire[0]].add(wire[1]);
            adj[wire[1]].add(wire[0]);
        }
        
        dfs(1, 1);
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int size = range[i][1] - range[i][0] + 1;
            answer = Math.min(answer, Math.abs(n - 2 * size));
        }

        return answer;
    }
    
    private void dfs(int now, int from) {
        range[now][0] = ++id;
        for (int to : adj[now]) {
            if (to == from) { continue; }
            dfs(to, now);
        }
        range[now][1] = id;
    }
}