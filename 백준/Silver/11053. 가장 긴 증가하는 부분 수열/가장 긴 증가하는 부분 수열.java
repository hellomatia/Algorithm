import java.io.*;
import java.util.*;

public class Main {
    int N; // 수열 A의 크기
    int[] A;
    int[] dp;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine());
        A = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        /*
        * 보통 이런 문제를 최장 증가 부분 수열(LIS)라고 한다. 말 그대로 주어진 수열에서 오름차순으로 구성 가능한 원소들을 선택하여 최대 길이를 찾아내는 것이다.
        * dp[] 배열에 메모이제이션을 한다. 먼저 A[0] = 10에 대한 수열을 찾아보면 A[0]보다 이전 값은 없으므로 10 자체 하나밖에 조재하지 않으므로 dp[0]=1 이 된다.
        * 그 다음 A[1]=20에 대한 수열을 찾아보면 A[0]=10으로 20보다 작기 때문에{10, 20}이라는 부분 수열이 되고, 길이는 2로 dp[1]=2가 되는 것이다.
        * A[2]=10의 경우 이전 값들 중 작은게 없으므로{10} 하나만 되므로 dp[2]=1이 되고.. 이런식으로 나간다.
        * Top - Down(재귀)방식이 있고, Bottom-Up(반복문)방식이 있다.
         */

        int max = 0;

        //Top-Down
        /*
        for(int i=0; i<N; i++) {
            LISTopDown(i);
        }
        */

        //Bottom-Up
        LISBottomUp();

        for(int i=0; i<N; i++) {
            max = Math.max(max, dp[i]);
        }

        bw.write(max+"\n");

        bw.flush();
        bw.close();
    }

    public int LISTopDown(int num) {
        if(dp[num] == 0) {
            // 만약 탐색하지 않았다는 의미이므로 1로 초기화, 모든 부분수열의 길이는 '최소한 1 이상' 이기때문
            dp[num] = 1;

            for(int i=num-1; i>=0; i--) {
                if(A[i]<A[num]) {
                    dp[num] = Math.max(dp[num], LISTopDown(i)+1);
                }
            }
        }

        return dp[num];
    }

    public void LISBottomUp() {
        for(int i=0; i<N; i++) {
            dp[i] = 1;

            for(int j=0; j<i; j++) {
                if(A[j] < A[i] && dp[i]<dp[j]+1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}