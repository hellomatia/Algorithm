import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        int T = Integer.parseInt(bf.readLine());

        for ( int i = 0; i < T; i++) {
            int K = Integer.parseInt(bf.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();

            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < K; j++) {
                long num = Long.parseLong(st.nextToken());
                pq.offer(num);
            }

            long totalCost = 0;
            for (int j = 1; j < K; j++) {
                long num1 = pq.poll();
                long num2 = pq.poll();
                long sumNum = num1 + num2;
                totalCost += sumNum;
                pq.offer(sumNum);
            }
            bw.write(totalCost + "\n");
        }

        bw.flush();
        bw.close();

    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}