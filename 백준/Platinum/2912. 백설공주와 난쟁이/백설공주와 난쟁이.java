import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Query implements Comparable<Query> {
	static double sqrtN;
	int a, b, idx, factor;
	
	Query(int a, int b, int idx) {
		this.a = a;
		this.b = b;
		this.idx = idx;
		this.factor = (int) Math.sqrt(a / sqrtN);
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

	private int N, C, M;
	private int[] arr;
	private Query[] queries;
	private int[] capCount;
	private int[][] result;
	
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
		 StringTokenizer st = new StringTokenizer(bf.readLine());
		 N = Integer.parseInt(st.nextToken());
		 C = Integer.parseInt(st.nextToken());
		 
		 st = new StringTokenizer(bf.readLine());
		 arr = new int[N + 1];
		 for (int i = 1; i <= N; i++) {
			 arr[i] = Integer.parseInt(st.nextToken());
		 } 
		 
		 M = Integer.parseInt(bf.readLine());
		 Query.sqrtN = Math.sqrt(N);
		 queries = new Query[M];
		 for (int i = 0; i < M; i++) {
			 st = new StringTokenizer(bf.readLine());
			 queries[i] = new Query(
					 Integer.parseInt(st.nextToken()),
					 Integer.parseInt(st.nextToken()),
					 i);
		 }
		 
		 capCount = new int[C + 1];
	}

	private void calcResult() throws IOException {
		result = new int[M][2];
		
		Arrays.sort(queries);
		
		int prevA = queries[0].a;
		int prevB = queries[0].a - 1;
		
		for (int i = 0; i < M; i++) {
			int curA = queries[i].a;
			int curB = queries[i].b;
			
			for (int j = prevB + 1; j <= curB; j++) push(arr[j]);
			for (int j = curA; j < prevA; j++) push(arr[j]);
			for (int j = prevA; j < curA; j++) pop(arr[j]);
			for (int j = curB + 1; j <= prevB; j++) pop(arr[j]);
			
			int count = curB - curA + 1;
			int mid = count / 2;
			for (int j = 1; j <= C; j++) {
//				System.out.print(capCount[j] + " ");
				if (capCount[j] > mid) {
					result[queries[i].idx][0] = 1;
					result[queries[i].idx][1] = j;
				}
			}
//			System.out.println();
			
			prevA = curA;
			prevB = curB;
		}
    }
	
	private void push(int num) {
		capCount[num]++;
	}

	private void pop(int num) {
		capCount[num]--;
	}

	private void printResult() throws IOException {
		for (int i = 0; i < M; i++) {
			if (result[i][0] == 0) {
				bw.write("no\n");
			} else {
				bw.write("yes " + result[i][1] + "\n");
			}
		}
		bw.flush();
	}
}
