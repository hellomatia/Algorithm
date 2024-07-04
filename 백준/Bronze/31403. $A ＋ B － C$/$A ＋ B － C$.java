import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int A;
    private int B;
    private int C;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        A = Integer.parseInt(bf.readLine());
        B = Integer.parseInt(bf.readLine());
        C = Integer.parseInt(bf.readLine());
    }

    private String calcAns() {
        StringBuilder sb = new StringBuilder();
        sb.append(A + B - C).append("\n");

        String stringNum = String.valueOf(A) + String.valueOf(B);
        int num = 0;
        for (int i = 0; i < stringNum.length(); i++) {
            num += stringNum.charAt(i) - '0';
            num *= 10;
        }
        num /= 10;
        sb.append(num - C);

        return sb.toString();
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans);
        bw.flush();
    }
}