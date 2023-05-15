import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        int maxCount = 0;

        if(row == 1) {
            maxCount = 1;
        } else if(row == 2) {
            maxCount = Math.min((col+1)/2,4);
        } else if(row>=3) {
            if(col < 7) {
                maxCount = Math.min(col,4);
            }else {
                maxCount = col-2;
            }
        }
        
        bw.write(String.valueOf(maxCount));
        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}