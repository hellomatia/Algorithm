import java.io.*;
import java.util.*;
class Color {
    int x;
    int y;
    Color(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static char[][] colorMap;
    static boolean[][] visitedNormal;
    static boolean[][] visitedProtanomaly;
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};
    public void solution() throws IOException {

        N = Integer.parseInt(bf.readLine());

        colorMap = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = bf.readLine();
            for (int j = 0; j < N; j++) {
                char ch = str.charAt(j);
                colorMap[i][j] = ch;
            }
        }
        int countNormal = 0;
        visitedNormal = new boolean[N][N];
        int countProtanomaly = 0;
        visitedProtanomaly = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visitedNormal[i][j]) {
                    getNormalVisionColorAreaCount(i, j);
                    countNormal++;
                }

                if (!visitedProtanomaly[i][j]) {
                    getProtanomalyVisionColorAreasCount(i, j);
                    countProtanomaly++;
                }

            }
        }

        bw.write(countNormal + " ");
        bw.write(countProtanomaly + "\n");

        bw.flush();
        bw.close();
    }

    public void getNormalVisionColorAreaCount(int x, int y) {
        char color = colorMap[x][y];

        Queue<Color> colors = new LinkedList<>();

        visitedNormal[x][y] = true;
        colors.offer(new Color(x, y));

        while (!colors.isEmpty()) {
            Color now = colors.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || N <= nextX || N <= nextY ||
                        visitedNormal[nextX][nextY] || colorMap[nextX][nextY] != color) continue;

                visitedNormal[nextX][nextY] = true;
                colors.offer(new Color(nextX, nextY));
            }

        }
    }

    public void getProtanomalyVisionColorAreasCount(int x, int y) {
        char color1 = colorMap[x][y];
        char color2;
        if (color1 == 'R' || color1 == 'G') {
            color1 = 'B';
            color2 = 'B';
        } else {
            color1 = 'R';
            color2 = 'G';
        }

        Queue<Color> colors = new LinkedList<>();

        visitedProtanomaly[x][y] = true;
        colors.offer(new Color(x, y));

        while (!colors.isEmpty()) {
            Color now = colors.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || N <= nextX || N <= nextY ||
                        visitedProtanomaly[nextX][nextY] || colorMap[nextX][nextY] == color1 || 
                        colorMap[nextX][nextY] == color2) continue;

                visitedProtanomaly[nextX][nextY] = true;
                colors.offer(new Color(nextX, nextY));
            }

        }
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}