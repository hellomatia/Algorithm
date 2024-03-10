import java.io.*;
import java.util.*;

class Query implements Comparable<Query> {
    static double sqrtN;
    int a, b, factor, idx;

    Query(int a, int b, int idx) {
        this.a = a;
        this.b = b;
        this.factor = (int) (a / sqrtN);
        this.idx = idx;
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

    private int N, Q;
    private int[] arr;
    private List<Query> queries;
    private int[] numCount;
    private int now;
    private int[] result;
    private Map<Integer, Integer> comNum;
    private int id;
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
        FastInput.initFI();
        N = FastInput.nextInt();
        comNum = new HashMap<>();

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = getComNum(FastInput.nextInt());
        }

        Query.sqrtN = Math.sqrt(N);

        Q = FastInput.nextInt();
        queries = new ArrayList<>(Q);


        for (int i = 0; i < Q; i++) {
            queries.add(new Query(
                    FastInput.nextInt(),
                    FastInput.nextInt(),
                    i));
        }

        sb = new StringBuilder();

        numCount = new int[1_000_000 + 10];
    }

    private void calcResult() {
        Collections.sort(queries);

        int prevA = queries.get(0).a;
        int prevB = queries.get(0).a - 1;

        result = new int[Q];
        for (int i = 0; i < Q; i++) {
            int curA = queries.get(i).a;
            int curB = queries.get(i).b;

            for (int j = curA; j < prevA; j++) push(arr[j]);
            for (int j = prevB + 1; j <= curB; j++) push(arr[j]);
            for (int j = prevA; j < curA; j++) pop(arr[j]);
            for (int j = curB + 1; j <= prevB; j++)  pop(arr[j]);

            result[queries.get(i).idx] = now;

            prevA = curA;
            prevB = curB;
        }
        for (int i = 0; i < Q; i++) {
            sb.append(result[i]).append("\n");
        }
    }

    private void push(int num) {
//        System.out.println("push num = " + num);
        numCount[num]++;
        if (numCount[num] == 1) {
            now++;
        }
    }

    private void pop(int num) {
//        System.out.println("pop num = " + num);
        numCount[num]--;
        if (numCount[num] == 0) {
            now--;
        }
    }

    private int getComNum(int num) {
        if (comNum.containsKey(num)) {
            return comNum.get(num);
        } else {
            comNum.put(num, ++id);
            return id;
        }
    }

    public void printResult() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }
}

class FastInput {
    private static final int DEFAULT_BUFFER_SIZE = 1 << 16;
    private static DataInputStream inputStream;
    private static byte[] buffer;
    private static int curIdx, maxIdx;

    protected static void initFI() {
        inputStream = new DataInputStream(System.in);
        buffer = new byte[DEFAULT_BUFFER_SIZE];
        curIdx = maxIdx = 0;
    }

    protected static int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        return ret;
    }

    private static byte read() throws IOException {
        if (curIdx == maxIdx) {
            maxIdx = inputStream.read(buffer, curIdx = 0, DEFAULT_BUFFER_SIZE);
            if (maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
}