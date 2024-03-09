import java.io.*;
import java.util.*;

class Query implements Comparable<Query> {
    static double sqrtN;
    int a, b, factor, idx;

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

    private int n, t;
    private int[] arr;
    private Query[] queries;
    private long[] result;
    private long now;
    private int[] numCount;

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
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Query.sqrtN = Math.sqrt(n);
        queries = new Query[t];
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(bf.readLine());
            queries[i] = new Query(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    i
            );
        }

        numCount = new int[1_000_000 + 10];
    }

    private void calcResult() {
        result = new long[t];

        Arrays.sort(queries);

        int prevA = queries[0].a;
        int prevB = queries[0].a - 1;

        for (int i = 0; i < t; i++) {
            int curA = queries[i].a;
            int curB = queries[i].b;

            for (int j = prevB + 1; j <= curB; j++) push(arr[j]);
            for (int j = curA; j < prevA; j++) push(arr[j]);
            for (int j = prevA; j < curA; j++) pop(arr[j]);
            for (int j = curB + 1; j <= prevB; j++) pop(arr[j]);

            result[queries[i].idx] = now;

            prevA = curA;
            prevB = curB;
        }
    }

    private void push(int num) {
        now -= ((long)numCount[num] * numCount[num] * num);
        numCount[num]++;
        now += ((long)numCount[num] * numCount[num] * num);
    }

    private void pop(int num) {
        now -= ((long)numCount[num] * numCount[num] * num);
        numCount[num]--;
        now += ((long)numCount[num] * numCount[num] * num);
    }

    public void printResult() throws IOException {
        for (int i = 0; i < t; i++) {
            bw.write(result[i] + "\n");
        }
        bw.flush();
    }
}