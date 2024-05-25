import java.io.*;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private final int G;

    public static void main(String[] args) throws IOException {
        int G = Integer.parseInt(bf.readLine());
        new Main(G).solution();
    }

    private Main(int G) {
        this.G = G;
    }

    private void solution() throws IOException {
        printAns(calcAns());
    }

    private String calcAns() {
        StringBuilder ans = new StringBuilder();
        int start = 1;
        int end = 1;
        int diff = 0;
        do {
            if (diff < G) {
                start++;
            } else {
                end++;
            }
            if (diff == G) {
                ans.append(start).append("\n");
            }
            diff = start * start - end * end;
        } while (start - end != 1 || diff <= G);
        if (ans.length() == 0) {
            return "-1";
        }
        return ans.toString();
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}