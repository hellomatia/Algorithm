import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] tree;
    static int[][] parent;
    static int[] depth;
    static int maxHeight;
    static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        
        // Initialize tree
        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        
        // Read edges
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            tree[x].add(y);
            tree[y].add(x);
        }
        
        // Calculate max height for LCA
        maxHeight = (int) Math.ceil(Math.log(N) / Math.log(2));
        parent = new int[N + 1][maxHeight + 1];
        depth = new int[N + 1];
        
        // Initialize depth and parent arrays
        dfs(1, 0);
        fillParent();
        
        // Process queries
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            sb.append(findCircumcenter(a, b, c)).append('\n');
        }
        
        System.out.print(sb);
    }
    
    static void dfs(int current, int prev) {
        depth[current] = depth[prev] + 1;
        parent[current][0] = prev;
        
        for (int next : tree[current]) {
            if (next != prev) {
                dfs(next, current);
            }
        }
    }
    
    static void fillParent() {
        for (int i = 1; i <= maxHeight; i++) {
            for (int j = 1; j <= N; j++) {
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }
    
    static int lca(int a, int b) {
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        
        for (int i = maxHeight; i >= 0; i--) {
            if (depth[b] - depth[a] >= (1 << i)) {
                b = parent[b][i];
            }
        }
        
        if (a == b) return a;
        
        for (int i = maxHeight; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        
        return parent[a][0];
    }
    
    static int getDistance(int a, int b) {
        int lcaNode = lca(a, b);
        return depth[a] + depth[b] - 2 * depth[lcaNode];
    }
    
    static int getNodeAtDistance(int start, int end, int distance) {
        int lcaNode = lca(start, end);
        
        // If target is in the path from start to LCA
        if (depth[start] - depth[lcaNode] >= distance) {
            int current = start;
            for (int i = maxHeight; i >= 0; i--) {
                if (distance >= (1 << i)) {
                    current = parent[current][i];
                    distance -= (1 << i);
                }
            }
            return current;
        }
        // If target is in the path from LCA to end
        else {
            int distToLca = depth[start] - depth[lcaNode];
            int remaining = distance - distToLca;
            int distFromLca = depth[end] - depth[lcaNode];
            if (remaining > distFromLca) return -1;
            
            int current = end;
            int upDistance = distFromLca - remaining;
            for (int i = maxHeight; i >= 0; i--) {
                if (upDistance >= (1 << i)) {
                    current = parent[current][i];
                    upDistance -= (1 << i);
                }
            }
            return current;
        }
    }
    
    static int findCircumcenter(int a, int b, int c) {
        // Get LCAs between each pair
        int lcaAB = lca(a, b);
        int lcaBC = lca(b, c);
        int lcaCA = lca(c, a);
        
        // Get distances
        int distAB = getDistance(a, b);
        int distBC = getDistance(b, c);
        int distCA = getDistance(c, a);
        
        // Check mid points of each path
        int[] candidates = new int[6];
        candidates[0] = getNodeAtDistance(a, b, distAB / 2);
        candidates[1] = getNodeAtDistance(a, b, (distAB + 1) / 2);
        candidates[2] = getNodeAtDistance(b, c, distBC / 2);
        candidates[3] = getNodeAtDistance(b, c, (distBC + 1) / 2);
        candidates[4] = getNodeAtDistance(c, a, distCA / 2);
        candidates[5] = getNodeAtDistance(c, a, (distCA + 1) / 2);
        
        int result = -1;
        int minDist = Integer.MAX_VALUE;
        
        // Check each candidate
        for (int candidate : candidates) {
            if (candidate == -1) continue;
            
            int distToA = getDistance(candidate, a);
            int distToB = getDistance(candidate, b);
            int distToC = getDistance(candidate, c);
            
            if (distToA == distToB && distToB == distToC && distToA < minDist) {
                minDist = distToA;
                result = candidate;
            }
        }
        
        return result;
    }
}