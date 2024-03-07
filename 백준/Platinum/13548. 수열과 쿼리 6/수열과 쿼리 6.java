import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Query implements Comparable<Query> {

	static double sqrtN;
	int a, b, idx, factor;

	Query(int a, int b, int idx) {
		this.a = a;
		this.b = b;
		this.idx = idx;
		this.factor = (int) (a / sqrtN);
	}

	@Override
	public int compareTo(Query o) {
		if (factor == o.factor) {
			return b - o.b;
		}
		return factor - o.factor;
	}
}

public class Main {

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int N, M, answer;
	private int[] numCount;
	private int[] countCount;
	private int[] arr;
	private int[] result;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		init();
		calcResult();
		printResult();
		bw.close();
	}

	private void init() throws IOException {
		N = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(bf.readLine());
		
		numCount = new int[100_000 + 50];
		countCount = new int[100_000 + 50];
	}

	private void calcResult() throws IOException {
		result = new int[M];
		
    	Query.sqrtN = Math.sqrt(N);
    	
    	Query[] queries = new Query[M];
    	for (int i = 0; i < M; i++ ) {
    		StringTokenizer st = new StringTokenizer(bf.readLine());
    		queries[i] = new Query(
    				Integer.parseInt(st.nextToken()),
    				Integer.parseInt(st.nextToken()),
    				i);
    	}
    	Arrays.sort(queries);

    	int prevA = queries[0].a;
    	int prevB = queries[0].a - 1;
    	    	
    	for (int i = 0; i < M; i++) {
    		int curA = queries[i].a;
    		int curB = queries[i].b;
    		
    		for (int j = curA; j < prevA; j++) push(arr[j]);
    		for (int j = prevB + 1; j <= curB; j++) push(arr[j]);
    		for (int j = prevA; j < curA; j++) pop(arr[j]);
    		for (int j = curB + 1; j <= prevB; j++) pop(arr[j]);
    		
    		prevA = curA;
    		prevB = curB;

    		result[queries[i].idx] = answer;
    	}
    }

	private void push(int num) {
		countCount[numCount[num]]--;
		if (++countCount[++numCount[num]] == 1 && answer < numCount[num]) answer = numCount[num];
	}

	private void pop(int num) {
		if (--countCount[numCount[num]] == 0 && answer == numCount[num]) answer--;
		countCount[--numCount[num]]++;
	} 

	private void printResult() throws IOException {
		for (int i = 0; i < M; i++) {
			bw.write(result[i] + "\n");
		}
		bw.flush();
	}
}