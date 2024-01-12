
import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, M;
    private static int[] treeHight;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
    	init();
    	printResult(calcResult());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
    	StringTokenizer st = new StringTokenizer(bf.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	treeHight = new int[N + 1];
    	st = new StringTokenizer(bf.readLine());
    	for (int i = 1; i <= N; i++) {
    		treeHight[i] = Integer.parseInt(st.nextToken());
    	}
    	Arrays.sort(treeHight);
    }
    
    private int calcResult() {
    	int maxTreeHight = treeHight[N];
    	int sum = 0;
    	int treeCount = 0;
    	int result = 0;
    	
    	for (int i = maxTreeHight; i >= 0; i--) {
    		while (treeHight[N - treeCount] == i) {
    			treeCount++;
    		}
    		sum += treeCount;
    		if (sum >= M) {
    			result = i - 1;
    			break;
    		}
    	}
    	
    	return result;
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}