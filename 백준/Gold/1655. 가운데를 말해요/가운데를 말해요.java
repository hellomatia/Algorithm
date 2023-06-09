import java.io.*;
import java.util.*;

public class Main {
    int N; // 백준이가 외치는 숫자 개수
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });


        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(bf.readLine());

            if(minHeap.size() <= maxHeap.size()) {
                minHeap.offer(num);
            } else {
                maxHeap.offer(num);
            }

            if(!maxHeap.isEmpty() && minHeap.peek()>maxHeap.peek()) {
                int temp = minHeap.poll();
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(temp);
            }

            bw.write(minHeap.peek()+"\n");
        }


        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}