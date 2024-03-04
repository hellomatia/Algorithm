import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    class MergeSortTree {
        
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
            
            int idx = 0, idx1 = 0, idx2 = 0;
            
            while (idx1 < arr1.length || idx2 < arr2.length) {
                if (idx1 == arr1.length) {
                    result[idx++] = arr2[idx2++];
                } else if (idx2 == arr2.length) {
                    result[idx++] = arr1[idx1++];
                } else if (arr1[idx1] < arr2[idx2]) {
                    result[idx++] = arr1[idx1++];
                } else {
                    result[idx++] = arr2[idx2++];
                }
            }

            return result;
        }
        
        int getCount(int node, int start, int end, int left, int rigth, int k) {
            if (rigth < start || end < left) {
                return 0;
            }
            if (left <= start && end <= rigth) {
                return tree[node].length - upperBound(tree[node], k);
            }
            
            int mid = (start + end) / 2;
            return getCount(node * 2, start, mid, left, rigth, k) + 
                    getCount(node * 2 + 1, mid + 1, end, left, rigth, k);
        }
        
        int upperBound(int[] arr, int k) {
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
    private int[] arr;
    
    private MergeSortTree mergeSortTree;
    
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
    
    private void solution() throws IOException {
        init();
        int lastAns = 0;
        for (int i = 0; i < M; i++) {
        	StringTokenizer st = new StringTokenizer(bf.readLine());
        	int ans = calcResult(
        			Integer.parseInt(st.nextToken()), 
        			Integer.parseInt(st.nextToken()), 
        			Integer.parseInt(st.nextToken()), 
        			lastAns);
        	printResult(ans);
        	lastAns = ans;
        }
    }
    
    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        mergeSortTree = new MergeSortTree(arr);
        M = Integer.parseInt(bf.readLine());
    }
    
    private int calcResult(int i, int j, int k, int lastAns) {
    	return mergeSortTree.getCount(1, 1, N, i ^ lastAns, j ^ lastAns, k ^ lastAns);
    }
    
    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}