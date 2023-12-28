import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int t;
    private static Map<String, Integer> costumeCategory;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        t = Integer.parseInt(bf.readLine());
        for (int testCase = 0; testCase < t; testCase++) {
            init();
            printResult(calcResult());
        }

        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        int n = Integer.parseInt(bf.readLine());
        costumeCategory = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String name = st.nextToken();
            String category = st.nextToken();
            if (costumeCategory.containsKey(category)) {
                costumeCategory.replace(category, costumeCategory.get(category) + 1);
            } else {
                costumeCategory.put(category, 2);
            }
        }
    }

    private long calcResult() {
        long result = 1;
        for (int count : costumeCategory.values()) {
            result *= count;
        }
        return result - 1;
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}
