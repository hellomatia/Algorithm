import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private final TreeSet<Problem> treeSet = new TreeSet<>();
    private final Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < M; i++) {
            calcAns();
        }
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            treeSet.add(new Problem(num, level));
            map.put(num, level);
        }

        M = Integer.parseInt(bf.readLine());
    }

    private void calcAns() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        String command = st.nextToken();
        if (command.equals("recommend")) {
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                printAns(treeSet.last().num + "");
            } else {
                printAns(treeSet.first().num + "");
            }
        } else if (command.equals("solved")) {
            int num = Integer.parseInt(st.nextToken());
            treeSet.remove(new Problem(num, map.get(num)));
            map.remove(num);
        } else if (command.equals("add")) {
            int num = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            treeSet.add(new Problem(num, level));
            map.put(num, level);
        }
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }

    public static class Problem implements Comparable<Problem> {
        private final int num;
        private final int level;

        public Problem(int num, int level) {
            this.num = num;
            this.level = level;
        }

        public int compareTo(Problem o) {
            if (level - o.level == 0) {
                return num - o.num;
            } else {
                return level - o.level;
            }
        }
    }
}