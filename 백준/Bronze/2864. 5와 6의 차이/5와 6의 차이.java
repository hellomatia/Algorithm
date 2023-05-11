import java.io.*;
import java.util.*;

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        String num1 = st.nextToken();
        String num2 = st.nextToken();

        num1 = num1.replaceAll("6", "5");
        num2 = num2.replaceAll("6", "5");
        int minNum = Integer.parseInt(num1) + Integer.parseInt(num2);

        num1 = num1.replaceAll("5", "6");
        num2 = num2.replaceAll("5", "6");
        int maxNum = Integer.parseInt(num1) + Integer.parseInt(num2);

        bw.write(String.valueOf(minNum));
        bw.write(" ");
        bw.write(String.valueOf(maxNum));

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}

