import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
class Point {
    long x;
    long y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

class Line {
    Point start;
    Point end;

    Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
}

class Vector {
    Point point1;
    Point point2;

    Vector(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static Line line1;
    private static Line line2;
    private static int[] values = new int[2];

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calculationValues();
        printResult();
    }

    private void init() throws IOException {
        initLines();
    }

    private void initLines() throws IOException {
        line1 = inputLine();
        line2 = inputLine();
    }

    private Line inputLine() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        Point start = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point end = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        return new Line(start, end);
    }

    private void calculationValues() {
        values[0] = calculationCcw(line1.start, line1.end, line2.start) *
                calculationCcw(line1.start, line1.end, line2.end);
        values[1] = calculationCcw(line2.start, line2.end, line1.start) *
                calculationCcw(line2.start, line2.end, line1.end);
    }

    private int calculationCcw(Point point1, Point point2, Point point3) {
        long value = (point1.x * point2.y + point2.x * point3.y + point3.x * point1.y)
                - (point2.x * point1.y + point3.x * point2.y + point1.x * point3.y);
        if (value > 0) {
            return 1;
        }
        if (value < 0) {
            return -1;
        }
        return 0;
    }

    private boolean isConnect() {
        if (values[0] == 0 && values[1] == 0) {
            if (Math.min(line1.start.x, line1.end.x) <= Math.max(line2.start.x, line2.end.x)
                    && Math.min(line2.start.x, line2.end.x) <= Math.max(line1.start.x, line1.end.x)
                    && Math.min(line1.start.y, line1.end.y) <= Math.max(line2.start.y, line2.end.y)
                    && Math.min(line2.start.y, line2.end.y) <= Math.max(line1.start.y, line1.end.y)) {
                return true;
            }
            return false;
        }
        if (values[0] <= 0 && values[1] <= 0) {
            return true;
        }
        return false;
    }

    private void printResult() throws IOException {
        if (isConnect()) {
            bw.write("1");
        } else {
            bw.write("0");
        }
        bw.flush();
        bw.close();
    }
}