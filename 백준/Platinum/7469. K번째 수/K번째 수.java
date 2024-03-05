import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	class MergeSortTree {
		
		int treeSize;
		int[][] tree;
		
		MergeSortTree(int[] arr) {
			treeSize = 1 << (int) Math.ceil(Math.log(arr.length) / Math.log(2) + 1);
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
		
		int getKCount(int node, int start, int end, int left, int right, int k) {
			if (right < start || end < left) {
				return 0;
			}
			if (left <= start && end <= right) {
				return getIndex(tree[node], k);
			}
			int mid = (start + end) / 2;
			return getKCount(node * 2, start, mid, left, right, k) +
					getKCount(node * 2 + 1, mid + 1, end, left, right, k);
			
		}
		
		int getIndex(int[] arr, int num) {
			int start = 0;
			int end = arr.length;
			
			while (start < end) {
				int mid = (start + end) / 2;
				
				if (arr[mid] <= num) {
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
        for (int i = 0; i < M; i++) {
        	StringTokenizer st = new StringTokenizer(bf.readLine());
        	int ans = calcResult(
        			Integer.parseInt(st.nextToken()), 
        			Integer.parseInt(st.nextToken()), 
        			Integer.parseInt(st.nextToken()));
        	printResult(ans);
        }
    }
    
    private void init() throws IOException {
    	StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(bf.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        mergeSortTree = new MergeSortTree(arr);
    }
    
    private int calcResult(int i, int j, int k) {
    	int start = -1_000_000_000;
    	int end = 1_000_000_000;
    	
    	while (start <= end) {
    		int mid = (start + end) / 2;
    		
    		int count = mergeSortTree.getKCount(1, 1, N, i, j, mid);
    		if (k <= count) {
    			end = mid - 1;
    		} else {
    			start = mid  + 1;
    		}
    	}
    	return start;
    }
    
    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}