import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private final static int[] dirX = {-1, 0, 1, 0};
    private final static int[] dirY = {0, -1, 0, 1};
    private final int r, c, k;
    private final char[][] map;
    private int ans;
    private boolean[][] visited;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        char[][] map = new char[r][c];
        for (int i = 0; i < r; i++) {
            String rowValue = bf.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = rowValue.charAt(j);
            }
        }
        new Main(map, k).solution();
    }

    private Main(char[][] map, int k) {
        this.map = map;
        r = map.length;
        c = map[0].length;
        this.k = k;
    }

    private void solution() throws IOException {
        printAns(calcAns());
    }


    private String calcAns() {
        visited = new boolean[r][c];
        visited[r - 1][0] = true;
        dfs(r - 1, 0, 1);
        return ans + "";
    }

    private void dfs(int x, int y, int count) {
        if (count == k) {
            if (x == 0 && y == c - 1) {
                ans++;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];
            if (isIn(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] == '.') {
                visited[nextX][nextY] = true;
                dfs(nextX, nextY, count + 1);
                visited[nextX][nextY] = false;
            }
        }
    }

    private boolean isIn(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}