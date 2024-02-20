import java.io.*;
import java.util.*;

public class Main {

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[] arr;
    private long[] level;
    
    public static void main (String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
    	init();
    	printResult(calcResult());
    	bw.flush();
    	bw.close();
    }
    
    private void init() throws IOException {
    	N = Integer.parseInt(bf.readLine());
    	arr = new int[N];
    	for (int i = 0; i < N; i++) {
    		arr[i] = Integer.parseInt(bf.readLine());
    	}
    	level = new long[N];
    }
    
    private long calcResult() {
    	TreeSet<Integer> treeSet = new TreeSet<>();
    	long result = 0;
    	for (int num : arr) {
    		if (treeSet.higher(num) == null) {
    			if (treeSet.lower(num) == null) {
    				level[num] = 1;
    			} else {
    				level[num] = level[treeSet.lower(num)] + 1;
    			}
    		} else {
    			if (treeSet.lower(num) == null) {
    				level[num] = level[treeSet.higher(num)] + 1;
    			} else {
    				level[num] = Math.max(level[treeSet.higher(num)], level[treeSet.lower(num)]) + 1;
    			}
    		}
    		result += level[num];
    		treeSet.add(num);
    	}
    	return result;
    }

    private void printResult(long result) throws IOException {
    	bw.write(result + "\n");
    }
}