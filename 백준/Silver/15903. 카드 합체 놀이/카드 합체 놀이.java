import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int cardCount = Integer.parseInt(st.nextToken());
        int cardCombinationCount = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        
        st = new StringTokenizer(bf.readLine(), " ");

        for(int i=0; i<cardCount; i++){
            pq.offer(Long.parseLong(st.nextToken()));
        }

        for(int i=0; i<cardCombinationCount; i++) {
            long temp = pq.poll() + pq.poll();
            pq.offer(temp);
            pq.offer(temp);
        }

        long minSum = 0;

        for(int i=0; i<cardCount; i++){
            minSum += pq.poll();
        }

        bw.write(String.valueOf(minSum));

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}