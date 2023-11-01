import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

class Number {
    int number;
    int count;

    Number(int number, int count) {
        this.number = number;
        this.count = count;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int R, C;
    private static int r,c,k;
    private static int[][] map;
    private static PriorityQueue<Number> pq = new PriorityQueue<>((o1, o2) -> {
        if (o1.count == o2.count) {
            return o1.number - o2.number;
        }
        return o1.count - o2.count;
    });


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        int time = -1;
        while (time++ <= 100) {
            if (r <= R - 1 && c <= C - 1) {
                if (map[r][c] == k) {
                    break;
                }
            }
            calculationRC();
        }

        if (time > 100) {
            time = -1;
        }
        printResult(time);
    }

    private void init() throws IOException {
        initRCK();
        initMap();
    }

    private void initRCK() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());
        R = 3;
        C = 3;
    }

    private void initMap() throws IOException {
        map = new int[R][C];
        for (int row = 0; row < R; row++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int col = 0; col < C; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void calculationRC() {
        if (R >= C) {
            calculationR();
        } else if (R < C) {
            calculationC();
        }
    }

    private void calculationR() {
        int maxCol = C;
        ArrayList<Integer>[] rowValue = new ArrayList[R];
        for (int row = 0; row < R; row++) {
            int[] numberCount = new int[101];
            for (int col = 0; col < C; col++) {
                numberCount[map[row][col]]++;
            }
            rowValue[row] = toList(numberCount);
            maxCol = Math.max(maxCol, rowValue[row].size());
        }

        C = maxCol;
        if (C > 100) {
            C = 100;
        }
        map = new int[R][C];
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < rowValue[row].size(); col++) {
                if (col >= 100) {
                    continue;
                }
                map[row][col] = rowValue[row].get(col);
            }
        }
    }

    private ArrayList<Integer> toList(int[] numberCount) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int number = 1; number < 101; number++) {
            if (numberCount[number] == 0) {
                continue;
            }
            pq.offer(new Number(number, numberCount[number]));
        }
        while (!pq.isEmpty()) {
            Number number = pq.poll();
            list.add(number.number);
            list.add(number.count);
        }
        return list;
    }

    private void calculationC() {
        int maxRow = R;
        ArrayList<Integer>[] colValue = new ArrayList[C];
        for (int col = 0; col < C; col++) {
            int[] numberCount = new int[101];
            for (int row = 0; row < R; row++) {
                numberCount[map[row][col]]++;
            }
            colValue[col] = toList(numberCount);
            maxRow = Math.max(maxRow, colValue[col].size());
        }

        R = maxRow;
        if (R > 100) {
            R = 100;
        }
        map = new int[R][C];
        for (int col = 0; col < C; col++) {
            for (int row = 0; row < colValue[col].size(); row++) {
                if (row >= 100) {
                    continue;
                }
                map[row][col] = colValue[col].get(row);
            }
        }
    }

    private void printMap() {
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                System.out.print(map[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}