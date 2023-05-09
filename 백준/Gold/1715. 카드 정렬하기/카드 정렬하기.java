import java.io.*;
import java.util.PriorityQueue;


public class Main {
    public void solution() throws IOException{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numCardComparisonsCount = Integer.parseInt(bf.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0; i<numCardComparisonsCount; i++){
            pq.add(Integer.parseInt(bf.readLine()));
        }

        int sum = 0;

        while(pq.size()>1){
            int temp = pq.poll() + pq.poll();
            sum += temp;
            pq.add(temp);
        }

        bw.write(String.valueOf(sum));


        bw.flush();
        bw.close();

    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

