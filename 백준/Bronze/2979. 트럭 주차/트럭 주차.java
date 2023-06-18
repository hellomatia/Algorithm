import java.io.*;
import java.util.*;

public class Main {
    int A, B, C; // 1분에 한 대당 A원을 내야 한다. 두 대를 주차할 때는 1분에 한 대당 B원, 세 대를 주차할 때는 1분에 한 대당 C원
    int[][] car;
    int cost;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        car = new int[3][2];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        car[0][0] = Integer.parseInt(st.nextToken());
        car[0][1] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        car[1][0] = Integer.parseInt(st.nextToken());
        car[1][1] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        car[2][0] = Integer.parseInt(st.nextToken());
        car[2][1] = Integer.parseInt(st.nextToken());

        simulation(0);

        bw.write(cost+"\n");

        bw.flush();
        bw.close();
    }

    public void simulation(int time) {
        if(time==101) {
            return;
        }

        int count=0;

        for(int i=0; i<3; i++) {
            if(car[i][0]<=time&&time<car[i][1]) {
                count++;
            }
        }

        if(count == 1) {
            cost += count*A;
        } else if(count == 2) {
            cost += count*B;
        } else {
            cost += count*C;
        }

        simulation(time+1);
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}