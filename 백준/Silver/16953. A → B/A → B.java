import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public void solution() throws IOException{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int minOperationsCount=1;

        while(B>A){
            if(B%10==1){
                B /= 10;
                minOperationsCount++;
            }
            else if (B%2==0) {
                B /= 2;
                minOperationsCount++;
            }
            else if(B%2==1){
                break;
            }
        }

        if(B!=A){
            minOperationsCount = -1;
        }

        bw.write(String.valueOf(minOperationsCount));


        bw.flush();
        bw.close();

    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

