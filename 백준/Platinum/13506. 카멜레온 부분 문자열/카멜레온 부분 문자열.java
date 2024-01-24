import java.io.*;
import java.util.*;

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private String S;

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
        S = bf.readLine();
    }

    private String calcResult() {
        int[] table = calcTable();

        int maxSuffix = table[S.length() - 1];
        if (maxSuffix == 0) {
            return "-1";
        }

        while (maxSuffix != 0) {
            for (int i = 0; i < S.length() - 1; i++) {
                if (table[i] == maxSuffix) {
                    return S.substring(0, maxSuffix);
                }
            }
            maxSuffix = table[maxSuffix - 1];
        }

        return "-1";
    }

    private int[] calcTable() {
        int length = S.length();
        int[] table = new int[length];

        int j = 0;
        for (int i = 1; i < length; i++) {
            while (j > 0 && S.charAt(i) != S.charAt(j)) {
                j = table[j - 1];
            }
            if (S.charAt(i) == S.charAt(j)) {
                j++;
                table[i] = j;
            }
        }

        return table;
    }

    private void printResult(String result) throws IOException {
        bw.write(result + "\n");
    }
}
