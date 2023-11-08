import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int T;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int testCase = 0; testCase < T; testCase++) {
            String W = readLine();
            int K = Integer.parseInt(readLine());
            findMinMaxLength(W, K);

        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initT();
    }

    private void initT() throws IOException {
        T = Integer.parseInt(bf.readLine());
    }

    private String readLine() throws IOException {
        return bf.readLine();
    }

    private void findMinMaxLength(String W, int K) throws IOException {
        ArrayList<Integer>[] alphabetPositions = new ArrayList[26];
        for (int index = 0; index < alphabetPositions.length; index++) {
            alphabetPositions[index] = new ArrayList<>();
        }

        IntStream.range(0, W.length())
                .forEach(index -> alphabetPositions[W.charAt(index) - 'a'].add(index));

        int minLength = Integer.MAX_VALUE;
        int maxLength = Integer.MIN_VALUE;

        for (int index = 0; index < alphabetPositions.length; index++) {
            if (alphabetPositions[index].size() < K) {
                continue;
            }
            for (int i = 0; i <= alphabetPositions[index].size() - K; i++) {
                int length = alphabetPositions[index].get(i + K - 1) - alphabetPositions[index].get(i) + 1;
                minLength = Math.min(length, minLength);
                maxLength = Math.max(length, maxLength);
            }
        }

        printResult(minLength, maxLength);
    }

    private void printResult(int minLength, int maxLength) throws IOException {
        if (minLength == Integer.MAX_VALUE || maxLength == Integer.MIN_VALUE) {
            bw.write("-1" + "\n");
            return;
        }
        bw.write(minLength + " " + maxLength + "\n");
    }
}
