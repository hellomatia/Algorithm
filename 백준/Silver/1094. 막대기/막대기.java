import java.io.*;

public class Main {
    private static final int STICK_LEN = 64;

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private final int target;

    public static void main(String[] args) throws IOException {
        int len = Integer.parseInt(bf.readLine());
        new Main(len).solution();
    }

    private Main(int len) {
        this.target = len;
    }

    private void solution() throws IOException {
        printAns(calcAns());
    }

    private String calcAns() {
        int ans = 0;
        int now = target;
        int len = STICK_LEN;
        while (len > 0) {
            if (len > now) {
                len /= 2;

            } else {
                now -= len;
                ans++;
            }
        }
        return String.valueOf(ans);
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}