import java.io.*;
import java.util.*;

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int E = Integer.parseInt(st.nextToken()); // 지구 (<=15)
        int S = Integer.parseInt(st.nextToken()); // 태양 (<=28)
        int M = Integer.parseInt(st.nextToken()); // 달  (<=19)

        int e=1, s=1, m=1;
        int year=1;


        while(true) {
            e %= 16;
            s %= 29;
            m %= 20;

            if(e == 0){
                e++;
            }
            if(s == 0){
                s++;
            }
            if(m == 0){
                m++;
            }
            
            if(e == E && s == S && m == M) {
                bw.write(year + "\n");
                break;
            }

            e++;
            s++;
            m++;
            year++;
        }



        bw.flush();
        bw.close();
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}