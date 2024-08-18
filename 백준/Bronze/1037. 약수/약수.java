import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int T;
    private int max = Integer.MIN_VALUE;
    private int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        T = Integer.parseInt(bf.readLine());
        
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < T; i++) {
            int num = Integer.parseInt(st.nextToken());
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
    }
    
    private String calcAns() {
        return max * min + "";
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}
