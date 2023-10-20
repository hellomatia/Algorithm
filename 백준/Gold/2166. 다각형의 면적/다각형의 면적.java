import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Point {
    long x;
    long y;
    Point(List<Long> point) {
        this.x = point.get(0);
        this.y = point.get(1);
    }
}

public class Main {
    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static List<Point> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calculationArea());
    }

    private void init() throws IOException {
        initN();
        initPoints();
    }

    private void initN() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private void initPoints() throws IOException {
        for (int i = 0; i < N; i++) {
            points.add(new Point(inputPoint()));
        }
        points.add(points.get(0));
    }
    private List<Long> inputPoint() throws IOException {
        return Arrays.stream(bf.readLine().split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    private double calculationArea() {
        int index = 0;
        double area = 0;
        while (index != N) {
            area += (points.get(index).x * points.get(index + 1).y) - (points.get(index).y * points.get(index + 1).x);
            index++;
        }
        area = Math.abs(area /= 2);
        return (double) Math.round(area * 10) / 10.0;
    }

    private void printResult(double area) throws IOException {
        String result = String.format("%.1f", area);
        bw.write(result);
        bw.flush();
        bw.close();
    }
}