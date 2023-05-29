import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    int N;
    long ans;
    ArrayList<Long> list = new ArrayList<>();

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine());

        decreaseNum();

        bw.write(ans+"\n");



        bw.flush();
        bw.close();
    }

    public void decreaseNum() {
        if(N<=10) {
            ans=N;
        } else if (N>1022) {
            ans=-1;
        } else {
            for(int i=0; i<10; i++) {
                initNum(i, 1);
            }
            Collections.sort(list);
            ans = list.get(N);
        }

    }

    public void initNum(long num, int idx) {

        if(idx>10) return;

        list.add(num);
        for(int i=0; i<num%10; i++) {
            initNum((num*10)+i, idx+1);
        }
    }



    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
