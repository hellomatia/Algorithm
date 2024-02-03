import java.io.*;
import java.util.*;

class CainCalendar {
    int year1;
    int year2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CainCalendar that = (CainCalendar) o;
        return year1 == that.year1 && year2 == that.year2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year1, year2);
    }
}


public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int M, N, x, y;
    private HashSet<CainCalendar> set;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        int T = Integer.parseInt(bf.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            init();
            printResult(calcResult());
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
    }

    private int calcResult() {
        int lcm = M * N / gcd(M, N);
        int n = 0;
        int result = -1;
        while (n * M < lcm) {
            if ((n * M + x - y) % N == 0) {
                result = n * M + x;
                break;
            }
            n++;
        }
        return result;
    }

    private int gcd(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        return gcd(num2, num1 % num2);
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
