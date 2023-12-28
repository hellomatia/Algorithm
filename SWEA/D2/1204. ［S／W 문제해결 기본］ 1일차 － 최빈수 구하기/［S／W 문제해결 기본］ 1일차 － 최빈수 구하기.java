import java.io.*;
import java.util.*;

public class Solution {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Map<Integer, Integer> scoreKeyCountValue;

    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private void solution() throws IOException {
        int testCase = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= testCase; t++) {
            init();
            printResult(t, calcResult());
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        bf.readLine();
        scoreKeyCountValue = new HashMap<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 1000; i++) {
            int score = Integer.parseInt(st.nextToken());
            if (scoreKeyCountValue.containsKey(score)) {
                scoreKeyCountValue.replace(score, scoreKeyCountValue.get(score) + 1);
            } else {
                scoreKeyCountValue.put(score, 1);
            }
        }
    }

    private int calcResult() {
        int maxCount = 0;
        int result = 0;
        for (int score : scoreKeyCountValue.keySet()) {
            int count = scoreKeyCountValue.get(score);
            if (count > maxCount) {
                maxCount = scoreKeyCountValue.get(score);
                result = score;
            } else if (count == maxCount && score > result) {
                result = score;
            }
        }
        return result;
    }

    private void printResult(int testCase, int result) throws IOException {
        bw.write("#"+testCase + " " + result + "\n");
    }
}
