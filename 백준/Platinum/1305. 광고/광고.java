import java.io.*;
import java.util.*;

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int L;
    private String str;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        L = Integer.parseInt(bf.readLine());
        str = bf.readLine();
    }

    private int calcResult() {
        int[] pi = failTable();
        return L - pi[L - 1];
    }

    private int[] failTable() {
        int[] pi = new int[L];
        int index = 0;

        for (int i = 1; i < L; i++) {
            while (index > 0 && str.charAt(i) != str.charAt(index)) {
                index = pi[index - 1];
            }
            if (str.charAt(i) == str.charAt(index)) {
                pi[i] = ++index;
            }
        }

        return pi;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}