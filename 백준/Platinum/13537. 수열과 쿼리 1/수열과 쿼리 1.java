import java.io.*;
import java.util.*;

public class Main {

    class MergeSortTree{

        int treeSize;
        int[][] tree;

        MergeSortTree(int[] arr) {
            treeSize = 1 << (int) (Math.ceil(Math.log(arr.length)/Math.log(2)) + 1);
            tree = new int[treeSize][];
            init(1, 1, arr.length - 1, arr);
        }

        void init(int node, int start, int end, int[] arr) {
            if (start == end) {
                tree[node] = new int[] {arr[start]};
                return;
            }

            int mid = (start + end) / 2;
            init(node * 2, start, mid, arr);
            init(node * 2 + 1, mid + 1, end, arr);

            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        int[] merge(int[] arr1, int[] arr2) {
            int[] result = new int[arr1.length + arr2.length];
            int idx = 0;
            int arr1Idx = 0;
            int arr2Idx = 0;
            while (idx < result.length) {
                if (arr2Idx == arr2.length || (arr1Idx < arr1.length && arr1[arr1Idx] < arr2[arr2Idx])) {
                    result[idx++] = arr1[arr1Idx++];
                } else {
                    result[idx++] = arr2[arr2Idx++];
                }
            }
            return result;
        }

        int getCount(int node, int start, int end, int left, int right, int k) {
            if (right < start || end < left) return 0;
            if (left <= start && end <= right) {
                return tree[node].length - getUpperBound(tree[node], k);
            }
            int mid = (start + end) / 2;
            return getCount(node * 2, start, mid, left, right, k) +
                    getCount(node * 2 + 1, mid + 1, end, left, right, k);
        }

        int getUpperBound(int[] arr, int k) {
            int start = 0;
            int end = arr.length;
            while (start < end) {
                int mid = (start + end) / 2;

                if (arr[mid] <= k) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            return end;
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private MergeSortTree mergeSortTree;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int t = 0; t < M; t++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            printResult(calcResult(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));
        }
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        mergeSortTree = new MergeSortTree(arr);
        M = Integer.parseInt(bf.readLine());
    }

    private int calcResult(int i, int j, int k) {
        return mergeSortTree.getCount(1, 1, N, i, j, k);
    }

    public void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}