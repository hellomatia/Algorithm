class Solution {
    public int uniquePaths(int m, int n) {
        int[][] map = new int[m][n];
        
        map[0][0] = 1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isIn(m, n, i - 1, j)) {
                    map[i][j] += map[i - 1][j];
                }
                if (isIn(m, n, i, j - 1)) {
                    map[i][j] += map[i][j - 1];
                }
            }
        }
        
        return map[m - 1][n - 1];
    }
    
    private boolean isIn(int m, int n, int nowX, int nowY) {
        return 0 <= nowX && nowX < m && 0 <= nowY && nowY < n;
    }
}