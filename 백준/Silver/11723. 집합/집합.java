import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int n;
    private static Set<Integer> set;
    private static Set<Integer> allSet = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            readCommand();
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initN();
    }

    private void initN() throws IOException {
        n = Integer.parseInt(bf.readLine());
    }

    private void readCommand() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        String command = st.nextToken();
        if (command.equals("all")) {
            set = new HashSet<>(allSet);
            return;
        }
        if (command.equals("empty")) {
            set = new HashSet<>();
            return;
        }
        if (command.equals("add")) {
            int num = Integer.parseInt(st.nextToken());
            set.add(num);
            return;
        }
        if (command.equals("remove")) {
            int num = Integer.parseInt(st.nextToken());
            set.remove(num);
            return;
        }
        if (command.equals("toggle")) {
            int num = Integer.parseInt(st.nextToken());
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
            return;
        }
        if (command.equals("check")) {
            int num = Integer.parseInt(st.nextToken());
            if (set.contains(num)) {
                printResult("1");
            } else {
                printResult("0");
            }
            return;
        }
    }

    private void printResult(String num) throws IOException {
        bw.write(num + "\n");
    }
}