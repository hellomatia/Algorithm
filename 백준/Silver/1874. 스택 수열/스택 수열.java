import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;
    private static int[] nums;
    
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
        n = Integer.parseInt(bf.readLine());
        nums = new int[n];
        for (int i = 0; i < n; i++) {
        	nums[i] = Integer.parseInt(bf.readLine());
        }
    }
    
    private String calcResult() {
    	StringBuilder sb = new StringBuilder();
    	
    	Stack<Integer> stack = new Stack<>();
    	
    	int index = 0;
    	for (int i = 1; i <= n; i++) {
    		stack.push(i);
    		sb.append("+\n");
    		while (!stack.isEmpty() && stack.peek() == nums[index]) {
    			index++;
    			stack.pop();
    			sb.append("-\n");
    		}
    	}
    	
    	if (index != n) {
    		return "NO";
    	}
    	
    	return sb.toString();
    }

    private void printResult(String result) throws IOException {
        bw.write(result + "\n");
    }
}