import java.io.*;
import java.util.*;

public class Main {

    int[][] schedule;
    int day;
    int maxPay = Integer.MIN_VALUE;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        day = Integer.parseInt(bf.readLine());
        schedule = new int[day][2];

        for(int i=0; i<day; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0,0);

        bw.write(String.valueOf(maxPay));


        bw.flush();
        bw.close();
    }

    public void dfs(int index, int startDay, int pay) {

        if(startDay>day) {
            pay -= schedule[index][1];
            maxPay = Math.max(pay, maxPay);
            return;
        } else if(startDay==day) {
            maxPay = Math.max(pay, maxPay);
            return;
        }

        for(int i=startDay; i<day; i++) {
                dfs(i, i+schedule[i][0], pay+schedule[i][1]);
        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}