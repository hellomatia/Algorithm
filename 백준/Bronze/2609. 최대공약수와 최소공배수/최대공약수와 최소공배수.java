import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int num1, num2, max;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        calcMinMax();
        bw.flush();
        bw.close();
    }

    private void calcMinMax() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        num1 = Integer.parseInt(st.nextToken());
        num2 = Integer.parseInt(st.nextToken());
        max = Math.max(num1, num2);

        int commonDivisor = 1;
        for (int i = max; i >= 1; i--) {
            if (num1 % i == 0 && num2 % i == 0) {
                commonDivisor = i;
                break;
            }
        }

        int commonMultiple = num1;
        for (int i = 1; i <= num1 * num2; i++) {
            if (i % num1 == 0 && i % num2 == 0) {
                commonMultiple = i;
                break;
            }
        }

        printResult(commonDivisor);
        printResult(commonMultiple);
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
