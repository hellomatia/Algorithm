import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static Map<Integer, Integer> playerBasedNums;
    private static Map<Integer, Integer> numBasedPlayers;
    private static int[] scores;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcScores();
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        playerBasedNums = new HashMap<>();
        numBasedPlayers = new HashMap<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int player = 0; player < N; player++) {
            int num = Integer.parseInt(st.nextToken());
            playerBasedNums.put(player, num);
            numBasedPlayers.put(num, player);
        }
    }

    private void calcScores() {
        scores = new int[N];
        for (int player = 0; player < N; player++) {
            int num = playerBasedNums.get(player);
            for (int otherNum = num * 2; otherNum <= 1_000_000; otherNum += num) {
                if (numBasedPlayers.containsKey(otherNum)) {
                    scores[player]++;
                    scores[numBasedPlayers.get(otherNum)]--;
                }
            }
        }
    }

    private void printResult() throws IOException {
        String[] results = Arrays.stream(scores)
                                .mapToObj(String::valueOf)
                                .toArray(String[]::new);
        bw.write(String.join(" ", results) + "\n");
    }
}