import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Main {
    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private BigInteger n, m;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = new BigInteger(st.nextToken());
        m = new BigInteger(st.nextToken());
    }

    private String calcAns() throws IOException {
        StringBuilder ans = new StringBuilder();
        ans.append(n.divide(m)).append('\n');
        ans.append(n.remainder(m));
        return ans.toString();
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}