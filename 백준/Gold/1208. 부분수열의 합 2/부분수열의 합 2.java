import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, S;
    private static int[] nums;
    private static List<Integer> leftSums = new ArrayList<>();
    private static List<Integer> rightSums = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        initNAndS();
        initNums();
        printResult(calcSumResultSCount());
        bw.flush();
        bw.close();
    }

    private void initNAndS() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
    }

    private void initNums() throws  IOException {
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    private long calcSumResultSCount() {
        calcSums(0, N/2, 0, leftSums);
        calcSums(N/2, N, 0, rightSums);

        Collections.sort(leftSums);
        Collections.sort(rightSums);

        long count = 0;
        int left = 0;
        int right = rightSums.size() - 1;
        while (left < leftSums.size() && right >= 0) {
            int leftValue = leftSums.get(left);
            int rightValue = rightSums.get(right);

            if (leftValue + rightValue == S) {
                int leftCount = 0;
                int rightCount = 0;
                while (left < leftSums.size() && leftValue == leftSums.get(left)) {
                    leftCount++;
                    left++;
                }
                while (0 <= right && rightValue == rightSums.get(right)) {
                    rightCount++;
                    right--;
                }
                count += ((long) leftCount * rightCount);
            }
            if (leftValue + rightValue < S) {
                left++;
            }
            if (leftValue + rightValue > S) {
                right--;
            }
        }

        if (S == 0 && count > 0) {
            count--;
        }

        return count;
    }

    private void calcSums(int start, int end, int sum, List<Integer> sums) {
        if (start == end) {
            sums.add(sum);
            return;
        }

        calcSums(start + 1, end, sum, sums);
        calcSums(start + 1, end, sum + nums[start], sums);
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}
