import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] nums;

    static {
        nums = new int[10][5];
        nums[0] = new int[]{0, 0, 0, 0, 0};
        nums[1] = new int[]{1, 1, 1, 1, 1};
        nums[2] = new int[]{2, 4, 8, 6};
        nums[3] = new int[]{3, 9, 7, 1};
        nums[4] = new int[]{4, 6};
        nums[5] = new int[]{5, 5, 5, 5, 5};
        nums[6] = new int[]{6, 6, 6, 6, 6};
        nums[7] = new int[]{7, 9, 3, 1};
        nums[8] = new int[]{8, 4, 2, 6};
        nums[9] = new int[]{9, 1};
    }

    private int t;
    private int[][] dataAndComputers;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        t = Integer.parseInt(bf.readLine());
        dataAndComputers = new int[t][2];
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            dataAndComputers[i][0] = Integer.parseInt(st.nextToken());
            dataAndComputers[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    private String calcAns() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int a = dataAndComputers[i][0];
            int b = dataAndComputers[i][1];
            int lastDigit = a % 10;
            int cycleLength = nums[lastDigit].length;
            int index = (b == 0) ? 0 : ((b - 1) % cycleLength);
            int result = nums[lastDigit][index];
            sb.append(result == 0 ? 10 : result).append("\n");
        }

        return sb.toString();
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans);
        bw.flush();
    }
}