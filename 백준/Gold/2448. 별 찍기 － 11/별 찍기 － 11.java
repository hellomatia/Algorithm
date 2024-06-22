import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private char[][] stars;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcAns(0, N - 1, N);
        printAns();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());

        stars = new char[N][N * 2 - 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(stars[i], ' ');
        }
    }

    private void calcAns(int x, int y, int count) {
        if (count == 3) {
            stars[x][y] =
            stars[x + 1][y - 1] =
            stars[x + 1][y + 1] =
            stars[x + 2][y - 2] =
            stars[x + 2][y - 1] =
            stars[x + 2][y] =
            stars[x + 2][y + 1] =
            stars[x + 2][y + 2] = '*';
            return;
        }

        int cut = count / 2;
        calcAns(x, y, cut);
        calcAns(x + cut, y - cut, cut);
        calcAns(x + cut, y + cut, cut);
    }

    private void printAns() throws IOException {
        for (int i = 0; i < stars.length; i++) {
            for (int j = 0; j < stars[i].length; j++) {
                bw.write(stars[i][j] + "");
            }
            bw.write('\n');
        }
        bw.flush();
    }
}
