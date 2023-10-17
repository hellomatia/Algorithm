import java.io.*;
import java.util.Arrays;

public class Main {
    static char[][] stars;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        int n = Integer.parseInt(bf.readLine());

        stars = new char[n][n];
        for (char[] row : stars) {
            Arrays.fill(row, ' ');
        }

        drawStars(n, 0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(stars[i]).append('\n');
        }

        bw.write(sb + "\n");
        bw.flush();
        bw.close();
    }

    static void drawStars(int n, int x, int y) {
        if (n == 1) {
            stars[x][y] = '*';
            return;
        }

        int size = n / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) continue; // 가운데 공백
                drawStars(size, x + i * size, y + j * size);
            }
        }
    }
}