import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static String game;
    private static HashSet<String> playerNames;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(getResult());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initNAndGame();
        initPlayerNames();
    }

    private void initNAndGame() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        game = st.nextToken();
    }

    private void initPlayerNames() throws IOException {
        playerNames = new HashSet<>();

        for (int count = 0; count < N; count++) {
            playerNames.add(bf.readLine());
        }
    }

    private int getResult() {
        if (game.equals("Y")) {
            return playerNames.size();
        } else if (game.equals("F")) {
            return playerNames.size() / 2;
        } else if (game.equals("O")) {
            return playerNames.size() / 3;
        }
        return -1;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
