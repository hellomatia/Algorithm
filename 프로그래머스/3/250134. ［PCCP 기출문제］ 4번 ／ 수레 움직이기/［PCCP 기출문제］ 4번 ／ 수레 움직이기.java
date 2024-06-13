class Solution {
    private static final int RED = 1;
    private static final int BLUE = 2;
    private static final int RED_HOLE = 3;
    private static final int BLUE_HOLE = 4;
    private static final int WALL = 5;
    private static final int[] dirX = {-1, 0, 1, 0};
    private static final int[] dirY = {0, 1, 0, -1};

    private int[][] maze;
    private boolean isAllArrive = false;
    private int answer = Integer.MAX_VALUE;

    public int solution(int[][] maze) {
        this.maze = maze;

        int redX = 0;
        int redY = 0;
        int blueX = 0;
        int blueY = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == RED) {
                    redX = i;
                    redY = j;
                    maze[i][j] = 0;
                } else if (maze[i][j] == BLUE) {
                    blueX = i;
                    blueY = j;
                    maze[i][j] = 0;
                }
            }
        }
        
        boolean[][][] isVisited = new boolean[3][maze.length][maze[0].length];
        isVisited[RED][redX][redY] = true;
        isVisited[BLUE][blueX][blueY] = true;
        
        dfs(redX, redY, blueX, blueY, isVisited, 0);
        if (!isAllArrive) {
            answer = 0;
        }
        return answer;
    }

    private void dfs(int redX, int redY,
                     int blueX, int blueY,
                     boolean[][][] isVisited,
                     int time) {
        // System.out.println(redX + " " + redY + " " + blueX + " " + blueY + " ");
        
        if (isArrive(RED, redX, redY) && isArrive(BLUE, blueX, blueY)) {
            isAllArrive = true;
            answer = Math.min(answer, time);
            return;
        }

        if (isArrive(RED, redX, redY)) {
            for (int i = 0; i < 4; i++) {
                int nextBlueX = blueX + dirX[i];
                int nextBlueY = blueY + dirY[i];
                if (isIn(nextBlueX, nextBlueY) &&
                        !isVisited[BLUE][nextBlueX][nextBlueY] &&
                        maze[nextBlueX][nextBlueY] != WALL) {
                    if (nextBlueX != redX || nextBlueY != redY) {
                        isVisited[BLUE][nextBlueX][nextBlueY] = true;
                        dfs(redX, redY, nextBlueX, nextBlueY, isVisited, time + 1);
                        isVisited[BLUE][nextBlueX][nextBlueY] = false;
                    }
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                int nextRedX = redX + dirX[i];
                int nextRedY = redY + dirY[i];
                if (isIn(nextRedX, nextRedY) &&
                        !isVisited[RED][nextRedX][nextRedY] &&
                        maze[nextRedX][nextRedY] != WALL) {
                    if (isArrive(BLUE, blueX, blueY)) {
                        if (nextRedX != blueX || nextRedY != blueY) {
                            isVisited[RED][nextRedX][nextRedY] = true;
                            dfs(nextRedX, nextRedY, blueX, blueY, isVisited, time + 1);
                            isVisited[RED][nextRedX][nextRedY] = false;
                        }
                    } else {
                        for (int j = 0; j < 4; j++) {
                            int nextBlueX = blueX + dirX[j];
                            int nextBlueY = blueY + dirY[j];
                            if (isIn(nextBlueX, nextBlueY) &&
                                    !isVisited[BLUE][nextBlueX][nextBlueY] &&
                                    maze[nextBlueX][nextBlueY] != WALL) {
                                if ((nextRedX == blueX && nextRedY == blueY) && (nextBlueX == redX && nextBlueY == redY)) {
                                    continue;
                                }
                                if (nextRedX == nextBlueX && nextRedY == nextBlueY) {
                                    continue;
                                }
                                isVisited[RED][nextRedX][nextRedY] = true;
                                isVisited[BLUE][nextBlueX][nextBlueY] = true;
                                dfs(nextRedX, nextRedY, nextBlueX, nextBlueY, isVisited, time + 1);
                                isVisited[BLUE][nextBlueX][nextBlueY] = false;
                                isVisited[RED][nextRedX][nextRedY] = false;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isArrive(int color, int x, int y) {
        if (color == RED) {
            return maze[x][y] == RED_HOLE;
        } else if (color == BLUE) {
            return maze[x][y] == BLUE_HOLE;
        }
        return false;
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x < maze.length && 0 <= y && y < maze[0].length;
    }
}