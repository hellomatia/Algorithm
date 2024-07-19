import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private Map<Integer, Integer> parent;
    private Map<Integer, Integer> robot;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        parent = new HashMap<>();
        robot = new HashMap<>();
    }

    private String calcAns() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String command = st.nextToken();
            if (command.equals("I")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            } else {
                int c = Integer.parseInt(st.nextToken());
                parent.putIfAbsent(c, c);
                c = find(c);
                robot.putIfAbsent(c, 1);
                sb.append(robot.get(c)).append("\n");
            }
        }
        return sb.toString();
    }

    private void union(int x, int y) {
        parent.putIfAbsent(x, x);
        parent.putIfAbsent(y, y);
        x = find(x);
        y = find(y);
        if (x < y) {
            parent.put(y, x);
            robot.put(x, robot.getOrDefault(x, 1) + robot.getOrDefault(y, 1));
            robot.put(y, 0);
        } else if (x > y) {
            parent.put(x, y);
            robot.put(y, robot.getOrDefault(x, 1) + robot.getOrDefault(y, 1));
            robot.put(x, 0);
        }
    }

    private int find(int x) {
        if (parent.get(x).equals(x)) {
            return x;
        }
        int result = find(parent.get(x));
        parent.put(x, result);
        return result;
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}
