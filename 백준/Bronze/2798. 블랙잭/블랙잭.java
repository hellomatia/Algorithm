import java.io.*;
import java.util.*;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int cardCount = Integer.parseInt(st.nextToken());
        int num = Integer.parseInt(st.nextToken());
        int[] card = new int[cardCount];

        st = new StringTokenizer(bf.readLine());

        for(int i=0; i<cardCount; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<cardCount; i++) {
            for(int j=i+1; j<cardCount; j++) {
                for(int k=j+1; k<cardCount; k++) {
                    int sum = card[i]+card[j]+card[k];
                    if(sum<=num) {
                        pq.offer(sum);
                    }
                }
            }
        }

        bw.write(String.valueOf(pq.peek()));


        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}