import java.io.*;
import java.util.*;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numOfCandidate = Integer.parseInt(bf.readLine());
        int dasomScore = 0;

        PriorityQueue<Integer> candidate = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<numOfCandidate; i++){
            if(i==0) {
                dasomScore = Integer.parseInt(bf.readLine());
                continue;
            }
            candidate.offer(Integer.parseInt(bf.readLine()));
        }

        if(candidate.isEmpty()) {
            bw.write("0");
            bw.flush();
            bw.close();
            return;
        }

        int bribeCount = 0;

        while(dasomScore<=candidate.peek()) {
            candidate.offer(candidate.poll()-1);
            dasomScore++;
            bribeCount++;
        }

        bw.write(String.valueOf(bribeCount));

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}