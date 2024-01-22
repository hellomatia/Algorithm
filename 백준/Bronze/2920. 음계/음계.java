import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static String music;

    private static final String ASCENDING = "1 2 3 4 5 6 7 8";
    private static final String DESCENDING = "8 7 6 5 4 3 2 1";

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
        music = bf.readLine();
    }

    private String calcResult() {
        if (music.equals(ASCENDING)) {
            return "ascending";
        }
        if (music.equals(DESCENDING)) {
            return "descending";
        }
        return "mixed";
    }

    private void printResult(String result) throws IOException {
        bw.write(result + "\n");
    }
}
