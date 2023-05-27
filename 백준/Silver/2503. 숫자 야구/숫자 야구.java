import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class NumBaseBall {
    int num;
    int strike;
    int ball;
    NumBaseBall(int num, int strike, int ball) {
        this.num = num;
        this.strike = strike;
        this.ball = ball;
    }
}

public class Main {
    int N; //질문 횟수
    ArrayList<NumBaseBall> numBaseBalls = new ArrayList<>();
    int count; //가능한 횟수
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine());

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int num = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            numBaseBalls.add(new NumBaseBall(num, strike, ball));
        }

        randomNum();

        bw.write(count+"\n");

        bw.flush();
        bw.close();
    }

    public void randomNum() {
        for(int i=1; i<10; i++) {
            for(int j=1; j<10; j++) {
                for(int k=1; k<10; k++) {
                    if(i==j || j==k || k==i) continue;
                    int num = i*100+j*10+k;
                    if(canBeNum(num)) {
                        count++;
                    }
                }
            }
        }
    }

    public boolean canBeNum(int num) {
        char[] numArr = String.valueOf(num).toCharArray();

        for(int i=0; i<N; i++) {
            char[] testNumArr = String.valueOf(numBaseBalls.get(i).num).toCharArray();

            // 스트라이크 확인
            int strike=0;
            for(int j=0; j<3; j++) {
                if(numArr[j]==testNumArr[j]) strike++;
            }

            if(strike!=numBaseBalls.get(i).strike) return false;

            //볼 확인
            int ball=0;
            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    if(j!=k && numArr[j]==testNumArr[k]) ball++;
                }
            }
            if(ball!=numBaseBalls.get(i).ball) return false;
        }

        return true;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}