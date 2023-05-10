import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public void solution() throws IOException{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] jewelry = new int[N][2];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine(), " ");
            jewelry[i][0] = Integer.parseInt(st.nextToken());
            jewelry[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] bagMaxWeight = new int[K];

        for(int i=0; i<K; i++){
            bagMaxWeight[i] = Integer.parseInt(bf.readLine());
        }


        Arrays.sort(jewelry, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]){
                    return o2[1]-o1[1];
                }
                return o1[0]-o2[0];
            }
        });

        Arrays.sort(bagMaxWeight);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        long maxPrice = 0;

        for (int i=0, j=0; i < K; i++) {

            while (j < N && (jewelry[j][0] <= bagMaxWeight[i])) {
                pq.offer(jewelry[j++][1]);
            }

            if (!pq.isEmpty()) {
                maxPrice += pq.poll();
            }
        }

        bw.write(String.valueOf(maxPrice));

        bw.flush();
        bw.close();

    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

