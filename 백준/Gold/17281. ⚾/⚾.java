import java.io.*;
import java.util.*;

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private final int OUT = 0;
    private final int ANTA = 1;
    private final int TWORU = 2;
    private final int THREERU = 3;
    private final int HOMERUN = 4;


    private int N;
    private int[][] baseBall;
    private int[] player;
    private boolean[] visited;
    private int result;

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        init();
        calcResult();
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());

        baseBall = new int[N][9];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 9; j++) {
                baseBall[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void calcResult() {
        visited = new boolean[9];
        player = new int[9];

        visited[3] = true;
        player[3] = 0;
        pickBaseballPlayer(1);
    }

    private void pickBaseballPlayer(int count) {
        if (count == 9) {
            result = Math.max(result, simulation());
        }

        for (int i = 0; i < 9; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            player[i] = count;
            pickBaseballPlayer(count + 1);
            visited[i] = false;
        }

    }

    private int simulation() {
        int now = 0;
        int score = 0;
        for (int i = 0; i < N; i++) {
            boolean player1 = false;
            boolean player2 = false;
            boolean player3 = false;

            int outCount = 0;

            while (outCount < 3) {
                int nowPlayer = player[now];
                if (baseBall[i][nowPlayer] == OUT) {
                    outCount++;
                } else if (baseBall[i][nowPlayer] == ANTA) {
                    if (player3) {
                        score++;
                        player3 = false;
                    }
                    if (player2) {
                        player2 = false;
                        player3 = true;
                    }
                    if (player1) {
                        player1 = false;
                        player2 = true;
                    }
                    player1 = true;
                } else if (baseBall[i][nowPlayer] == TWORU) {
                    if (player3) {
                        score++;
                        player3 = false;
                    }
                    if (player2) {
                        score++;
                        player2 = false;
                    }
                    if (player1) {
                        player1 = false;
                        player3 = true;
                    }
                    player2 = true;
                } else if (baseBall[i][nowPlayer] == THREERU) {
                    if (player3) {
                        score++;
                        player3 = false;
                    }
                    if (player2) {
                        score++;
                        player2 = false;
                    }
                    if (player1) {
                        score++;
                        player1 = false;
                    }
                    player3 = true;
                } else if (baseBall[i][nowPlayer] == HOMERUN) {
                    if (player3) {
                        score++;
                        player3 = false;
                    }
                    if (player2) {
                        score++;
                        player2 = false;
                    }
                    if (player1) {
                        score++;
                        player1 = false;
                    }
                    score++;
                }
                now = (now + 1) % 9;
            }
        }
        return score;
    }

    private void printResult() throws IOException {
        bw.write(result + "\n");
    }
}