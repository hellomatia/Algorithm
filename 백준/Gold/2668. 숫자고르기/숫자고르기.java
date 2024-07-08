import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[] nums;
    private boolean[] visited;
    private List<Integer> answer;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());

        nums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(bf.readLine());
        }
    }

    private String calcAns() {
        visited = new boolean[N + 1];
        answer = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append('\n');
        for (int ans : answer) {
            sb.append(ans).append('\n');
        }
        return sb.toString();
    }

    private void dfs(int now, int start) {
        if (!visited[nums[now]]) {
            visited[nums[now]] = true;
            dfs(nums[now], start);
            visited[nums[now]] = false;
        }

        if (nums[now] == start) {
            answer.add(start);
        }
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}
