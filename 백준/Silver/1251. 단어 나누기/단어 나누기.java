import java.util.*;
import java.io.*;

public class Main {
    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private String str;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        str = bf.readLine();
    }

    private String calcAns() {
        PriorityQueue<String> pq = new PriorityQueue<>();
        for (int i = 1; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                pq.add(calcWord(i, j));
            }
        }
        return pq.poll();
    }

    private String calcWord(int index1, int index2) {
        StringBuilder sb = new StringBuilder();
        sb.append(reverseWord(str.substring(0, index1)));
        sb.append(reverseWord(str.substring(index1, index2)));
        sb.append(reverseWord(str.substring(index2, str.length())));

        return sb.toString();
    }

    private String reverseWord(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}