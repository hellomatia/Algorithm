import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;
    private static int[] pageCount;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcResult();
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        n = Integer.parseInt(bf.readLine());
        pageCount = new int[10];
    }

    private void calcResult() {
        int start = 1;
        int page = n;
        int point = 1;
        while (start <= page) {
            while (page % 10 != 9 && start <= page) {
                correction(page, point);
                page--;
            }
            if (page < start) {
                break;
            }
            while (start % 10 != 0 && start <= page) {
                correction(start, point);
                start++;
            }

            start /= 10;
            page /= 10;

            for (int i = 0; i < 10; i++) {
                pageCount[i] += (page - start + 1) * point;
            }

            point *= 10;
        }
    }

    private void correction(int num, int point) {
        while (num > 0) {
            pageCount[num % 10] += point;
            num /= 10;
        }
    }

    private void printResult() throws IOException {
        for (int count : pageCount) {
            bw.write(count + " ");
        }
        bw.write("\n");
    }
}
