import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bf.readLine());
        int[] num = new int[N];
        int numCount = 0;

        StringTokenizer st = new StringTokenizer(bf.readLine()," ");

        for (int i=0; i<N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int chekNum = Integer.parseInt(bf.readLine());

        for (int i=0; i<N; i++) {

            if (chekNum==num[i]) {
                numCount++;
            }

        }

        bw.write(String.valueOf(numCount));
        bw.flush();
        bw.close();

    }
    }
