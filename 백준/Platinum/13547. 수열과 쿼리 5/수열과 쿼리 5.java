import java.io.*;
import java.util.*;

class Query implements Comparable<Query> {
    static double sqrtN;
    int a, b, factor, idx;

    Query(int a, int b, int idx) {
        this.a = a;
        this.b = b;
        this.idx = idx;
        this.factor = (int) (a/sqrtN);
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


    private int N, M;
    private int[] numCount;
    private int[] arr;
    private int count;
    private StringBuilder sb;

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

        arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(bf.readLine());
        numCount = new int[1000_000 + 1];
        sb = new StringBuilder();
    }

    private void calcResult() throws IOException {
        int[] result = new int[M];

        Query.sqrtN = Math.sqrt(N);
        Query[] queries = new Query[M];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            queries[i] = new Query(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    i
            );
        }

        Arrays.sort(queries);

        int prevA = queries[0].a;
        int prevB = queries[0].a - 1;

        for (int i = 0; i < M; i++) {
            int curA = queries[i].a;
            int curB = queries[i].b;

            for (int j = prevA; j < curA; j++) {
                pop(arr[j]);
            }
            for (int j = curA; j < prevA; j++) {
                push(arr[j]);
            }

            for (int j = prevB + 1; j <= curB; j++) {
                push(arr[j]);
            }
            for (int j = curB + 1; j <= prevB; j++) {
                pop(arr[j]);
            }

            result[queries[i].idx] = count;

            prevA = curA;
            prevB = curB;
        }

        for (int ans : result) {
            sb.append(ans).append("\n");
        }
    }

    private void push(int num) {
        if (++numCount[num] == 1) count++;
    }

    private void pop(int num) {
        if (--numCount[num] == 0) count--;
    }

    public void printResult() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }
}