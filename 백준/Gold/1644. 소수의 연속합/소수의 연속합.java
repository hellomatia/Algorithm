import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int size;
    private int[] primes;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        initPrime();
    }

    private void initPrime() {
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        primes = new int[N + 1];
        int index = 0;
        for (int i = 1; i <= N; i++) {
            if (isPrime[i]) {
                primes[index++] = i;
                size++;
            }
        }
        primes[N] = 0;
        size++;
    }

    private String calcAns() {
        int start = 0;
        int end = 0;
        int ans = 0;

        int sum = 0;
        for (;start <= end && end < size;) {
            if (sum < N) {
                sum += primes[end++];
            } else {
                if (sum == N) {
                    ans++;
                }
                sum -= primes[start++];
            }
        }

        return ans + "";
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}
