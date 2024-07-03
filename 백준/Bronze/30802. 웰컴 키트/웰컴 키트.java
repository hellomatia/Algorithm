import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[] numOfTShirts;
    private int T;
    private int P;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());

        numOfTShirts = new int[6];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 6; i++) {
            numOfTShirts[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
    }

    private String calcAns() {
        StringBuilder sb = new StringBuilder();

        int tCount = 0;
        for (int i = 0; i < 6; i++) {
            tCount += numOfTShirts[i] / T;
            if (numOfTShirts[i] % T != 0) {
                tCount += 1;
            }
        }

        int panBundleCount = N / P;
        int panCount = N % P;

        sb.append(tCount).append("\n");
        sb.append(panBundleCount).append(" ");
        sb.append(panCount).append("\n");

        return sb.toString();
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans);
        bw.flush();
    }
}