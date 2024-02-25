import java.io.*;
import java.util.*;

public class Main {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[] arr;
    private StringBuilder sb;

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        init();
        calcResult();
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        sb = new StringBuilder();
    }

    private void calcResult() {
        if (!nextPermutation(arr)) sb.append(-1);
        else {
            for (int num : arr) {
                sb.append(num).append(" ");
            }
        }
    }

    private boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) i--;
        if (i <= 0) return false;

        int j = arr.length - 1;

        while (arr[j] <= arr[i - 1]) j--;
        swap(arr, i - 1, j);

        j = arr.length - 1;
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
        return true;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private void printResult() throws IOException {
        bw.write(sb.toString() + "\n");
    }
}