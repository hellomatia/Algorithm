import java.io.*;
import java.util.*;

public class Main {

    static int N, K; // N : 1~N명의 사람, K : K번째 사람 제거
    static int[] ans;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ans = new int[N];

        // 두개의 큐를 선언하고, 서로의 큐에 숫자를 넘겨주면서 K번째에 해당하는 값을 ans배열에 넘겨준다.
        Queue<Integer> heap1 = new LinkedList<>();
        Queue<Integer> heap2 = new LinkedList<>();

        // 처음 사용할 큐에 1~N까지의 값을 넘겨준다.
        for(int i=1; i<=N; i++){
            heap1.add(i);
        }

        // ans 배열에 사용할 index값을 선언
        int idx = 0;

        // K번째인지 확인할 변수 선언
        int count = 1;

        //현재 사용해야할 Queue인지 확인할 boolean형 변수 선언
        boolean isHeap1 = true;

        // ans배열에 모든 값을 다 채워넣었으면 종료한다.
        while(idx != N){

            // 큐에 값이 비어있으면 주 큐를 바꾼다.
            if(heap1.isEmpty()) {
                isHeap1 = false;
            } else if(heap2.isEmpty()) {
                isHeap1 = true;
            }

            // K번째 사람일 경우 ans에 넘겨준다
            if(count==K) {
                if(isHeap1) {
                    ans[idx++] = heap1.poll();
                    //System.out.println(1);
                } else {
                    ans[idx++] = heap2.poll();
                    //System.out.println(2);
                }
                count = 1;
                continue;
            }

            // 서로 값을 넘겨준다.
            if(isHeap1) {
                heap2.add(heap1.poll());
            } else {
                heap1.add(heap2.poll());
            }

            count++;
        }

        bw.write("<");
        for(int i=0; i<N-1; i++) {
            bw.write(ans[i]+", ");
        }
        bw.write(ans[N-1]+">");


        bw.flush();
        bw.close();
    }



    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}