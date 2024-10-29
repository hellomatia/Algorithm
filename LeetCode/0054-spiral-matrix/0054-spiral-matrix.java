class Solution {
    
    private int[] dirX = new int[]{0, 1, 0, -1};
    private int[] dirY = new int[]{1, 0, -1, 0};
    
    public List<Integer> spiralOrder(int[][] matrix) {
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    
        List<Integer> answer = new ArrayList<>();
        int nowX = 0;
        int nowY = 0;
        int nowDir = 0;
        while (true) {
            if (!isIn(nowX, nowY, matrix) || visited[nowX][nowY]) {
                break;
            }
            
            visited[nowX][nowY] = true;
            answer.add(matrix[nowX][nowY]);
            
            int nextX = nowX + dirX[nowDir];
            int nextY = nowY + dirY[nowDir];
            if (!isIn(nextX, nextY, matrix) || visited[nextX][nextY]) {
                nowDir += 1;
                nowDir %= 4;
                nextX = nowX + dirX[nowDir];
                nextY = nowY + dirY[nowDir];
            }
            nowX = nextX;
            nowY = nextY;
        }
        
        return answer;
    }
    
    private boolean isIn(int x, int y, int[][] matrix) {
        return 0 <= x && x < matrix.length && 0 <= y && y < matrix[0].length;
    }
}