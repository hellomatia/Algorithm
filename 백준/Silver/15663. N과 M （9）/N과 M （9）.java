import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static int[] nums;
    private static boolean[] visited;
    private static Set<String> strings = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        findSequence(new StringBuilder(), 0);
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initNAndM();
        initNums();
    }

    private void initNAndM() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    private void initNums() throws IOException {
        nums = new int[N];
        visited = new boolean[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
    }

    private void findSequence(StringBuilder sb, int count) {
        if (count == M) {
            strings.add(sb.toString());
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            findSequence(new StringBuilder(sb).append(nums[i]).append(" "), count + 1);
            visited[i] = false;
        }
    }

    private void printResult() throws IOException {
        for (String result : strings) {
            bw.write(result + "\n");
        }
    }
}
