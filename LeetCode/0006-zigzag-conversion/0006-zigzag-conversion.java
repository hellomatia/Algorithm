class Solution {
    
    private char[][] map;
    private int[] dirX = new int[]{1, -1};
    private int[] dirY = new int[]{0, 1};
    
    public String convert(String s, int numRows) {
        map = new char[numRows][s.length()];
        
        if (numRows == 1) {
            return s;
        }
        
        go(0, 0, 0, s, 0);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < s.length(); j++) {
                if (map[i][j] == 0) {
                    continue;
                } else {
                    sb.append(map[i][j]);
                }
            }
        }
        
        return sb.toString();
    }
    
    private void go(int x, int y, int count, String s, int prevDir) {
        
        if (count == s.length()) {
            return;
        }
        
        map[x][y] = s.charAt(count);
        
        if (prevDir == 0) {
            if (x == map.length - 1) {
                go(x + dirX[1], y + dirY[1], count + 1, s, 1);
            } else {
                go(x + dirX[0], y + dirY[0], count + 1, s, 0);
            }    
        } else {
            if (x == 0) {
                go(x + dirX[0], y + dirY[0], count + 1, s, 0);
            } else {
                go(x + dirX[1], y + dirY[1], count + 1, s, 1);
            }
        }
    }
}