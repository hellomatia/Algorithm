import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static long T;
    private static int ACount;
    private static int BCount;
    private static long[] A;
    private static long[] B;
    private static Map<Long, Long> ACombinationCount;
    private static Map<Long, Long> BCombinationCount;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        calcCombinationCount();
        printResult(calcResult());
        //printMap();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        T = Long.parseLong(bf.readLine());

        ACount = Integer.parseInt(bf.readLine());
        A = new long[ACount + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= ACount; i++) {
            A[i] += A[i - 1] + Long.parseLong(st.nextToken());
        }

        BCount = Integer.parseInt(bf.readLine());
        B = new long[BCount + 1];
        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= BCount; i++) {
            B[i] += B[i - 1] + Long.parseLong(st.nextToken());
        }
    }

    private void calcCombinationCount() {
        ACombinationCount = new HashMap<>();
        for (int count = 1; count <= ACount; count++) {
            for (int start = 0; start + count <= ACount; start++) {
                long combinationNum = A[start + count] - A[start];
                if (!ACombinationCount.containsKey(combinationNum)) {
                    ACombinationCount.put(combinationNum, 1L);
                } else {
                    ACombinationCount.replace(combinationNum, ACombinationCount.get(combinationNum) + 1);
                }
            }
        }

        BCombinationCount = new HashMap<>();
        for (int count = 1; count <= BCount; count++) {
            for (int start = 0; start + count <= BCount; start++) {
                long combinationNum = B[start + count] - B[start];
                if (!BCombinationCount.containsKey(combinationNum)) {
                    BCombinationCount.put(combinationNum, 1L);
                } else {
                    BCombinationCount.replace(combinationNum, BCombinationCount.get(combinationNum) + 1);
                }
            }
        }
    }

    private long calcResult() {
        long result = 0;

        for (Long ACombinationNum : ACombinationCount.keySet()) {
            Long BCombinationNum = T - ACombinationNum;
            if (BCombinationCount.containsKey(BCombinationNum)) {
                result += ACombinationCount.get(ACombinationNum) * BCombinationCount.get(BCombinationNum);
            }
        }

        return result;
    }

    private void printMap() {
        System.out.print("ACombinationCount: ");
        for (Long ACombinationNum : ACombinationCount.keySet()) {
            System.out.print(ACombinationNum + " ");
        }
        System.out.println();
        System.out.print("BCombinationCount: ");
        for (Long BCombinationNum : BCombinationCount.keySet()) {
            System.out.print(BCombinationNum + " ");
        }
        System.out.println();
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}
