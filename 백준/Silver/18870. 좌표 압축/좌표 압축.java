import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static Integer[] nums;
    private static Integer[] sortNums;
    private static HashMap<Integer, Integer> map;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        nums = new Integer[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        initSortNums();
        initMap();
    }

    private void initSortNums() {
        sortNums = new HashSet<>(Arrays.asList(nums))
                .toArray(new Integer[0]);
        Arrays.sort(sortNums);
    }

    private void initMap() {
        map = new HashMap<>();
        for (int index = 0; index < sortNums.length; index++) {
            int num = sortNums[index];
            map.put(num, index);
        }
    }

    private void printResult() throws IOException {
        for (int num : nums) {
            bw.write(map.get(num) + " ");
        }
    }
}
