import java.io.*;
import java.util.*;

class Line implements Comparable<Line> {
    int start;
    int end;

    Line(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Line o) {
        if (this.end == o.end) {
            return this.start - o.start;
        }
        return this.end - o.end;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;
    private static Line[] lines;
    private static int length;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        n = Integer.parseInt(bf.readLine());
        lines = new Line[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            lines[i] = new Line(Math.min(num1, num2), Math.max(num1, num2));
        }
        length = Integer.parseInt(bf.readLine());
        Arrays.sort(lines);
    }

    private int calcResult() {
        int result = 0;
        PriorityQueue<Line> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.start - o2.start;
        });
        for (Line now : lines) {
            if (now.end - now.start > length) {
                continue;
            }
            pq.offer(now);
            while (!pq.isEmpty()) {
                if (now.end - pq.peek().start > length) {
                    pq.poll();
                } else {
                    break;
                }
            }
            result = Math.max(pq.size(), result);
        }
        return result;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
