import java.io.*;

public class Main {

    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bf.readLine());

        boolean odd = false;

        if (N % 2 == 1) odd = true;

        if (odd) {
            bw.write("SK");
        }
        if (!odd) {
            bw.write("CY");
        }


        bw.flush();
        bw.close();

    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}