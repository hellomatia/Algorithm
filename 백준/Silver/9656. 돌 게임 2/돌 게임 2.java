import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solution() throws IOException {

        int N = Integer.parseInt(bf.readLine());

        N %= 4;
        N %= 2;

        if (N == 0) {
            bw.write("SK");
        } else {
            bw.write("CY");
        }

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}