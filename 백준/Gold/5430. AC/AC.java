import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int T;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < T; i++) {
            printAns(calcAns());
        }
    }

    private void init() throws IOException {
        T = Integer.parseInt(bf.readLine());
    }

    private String calcAns() throws IOException {
        String command = bf.readLine();
        Deque<String> dq = new ArrayDeque<>();

        int n = Integer.parseInt(bf.readLine());
        String arrayString = bf.readLine();
        arrayString = arrayString.replace("[", "");
        arrayString = arrayString.replace("]", "");

        for (String num : arrayString.split(",")) {
            if(num.isEmpty()) continue;
            dq.offer(num);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean reverse = false;
        for (String c : command.split("")) {
            if (c.equals("R")) {
                reverse = !reverse;
            } else {
                if (dq.isEmpty()) {
                    return "error";
                } else if (reverse) {
                    dq.pollLast();
                } else {
                    dq.pollFirst();
                }
            }
        }

        int size = dq.size();
        if (size == 0) {
            return "[]";
        }

        for (int i = 0; i < size; i++) {
            if (reverse) {
                sb.append(dq.pollLast()).append(",");
            } else {
                sb.append(dq.pollFirst()).append(",");
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}
