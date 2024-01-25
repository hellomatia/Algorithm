import java.io.*;
import java.util.*;

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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
        str = bf.readLine();
    }

    private int calcResult() {
        int result = 0;

        for (int start = 0; start < str.length(); start++) {
            int index = 0;
            String subString = str.substring(start);
            int[] table = new int[subString.length()];

            for (int i = 1; i < subString.length(); i++) {
                while (index > 0 && subString.charAt(i) != subString.charAt(index)) {
                    index = table[index - 1];
                }
                if (subString.charAt(i) == subString.charAt(index)) {
                    table[i] = ++index;
                    result = Math.max(index, result);
                }
            }
        }

        return result;
    }



    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
