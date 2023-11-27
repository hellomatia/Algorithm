import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int A;
    private static int[] nums;
    private static int maxCount;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        initA();
        initNums();
        findMaxIncrease();
        printResult();
        bw.flush();
        bw.close();
    }

    private void initA() throws IOException {
        A = Integer.parseInt(bf.readLine());
    }

    private void initNums() throws IOException {
        nums = new int[A];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < A; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    private void findMaxIncrease() {
        int[] lis = new int[A];
        Arrays.fill(lis, Integer.MAX_VALUE);

        for (int i = 0; i < A; i++) {
            lis[binarySearch(lis, nums[i])] = nums[i];
        }

        for (int i = A - 1; i >= 0; i--) {
            if (lis[i] != Integer.MAX_VALUE) {
                maxCount = i + 1;
                return;
            }
        }
    }

    private int binarySearch(int[] lis, int find) {
        int start = 0;
        int end = A - 1;

        while (start < end) {
            int mid = (end + start) / 2;
            if (find > lis[mid]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }

    private void printResult() throws IOException {
        bw.write(maxCount + "\n");
    }
}
